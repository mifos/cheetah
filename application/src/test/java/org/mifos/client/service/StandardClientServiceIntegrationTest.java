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
import org.mifos.core.MifosServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@TransactionConfiguration(transactionManager="transactionManager")
@ContextConfiguration(locations={"classpath:integrationTestContext.xml"})
@Test(groups = { "integration" })
public class StandardClientServiceIntegrationTest extends AbstractTransactionalTestNGSpringContextTests {

    private ClientService clientService;
        
	@BeforeMethod
    protected void setUp() {
		// do nothing
    }

    public void testCreateClient() throws MifosServiceException {
    	String expectedFirstName = "Jane";
    	String expectedLastName = "Smith";
    	LocalDate expectedDateOfBirth = new LocalDate();
        createAndVerifyClient(expectedFirstName, expectedLastName, expectedDateOfBirth);
    }
    
    private void createAndVerifyClient(String expectedFirstName,
			String expectedLastName, LocalDate expectedDateOfBirth) throws MifosServiceException {
    	ClientDto clientDto = createclientDto(expectedFirstName,
				expectedLastName, expectedDateOfBirth);
    	ClientDto newClient  = clientService.createClient(clientDto);
        Assert.assertNotNull(newClient);
        Assert.assertNotNull(newClient.getId());
        Assert.assertEquals(newClient.getFirstName(), expectedFirstName);
        Assert.assertEquals(newClient.getLastName(), expectedLastName);
        Assert.assertEquals(newClient.getLocalDateOfBirth(), expectedDateOfBirth);
	}

	private ClientDto createclientDto(String expectedFirstName,
			String expectedLastName, LocalDate expectedDateOfBirth) {
		ClientDto clientDto = new ClientDto();
    	clientDto.setFirstName(expectedFirstName);
    	clientDto.setLastName(expectedLastName);
    	clientDto.setLocalDateOfBirth(expectedDateOfBirth);
		return clientDto;
	}

	@Autowired
    @Test(enabled = false)
    public void setClientService(StandardClientService clientService) {
		this.clientService = clientService;
	}

}
