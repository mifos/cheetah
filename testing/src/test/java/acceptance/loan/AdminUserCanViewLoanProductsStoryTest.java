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
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import framework.pageobjects.LoginPage;
import framework.pageobjects.ViewLoanProductDetailsPage;
import framework.pageobjects.ViewLoanProductsPage;
import framework.test.UiTestCaseBase;

/*
 * Corresponds to story 679 in mingle
 * http://mingle.mifos.org:7070/projects/cheetah/cards/679
 */
@ContextConfiguration(locations={"classpath:ui-test-context.xml"})
@Test(groups={"ViewLoanProductsStory","acceptance","ui"})
public class AdminUserCanViewLoanProductsStoryTest extends UiTestCaseBase {
	

	private LoginPage loginPage;

	@Autowired
	private DriverManagerDataSource dataSource;
    
    private static final DatabaseTestUtils dbTestUtils = new DatabaseTestUtils();
	    
    private static final String loanProductDataSetTwoProductsXml = 
    		"<dataset>\r\n" + 
    		"  <loanproducts id=\"1\" longName=\"long1\" maxInterestRate=\"2.0\" minInterestRate=\"1.0\" shortName=\"short1\" status=\"ACTIVE\" deletedStatus=\"VISIBLE\" />\r\n" + 
    		"  <loanproducts id=\"2\" longName=\"long2\" maxInterestRate=\"2.0\" minInterestRate=\"1.0\" shortName=\"short2\" status=\"ACTIVE\" deletedStatus=\"VISIBLE\" />\r\n" + 
    		"  <loans/>\r\n" + 
    		"</dataset>\r\n";

	    
    private static final String loanProductDataSetZeroProductsXml = 
    		"<dataset>\r\n" + 
    		"  <loanproducts/>\r\n" + 
    		"  <loans/>\r\n" + 
    		"</dataset>\r\n";

    @BeforeMethod
    public void setUp() throws Exception {
        super.setUp();
        loginPage = new LoginPage(selenium);
    }

    @AfterMethod
    public void logOut() {
        loginPage.logout();
    }

	public void viewTwoLoanProducts() throws Exception {
	    dbTestUtils.cleanAndInsertDataSet(loanProductDataSetTwoProductsXml, dataSource);
	    navigateToViewLoanProductsPage();
	    assertElementTextIncludes("short1", "short-name-1");
	    assertElementTextIncludes("short2", "short-name-2");
	}

	public void viewZeroLoanProducts() throws Exception {
	    dbTestUtils.cleanAndInsertDataSet(loanProductDataSetZeroProductsXml, dataSource);
	    navigateToViewLoanProductsPage();
	    Assert.assertTrue(selenium.isTextPresent("No loan products have been defined"));
	}

    public void canReachViewLoanProductDetailsPage () {
        navigateToViewLoanProductDetailsPage("short1");
        //UiTestUtils.sleep(20000);
        //assertTextFoundOnPage("viewLoanProduct.ftl", "Didn't reach viewLoanProduct.ftl");
        assertElementTextIncludes("short1", "shortName");
    }
    
    private ViewLoanProductDetailsPage navigateToViewLoanProductDetailsPage (String linkName){
        return
            loginPage
                .loginAs("mifos", "testmifos")
                .navigateToAdminPage()
                .navigateToViewLoanProductsPage()
                .navigateToViewLoanProductDetailsPage(linkName);
    }

	
    private ViewLoanProductsPage navigateToViewLoanProductsPage (){
        return
            loginPage
                .loginAs("mifos", "testmifos")
                .navigateToAdminPage()
                .navigateToViewLoanProductsPage();
    }

	@Test(enabled=false)
	public DriverManagerDataSource getDataSource() {
		return dataSource;
	}

	@Test(enabled=false)
	public void setDataSource(DriverManagerDataSource datasource) {
		this.dataSource = datasource;
	}
}
