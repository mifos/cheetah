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
@Test(groups={"basicFlatInterestLoanStory","acceptance","ui"})
public class UserCanCreateBasicClientStoryTest extends UiTestCaseBase {

	private LoginPage loginPage;
	
	@BeforeMethod
	public void setUp() {
		super.setUp();
		loginPage = new LoginPage(selenium);
	}

	@AfterMethod
	public void logOut() {
		loginPage.logout();
	}

	public void createValidClientTest() {
		String firstName = "John";
		String lastName = "Ya-ya";
		String dateOfBirth = "1971-01-19";
		HomePage homePage = loginPage.loginAs("mifos", "testmifos");
		ClientsAndAccountsPage clientsAndAccountsPage = homePage.navigateToClientsAndAccountsPage();
		CreateClientPage createClientPage = clientsAndAccountsPage.navigateToCreateClientPage();
		createClientPage.verifyPage();
		CreateClientSuccessPage createClientSuccessPage = createClientPage.createValidClient(firstName, lastName, dateOfBirth);
		createClientSuccessPage.verifyPage();
	}

}

