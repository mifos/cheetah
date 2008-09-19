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

package org.mifos.client.repository;

import java.util.List;

import junit.framework.Assert;

import org.joda.time.DateTime;
import org.mifos.client.domain.Client;
import org.mifos.core.MifosException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test(groups = { "unit" })
public class InMemoryClientDaoTest {

	private ClientDao clientDao;
	
	@BeforeMethod
	void setUp() {
		clientDao = new InMemoryClientDao();
	}
	
	public void testCreateClient() throws MifosException {
		String expectedFirstName = "John";
		String expectedLastName = "Smith";
		DateTime expectedDateOfBirth = new DateTime();
		Client client = clientDao.create(expectedFirstName, expectedLastName, expectedDateOfBirth);
		Assert.assertEquals(expectedFirstName, client.getFirstName());
		Assert.assertEquals(expectedLastName, client.getLastName());
		Assert.assertEquals(expectedDateOfBirth, client.getDateOfBirth());
	}

	public void testGetClient() throws MifosException {
		Client client = clientDao.create("John", "Careful Walker", new DateTime());
		Client newClient = clientDao.get(client.getId());
		Assert.assertEquals(client.getFirstName(), newClient.getFirstName());
		Assert.assertEquals(client.getLastName(), newClient.getLastName());
		Assert.assertEquals(client.getDateOfBirth(), newClient.getDateOfBirth());
	}

	public void testGetAll() throws MifosException {
		clientDao.create("John", "Icicle Boy", new DateTime());
		clientDao.create("John", "Starbird", new DateTime());
		List<Client> clientList = clientDao.getAll();
		Assert.assertEquals(2, clientList.size());
	}

	
	
}
