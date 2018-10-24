package com.coding.challenge.service;

import static com.coding.challenge.TestDataObjectMother.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.coding.challenge.api.ContactValidationException;
import com.coding.challenge.domain.Contact;
import com.coding.challenge.dto.ManageContactsRequest;
import com.coding.challenge.dto.RetrieveContactsRequest;
import com.coding.challenge.dto.RetrieveContactsResponse;
import com.coding.challenge.repository.ContactRepository;
import com.coding.challenge.repository.ContactRepositoryImpl;

public class ManageContactsServiceTest 
{
	
	  private static Logger 	LOGGER = Logger.getLogger(ManageContactsServiceTest.class);
	 
	  @InjectMocks
	  private ManageContactsService manageContactsService;
	  
	  @Mock
	  ContactRepository   contactRepository;
	  
	  @Before
	  public void setUp()
	  {
		  manageContactsService = new ManageContactsServiceImpl();
		  contactRepository     = new ContactRepositoryImpl();
	      MockitoAnnotations.initMocks(this);
	  }
	  
	  @Test
	  public void shouldSuccessfullyCreateContactForGivenValidContactNameNumberAndAddressBookId()
	  {
	        //given
	        Contact contactRashendra = getContactRashendra();
	        Mockito.when(contactRepository.findByContactName(CONTACT_NAME,ADDRESS_BOOK)).thenReturn(null);
	        Mockito.when(contactRepository.save(contactRashendra)).thenReturn(1L);
	        contactRepository.save(contactRashendra);

	        //when
	        Long id;
			try {
				id = manageContactsService.createContacts(getManageContactsRequest()).getContactId();
				Assertions.assertThat(id).isEqualTo(1L);
			} 
			catch (ContactValidationException e) 
			{
				LOGGER.error(" Error in shouldSuccessfullyCreateContactForGivenValidContactNameNumberAndAddressBookId ::  "+e.getMessage());
			}
	        //then
	   }
	  
	  
	  
	  @Test 
	  public void shouldSuccessfullyRemoveContactForGiveContactNameAndAddressBookId()
	  {
		  final Contact contactRashendra  = getContactRashendra();
		  contactRepository.save(contactRashendra);
		  ManageContactsRequest request   = getManageContactsRequest();
		  try 
		  {
			manageContactsService.removeContacts(request);
		  } 
		  catch (ContactValidationException e) 
		  {
			  LOGGER.error("Erro in shouldSuccessfullyRemoveContactForGiveContactNameAndAddressBookId :: "+e.getMessage());
		  }
		  List<Contact> findByContactName = contactRepository.findByContactName(CONTACT_NAME,ADDRESS_BOOK);
		  Assertions.assertThat(findByContactName.size() == 0);
		  
	  }
	  
	  @Test
	  public void shouldRetrieveAllContactsForGivenAddressBook()
	  {
		  Contact contactRashendraOne  = getContactRashendra();
		  Contact contactRashendraTwo  = getContactRashendra();
		  contactRashendraTwo.setContactName(CONTACT_NAME_TWO);



		  List<Contact>  contactList = new ArrayList<>();
		  contactList.add(contactRashendraOne);
		  contactList.add(contactRashendraTwo);
		  Mockito.when(contactRepository.findAllByAddressBook(1L)).thenReturn(contactList);
		  
		  RetrieveContactsRequest  retrieveContactsRequest = getRetrieveContactsRequest();
		  RetrieveContactsResponse retrieveAllContacts;
		  try 
		  {
				retrieveAllContacts = manageContactsService.retrieveAllContacts(retrieveContactsRequest);
				Assertions.assertThat(retrieveAllContacts.getContacts()).isNotEmpty();
				Assertions.assertThat(retrieveAllContacts.getContacts()).contains(contactRashendraOne);
				Assertions.assertThat(retrieveAllContacts.getContacts()).contains(contactRashendraTwo);
		  } 
		  catch (ContactValidationException e) 
		  {
			  LOGGER.error("Error in shouldRetrieveAllContactsForGivenAddressBook :: "+e.getMessage());
		  }
		  
		  
		  
	  }
	  
	  @Test
	  public void shouldRetrieveAllUniqueContactsForGivenAddressBook()
	  {
		  Contact contactRashendraOne  = getContactRashendra();
		  Contact contactRashendraTwo  = getContactRashendra();
		  contactRashendraTwo.setContactName(CONTACT_NAME_TWO);
		  
		  Contact contactRashendraOneABTwo  = getContactRashendra();
		  Contact contactRashendraTwoABTwo  = getContactRashendra();
		  contactRashendraTwoABTwo.setContactName(CONTACT_NAME_TWO);
		  contactRashendraTwoABTwo.setAddressBook(ADDRESS_BOOK_TWO);
		  contactRashendraOneABTwo.setAddressBook(ADDRESS_BOOK_TWO);


		  List<Contact>  contactListAddressBookOne  = new ArrayList<>();
		  contactListAddressBookOne.add(contactRashendraOne);
		  contactListAddressBookOne.add(contactRashendraTwo);
		  Mockito.when(contactRepository.findAllByAddressBook(ADDRESS_BOOK)).thenReturn(contactListAddressBookOne);
		  
		  List<Contact>  contactListAddressBookOTwo = new ArrayList<>();
		  contactListAddressBookOTwo.add(contactRashendraOneABTwo);
		  contactListAddressBookOTwo.add(contactRashendraTwoABTwo);
		  Mockito.when(contactRepository.findAllByAddressBook(ADDRESS_BOOK_TWO)).thenReturn(contactListAddressBookOTwo);
		  
		  RetrieveContactsRequest  retrieveContactsRequest = getRetrieveContactsRequestUniqueForUser();
		  
		  RetrieveContactsResponse retrieveAllContacts;
		  try 
		  {
			  retrieveAllContacts = manageContactsService.retrieveAllContacts(retrieveContactsRequest);
			  Assertions.assertThat(retrieveAllContacts.getContacts()).isNotEmpty();
			  Assertions.assertThat(retrieveAllContacts.getContacts().size() == 2);
			  List<String> contactNameList = retrieveAllContacts.getContacts().stream().map(e ->e.getContactName()).collect(Collectors.toList());
			  Assertions.assertThat(contactNameList).contains(CONTACT_NAME);
			  Assertions.assertThat(contactNameList).contains(CONTACT_NAME_TWO);
		  } 
		  catch (ContactValidationException e1)
		  {
			  LOGGER.error("Error in shouldRetrieveAllUniqueContactsForGivenAddressBook :: "+e1.getMessage());
		  }
		  
		  
		  
	  }
	  
	
}
