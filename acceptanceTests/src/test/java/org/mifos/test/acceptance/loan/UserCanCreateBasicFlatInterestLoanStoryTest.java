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
package org.mifos.test.acceptance.loan;

import org.mifos.test.acceptance.framework.CreateLoanPage;
import org.mifos.test.acceptance.framework.HomePage;
import org.mifos.test.acceptance.framework.LoginPage;
import org.mifos.test.acceptance.framework.UiTestCaseBase;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


/*
 * Corresponds to story 675 in mingle
 * http://mingle.mifos.org:7070/projects/cheetah/cards/675
 */
@ContextConfiguration(locations={"classpath:ui-test-context.xml"})
@Test(groups={"basicFlatInterestLoanStory","acceptance","ui"})
@SuppressWarnings("PMD.SignatureDeclareThrowsException") // for test cases, throwing Exception is ok
public class UserCanCreateBasicFlatInterestLoanStoryTest extends UiTestCaseBase {

	private LoginPage loginPage;
	
	private static final String MIN_INTEREST_RATE = "5";
	
	@BeforeMethod
	public void setUp() throws Exception {
		super.setUp();
		loginPage = new LoginPage(selenium);
		deleteDataFromTables("loanproducts", "loans");
	}

	@AfterMethod
	public void logOut() {
		loginPage.logout();
	}

	public void createValidLoanTest() {
		double LOAN_AMOUNT = 1200;
		double INTEREST_RATE = 12;
		
		ensureLoanProductExists();
		
		HomePage homePage = loginPage.loginAs("mifos", "testmifos");
		CreateLoanPage createLoanPage = homePage.navigateToCreateLoanPage();
		assertElementTextExactMatch("Create a new loan","createLoanHeading");
		createLoanPage.createLoan(LOAN_AMOUNT, INTEREST_RATE);
		assertElementTextExactMatch("The loan has been created","loanEditMessage");
	}

	public void createInvalidLoanTest() {
		double LOAN_AMOUNT = 1200;
		double INVALID_INTEREST_RATE = -5;
        
        ensureLoanProductExists();
        
		HomePage homePage = loginPage.loginAs("mifos", "testmifos");
		CreateLoanPage createLoanPage = homePage.navigateToCreateLoanPage();
        assertElementTextExactMatch("Create a new loan","createLoanHeading");
		createLoanPage.createLoanExpectingError(LOAN_AMOUNT, INVALID_INTEREST_RATE);
		assertErrorTextExactMatch("Please enter an interest rate greater than or equal to the minimum of " +
                MIN_INTEREST_RATE + " allowed by loan product.");
	}

	private void ensureLoanProductExists() {
		String LONG_NAME = "Loan Product";
		String SHORT_NAME = "LP";
		String MAX_INTEREST_RATE = "100";
		
		loginPage
			.loginAs("mifos", "testmifos")
			.navigateToAdminPage()
			.navigateToCreateLoanProductPage()
			.createValidLoanProduct(LONG_NAME, SHORT_NAME, MIN_INTEREST_RATE, MAX_INTEREST_RATE);
		loginPage.logout();
	}
	
}

