package com.coding.challenge.repository;

import static com.coding.challenge.TestDataObjectMother.*;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import com.coding.challenge.domain.Contact;

public class ContactRepositoryTest 
{
	
	private ContactRepository contactRepository;
	
	@Before
	public void setup()
	{
		contactRepository  = new ContactRepositoryImpl();
	}
	
	
	 	@Test
	    public void shouldSaveGivenValidContact(){
	        //given
	        final Contact contact = getContactRashendra();

	        //when
	        final Long id = contactRepository.save(contact);

	        //then
	        Assertions.assertThat(id).isNotNull();

	    }
	 	
	 	@Test 
	 	public void shouldFindByValidContactNameAndAddressBook()
	 	{
	 		final Contact 	contact = getContactRashendra();
	 		
	        final Long 		id 		= contactRepository.save(contact);
	        
	        List<Contact> findByContactName = contactRepository.findByContactName(CONTACT_NAME, 1l);
	        
	        Assertions.assertThat(findByContactName.size() > 0);
	        
	        Assertions.assertThat(findByContactName.get(0)).isNotNull();
	 		
	 	}
	 	 
	 	
	 	@Test 
	 	public void shouldRemoveGivenValidContactNameAndAddressBook()
	 	{
	 		final Contact 	contact = getContactRashendra();
	 		
	        final Long 		id 		= contactRepository.save(contact);
	        
	        contactRepository.removeContact(CONTACT_NAME, 1L);
	        
	        List<Contact> findByContactName = contactRepository.findByContactName(CONTACT_NAME, 1l);
	        
	        Assertions.assertThat(findByContactName.size() == 0);
	 		
	 	}
	 	
	 	@Test 
	 	public void shouldFindByValidNumberAndAddressBookId()
	 	{
	 		
	 		final Contact 	contact = getContactRashendra();
	 		
	        final Long 		id 		= contactRepository.save(contact);
	        
	        List<Contact> findByNumbers = contactRepository.findByNumbers(NUMBER, 1l);
	        
	        Assertions.assertThat(findByNumbers.size() > 0);
	        
	        Assertions.assertThat(findByNumbers.get(0)).isNotNull();
	 	}
	 	
	 	@Test 
	 	public void shouldFindAllByAddressBookId()
	 	{
	 		Contact 	contact 				= getContactRashendra();
	 		Contact 	contactTwo 			= getContactRashendra();
	 		contactTwo.setContactName(CONTACT_NAME_TWO);
	 		
	 		final Long 		id 					= contactRepository.save(contact);
	        final Long 		idTwo 				= contactRepository.save(contactTwo);
	        
	        List<Contact> 	findByAddressBook 	= contactRepository.findAllByAddressBook(1L);
	        
	        Assertions.assertThat(findByAddressBook.size() == 2);
	        
	        Assertions.assertThat(findByAddressBook.get(0)).isNotNull();
	 	}
	 	

}
