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
package org.mifos.loan.service;

import org.joda.time.DateTime;
import org.mifos.client.domain.Client;
import org.mifos.client.repository.InMemoryClientDao;
import org.mifos.client.service.ClientService;
import org.mifos.core.MifosException;
import org.mifos.loan.repository.ClientDao;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test(groups = { "unit" })
public class BasicClientServiceTest {

    private ClientService clientService;
        
    @BeforeMethod(groups = { "unit" })
    protected void setUp() {
        clientService = new BasicClientService();
        ClientDao clientDao = new InMemoryClientDao();
        clientService.setClientDao(clientDao);
    }

    public void testCreateClient() throws MifosException {
    	String expectedFirstName = "Jane";
    	String expectedLastName = "Smith";
    	DateTime expectedDateOfBirth = new DateTime();
        createAndVerifyClient(expectedFirstName, expectedLastName, expectedDateOfBirth);
    }
    
    @Test(groups = { "workInProgress" })
    public void testCreateClientEmptyName() throws MifosException {
    	String expectedFirstName = "";
    	String expectedLastName = "";
    	DateTime expectedDateOfBirth = new DateTime();
    	verifyMifosException(expectedFirstName, expectedLastName, expectedDateOfBirth, "Should throw exception for empty names.");
    }

    @edu.umd.cs.findbugs.annotations.SuppressWarnings(value={"NP_LOAD_OF_KNOWN_NULL_VALUE"}, justification="testing behavior when date is null")
    public void testCreateClientNullDate() {
    	String expectedFirstName = "Foo";
    	String expectedLastName = "Bar";
    	DateTime expectedDateOfBirth = null;
    	verifyMifosException(expectedFirstName, expectedLastName, expectedDateOfBirth, "Should throw exception for null date.");
    }

    private void verifyMifosException(String expectedFirstName, String expectedLastName, 
    		DateTime expectedDateOfBirth, String message ) {
    	try {
    		clientService.createClient(expectedFirstName, expectedLastName, expectedDateOfBirth);
    		Assert.fail(message);
    	} catch (MifosException mifosException) {
    		// expected - do nothing
		}
    }
    
    
    public void testGetClient() throws MifosException {
    	String expectedFirstName = "Homer";
    	String expectedLastName = "Simpson";
    	DateTime expectedDateOfBirth = new DateTime();
        Client expectedClient = clientService.createClient(expectedFirstName, expectedLastName, expectedDateOfBirth);
        Integer clientId = expectedClient.getId();
        Client freshClient = clientService.getClient(clientId);
        Assert.assertEquals(freshClient.getId(), expectedClient.getId());
        Assert.assertEquals(freshClient.getFirstName(), expectedClient.getFirstName());
        Assert.assertEquals(freshClient.getLastName(), expectedClient.getLastName());
        Assert.assertEquals(freshClient.getDateOfBirth(), expectedClient.getDateOfBirth());
    }

    private void createAndVerifyClient(String expectedFirstName,
			String expectedLastName, DateTime expectedDateOfBirth) throws MifosException {
		Client client = clientService.createClient(expectedFirstName, expectedLastName, expectedDateOfBirth);
        Assert.assertNotNull(client);
        Assert.assertEquals(client.getFirstName(), expectedFirstName);
        Assert.assertEquals(client.getLastName(), expectedLastName);
        Assert.assertEquals(client.getDateOfBirth(), expectedDateOfBirth);
	}

}
