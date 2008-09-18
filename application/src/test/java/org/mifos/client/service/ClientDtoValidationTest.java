package org.mifos.client.service;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.mifos.core.AbstractDtoValidationTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test(groups = { "unit" })
public class ClientDtoValidationTest  extends AbstractDtoValidationTest {
	
	private static final String eightyCharName = "01234567890123456789012345678901234567890123456789012345678901234567890123456789";
	private static final String eightyOneCharName = eightyCharName + "1";

	private ClientDto clientDto;
	
	@BeforeMethod
	public void setup() {
		clientDto = validClientDto();
	}
	
	public void testValidInputs() {
		verifyNoErrors(clientDto);
	}

	public void testBlankFirstName () {
		clientDto.setFirstName("");
		verifyFieldError(clientDto, "firstName", "not.blank");
	}

	public void testNullFirstName () {
		clientDto.setFirstName(null);
		verifyFieldError(clientDto, "firstName", "not.null");
	}

	public void testValidLongFirstNameBoundary () {
		clientDto.setFirstName(eightyCharName);
		verifyNoErrors(clientDto);
	}
	
	public void testInvalidLongFirstName () {
		clientDto.setFirstName(eightyOneCharName);
		verifyFieldError(clientDto, "firstName", "length");
	}
	
	public void testValidLongLastNameBoundary () {
		clientDto.setLastName(eightyCharName);
		verifyNoErrors(clientDto);
	}
	
	public void testInvalidLongLastName () {
		clientDto.setLastName(eightyOneCharName);
		verifyFieldError(clientDto, "lastName", "length");
	}
	
	public void testBlankLastName () {
		clientDto.setLastName("");
		verifyFieldError(clientDto, "lastName", "not.blank");
	}

	public void testNullLastName () {
		clientDto.setLastName(null);
		verifyFieldError(clientDto, "lastName", "not.null");
	}

	public void testNullDateOfBirth () {
		clientDto.setDateTimeOfBirth(null);
		verifyFieldError(clientDto, "dateOfBirth", "not.null");
	}

	public void testValidDateOfBirth () {
		clientDto.setDateTimeOfBirth(new DateTime());
		verifyNoErrors(clientDto);
	}

	public void testValidDateOfBirthBoundary () {
		clientDto.setDateTimeOfBirth(getDateTime("1800-01-01"));
		verifyNoErrors(clientDto);
	}

	public void testInvalidDateOfBirthBoundary () {
		clientDto.setDateTimeOfBirth(getDateTime("1799-12-31"));
		verifyFieldError(clientDto, "dateOfBirth", "expression");
	}

	public void testInValidDateOfBirth () {
		clientDto.setDateTimeOfBirth(getDateTime("1753-01-01"));
		verifyFieldError(clientDto, "dateOfBirth", "expression");
	}

	private DateTime getDateTime(String date) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd");
		return dateTimeFormatter.parseDateTime(date);
	}
	
	private ClientDto validClientDto() {
		clientDto = new ClientDto();
		clientDto.setFirstName("Jane");
		clientDto.setLastName("Doe");
		clientDto.setDateTimeOfBirth(new DateTime());
		return clientDto;
	}

}
