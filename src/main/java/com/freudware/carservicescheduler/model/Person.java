package com.freudware.carservicescheduler.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Person {

    private String firstName;
    private String lastName;

    public Person() {
        
    }

    public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
    }
    
    @Override
    public String toString() {
        return "{" +
            "\"firstName\": \"" + this.firstName + "\", " +
            "\"lastName\": \"" + this.lastName + "\"" +
        "}";
    }
}
