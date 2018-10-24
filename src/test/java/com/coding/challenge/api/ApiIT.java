package com.coding.challenge.api;

import com.coding.challenge.BaseIntegrationTest;
import com.coding.challenge.domain.Contact;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static com.coding.challenge.TestDataObjectMother.*;
import static com.coding.challenge.api.ApplicationConstants.*;
import static io.restassured.http.ContentType.JSON;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.core.IsEqual.equalTo;

public class ApiIT extends BaseIntegrationTest {


    @Before
    public void setUp(){
        contactRepository.clearDb();
    }

  
    @Test
    public void shouldCreateNewContactWhenContactDetailsAndAddressBookGive() {

        RestAssured
                .given()
                    .port(port)
                    .contentType(JSON)
                    .header(getValidAuthHeader())
                    .body(getManageContactsRequest())
                .when()
                    .post(ADDRESSBOOK_API_V0+CREATENEWCONTACTS)
                .then()
                    .statusCode(SC_CREATED)
                    .body("manageContactsRes.responseStatus", equalTo("Success"))
                    .body("manageContactsRes.contactId", equalTo(1));
    }
    
    @Test
    public void shouldRemoveContactWhenContactDetailsAndAddressBookGive() 
    {

    	contactRepository.save(getContactRashendra());
        RestAssured
                .given()
                    .port(port)
                    .contentType(JSON)
                    .header(getValidAuthHeader())
                    .body(getManageContactsRequest())
                .when()
                    .post(ADDRESSBOOK_API_V0+REMOVECONTACTS)
                .then()
                    .statusCode(SC_OK)
                    .body("manageContactsRes.responseStatus", equalTo("Success"))
                    .body("manageContactsRes.contactId", equalTo(1));
    }
    
    @Test
    public void shouldPrintAllContactsGivenAddressBook() 
    {
    	populateContactsAddressBookOne();
    	
        RestAssured
                .given()
                    .port(port)
                    .contentType(JSON)
                    .header(getValidAuthHeader())
                    .body(getRetrieveContactsRequest())
                .when()
                    .post(ADDRESSBOOK_API_V0+GETALLCONTACTS)
                .then()
                    .statusCode(SC_OK)
                    .body("retrieveContactsRes.errorMessage", equalTo(null))
                    .body("retrieveContactsRes.responseStatus", equalTo("Success"))
                    .body("retrieveContactsRes.contacts.size()", equalTo(2));
    }
    
    
    @Test
    public void shouldPrintAllUniqueContactsGivenAddressBookList() 
    {
    	populateContactsAddressBookOne();
    	populateContactsAddressBookTwo();
    	
        RestAssured
                .given()
                    .port(port)
                    .contentType(JSON)
                    .header(getValidAuthHeader())
                    .body(getRetrieveContactsRequestUniqueForUser())
                .when()
                    .post(ADDRESSBOOK_API_V0+GETALLCONTACTS)
                .then()
                    .statusCode(SC_OK)
                    .body("retrieveContactsRes.errorMessage", equalTo(null))
                    .body("retrieveContactsRes.responseStatus", equalTo("Success"))
                    .body("retrieveContactsRes.contacts.size()", equalTo(2));
    }
    

	private void populateContactsAddressBookOne() 
	{
		Contact contactRashendraTwo = getContactRashendra();
    	contactRashendraTwo.setContactName(CONTACT_NAME_TWO);
		contactRepository.save(contactRashendraTwo);
    	contactRepository.save(getContactRashendra());
	}
	
	private void populateContactsAddressBookTwo() 
	{
		Contact contactRashendraOne = getContactRashendra();
		Contact contactRashendraTwo = getContactRashendra();
		
	    	contactRashendraTwo.setContactName(CONTACT_NAME_TWO);
	    	contactRashendraTwo.setAddressBook(ADDRESS_BOOK_TWO);
	    	contactRashendraOne.setAddressBook(ADDRESS_BOOK_TWO);
    	
		contactRepository.save(contactRashendraTwo);
    	    contactRepository.save(contactRashendraOne);
	}
    


}
