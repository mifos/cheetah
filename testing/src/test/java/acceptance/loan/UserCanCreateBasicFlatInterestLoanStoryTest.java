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

import java.io.IOException;
import java.sql.SQLException;

import junit.framework.Assert;

import org.dbunit.DatabaseUnitException;
import org.joda.time.DateTime;
import org.mifos.test.framework.util.DatabaseTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import framework.pageobjects.CreateLoanPage;
import framework.pageobjects.HomePage;
import framework.pageobjects.LoginPage;
import framework.test.UiTestCaseBase;
import framework.util.UiTestUtils;

/*
 * Corresponds to story 675 in mingle
 * http://mingle.mifos.org:7070/projects/cheetah/cards/675
 */
@ContextConfiguration(locations={"classpath:ui-test-context.xml"})
@Test(groups={"basicFlatInterestLoanStory","acceptance","ui"})
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
		Assert.assertTrue(selenium.isTextPresent("Create a new loan"));
		createLoanPage.createLoan(LOAN_AMOUNT, INTEREST_RATE);
		Assert.assertTrue(selenium.isTextPresent("The loan has been created"));
	}

	public void createInvalidLoanTest() {
		double LOAN_AMOUNT = 1200;
		double INVALID_INTEREST_RATE = -5;
        
        ensureLoanProductExists();
        
		HomePage homePage = loginPage.loginAs("mifos", "testmifos");
		CreateLoanPage createLoanPage = homePage.navigateToCreateLoanPage();
		Assert.assertTrue(selenium.isTextPresent("Create a new loan"));
		createLoanPage.createLoanExpectingError(LOAN_AMOUNT, INVALID_INTEREST_RATE);
		Assert.assertTrue(selenium.isTextPresent("Please enter an interest rate greater than or equal to the minimum of " +
				MIN_INTEREST_RATE + " allowed by loan product."));
	}

	private void ensureLoanProductExists() {
		DateTime now = new DateTime();
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

