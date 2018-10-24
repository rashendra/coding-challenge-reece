package com.coding.challenge.repository;

import java.util.List;

import com.coding.challenge.domain.Contact;

// TODO: Auto-generated Javadoc
/**
 * The Interface ContactRepository.
 */
public interface ContactRepository 
{
    
    /**
     * Save.
     *
     * @param entity the entity
     * @return the long
     */
    Long save(Contact entity);
    
//    Contact find(Long id);

    /**
 * Removes the contact.
 *
 * @param contactName the contact name
 * @param addressBook the address book
 * @return the long
 */
Long removeContact(String contactName,Long addressBook);

    /**
     * Find by contact name.
     *
     * @param contactName the contact name
     * @param addressBook the address book
     * @return the list
     */
    List<Contact> findByContactName(String contactName, Long addressBook);

    /**
     * Find by numbers.
     *
     * @param numbers the numbers
     * @param addressBook the address book
     * @return the list
     */
    List<Contact> findByNumbers(String numbers,Long addressBook);
    
    /**
     * Find all by address book.
     *
     * @param addressBook the address book
     * @return the list
     */
    List<Contact> findAllByAddressBook(Long addressBook);
    
    
	
}
