package com.coding.challenge.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coding.challenge.api.ApplicationConstants;
import com.coding.challenge.api.ContactValidationException;
import com.coding.challenge.domain.Contact;
import com.coding.challenge.dto.ManageContactsRequest;
import com.coding.challenge.dto.ManageContactsResponse;
import com.coding.challenge.dto.RetrieveContactsRequest;
import com.coding.challenge.dto.RetrieveContactsResponse;
import com.coding.challenge.enums.ContactTypes;
import com.coding.challenge.repository.ContactRepository;

@Service("manageContactsService")
public class ManageContactsServiceImpl implements ManageContactsService
{
	
	
	
    private static Logger 	LOGGER = Logger.getLogger(ManageContactsServiceImpl.class);
	
	@Autowired
	private ContactRepository contactRepository;
	
	
	@Override
	public ManageContactsResponse createContacts(ManageContactsRequest request) throws ContactValidationException 
	{
		
		try
		{
			ManageContactsResponse 	response 			= new ManageContactsResponse();
			Contact 				contact 			= null;
			
			List<Contact> 			findByContactName 	= contactRepository.findByContactName(request.getContactName(), request.getAddressBook());
			contact = initializeContact(findByContactName);
			
			ContactTypes 			contactType 		= getEnumByName(request.getContactType());
			Map<String,String>  	numbersMap  		= contact.getNumbers();
			numbersMap.put(contactType.getDisplayName(), request.getContactNumber());
			contact.setAddressBook(request.getAddressBook());
			contact.setContactName(request.getContactName());
			contact.setNumbers(numbersMap);
			Long contactId = contactRepository.save(contact);
			
			populateResponse(response, contactId);
			
			return response;
		}
		catch(Exception e)
		{
			LOGGER.error("Error in createContacts :: "+e.getMessage());
			throw new ContactValidationException();
		}
	}


	private void populateResponse(ManageContactsResponse response, Long contactId) {
		response.setErrorMessage(null);
		response.setResponseStatus(ApplicationConstants.SUCCESS);
		response.setContactId(contactId);
	}
	
	
	
	

	private Contact initializeContact(List<Contact> findByContactName) 
	{
		Contact contact;
		if(findByContactName != null && findByContactName.size()>0)
		{
			contact = findByContactName.get(0);
		}
		else
		{
			contact  = new Contact();
		}
		return contact;
	}

	@Override
	public ManageContactsResponse removeContacts(ManageContactsRequest request) throws ContactValidationException
	{
		// TODO Auto-generated method stub
		try
		{
			ManageContactsResponse 	response 		= new ManageContactsResponse();
			Long 					removeContactId = contactRepository.removeContact(request.getContactName(), request.getAddressBook());
			populateResponse(response, removeContactId);
			return response;
		}
		catch(Exception e)
		{
			LOGGER.error("Error in removeContacts :: "+e.getMessage());
			throw new ContactValidationException();
		}
	}

	/*
	 * Scenario 3 : Users should be able to print all contacts in an address book
	 * Scenario 5 : Users should be able to print a unique set of all contacts across multiple address books 
	 * */
	@Override
	public RetrieveContactsResponse retrieveAllContacts(RetrieveContactsRequest request) throws ContactValidationException
	{
		try
		{
			RetrieveContactsResponse 	response 		= new RetrieveContactsResponse();
			List<Long> 					addressBookList = request.getAddressBookList();
			Map<String,Contact>			uniqueContacts  = new HashMap<String,Contact>();
			
			if(request.getUnique())
			{
				generateUniqueContacts(response, addressBookList, uniqueContacts);
			}
			else
			{
				generateContactsForGivenAddressBook(response, addressBookList);
			}
			
			response.setErrorMessage(null);
			response.setResponseStatus(ApplicationConstants.SUCCESS);
			return response;
		}
		catch (Exception e)
		{
			LOGGER.error("Error in retrieveAllContacts"+e.getMessage());
			throw new ContactValidationException();
		}
	}





	private void generateContactsForGivenAddressBook(RetrieveContactsResponse response, List<Long> addressBookList) 
	{
		if(addressBookList != null && addressBookList.size()>0)
		{
			Long addressBook = addressBookList.get(0);
			List<Contact> findAllByAddressBook = contactRepository.findAllByAddressBook(addressBook);
			response.setContacts(findAllByAddressBook);
		}
	}





	private void generateUniqueContacts(RetrieveContactsResponse response, List<Long> addressBookList,
			Map<String, Contact> uniqueContacts)
	{
		for(Long addressBook : addressBookList)
		{
			getUniqueContacts(addressBook,uniqueContacts);
		}
		if(uniqueContacts != null && uniqueContacts.size() >0)
		{
			response.setContacts(uniqueContacts.values().stream().collect(Collectors.toList()));
		}
	}
	
	/*
	 * Scenario 5: Users should be able to print a unique set of contacts across multiple address book
	 * Assumption : In real world contact Name will not be unique in the same time contact number may not be unique 
	 * To demonstrate this scenario I have considered name to get unique contacts 
	 * */
	private void  getUniqueContacts(Long addressBook, Map<String,Contact>	uniqueContacts)
	{
		contactRepository.findAllByAddressBook(addressBook).forEach(contact ->uniqueContacts.put(contact.getContactName(), contact));
	}
	
	private ContactTypes getEnumByName(String displayName)
	{
		ContactTypes[] values = ContactTypes.values();
		for(ContactTypes contactType : values)
		{
			if(contactType.getDisplayName().equalsIgnoreCase(displayName))
			{
				return contactType;
			}
		}
		return ContactTypes.OTHR;
	}

}
