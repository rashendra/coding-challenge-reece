package com.coding.challenge.enums;

public enum ContactTypes 
{

	MOB("MOB","MOBILE"),
	
	HOME("HOME","HOME"),
	
    OTHR("OTHR","OTHER"),
    
    WORK("WORK","WORK");
	
	
	private String code;
	
	private String displayName;
	
	private ContactTypes(String code, String displayName)
	{
	        this.code = code;
	        this.displayName = displayName;
	}
	
	
	public ContactTypes getEnumByName(String displayName)
	{
		ContactTypes[] values = ContactTypes.values();
		for(ContactTypes contactType : values)
		{
			if(contactType.getDisplayName().equalsIgnoreCase(displayName))
			{
				return contactType;
			}
		}
		return ContactTypes.OTHR;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	

	
}
