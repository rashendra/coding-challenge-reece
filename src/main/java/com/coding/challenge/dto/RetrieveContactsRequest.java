package com.coding.challenge.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;


@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@JsonPropertyOrder({    
					"addressBookList",
                    "userName",
                	"unique"
})
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT) 
@JsonTypeName(value ="retrieveContactsReq")
public class RetrieveContactsRequest 
{
	
	private List<Long>  addressBookList;
	
	private String userName;
	
	private Boolean  unique;
	

	public List<Long> getAddressBookList() {
		return addressBookList;
	}

	public void setAddressBookList(List<Long> addressBookList) {
		this.addressBookList = addressBookList;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Boolean getUnique() {
		return unique;
	}

	public void setUnique(Boolean unique) {
		this.unique = unique;
	}
	
	
	
}
