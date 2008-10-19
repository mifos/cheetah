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

import org.dbunit.DatabaseUnitException;
import org.dbunit.dataset.DataSetException;
import org.mifos.test.framework.util.DatabaseTestUtils;
import org.mifos.test.framework.util.SimpleDataSet;
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
    
    @BeforeMethod
    public void setUp() throws Exception {
        super.setUp();
        loginPage = new LoginPage(selenium);
    }

    @AfterMethod
    public void logOut() {
        loginPage.logout();
    }

	public void viewTwoLoanProducts()  throws DataSetException, IOException, SQLException, DatabaseUnitException  {
	    insertLoanProductTwoProductsDataSet();
	    navigateToViewLoanProductsPage();
	    assertElementTextIncludes("lp1", "short-name-1");
	    assertElementTextIncludes("lp2", "short-name-2");
	}

	public void viewZeroLoanProducts()  throws DataSetException, IOException, SQLException, DatabaseUnitException {
	    insertLoanProductZeroProductsDataSet();
	    navigateToViewLoanProductsPage();
	    Assert.assertTrue(selenium.isTextPresent("No loan products have been defined"));
	}

    public void testCanReachViewLoanProductDetailsPage () throws DataSetException, IOException, SQLException, DatabaseUnitException {
        insertLoanProductTwoProductsDataSet();
        navigateToViewLoanProductDetailsPage("lp1");
        assertElementTextIncludes("lp1", "shortName");
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

    private void insertLoanProductTwoProductsDataSet() throws DataSetException, IOException, SQLException, DatabaseUnitException {
        SimpleDataSet simpleDataSet = new SimpleDataSet();
        simpleDataSet.row("loanProducts", "id=1", "longName=long1",  "maxInterestRate=2.0", "minInterestRate=1.0", "shortName=lp1", "status=ACTIVE", "deletedStatus=VISIBLE"); 
        simpleDataSet.row("loanProducts", "id=2", "longName=long2",  "maxInterestRate=2.0", "minInterestRate=1.0", "shortName=lp2", "status=ACTIVE", "deletedStatus=VISIBLE"); 
        simpleDataSet.row("loans");
        simpleDataSet.insert(this.dataSource);
    }
        
    private void insertLoanProductZeroProductsDataSet() throws DataSetException, IOException, SQLException, DatabaseUnitException {
        SimpleDataSet simpleDataSet = new SimpleDataSet();
        simpleDataSet.row("loanProducts");
        simpleDataSet.row("loans");
        simpleDataSet.insert(this.dataSource);
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
