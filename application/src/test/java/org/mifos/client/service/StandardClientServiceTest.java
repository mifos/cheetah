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

import org.apache.log4j.Logger;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.mifos.client.repository.ClientDao;
import org.mifos.client.repository.InMemoryClientDao;
import org.mifos.core.MifosException;
import org.mifos.core.MifosServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.validation.Validator;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@ContextConfiguration(locations={"classpath:unitTestContext.xml"})
@Test(groups = { "unit" })
public class StandardClientServiceTest extends AbstractTestNGSpringContextTests {

    private static final Logger logger = Logger.getLogger(StandardClientServiceTest.class);
    private ClientService clientService;
    Validator validator;
        
	@BeforeMethod(groups = { "unit" })
    protected void setUp() {
		clientService = new StandardClientService();
		ClientDao clientDao = new InMemoryClientDao();
        clientService.setClientDao(clientDao);
        clientService.setValidator(validator);
    }

    public void testCreateClient() throws MifosServiceException {
    	String expectedFirstName = "Jane";
    	String expectedLastName = "Smith";
    	LocalDate expectedDateOfBirth = new LocalDate();
        createAndVerifyClient(expectedFirstName, expectedLastName, expectedDateOfBirth);
    }
    
    public void testCreateClientEmptyName() throws MifosException {
    	String expectedFirstName = "Foo";
    	String expectedLastName = "";
    	LocalDate expectedDateOfBirth = new LocalDate();
    	verifyMifosServiceException(expectedFirstName, expectedLastName, expectedDateOfBirth, "Should throw exception for empty names.");
    	expectedFirstName = "";
    	expectedLastName = "Bar";
    	verifyMifosServiceException(expectedFirstName, expectedLastName, expectedDateOfBirth, "Should throw exception for empty names.");
    }

    public void testCreateClientLongName() throws MifosException, MifosServiceException {
    	String eightyCharName = "01234567890123456789012345678901234567890123456789012345678901234567890123456789";
		String expectedFirstName80Chars = eightyCharName;
    	String expectedLastName = "Bar";
    	LocalDate expectedDateOfBirth = new LocalDate();
    	createClient(expectedFirstName80Chars, expectedLastName, expectedDateOfBirth);
		String expectedFirstName = "Foo";
    	String expectedLastName80Chars = eightyCharName;
    	createClient(expectedFirstName, expectedLastName80Chars, expectedDateOfBirth);
    }

    public void testCreateClientTooLongName() throws MifosException {
    	String eightyOneCharName = "012345678901234567890123456789012345678901234567890123456789012345678901234567891";
		String expectedFirstName81Chars = eightyOneCharName;
    	String expectedLastName = "Bar";
    	LocalDate expectedDateOfBirth = new LocalDate();
    	verifyMifosServiceException(expectedFirstName81Chars, expectedLastName, expectedDateOfBirth, "Should throw exception for empty names.");
		String expectedFirstName = "Foo";
    	String expectedLastName81Chars = eightyOneCharName;
    	verifyMifosServiceException(expectedFirstName, expectedLastName81Chars, expectedDateOfBirth, "Should throw exception for empty names.");
    }

    @edu.umd.cs.findbugs.annotations.SuppressWarnings(value={"NP_LOAD_OF_KNOWN_NULL_VALUE"}, justification="testing behavior when date is null")
    public void testCreateClientNullDate() {
    	String expectedFirstName = "Foo";
    	String expectedLastName = "Bar";
    	LocalDate expectedDateOfBirth = null;
    	verifyMifosServiceException(expectedFirstName, expectedLastName, expectedDateOfBirth, "Should throw exception for null date.");
    }

