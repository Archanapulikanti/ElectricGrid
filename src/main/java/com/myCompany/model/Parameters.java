package com.myCompany.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Parameters {
	
	@JsonProperty("Key") 
	public String key;
	
	@JsonProperty("Value") 
	public String value;	
	
}
