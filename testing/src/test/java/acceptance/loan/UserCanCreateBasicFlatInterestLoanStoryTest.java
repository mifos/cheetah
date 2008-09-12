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
package acceptance.loan;

import static org.testng.Assert.assertEquals;

import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import framework.pageobjects.CreateLoanPage;
import framework.pageobjects.CreateLoanSuccessPage;
import framework.pageobjects.HomePage;
import framework.pageobjects.LoginPage;
import framework.test.UiTestCaseBase;

/*
 * Corresponds to story 675 in mingle
 * http://mingle.mifos.org:7070/projects/cheetah/cards/675
 */
@ContextConfiguration(locations={"classpath:ui-test-context.xml"})
@Test(groups={"basicFlatInterestLoanStory","acceptance","ui"})
public class UserCanCreateBasicFlatInterestLoanStoryTest extends UiTestCaseBase {

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
	
	// do nothing method to leave Selenium in a good state after 
	// a test class that has no tests to run.  we should fix this
	// up so it is unnecessary.
	public void dummyTest() {
		assert(true);
	}

	@Test(groups={"workInProgress"})
	public void createValidLoanTest() {
		double LOAN_AMOUNT = 1200;
		double INTEREST_RATE = 12;
		HomePage homePage = loginPage.loginAs("mifos", "testmifos");
		CreateLoanPage createLoanPage = homePage.navigateToCreateLoanPage();
		assertEquals(selenium.getText("createLoanAccount"), "Create Loan Account");
		CreateLoanSuccessPage createLoanSuccessPage = createLoanPage.createLoan(LOAN_AMOUNT, INTEREST_RATE);
		assertEquals(selenium.getText("createLoanAccountResult"), "Loan Account Successfully Created");
		createLoanSuccessPage.logout();
	}

	@Test(groups={"workInProgress"})
	public void createInvalidLoanTest() {
		double LOAN_AMOUNT = 1200;
		double INTEREST_RATE = -5;
		HomePage homePage = loginPage.loginAs("mifos", "testmifos");
		CreateLoanPage createLoanPage = homePage.navigateToCreateLoanPage();
		assertEquals(selenium.getText("createLoanAccount"), "Create Loan Account");
		createLoanPage = createLoanPage.createLoanExpectingError(LOAN_AMOUNT, INTEREST_RATE);
		assertEquals(selenium.getText("createLoan.errormessage"), "Change the interest rate to a valid value.");
		createLoanPage.logout();
	}

}

