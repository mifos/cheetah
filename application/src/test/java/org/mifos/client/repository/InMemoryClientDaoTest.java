/*
 * Copyright (c) 2005-2009 Grameen Foundation USA
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

import org.mifos.core.MifosException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test(groups = { "unit" })
public class InMemoryClientDaoTest {

	private ClientDaoTestHelper clientDaoTestHelper;
	
	@BeforeMethod
	void setUp() {
	    ClientDao clientDao = new InMemoryClientDao();
		clientDaoTestHelper = new ClientDaoTestHelper(clientDao);
	}
	
	public void testCreateClient() throws MifosException {
	    clientDaoTestHelper.testCreateClient();
	}

	public void testGetClient() throws MifosException {
	    clientDaoTestHelper.testGetClient();
	}

	public void testGetAll() throws MifosException {
	    clientDaoTestHelper.testGetAll();
	}

	public void testFindClientSuccess() throws MifosException {
        clientDaoTestHelper.testFindClientSuccess();
	}
	
	public void testFindClientFailure() throws MifosException {
        clientDaoTestHelper.testFindClientFailure();
	}
}
