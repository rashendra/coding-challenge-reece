package com.coding.challenge.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.coding.challenge.dto.ManageContactsRequest;
import com.coding.challenge.dto.ManageContactsResponse;
import com.coding.challenge.dto.RetrieveContactsRequest;
import com.coding.challenge.dto.RetrieveContactsResponse;
import com.coding.challenge.service.ManageContactsService;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;

import static com.coding.challenge.api.ApplicationConstants.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 *  This class deals with API operations for Suburb Information.
 */
@RestController
@RequestMapping(value = ADDRESSBOOK_API_V0, produces = APPLICATION_JSON_VALUE)
public class RestAPIController {

    public static final String LOCATION_HEADER = "Location";

    public static final String 	CONTACT_NAME 	 = "Rashendra";
    public static final String 	CONTACT_NAME_TWO = "Rashen";
    public static final Long 	ADDRESS_BOOK 	 = 1L;
    public static final Long 	ADDRESS_BOOK_TWO = 2L;
    public static final String	NUMBER       	 = "0499177569";
    public static final String	CONTACT_TYPE 	 = "Mobile";
    public static final String	USERNAME     	 = "rashendra";

    
    @Autowired
    private ManageContactsService manageContactsService;
    
    /*
     * Should get the address book id and the contact name
     * */
	@RequestMapping(value = CREATENEWCONTACTS , method= RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
    public ManageContactsResponse createNewContacts(@Valid @RequestBody ManageContactsRequest request)
    {
		ManageContactsResponse response = null;
	    	try 
	    	{
	    		response = manageContactsService.createContacts(request);
		} 
	    	catch (ContactValidationException e) 
	    	{
	    		populateErrorResponse(response);
		}
	    	
	    	return response;
    }
    
	@RequestMapping(value = REMOVECONTACTS , method= RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
    public ManageContactsResponse removeContacts(@Valid @RequestBody ManageContactsRequest request)
    {
		ManageContactsResponse response = null;
	    	try 
	    	{
	    		response =  manageContactsService.removeContacts(request);
		} 
	    	catch (ContactValidationException e)
	    	{
	    		populateErrorResponse(response);
		}
	    	return response;
    }
	
	/*
	 * Can be further enhanced to include an error message
	 * */
	private void populateErrorResponse(ManageContactsResponse response)
	{
		response.setErrorMessage("Error in API ");
		response.setResponseStatus("Failure");
	}
    
    
	@RequestMapping(value = GETALLCONTACTS , method= RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
    public RetrieveContactsResponse getAllContactsForTheAddressBook(@Valid @RequestBody RetrieveContactsRequest request)
    {
		RetrieveContactsResponse response = null;
	    	try 
	    	{
	    		response =  manageContactsService.retrieveAllContacts(request);
		} 
	    	catch (ContactValidationException e)
	    	{
	    		populateErrorResponse(response);
		}
	    	return response;
    }
    
	@RequestMapping(value = GETALLUNIQUECONTACTS , method= RequestMethod.POST , consumes = APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
    public RetrieveContactsResponse getAllUniqueContacts(@Valid @RequestBody RetrieveContactsRequest request)
    {
		RetrieveContactsResponse response = null;
		try 
		{
			response = manageContactsService.retrieveAllContacts(request);
		} 
		catch (ContactValidationException e)
		{
			populateErrorResponse(response);
		}
		return response;
    }
	
	private void populateErrorResponse(RetrieveContactsResponse response)
	{
		response.setErrorMessage("Error in API ");
		response.setResponseStatus("Failure");
	}
	
	
	
	
	
	@RequestMapping(value = "/retrieveManageContactsRequest/{id}" , method= RequestMethod.GET)
	@ResponseBody
	public ManageContactsRequest  retrieveManageContactsRequest()
	{
		ManageContactsRequest request = new ManageContactsRequest();
		request.setUserName(USERNAME);
		request.setContactName(CONTACT_NAME);
		request.setContactNumber(NUMBER);
		request.setContactType(CONTACT_TYPE);
		request.setAddressBook(ADDRESS_BOOK);
		return request;
	}
	
	
	@RequestMapping(value = "/retrieveRetrieveContactsRequest/{id}" , method= RequestMethod.GET)
	@ResponseBody
	public RetrieveContactsRequest  retrieveRetrieveContactsRequest()
	{
		RetrieveContactsRequest request = new RetrieveContactsRequest();
		List<Long>  addressBookList     = new ArrayList<>();
		addressBookList.add(ADDRESS_BOOK);
		request.setAddressBookList(addressBookList);
		request.setUnique(Boolean.FALSE);
		request.setUserName(USERNAME);
		return request;
	}
	
  
}
