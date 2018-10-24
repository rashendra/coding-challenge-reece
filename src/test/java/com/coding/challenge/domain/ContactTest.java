package com.coding.challenge.domain;

import static com.coding.challenge.TestDataObjectMother.*;

import org.assertj.core.api.Assertions;
import org.junit.Test;


public class ContactTest 
{
	 	@Test
	    public void shouldReturnTrueIfContactIsMeaningfullyEqual(){
	        //given
	        Contact  contact 			= getContactRashendra();
	        Contact  contactRashendra 	= new Contact(ADDRESS_BOOK, CONTACT_NAME, null);

	        //when
	        boolean 	 isEqual 			= contact.equals(contactRashendra);
	        //then
	        Assertions.assertThat(isEqual).isTrue();
	    }

	    @Test
	    public void shouldReturnFalseIfContactIsNotMeaningfullyEqual(){
	    	 
	    	//given
	    		Contact  contact 			= getContactRashendra();
		    Contact  contactRashendra 	= new Contact(2L, CONTACT_NAME, null);

		    //when
		    boolean 	 isEqual 			= contact.equals(contactRashendra);;

	        //then
	        Assertions.assertThat(isEqual).isFalse();
	    }

	    @Test
	    public void shouldReturnSameHashCodeForEqualObjects(){
	        //given
	    		Contact  contact 			= getContactRashendra();
		    Contact  contactRashendra 	= new Contact(ADDRESS_BOOK, CONTACT_NAME, null);

	        //then
	        Assertions.assertThat(contact.hashCode()).isEqualTo(contactRashendra.hashCode());
	    }

}
