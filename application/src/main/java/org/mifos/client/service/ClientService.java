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

import java.util.List;

import org.mifos.client.repository.ClientDao;
import org.mifos.core.MifosServiceException;
import org.springframework.validation.Validator;

/**
 *
 */
public interface ClientService {

	ClientDto getClient(Integer clientId);
	ClientDto createClient(ClientDto clientDto) throws MifosServiceException;
	/**
	 * Find clients.
	 * 
	 * @param searchString the string to search for.  The initial implementation
	 * considers a match to be a client who's first or last name contains the 
	 * entire search string.  We will want to refactor and refine this behavior
	 * later.
	 * 
	 * @return a list of client DTOs for clients matching the search string. 
	 */
	List<ClientDto> findClients(String searchString);
    List<ClientDto> getAll();
    
	void setClientDao(ClientDao clientDao);
	void setValidator(Validator validator);

	
}
