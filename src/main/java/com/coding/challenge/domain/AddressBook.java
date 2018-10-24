package com.coding.challenge.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class AddressBook 
{

    @JsonIgnore
    private Long id;
    
    //This will be @ManyToOne 
    private Users user;
    
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private List<Contact> contacts;

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}
	
	
	
	
}
