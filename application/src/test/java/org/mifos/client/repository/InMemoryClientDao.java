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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.mifos.client.domain.Client;
import org.mifos.core.MifosException;
import org.springframework.validation.Validator;

public class InMemoryClientDao implements ClientDao {

	private int nextId = 1;
	private final Map<Integer, Client> clients = new HashMap<Integer, Client>(); 
	
	@Override
	public Client create(String firstName, String lastName, DateTime dateOfBirth) throws MifosException {
		Client client = new Client(generateNextId(), firstName, lastName, dateOfBirth);
		clients.put(client.getId(), client);
		return client;
	}

	@Override
	public Client get(Integer clientId) {
		return clients.get(clientId);
	}

	private Validator validator; 

	public Validator getValidator() {
		return validator;
	}

	public void setValidator(Validator validator) {
		this.validator = validator;
	}

	@Override
	public List<Client> getAll() {
		ArrayList<Client> clientList = new ArrayList<Client>();
		clientList.addAll(clients.values());
		return clientList;
	}
	
	private Integer generateNextId() {
		synchronized(this) {
			return Integer.valueOf(nextId++);
		}
	}	
	

}
