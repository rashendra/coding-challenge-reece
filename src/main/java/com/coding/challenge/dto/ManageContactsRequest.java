package com.coding.challenge.dto;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@JsonPropertyOrder({    
					"addressBook",
                    "userName",
                    "contactName",
                    "contactType",
                	"contactNumber"
})
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT) 
@JsonTypeName(value ="manageContactsReq")
public class ManageContactsRequest 
{
	
    private Long   addressBook;
    
    private String userName;
	
    @NotEmpty(message = "ContactName is Mandatory")
	private String contactName;
	
	private String contactType;
	
	@Pattern(message="Please check the format of Number ! Should only contain numeric values" , regexp="[0-9]+")
	private String contactNumber;
	
	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getAddressBook() {
		return addressBook;
	}

	public void setAddressBook(Long addressBook) {
		this.addressBook = addressBook;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactType() {
		return contactType;
	}

	public void setContactType(String contactType) {
		this.contactType = contactType;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	

}
