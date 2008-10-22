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
package org.mifos.testing.acceptance.loan;

import org.mifos.testing.acceptance.framework.CreateLoanProductPage;
import org.mifos.testing.acceptance.framework.LoginPage;
import org.mifos.testing.acceptance.framework.UiTestCaseBase;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


/*
 * Corresponds to story 678 in mingle
 * http://mingle.mifos.org:7070/projects/cheetah/cards/678
 */
@ContextConfiguration(locations={"classpath:ui-test-context.xml"})
@Test(groups={"createLoanProductStory","acceptance","ui"})
public class AdminUserCanCreateLoanProductStoryTest extends UiTestCaseBase {

	private LoginPage loginPage;
	private static final String LONG_NAME = "Simple Loan Product";
	private static final String SHORT_NAME = "SLP";
	private static final String MIN_INTEREST_RATE = "5";
	private static final String MAX_INTEREST_RATE = "100";
	private static final String STATUS = "ACTIVE";

	@BeforeMethod
	public void setUp() throws Exception {
		super.setUp();
		deleteDataFromTables("loanproducts", "loans");
		loginPage = new LoginPage(selenium);
	}

	@AfterMethod
	public void logOut() {
		loginPage.logout();
	}

	public void createValidLoanProductTest() {
		
		navigateToCreateLoanProductPage()
			.createValidLoanProduct(LONG_NAME, SHORT_NAME, MIN_INTEREST_RATE, MAX_INTEREST_RATE);
		assertTextFoundOnPage("Loan product \"" + LONG_NAME + "\" was successfully defined.", "Didn't reach page loanProductCreateSuccess.ftl");
		assertElementTextExactMatch(LONG_NAME, "longName");
		assertElementTextExactMatch(SHORT_NAME, "shortName");
		assertElementTextExactMatch(MIN_INTEREST_RATE, "minInterestRate");
		assertElementTextExactMatch(MAX_INTEREST_RATE, "maxInterestRate");
		assertElementTextExactMatch(STATUS, "status");
	}
	
	public void createLoanProductMissingShortName() {
		navigateToCreateLoanProductPage()
			.createInvalidLoanProduct(LONG_NAME, "", MIN_INTEREST_RATE, MAX_INTEREST_RATE);
		assertErrorTextExactMatch("Please enter a short name.");
	}
		
	public void createLoanProductMissingLongName() {
		navigateToCreateLoanProductPage()
			.createInvalidLoanProduct("", SHORT_NAME, MIN_INTEREST_RATE, MAX_INTEREST_RATE);
		assertErrorTextExactMatch("Please enter a long name.");
		
	}
		
	public void createLoanProductNegativeMinInterestRate() {		
		navigateToCreateLoanProductPage()
			.createInvalidLoanProduct(LONG_NAME, SHORT_NAME, "-5", MAX_INTEREST_RATE);
		assertErrorTextExactMatch("Minimum interest rate must be 0 or more.");
		
	}
		
	public void createLoanProductNegativeMaxInterestRate() {
		navigateToCreateLoanProductPage()
			.createInvalidLoanProduct(LONG_NAME, SHORT_NAME, MIN_INTEREST_RATE, "-5");
		assertErrorTextIncludes("Maximum interest rate must be 0 or more.");		
	}
		
	public void createLoanProductMinInterestRateExceedsMaxInterestRate() {
		navigateToCreateLoanProductPage()
			.createInvalidLoanProduct(LONG_NAME, SHORT_NAME, "5", "4");
		assertErrorTextExactMatch("The minimum interest rate cannot exceed the maximum interest rate.");
	}
		
	public void createLoanProductMissingMaxInterestRate() {
		navigateToCreateLoanProductPage()
			.createInvalidLoanProduct(LONG_NAME, SHORT_NAME, "5", "");
		assertErrorTextIncludes("Please enter the maximum annual interest rate.");
	}
		
	public void createLoanProductMissingMinInterestRate() {
		navigateToCreateLoanProductPage()
			.createInvalidLoanProduct(LONG_NAME, SHORT_NAME, "", "5");
		assertErrorTextExactMatch("Please enter the minimum annual interest rate.");		
	}

	private CreateLoanProductPage navigateToCreateLoanProductPage (){
		return
			loginPage
				.loginAs("mifos", "testmifos")
				.navigateToAdminPage()
				.navigateToCreateLoanProductPage();
	}
}

