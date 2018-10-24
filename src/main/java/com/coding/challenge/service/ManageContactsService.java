package com.coding.challenge.service;

import com.coding.challenge.api.ContactValidationException;
import com.coding.challenge.dto.ManageContactsRequest;
import com.coding.challenge.dto.ManageContactsResponse;
import com.coding.challenge.dto.RetrieveContactsRequest;
import com.coding.challenge.dto.RetrieveContactsResponse;

// TODO: Auto-generated Javadoc
/**
 * The Interface ManageContactsService.
 */
public interface ManageContactsService 
{

	/**
	 * Creates the contacts.
	 *
	 * @param request the request
	 * @return the manage contacts response
	 * @throws ContactValidationException the contact validation exception
	 */
	ManageContactsResponse    createContacts(ManageContactsRequest request) throws ContactValidationException;
	
	/**
	 * Removes the contacts.
	 *
	 * @param request the request
	 * @return the manage contacts response
	 * @throws ContactValidationException the contact validation exception
	 */
	ManageContactsResponse    removeContacts(ManageContactsRequest request) throws ContactValidationException;
	
	/**
	 * Retrieve all contacts.
	 *
	 * @param request the request
	 * @return the retrieve contacts response
	 * @throws ContactValidationException the contact validation exception
	 */
	RetrieveContactsResponse  retrieveAllContacts(RetrieveContactsRequest request) throws ContactValidationException;

}
