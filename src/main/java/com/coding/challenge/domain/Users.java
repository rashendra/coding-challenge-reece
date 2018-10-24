package com.coding.challenge.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Users {
	
    @JsonIgnore
    private Long id;
    
	private String userName;
	
	private String password;
	
	
	private List<AddressBook>   adressBooks;

	
	
	public List<AddressBook> getAdressBooks() {
		return adressBooks;
	}

	public void setAdressBooks(List<AddressBook> adressBooks) {
		this.adressBooks = adressBooks;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
