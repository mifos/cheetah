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
