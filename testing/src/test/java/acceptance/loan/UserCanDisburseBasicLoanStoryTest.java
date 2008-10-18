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

import org.mifos.test.framework.util.DatabaseTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import framework.pageobjects.ClientDetailPage;
import framework.pageobjects.ClientSearchResultsPage;
import framework.pageobjects.DisburseLoanPage;
import framework.pageobjects.HomePage;
import framework.pageobjects.LoanDetailPage;
import framework.pageobjects.LoginPage;
import framework.test.UiTestCaseBase;

/*
 * Corresponds to story 675 in mingle
 * http://mingle.mifos.org:7070/projects/cheetah/cards/675
 */
@ContextConfiguration(locations={"classpath:ui-test-context.xml"})
@Test(groups={"userCanDisburseBasicLoanStory","acceptance","ui"})
public class UserCanDisburseBasicLoanStoryTest extends UiTestCaseBase {

    @Autowired
    private DriverManagerDataSource dataSource;
        
    private static final DatabaseTestUtils dbTestUtils = new DatabaseTestUtils();
    
    private static final String dataSetXml = 
            "<dataset>\r\n" + 
            "  <loanproducts id=\"1\" longName=\"loanProd1\" maxInterestRate=\"10.0\" minInterestRate=\"1.0\" shortName=\"short1\" status=\"ACTIVE\"/>\r\n" + 
            "  <clients id=\"1\" firstName=\"Sue\" lastName=\"Smith\" dateOfBirth=\"2000-12-30\"/>\r\n" + 
            "  <loans id=\"1\" clientId=\"1\" loanProductId=\"1\" amount=\"100\" interestRate=\"5\"/>\r\n" + 
            "</dataset>\r\n";

    
	private LoginPage loginPage;
    private static final String CLIENT_FIRST_NAME = "Sue";
    private static final String CLIENT_FULL_NAME = "Sue Smith";
	private static final String LOAN_NAME = "loanProd1";
	
	@BeforeMethod
	public void setUp() throws Exception {
		super.setUp();
		loginPage = new LoginPage(selenium);
        dbTestUtils.cleanAndInsertDataSet(dataSetXml, dataSource); 
	}

	@AfterMethod
	public void logOut() {
		loginPage.logout();
	}

	public void disburseBasicLoanTest() {
		// preconditions: a loan created for a loan product and a client name "Sue Smith" must exist
		HomePage homePage = loginPage.loginAs("mifos", "testmifos");
		ClientSearchResultsPage clientSearchResultsPage = homePage.searchForClient(CLIENT_FIRST_NAME);
		// verify that we landed on the client search list result with the client listed
		assertElementTextExactMatch(CLIENT_FULL_NAME, "client-name-1");
		
		ClientDetailPage clientDetailPage = clientSearchResultsPage.navigateToClientDetailPageForClient(1);
		LoanDetailPage loanDetailPage = clientDetailPage.navigateToLoanDetailPageForLoan(1);
		// verify that we landed on the loan detail page
        assertElementTextExactMatch(LOAN_NAME, "loanProductName");

		DisburseLoanPage disburseLoanPage = loanDetailPage.navigateToDisburseLoanPage();
        String dateInput = "12/1/2000";
        String dateOutput = "12/1/00";
		disburseLoanPage.disburseLoan(dateInput);
		assertElementTextExactMatch(dateOutput, "disbursalDate");
	}

}