    public void testGetClient() throws MifosException, MifosServiceException {
    	String expectedFirstName = "Homer";
    	String expectedLastName = "Simpson";
    	LocalDate expectedDateOfBirth = new LocalDate();
		ClientDto clientDto = createClientDto(expectedFirstName, expectedLastName, expectedDateOfBirth);
		ClientDto expectedClientDto = clientService.createClient(clientDto);
        Integer clientId = expectedClientDto.getId();
        Assert.assertNotNull(clientId);
        ClientDto freshclientDto = clientService.getClient(clientId);
        Assert.assertEquals(freshclientDto.getId(), expectedClientDto.getId());
        Assert.assertEquals(freshclientDto.getFirstName(), expectedClientDto.getFirstName());
        Assert.assertEquals(freshclientDto.getLastName(), expectedClientDto.getLastName());
        Assert.assertEquals(freshclientDto.getLocalDateOfBirth(), expectedClientDto.getLocalDateOfBirth());
    }
    
    public void testCreateClientGoodDateOfBirth() throws MifosServiceException {
    	DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd");
    	LocalDate expectedDateOfBirth = dateTimeFormatter.parseDateTime("1880-01-01").toLocalDate();
    	String expectedFirstName = "Yeung";
    	String expectedLastName = "Enough";
       	createClient(expectedFirstName, expectedLastName, expectedDateOfBirth);
    }
    
    public void testCreateClientBadDateOfBirth() throws MifosServiceException {
    	DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd");
    	LocalDate expectedDateOfBirth = dateTimeFormatter.parseDateTime("1753-01-01").toLocalDate();
    	String expectedFirstName = "Far";
    	String expectedLastName = "Too-Old";
       	verifyMifosServiceException(expectedFirstName, expectedLastName, expectedDateOfBirth, "Should throw exception for date older than 1 January 1880");
    }
    
    public void testFindClient() throws MifosServiceException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd");
        createClient("Will", "Do", dateTimeFormatter.parseDateTime("2000-01-01").toLocalDate());
        createClient("Cant", "Wait", dateTimeFormatter.parseDateTime("2000-01-01").toLocalDate());
        
        Assert.assertEquals(clientService.findClients("Do").size(),1);
        Assert.assertEquals(clientService.findClients("W").size(),2);
        Assert.assertEquals(clientService.findClients("ya").size(),0);
    }

    private void verifyMifosServiceException(String expectedFirstName, String expectedLastName, 
    		LocalDate expectedDateOfBirth, String message ) {
    	try {
    		createClient(expectedFirstName, expectedLastName,
					expectedDateOfBirth);
    		Assert.fail(message);
    	} catch (MifosServiceException mifosException) {
    		// expected - do nothing
    		logger.info("Errors: " + mifosException.getErrors().toString());
    		logger.info("Errors: " + mifosException.getErrors().getAllErrors().toString());
		}
    }

	private void createClient(String expectedFirstName,
			String expectedLastName, LocalDate expectedDateOfBirth)
			throws MifosServiceException {
		ClientDto clientDto = createClientDto(expectedFirstName, expectedLastName, expectedDateOfBirth);
		clientService.createClient(clientDto);
	}

    private void createAndVerifyClient(String expectedFirstName,
			String expectedLastName, LocalDate expectedDateOfBirth) throws MifosServiceException {
    	ClientDto clientDto = createClientDto(expectedFirstName,
				expectedLastName, expectedDateOfBirth);
    	ClientDto newClientDto  = clientService.createClient(clientDto);
        Assert.assertNotNull(newClientDto);
        Assert.assertEquals(newClientDto.getFirstName(), expectedFirstName);
        Assert.assertEquals(newClientDto.getLastName(), expectedLastName);
        Assert.assertEquals(newClientDto.getLocalDateOfBirth(), expectedDateOfBirth);
	}

	private ClientDto createClientDto(String expectedFirstName,
			String expectedLastName, LocalDate expectedDateOfBirth) {
		ClientDto clientDto = new ClientDto();
    	clientDto.setFirstName(expectedFirstName);
    	clientDto.setLastName(expectedLastName);
    	clientDto.setLocalDateOfBirth(expectedDateOfBirth);
		return clientDto;
	}

	@Autowired
    @Test(enabled = false)
	public void setValidator(Validator validator) {
		this.validator = validator;
	}

    @Test(enabled = false)
    public void setClientService(StandardClientService clientService) {
		this.clientService = clientService;
	}

}
