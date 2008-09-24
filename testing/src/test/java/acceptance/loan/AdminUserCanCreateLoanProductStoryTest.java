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
import junit.framework.Assert;

import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import framework.pageobjects.AdminPage;
import framework.pageobjects.CreateLoanPage;
import framework.pageobjects.CreateLoanProductPage;
import framework.pageobjects.CreateLoanSuccessPage;
import framework.pageobjects.HomePage;
import framework.pageobjects.LoginPage;
import framework.test.UiTestCaseBase;
import framework.util.UiTestUtils;

/*
 * Corresponds to story 678 in mingle
 * http://mingle.mifos.org:7070/projects/cheetah/cards/678
 */
@ContextConfiguration(locations={"classpath:ui-test-context.xml"})
@Test(groups={"createLoanProductStory","acceptance","ui"})
public class AdminUserCanCreateLoanProductStoryTest extends UiTestCaseBase {

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

	/**
	 * This test is disabled until a framework is in place to clear out the loanproducts
	 * table prior to running it. Because LoanProduct.shortName is required to be unique,
	 * this test will faile the second time it is run without clearing the table before
	 * the second run.
	 */
	@Test(enabled=false)
	public void createValidLoanProductTest() {
		String LONG_NAME = "Simple Loan Product";
		String SHORT_NAME = "SLP";
		double MIN_INTEREST_RATE = 5;
		double MAX_INTEREST_RATE = 100;
		
		navigateToCreateLoanProductPage()
			.createValidLoanProduct(LONG_NAME, SHORT_NAME, MIN_INTEREST_RATE, MAX_INTEREST_RATE);
		
		assertTextFoundOnPage("Loan product created.");
	}
	
	public void createLoanProductMissingShortName() {
		String LONG_NAME = "Simple Loan Product";
		String SHORT_NAME = "";
		double MIN_INTEREST_RATE = -5;
		double MAX_INTEREST_RATE = 5000;
		
		navigateToCreateLoanProductPage()
			.createInvalidLoanProduct(LONG_NAME, SHORT_NAME, MIN_INTEREST_RATE, MAX_INTEREST_RATE);
		assertTextFoundOnPage("Minimum interest rate must be 0 or more.");
		assertTextFoundOnPage(SHORT_NAME);
		
	}
	
	private CreateLoanProductPage navigateToCreateLoanProductPage (){
		return
			loginPage
				.loginAs("mifos", "testmifos")
				.navigateToAdminPage()
				.navigateToCreateLoanProductPage();
	}

	private void assertTextFoundOnPage (String text) {
		Assert.assertTrue(selenium.isTextPresent(text));

	}
}

