/*
 * Copyright (c) 2005-2008 Grameen Foundation USA
 * All rights reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * permissions and limitations under the License.
 * 
 * See also http://www.apache.org/licenses/LICENSE-2.0.html for an
 * explanation of the license and how it is applied.
 */

package org.mifos.client.service;

import org.joda.time.LocalDate;
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
		clientDto.setLocalDateOfBirth(null);
		verifyFieldError(clientDto, "dateOfBirth", "not.null");
	}

	public void testValidDateOfBirth () {
		clientDto.setLocalDateOfBirth(new LocalDate());
		verifyNoErrors(clientDto);
	}

	public void testValidDateOfBirthBoundary () {
		clientDto.setLocalDateOfBirth(getLocalDate("1880-01-01"));
		verifyNoErrors(clientDto);
	}

	public void testInvalidDateOfBirthBoundary () {
		clientDto.setLocalDateOfBirth(getLocalDate("1879-12-31"));
		verifyFieldError(clientDto, "dateOfBirth", "expression");
	}

	public void testInValidDateOfBirth () {
		clientDto.setLocalDateOfBirth(getLocalDate("1753-01-01"));
		verifyFieldError(clientDto, "dateOfBirth", "expression");
	}

	private LocalDate getLocalDate(String date) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd");
		return dateTimeFormatter.parseDateTime(date).toLocalDate();
	}
	
	private ClientDto validClientDto() {
		clientDto = new ClientDto();
		clientDto.setFirstName("Jane");
		clientDto.setLastName("Doe");
		clientDto.setLocalDateOfBirth(new LocalDate());
		return clientDto;
	}

}
