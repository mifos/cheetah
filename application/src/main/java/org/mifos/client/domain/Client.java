package org.mifos.client.domain;

import org.joda.time.DateTime;

public class Client {

	private String firstName;
	private String lastName;
	private DateTime dateOfBirth;

	public Client(String firstName, String lastName, DateTime dateOfBirth) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = new DateTime(dateOfBirth);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public DateTime getDateOfBirth() {
		return new DateTime(this.dateOfBirth);
	}
	
	public void setDateOfBirth(DateTime dateOfBirth) {
		this.dateOfBirth = new DateTime(dateOfBirth);
	}
	
}
