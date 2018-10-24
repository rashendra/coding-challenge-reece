# Contact Information API

### How To Build and Run the API

Pre-requisites
--------------
JDK 1.8.x 

Technologies/ Libraries used

    Java 8
    Spring boot
    Spring boot security
    Mockito
    AssertJ

#### How to run and deploy
Go to the project root directory and run following commands
###### To build the application
    ./gradlew build    or just gradle build
    
###### To Run the application    
    ./gradlew bootRun or just gradle bootRun


## Access the API
Please use one of the below tools to access the application 
  1. Postman either stand alone or Ext to chorome 
  2. SOAP UI 


=======================================================================
## API Information
The contact information API provides 

    1   Create a contact for a given address book  ( Add new Contact)
    2   Remove contacts for give contact name and address book
    3   Users should be able to print all contacts in an address book
    4   Allow users to maintain multiple address books 
    5   Users to print all unique contacts based on names 

## How to use the API (All requests should have the header key/value  API-KEY xJ9a34fo, and please select the media type to JSON. Please find the sample request from postman)
key 		:   API-KEY
Value   :   xJ9a34fo

# Create Contacts and remove Contacts 
### createContact url/Request Method POST  : http://localhost:8080/api/v0/addressBook/createNewContacts
### remove  url Request Method POST   	   : http://localhost:8080/api/v0/addressBook/removeContacts
### use the header values with the key API-KEY and value xJ9a34fo ( This can be further enhanced to authenticate the request using a CustomUserAuthenticationFilter instead of GenericFilterBean
### use the below request for both 

## Sample Request for manageContactReq
		{
		    "manageContactsReq": {
		        "addressBook": 1,
		        "userName": "rashendra",
		        "contactName": "Rashendra",
		        "contactType": "Mobile",
		        "contactNumber": "0499177569"
		    }
		}
### Sample response when user creates an address book
		{
		    "manageContactsRes": {
		        "errorMessage": null,
		        "responseStatus": "Success",
		        "contactId": 1
		    }
		}

# Retrieve all contacts for a given user/addressbook
### getAllContacts/Request Method POST    		: http://localhost:8080/api/v0/addressBook/getAllContacts
### getAllUniqueContacts / Request Method POST   : http://localhost:8080/api/v0/addressBook/getAllUniqueContacts
### set unique attribute to true if if you want to get all the unique contacts , and should include all the address books 

## Sample Request for Retrieve Contacts
 
	{
	    "retrieveContactsReq": {
	        "addressBookList": [
	            1
	        ],
	        "userName": "rashendra",
	        "unique": false
	    }
	}
### Sample response for retrieving contacts 
	{
	    "retrieveContactsRes": {
	        "errorMessage": null,
	        "responseStatus": "Success",
	        "contacts": [
	            {
	                "addressBook": 1,
	                "contactName": "RashendraSixs",
	                "numbers": {
	                    "WORK": "0499177569",
	                    "MOBILE": "0499177569"
	                }
	            },
	            {
	                "addressBook": 1,
	                "contactName": "RashendraSixss",
	                "numbers": {
	                    "MOBILE": "0499177569"
	                }
	            },
	            {
	                "addressBook": 1,
	                "contactName": "RashendraSix",
	                "numbers": {
	                    "HOME": "0499177569"
	                }
	            }
	        ]
	    }
	}

## Security Features     
####If you do not have a valid Auth Token, the API will return following error
        HTTP STATUS 403 
       {
	    "timestamp": "2017-08-27T22:16:58.898+0000",
	    "status": 403,
	    "error": "Forbidden",
	    "message": "Could not verify the provided CSRF token because your session was not found.",
	    "path": "/api/v0/addressBook/createNewContacts"
		}
    
#Validations
## Number should be numeric 
## Contact name should not be empty    
    
# Further enhancement and limitations 
## Include Spring AspectJ /AOP concepts  to intercept the request/response and log the request/response 
## Replace the in memory database with RDMS such as mysql, postgres
## Use JpaRepository or spring CrudRepository which will build the ORM support 
## Use the combination of numbers and contact name or created date timestamp to identify unique records 
## One contact type will only hold one number. This can be enhanced to hold many numbers used in an Entity
     
    
    