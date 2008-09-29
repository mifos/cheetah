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
package acceptance.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import framework.pageobjects.ClientsAndAccountsPage;
import framework.pageobjects.CreateClientPage;
import framework.pageobjects.CreateClientSuccessPage;
import framework.pageobjects.HomePage;
import framework.pageobjects.LoginPage;
import framework.test.UiTestCaseBase;

/*
 * Corresponds to story 683 in mingle
 * http://mingle.mifos.org:7070/projects/cheetah/cards/683
 */
@ContextConfiguration(locations={"classpath:ui-test-context.xml"})
@Test(groups={"userCanCreateBasicClientStoryTest","acceptance","ui"})
public class UserCanCreateBasicClientStoryTest extends UiTestCaseBase{

	private LoginPage loginPage;
	private DriverManagerDataSource dataSource;
    private static final Log LOG = LogFactory.getLog(UserCanCreateBasicClientStoryTest.class);

	@BeforeMethod
	public void setUp() throws Exception {
		super.setUp();
		loginPage = new LoginPage(selenium);
        deleteDataFromClientsTable();
	}

	@AfterMethod
	public void logOut() {
		loginPage.logout();
	}

	public void createValidClientTest() {
		String dateOfBirth = "1971-01-19";
		createAndVerifyValidClient(dateOfBirth);
	}

	public void createValidClientEdgeTest() {
		String dateOfBirth = "1880-01-01";
		createAndVerifyValidClient(dateOfBirth);
	}

	public void createInvalidClientTest() {
		String dateOfBirth = "1753-01-01";
		createAndVerifyInvalidClient(dateOfBirth);		
	}

	public void createInvalidClientTestEdge() {
		String dateOfBirth = "1879-12-31";
		createAndVerifyInvalidClient(dateOfBirth);		
	}

	private void createAndVerifyValidClient(String dateOfBirth) {
		String firstName = "John";
		String lastName = "Ya-ya";
		CreateClientPage createClientPage = loginAndNavigateToCreateClientPage();
		CreateClientSuccessPage createClientSuccessPage = createClientPage.createValidClient(firstName, lastName, dateOfBirth);
		createClientSuccessPage.verifyPage();
	}

	private void createAndVerifyInvalidClient(String dateOfBirth) {
		String firstName = "John";
		String lastName = "Ready To Fly";
		CreateClientPage createClientPage = loginAndNavigateToCreateClientPage();
		CreateClientPage createClientPage2 = createClientPage.createClientExpectingError(firstName, lastName, dateOfBirth);
		createClientPage2.verifyPage();
		createClientPage2.verifyErrorExists("Please enter a valid date of birth.");
	}

	private CreateClientPage loginAndNavigateToCreateClientPage() {
		HomePage homePage = loginPage.loginAs("mifos", "testmifos");
		ClientsAndAccountsPage clientsAndAccountsPage = homePage.navigateToClientsAndAccountsPage();
		CreateClientPage createClientPage = clientsAndAccountsPage.navigateToCreateClientPage();
		createClientPage.verifyPage();
		return createClientPage;
	}

	private void deleteDataFromClientsTable() throws IOException, DataSetException, SQLException, DatabaseUnitException {
		StringReader dataSetXmlStream = new StringReader("<dataset><clients/></dataset>");
		IDataSet dataSet = new FlatXmlDataSet(dataSetXmlStream);
		IDatabaseConnection databaseConnection = new DatabaseDataSourceConnection(this.getDataSource());
		DatabaseOperation.CLEAN_INSERT.execute(databaseConnection, dataSet);
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


}

