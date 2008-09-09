package org.mifos.client.service;

import org.joda.time.DateTime;
import org.mifos.client.domain.Client;
import org.mifos.loan.repository.ClientDao;

public interface ClientService {

	Client createClient(String firstName, String lastName, DateTime expectedDateOfBirth);
	void setClientDao(ClientDao clientDao);
	ClientDao getClientDao();
	
}
