package com.coding.challenge.dto;

import java.util.List;

import com.coding.challenge.domain.Contact;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;


@JsonPropertyOrder
({     
    "errorMessage",
    "responseStatus",
    "contacts"
})
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT) 
@JsonTypeName(value ="retrieveContactsRes")
public class RetrieveContactsResponse 
{

	private String 		  errorMessage;
	
	private String 		  responseStatus;
	
	private List<Contact> contacts;
	

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(String responseStatus) {
		this.responseStatus = responseStatus;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}
	
	
}
