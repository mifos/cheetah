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

package org.mifos.testing.acceptance.user;

import org.joda.time.format.DateTimeFormat;
import org.mifos.test.framework.util.DatabaseTestUtils;
import org.mifos.testing.acceptance.framework.ClientsAndAccountsPage;
import org.mifos.testing.acceptance.framework.CreateClientPage;
import org.mifos.testing.acceptance.framework.CreateClientSuccessPage;
import org.mifos.testing.acceptance.framework.HomePage;
import org.mifos.testing.acceptance.framework.LoginPage;
import org.mifos.testing.acceptance.framework.UiTestCaseBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


/*
 * Corresponds to story 987 in mingle
 * http://mingle.mifos.org:7070/projects/cheetah/cards/987
 */
@ContextConfiguration(locations={"classpath:ui-test-context.xml"})
@Test(sequential=true, groups={"userCanCreateBasicClientStoryTest","acceptance","ui","workInProgress"})
public class BranchOfficeAndHeadOfficeAreAutomaticallyCreatedStoryTest extends UiTestCaseBase{

	private LoginPage loginPage;
	private DriverManagerDataSource dataSource;
    private DatabaseTestUtils databaseTestUtils;

	@BeforeMethod
	public void setUp() throws Exception {
		super.setUp();
		loginPage = new LoginPage(selenium);
 	}

	@AfterMethod
	public void logOut() {
		loginPage.logout();
	}

	public void adminUserCanSeeListOfDefaultOffices() {
        loginPage
        .loginAs("mifos", "testmifos")
        .navigateToAdminPage()
        .navigateToViewOfficesPage();
        
        assertElementTextExactMatch("Mifos Head Office", "headOffice");
        assertElementTextExactMatch("Mifos Default Branch Office", "office-1");	    
	}

	public void nonAdminUserCannotSeeOfficeList() {
        loginPage
        .loginAs("user1", "password");

        selenium.open("/mifos/viewOffices.ftl");

        assertElementTextExactMatch("Access Denied", "accessDeniedHeading");
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

