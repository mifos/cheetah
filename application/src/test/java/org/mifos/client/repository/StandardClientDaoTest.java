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

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.dbunit.DatabaseUnitException;
import org.dbunit.dataset.DataSetException;
import org.joda.time.LocalDate;
import org.mifos.client.domain.Client;
import org.mifos.core.MifosException;
import org.mifos.util.DatabaseTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@ContextConfiguration(locations={"classpath:integrationTestContext.xml"})
@TransactionConfiguration(transactionManager="transactionManager")
@Test(groups = { "integration" })
public class StandardClientDaoTest extends AbstractTransactionalTestNGSpringContextTests {

	@Autowired
	private ClientDao clientDao;
	private DriverManagerDataSource dataSource;
    private DatabaseTestUtils databaseTestUtils;

	@BeforeMethod
	public void setUp() throws DataSetException, IOException, SQLException, DatabaseUnitException {
	    this.databaseTestUtils.deleteDataFromTable("clients", this.getDataSource());
	}
	
    public void testCreateClient() throws MifosException {
        String expectedFirstName = "John";
        String expectedLastName = "Smith";
        LocalDate expectedDateOfBirth = new LocalDate();
        Client client = clientDao.create(expectedFirstName, expectedLastName, expectedDateOfBirth);
        Assert.assertEquals(expectedFirstName, client.getFirstName());
        Assert.assertEquals(expectedLastName, client.getLastName());
        Assert.assertEquals(expectedDateOfBirth, client.getDateOfBirth());
    }

    public void testGetClient() throws MifosException {
        Client client = clientDao.create("John", "Careful Walker", new LocalDate());
        Client newClient = clientDao.get(client.getId());
        Assert.assertEquals(client.getFirstName(), newClient.getFirstName());
        Assert.assertEquals(client.getLastName(), newClient.getLastName());
        Assert.assertEquals(client.getDateOfBirth(), newClient.getDateOfBirth());
    }

    public void testGetAll() throws MifosException {
        clientDao.create("John", "Icicle Boy", new LocalDate());
        clientDao.create("John", "Starbird", new LocalDate());
        List<Client> clientList = clientDao.getAll();
        Assert.assertEquals(clientList.size(), 2);
    }

    public void testFindClientSuccess() throws MifosException {
        clientDao.create("John", "Icicle Boy", new LocalDate());
        clientDao.create("Sue", "Ohloh", new LocalDate());
        List<Client> clients = clientDao.findClients("John");
        Assert.assertEquals(1, clients.size());

        clients = clientDao.findClients("Boy");
        Assert.assertEquals(1, clients.size());

        clients = clientDao.findClients("cic");
        Assert.assertEquals(1, clients.size());

        clients = clientDao.findClients("oh");
        Assert.assertEquals(2, clients.size());

        clients = clientDao.findClients("");
        Assert.assertEquals(2, clients.size());
    }
    
    public void testFindClientFailure() throws MifosException {
        clientDao.create("John", "Icicle Boy", new LocalDate());
        List<Client> clients = clientDao.findClients("Joe");
        Assert.assertEquals(0, clients.size());

        clients = clientDao.findClients("John Icicle Boy");
        Assert.assertEquals(0, clients.size());
        
        // TODO: handle null
        //clients = clientDao.findClients(null);
        //Assert.assertEquals(0, clients.size());
        
    }
    
    @Test(enabled=false)
    public DriverManagerDataSource getDataSource() {
        return dataSource;
    }

    @Autowired
    @Test(enabled=false)
    public void setDataSource(DriverManagerDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Autowired
    @Test(enabled=false)
    public void setDatabaseTestUtils(DatabaseTestUtils databaseTestUtils) {
        this.databaseTestUtils = databaseTestUtils;
    }

}
