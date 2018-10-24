package com.coding.challenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.coding.challenge.domain.Contact;
import com.coding.challenge.dto.ManageContactsRequest;
import com.coding.challenge.dto.RetrieveContactsRequest;
import com.coding.challenge.dto.RetrieveContactsResponse;

public class TestDataObjectMother {


    public static final String 	CONTACT_NAME 	 = "Rashendra";
    public static final String 	CONTACT_NAME_TWO = "Rashen";
    public static final Long 	ADDRESS_BOOK 	 = 1L;
    public static final Long 	ADDRESS_BOOK_TWO = 2L;
    public static final String	NUMBER       	 = "0499177569";
    public static final String	CONTACT_TYPE 	 = "Mobile";
    public static final String	USERNAME     	 = "rashendra";
    
    
    public static Contact getContactRashendra()
    {
    	Map<String, String>  numbers  = new HashMap<String, String>();
    	numbers.put(CONTACT_TYPE, NUMBER);
    	return new Contact(ADDRESS_BOOK, CONTACT_NAME, numbers);
    }
    
    public static ManageContactsRequest getManageContactsRequest()
    {
	    	ManageContactsRequest  request = new ManageContactsRequest();
	    	request.setAddressBook(ADDRESS_BOOK);
	    	request.setContactName(CONTACT_NAME);
	    	request.setContactNumber(NUMBER);
	    	request.setContactType(CONTACT_TYPE);
	    	request.setUserName(USERNAME);
	    
	    	return request;
    	
    }
    
    public static RetrieveContactsRequest getRetrieveContactsRequest()
    {
	    	RetrieveContactsRequest  request = new RetrieveContactsRequest();
	    	List<Long>  addressBook = new ArrayList<>();
	    	addressBook.add(ADDRESS_BOOK);
	    	request.setAddressBookList(addressBook);
	    	request.setUnique(Boolean.FALSE);
	    	request.setUserName(USERNAME);
	    
	    	return request;
    	
    }
    
    public static RetrieveContactsRequest getRetrieveContactsRequestUniqueForUser()
    {
	    	RetrieveContactsRequest  request = new RetrieveContactsRequest();
	    	List<Long>  addressBook = new ArrayList<>();
	    	addressBook.add(ADDRESS_BOOK);
	    	addressBook.add(ADDRESS_BOOK_TWO);
	    	request.setAddressBookList(addressBook);
	    	request.setUnique(Boolean.TRUE);
	    	request.setUserName(USERNAME);
	    
	    	return request;
    	
    }
    
    public static Contact getSavedContactRashendra()
    {
	    	Map<String, String>  numbers  = new HashMap<String, String>();
	    	numbers.put(CONTACT_TYPE, NUMBER);
	    	Contact contact = new Contact(ADDRESS_BOOK, CONTACT_NAME, numbers);
	    	contact.setId(1l);
		return contact;
    }
    

}

