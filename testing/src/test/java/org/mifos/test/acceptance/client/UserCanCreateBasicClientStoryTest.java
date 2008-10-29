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

package org.mifos.test.acceptance.client;

import org.joda.time.format.DateTimeFormat;
import org.mifos.test.acceptance.framework.ClientsAndAccountsPage;
import org.mifos.test.acceptance.framework.CreateClientPage;
import org.mifos.test.acceptance.framework.CreateClientSuccessPage;
import org.mifos.test.acceptance.framework.HomePage;
import org.mifos.test.acceptance.framework.LoginPage;
import org.mifos.test.acceptance.framework.UiTestCaseBase;
import org.mifos.test.framework.util.DatabaseTestUtils;
import org.mifos.test.framework.util.SimpleDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


/*
 * Corresponds to story 683 in mingle
 * http://mingle.mifos.org:7070/projects/cheetah/cards/683
 */
@ContextConfiguration(locations={"classpath:ui-test-context.xml"})
@Test(sequential=true, groups={"userCanCreateBasicClientStoryTest","acceptance","ui"})
public class UserCanCreateBasicClientStoryTest extends UiTestCaseBase {

	private LoginPage loginPage;
	private DriverManagerDataSource dataSource;
    private DatabaseTestUtils databaseTestUtils;

	@BeforeMethod
	public void setUp() throws Exception {
		super.setUp();
		loginPage = new LoginPage(this.selenium);
        this.databaseTestUtils.deleteDataFromTables(this.getDataSource(), "clients");
	}

	@AfterMethod
	public void logOut() {
		loginPage.logout();
	}

	public void createValidClientTest() throws Exception {
		createAndVerifyValidClient("01/19/1971");
	}

	public void createValidClientEdgeTest() throws Exception {
		createAndVerifyValidClient("11/19/1883");
	}

	public void createInvalidClientTest() {
		createAndVerifyInvalidClient("01/01/1753");		
	}

	public void createInvalidClientTestEdge() {
		createAndVerifyInvalidClient("12/31/1879");		
	}

    public void createValidClientFrenchLocaleTest() throws Exception {
        createAndVerifyValidClientWithLocale("19/01/1971", "dd/MM/yyyy", "fr_Fr");
    }

    public void createValidClientPhilipinesLocaleTest() throws Exception {
        createAndVerifyValidClientWithLocale("1/19/1971", "M/d/yyyy", "en_PH");
    }

	private void createAndVerifyValidClient(String dateOfBirth) throws Exception {
	    createAndVerifyValidClientWithLocale(dateOfBirth, "M/d/yyyy", null);
	}

    private void createAndVerifyValidClientWithLocale(String dateOfBirth, String datePattern, String locale) throws Exception {
        String firstName = "John";
        String lastName = "Ya-ya";
        CreateClientPage createClientPage = loginAndNavigateToCreateClientPage();
        if (locale != null) {
          createClientPage = createClientPage.changeLocale(locale);
        }
        createClientPage.verifyDatePatternMessage(datePattern);
        CreateClientSuccessPage createClientSuccessPage = createClientPage.createValidClient(firstName, lastName, dateOfBirth);
        createClientSuccessPage.verifyPage();
        createClientSuccessPage.verifyMessage();
        verifyClientCreation(firstName, lastName, dateOfBirth, datePattern);
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
        createClientPage = createClientPage.changeLocale("en_US");
		return createClientPage;
	}
	
	private void verifyClientCreation(String firstName, String lastName, String dateOfBirth, String datePattern) throws Exception {
	    SimpleDataSet dataSet = new SimpleDataSet();
	    dataSet.row("clients", "id=1",
	                           "first_name=" + firstName, 
	                           "last_name=" + lastName, 
	                           "date_of_birth=" + getIso8601Date(dateOfBirth, datePattern));
        databaseTestUtils.verifyTable(dataSet.toString(), "clients", this.dataSource);        
	}
	
	private String getIso8601Date(String date, String datePattern) {
        return DateTimeFormat.forPattern(datePattern).parseDateTime(date).toDateMidnight().toLocalDate().toString();
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

