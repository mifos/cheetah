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

import org.joda.time.DateTime;
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
public class BasicClientServiceTest extends AbstractTestNGSpringContextTests {

    private ClientService clientService;
    Validator validator;
        
	@BeforeMethod(groups = { "unit" })
    protected void setUp() {
		clientService = new BasicClientService();
		ClientDao clientDao = new InMemoryClientDao();
        clientService.setClientDao(clientDao);
        clientService.setValidator(validator);
    }

	@Test(groups = { "unit" })
    public void testCreateClient() throws MifosServiceException {
    	String expectedFirstName = "Jane";
    	String expectedLastName = "Smith";
    	DateTime expectedDateOfBirth = new DateTime();
        createAndVerifyClient(expectedFirstName, expectedLastName, expectedDateOfBirth);
    }
    
    @Test(groups = { "unit" })
    public void testCreateClientEmptyName() throws MifosException {
    	String expectedFirstName = "";
    	String expectedLastName = "";
    	DateTime expectedDateOfBirth = new DateTime();
    	verifyMifosException(expectedFirstName, expectedLastName, expectedDateOfBirth, "Should throw exception for empty names.");
    }

    @edu.umd.cs.findbugs.annotations.SuppressWarnings(value={"NP_LOAD_OF_KNOWN_NULL_VALUE"}, justification="testing behavior when date is null")
    @Test(groups = { "unit" })
    public void testCreateClientNullDate() {
    	String expectedFirstName = "Foo";
    	String expectedLastName = "Bar";
    	DateTime expectedDateOfBirth = null;
    	verifyMifosException(expectedFirstName, expectedLastName, expectedDateOfBirth, "Should throw exception for null date.");
    }

    private void verifyMifosException(String expectedFirstName, String expectedLastName, 
    		DateTime expectedDateOfBirth, String message ) {
    	try {
    		ClientForm clientForm = createClientForm(expectedFirstName, expectedLastName, expectedDateOfBirth);
    		clientService.createClient(clientForm);
    		Assert.fail(message);
    	} catch (MifosServiceException mifosException) {
    		// expected - do nothing
		}
    }
    
    @Test(groups = { "unit" })
    public void testGetClient() throws MifosException, MifosServiceException {
    	String expectedFirstName = "Homer";
    	String expectedLastName = "Simpson";
    	DateTime expectedDateOfBirth = new DateTime();
		ClientForm clientForm = createClientForm(expectedFirstName, expectedLastName, expectedDateOfBirth);
		ClientForm expectedClientForm = clientService.createClient(clientForm);
        Integer clientId = expectedClientForm.getId();
        ClientForm freshClientForm = clientService.getClient(clientId);
        Assert.assertEquals(freshClientForm.getId(), expectedClientForm.getId());
        Assert.assertEquals(freshClientForm.getFirstName(), expectedClientForm.getFirstName());
        Assert.assertEquals(freshClientForm.getLastName(), expectedClientForm.getLastName());
        Assert.assertEquals(freshClientForm.getDateOfBirth(), expectedClientForm.getDateOfBirth());
    }

    private void createAndVerifyClient(String expectedFirstName,
			String expectedLastName, DateTime expectedDateOfBirth) throws MifosServiceException {
    	ClientForm clientForm = createClientForm(expectedFirstName,
				expectedLastName, expectedDateOfBirth);
    	ClientForm newClientForm  = clientService.createClient(clientForm);
        Assert.assertNotNull(newClientForm);
        Assert.assertEquals(newClientForm.getFirstName(), expectedFirstName);
        Assert.assertEquals(newClientForm.getLastName(), expectedLastName);
        Assert.assertEquals(newClientForm.getDateOfBirth(), expectedDateOfBirth);
	}

	private ClientForm createClientForm(String expectedFirstName,
			String expectedLastName, DateTime expectedDateOfBirth) {
		ClientForm clientForm = new ClientForm();
    	clientForm.setFirstName(expectedFirstName);
    	clientForm.setLastName(expectedLastName);
    	clientForm.setDateOfBirth(expectedDateOfBirth);
		return clientForm;
	}

	@Autowired
	public void setValidator(Validator validator) {
		this.validator = validator;
	}

    public void setClientService(BasicClientService clientService) {
		this.clientService = clientService;
	}

}
