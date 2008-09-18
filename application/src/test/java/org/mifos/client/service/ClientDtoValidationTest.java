package org.mifos.client.service;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@ContextConfiguration(locations={"classpath:unitTestContext.xml"})
@Test(groups = { "unit" })
public class ClientDtoValidationTest  extends AbstractTestNGSpringContextTests{
	
	private static final String maxLengthName = "01234567890123456789012345678901234567890123456789012345678901234567890123456789";
	private static final String tooLongName = maxLengthName + "1";

	private ClientDto clientDto;
	
	private Validator validator;
	
	@BeforeMethod
	public void setup() {
		clientDto = validClientDto();
	}
	
	public void testValidInputs() {
		verifyNoErrors();
	}

	private void verifyNoErrors() {
		Assert.assertEquals(getErrors().getErrorCount(), 0);
	}

	public void testBlankFirstName () {
		clientDto.setFirstName("");
		assertFieldError ("firstName", "not.blank");
	}

	public void testNullFirstName () {
		clientDto.setFirstName(null);
		assertFieldError ("firstName", "not.null");
	}

	public void testValidLongFirstNameBoundary () {
		clientDto.setFirstName(maxLengthName);
		verifyNoErrors();
	}
	
	public void testInvalidLongFirstName () {
		clientDto.setFirstName(tooLongName);
		assertFieldError ("firstName", "length");
	}
	
	public void testValidLongLastNameBoundary () {
		clientDto.setLastName(maxLengthName);
		verifyNoErrors();
	}
	
	public void testInvalidLongLastName () {
		clientDto.setLastName(tooLongName);
		assertFieldError ("lastName", "length");
	}
	
	public void testBlankLastName () {
		clientDto.setLastName("");
		assertFieldError ("lastName", "not.blank");
	}

	public void testNullLastName () {
		clientDto.setLastName(null);
		assertFieldError ("lastName", "not.null");
	}

	public void testNullDateOfBirth () {
		clientDto.setDateTimeOfBirth(null);
		assertFieldError ("dateOfBirth", "not.null");
	}

	public void testValidDateOfBirth () {
		clientDto.setDateTimeOfBirth(new DateTime());
		verifyNoErrors();
	}

	public void testValidDateOfBirthBoundary () {
		clientDto.setDateTimeOfBirth(getDateTime("1800-01-01"));
		verifyNoErrors();
	}

	public void testInvalidDateOfBirthBoundary () {
		clientDto.setDateTimeOfBirth(getDateTime("1799-12-31"));
		assertFieldError ("dateOfBirth", "expression");
	}

	public void testInValidDateOfBirth () {
		clientDto.setDateTimeOfBirth(getDateTime("1753-01-01"));
		assertFieldError ("dateOfBirth", "expression");
	}

	private DateTime getDateTime(String date) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd");
		return dateTimeFormatter.parseDateTime(date);
	}
	
	@Autowired
    @Test(enabled = false)
	public void setValidator(Validator validator) {
		this.validator = validator;
	}

	private ClientDto validClientDto() {
		clientDto = new ClientDto();
		clientDto.setFirstName("Jane");
		clientDto.setLastName("Doe");
		clientDto.setDateTimeOfBirth(new DateTime());
		return clientDto;
	}
	
	private void assertFieldError (String fieldName, String errorMessage) {
		BeanPropertyBindingResult errors = new BeanPropertyBindingResult(clientDto, "client");
		validator.validate(clientDto, errors);
		Assert.assertTrue(errors.getErrorCount() > 0, "Expected errors but got none.");
		FieldError fieldError = errors.getFieldError(fieldName);
		Assert.assertNotNull(fieldError, "Expected error on field " + fieldName + ", but got none");
		Assert.assertEquals(fieldError.getDefaultMessage(), errorMessage, "Incorrect validation error message.");
	}

	private Errors getErrors(){
		BeanPropertyBindingResult errors = new BeanPropertyBindingResult(clientDto, "client");
		validator.validate(clientDto, errors);
		return errors;

	}
}
