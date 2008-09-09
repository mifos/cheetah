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
import org.mifos.client.service.ClientService;
import org.mifos.loan.repository.ClientDao;

public class BasicClientService implements ClientService {

	private ClientDao clientDao;
	
	public ClientDao getClientDao() {
		return clientDao;
	}

	@Override
	public Client createClient(String firstName, String lastName, DateTime dateOfBirth) {
		return new Client(firstName, lastName, dateOfBirth);
	}

	@Override
	public void setClientDao(ClientDao clientDao) {
		this.clientDao = clientDao;
	}

}
