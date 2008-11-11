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

import org.dbunit.DatabaseUnitException;
import org.dbunit.dataset.DataSetException;
import org.mifos.core.MifosException;
import org.mifos.test.framework.util.DatabaseTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
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
    private ClientDaoTestHelper clientDaoTestHelper;

	@BeforeMethod
	public void setUp() throws DataSetException, IOException, SQLException, DatabaseUnitException {
        this.databaseTestUtils.deleteDataFromTables(this.getDataSource(), "clients");
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
