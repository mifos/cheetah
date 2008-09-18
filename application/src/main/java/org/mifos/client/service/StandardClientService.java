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

import org.mifos.client.domain.Client;
import org.mifos.client.repository.ClientDao;
import org.mifos.core.MifosException;
import org.mifos.core.MifosServiceException;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Validator;

public class StandardClientService implements ClientService {

	private ClientDao clientDao;
	private Validator validator;
	
	@Override
	public ClientDto createClient(ClientDto clientDto) throws MifosServiceException {
		validate(clientDto);
		try {
			Client client;
			client = clientDao.create(clientDto.getFirstName(), clientDto.getLastName(), clientDto.getDateTimeOfBirth());
			return createClientDto(client);
		} catch (MifosException e) {
			throw new MifosServiceException("Caught exception in ClientDao.", e, new BeanPropertyBindingResult(clientDto, "clientForm"));
		}
	}

	private void validate(ClientDto clientDto) throws MifosServiceException {
		BeanPropertyBindingResult errors = new BeanPropertyBindingResult(clientDto, "clientDto");
		validator.validate(clientDto, errors);
		if (errors.getErrorCount() > 0) {
			throw new MifosServiceException("Client form validation failed.", errors);
		}
		
	}

	@Override
	public ClientDto getClient(Integer clientId) {
		Client client = clientDao.get(clientId);
		return createClientDto(client);
	}

	private ClientDto createClientDto(Client client) {
		ClientDto clientDto = new ClientDto();
		clientDto.setFirstName(client.getFirstName());
		clientDto.setLastName(client.getLastName());
		clientDto.setDateTimeOfBirth(client.getDateOfBirth());
		return clientDto;
	}


	@Override
	public void setClientDao(ClientDao clientDao) {
		this.clientDao = clientDao;
	}

	@Override
	public void setValidator(Validator validator) {
		this.validator = validator;
	}

}
