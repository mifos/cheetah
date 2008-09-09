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

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.mifos.client.domain.Client;
import org.mifos.client.repository.InMemoryClientDao;
import org.mifos.client.service.ClientService;
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

    public void testCreateClient() {
    	String expectedFirstName = "Jane";
    	String expectedLastName = "Smith";
    	DateTime expectedDateOfBirth = new DateTime();
        Client client = clientService.createClient(expectedFirstName, expectedLastName, expectedDateOfBirth);
        Assert.assertNotNull(client);
        Assert.assertEquals(client.getFirstName(), expectedFirstName);
        Assert.assertEquals(client.getLastName(), expectedLastName);
        Assert.assertEquals(client.getDateOfBirth(), expectedDateOfBirth);
    }
}
