package com.coding.challenge.domain;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Contact
{
    @JsonIgnore
    private Long id;
    
    // With a JPA or Hibernate repository this will be @ManyToOne relationship with AddressBook
    @JsonProperty("addressBook")
    private Long addressBook;    
    
    @JsonProperty("contactName")
	private String contactName;
	
	/*
	 *  In a real application this will  be represented in a entity with oneToMany
	 *  With number Type and Number
	 *  One number type will hold only one number as per real 
	 * */
    @JsonProperty("numbers")
	private Map<String, String>  numbers  = new HashMap<String, String>();
	
    
	
    
	public Contact() {
		super();
	}

	@JsonCreator
	public Contact( Long addressBook, String contactName, Map<String, String> numbers) {
		super();
		this.addressBook = addressBook;
		this.contactName = contactName;
		this.numbers = numbers;
	}

	public Long getAddressBook() {
		return addressBook;
	}

	public void setAddressBook(Long addressBook) {
		this.addressBook = addressBook;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public Map<String, String> getNumbers() 
	{
		if(numbers == null )
		{
			numbers = new HashMap<String,String>();
		}
		return numbers;
	}

	public void setNumbers(Map<String, String> numbers) {
		this.numbers = numbers;
	}
	
	
		/*
		 * Both addressBook and the contactName should be equal 
		 * */
	    @Override
	    public boolean equals(Object o) 
	    {
	        if (this == o) 
	        {
	        	return true;
	        }
	        if (o == null || getClass() != o.getClass()) {
	        	return false;
	        }

	        Contact contact = (Contact) o;
	        if(addressBook != contact.addressBook)
	        {
	        	return false;
	        }
	        
	        return contactName.equals(contact.contactName);
	    }

	    @Override
	    public int hashCode() 
	    {
	        int result  = addressBook != null ? addressBook.hashCode() : 0;
	        result 		= 31 * result + (contactName != null ? contactName.hashCode() : 0);
	        return result;
	    }

	

}
