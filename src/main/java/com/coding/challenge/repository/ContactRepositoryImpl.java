package com.coding.challenge.repository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.function.LongSupplier;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.coding.challenge.domain.Contact;
import com.coding.challenge.service.ManageContactsServiceImpl;

@Repository("contactRepository")
public class ContactRepositoryImpl  implements ContactRepository
{

	private Set<Contact> 	inMemoryDatabase = new HashSet<>();
	
	private static Logger 	LOGGER 			 = Logger.getLogger(ContactRepositoryImpl.class);
	/*
	 * Scenario : User should be able to add new contact entries
	 * This method will prodice the behavior of save or update
	 * */
	@Override
	public Long save(Contact contact) 
	{
		if(contact.getId() == null || contact.getId() == 0)
		{
			contact.setId(generateIdSupplier().getAsLong());
		}
	    inMemoryDatabase.add(contact);
	    return contact.getId();
	}

	
	/*
	 * contactName for a given address book considered unique 
	 * */
	@Override
	public List<Contact> findByContactName(String contactName, Long addressBook) 
	{
		Predicate<String> compareContactName = (contactObjName) -> contactName != null ?contactName.equalsIgnoreCase(contactObjName):Boolean.FALSE;
		BiPredicate<Long, Long> compareAddressBookPredicate = compareAddressBookPredicate();
		
		return inMemoryDatabase.stream()
					.filter(contact ->( compareContactName.test(contact.getContactName())
										&& compareAddressBookPredicate.test(addressBook, contact.getAddressBook())))
					.collect(Collectors.toList());
		
	}

	@Override
	public List<Contact> findByNumbers(String numbers, Long addressBook) 
	{
		BiPredicate<Long, Long> compareAddressBookPredicate = compareAddressBookPredicate();
		Predicate<Contact>  compareNumbers = (contact) ->  
														{
															return contact.getNumbers().values()
																			    .stream()
																			    .filter(iteratedNumber -> iteratedNumber.equalsIgnoreCase(numbers))
																			    .count()>0l
																   && compareAddressBookPredicate.test(addressBook, contact.getAddressBook()) ;
														};
	  													
		return inMemoryDatabase.stream()
				.filter(contact -> compareNumbers.test(contact))
				.collect(Collectors.toList());
	}
	
	
	
	
	/*
	 * TODO check this 
	 * Java 8 Features
	 * */
	private LongSupplier generateIdSupplier()
	{
		 	LongSupplier generateId = () -> Long.valueOf(inMemoryDatabase.size() + 1);
		 	return generateId;
	}
	
	
	/*
	 * Java 8 features
	 * */
	private BiPredicate<Long, Long> compareAddressBookPredicate()
	{
		BiPredicate<Long, Long>  compareAddressBook = (recievedId, contactAddBookId) -> recievedId == contactAddBookId;
		return compareAddressBook;
	}

	@Override
	public Long removeContact(String contactName,Long addressBook) 
	{
		List<Contact> 				findByContactName 	= findByContactName(contactName,addressBook);
		Predicate<List<Contact>> 	contactExist 		= e-> (e != null && e.size() >0);
		Long 						deletedContactId    = null;
		if(contactExist.test(findByContactName))
		{
			Contact removedContact = findByContactName.get(0);
			deletedContactId	   = removedContact.getId();
			LOGGER.info(" The contact id which will be removed:: "+deletedContactId);
			inMemoryDatabase.remove(removedContact);
		}
		return deletedContactId;
	}

	/*
	 * Used to print all the contacts for a given address book
	 * Scenario : User should be able to print all contacts inan address book
	 * */
	@Override
	public List<Contact> findAllByAddressBook(Long addressBook) {
		// TODO Auto-generated method stub
		BiPredicate<Long, Long> compareAddressBookPredicate = compareAddressBookPredicate();
	    return inMemoryDatabase.stream()
	    				.filter(contact ->compareAddressBookPredicate.test(addressBook, contact.getAddressBook()))
	    				.collect(Collectors.toList());
	}
	
	 public void clearDb()
	 {
		 inMemoryDatabase.clear();
	 }

}
