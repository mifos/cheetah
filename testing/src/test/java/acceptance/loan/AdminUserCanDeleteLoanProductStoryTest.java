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
import java.sql.Connection;
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

import framework.pageobjects.DeleteLoanProductFailurePage;
import framework.pageobjects.DeleteLoanProductPage;
import framework.pageobjects.DeleteLoanProductSuccessPage;
import framework.pageobjects.LoginPage;
import framework.pageobjects.ViewLoanProductDetailsPage;
import framework.test.UiTestCaseBase;

/*
 * Corresponds to story 681 in Mingle
 * {@link http://mingle.mifos.org:7070/projects/cheetah/cards/681 }
 */
@ContextConfiguration(locations={"classpath:ui-test-context.xml"})
@Test(groups={"DeleteLoanProductStory","acceptance","ui"})
public class AdminUserCanDeleteLoanProductStoryTest extends UiTestCaseBase {

    private LoginPage loginPage;

    @Autowired
    private DriverManagerDataSource dataSource;
        
    private static final DatabaseTestUtils databaseTestUtils = new DatabaseTestUtils();

    @BeforeMethod
    @SuppressWarnings("PMD.SignatureDeclareThrowsException") // signature of superclass method
    public void setUp() throws Exception {
        super.setUp();
        loginPage = new LoginPage(selenium);
    }

    @AfterMethod
    public void logOut() {
        loginPage.logout();
    }            

    @SuppressWarnings("PMD.SignatureDeclareThrowsException") // one of the dependent methods throws Exception
    public void testDeleteLoanProductWithoutLoans() throws Exception {
        insertLoanProductWithNoLoansDataSet();
        DeleteLoanProductPage deleteLoanProductPage = deleteLoanProduct();
        DeleteLoanProductSuccessPage deleteLoanProductSuccessPage = (DeleteLoanProductSuccessPage) deleteLoanProductPage.deleteLoanProduct();
        deleteLoanProductSuccessPage.verifyPage();
        deleteLoanProductSuccessPage.verifyMessage("long1");
        verifyDeletedStatus();
    }
    
    public void testDeleteLoanProductWithLoans() throws DataSetException, IOException, SQLException, DatabaseUnitException {
        insertLoanProductWithLoansDataSet();
        DeleteLoanProductPage deleteLoanProductPage = deleteLoanProduct();
        DeleteLoanProductFailurePage deleteLoanProductFailurePage = (DeleteLoanProductFailurePage) deleteLoanProductPage.deleteLoanProduct();
        deleteLoanProductFailurePage.verifyPage();
        deleteLoanProductFailurePage.verifyMessage("long1");
    }

    private DeleteLoanProductPage deleteLoanProduct() {
        ViewLoanProductDetailsPage viewLoanProductDetailsPage = navigateToViewLoanProductDetailsPage("short1");
        DeleteLoanProductPage deleteLoanProductPage = viewLoanProductDetailsPage.navigateToDeleteLoanProductPage();
        deleteLoanProductPage.verifyPage();
        return deleteLoanProductPage;
    }
    
    private ViewLoanProductDetailsPage navigateToViewLoanProductDetailsPage (String linkName){
        return
            loginPage
                .loginAs("mifos", "testmifos")
                .navigateToAdminPage()
                .navigateToViewLoanProductsPage()
                .navigateToViewLoanProductDetailsPage(linkName);
    }
    
    @SuppressWarnings("PMD.SignatureDeclareThrowsException") // one of the dependent methods throws Exception
    private void verifyDeletedStatus() throws Exception {
        Connection jdbcConnection = null;
        SimpleDataSet simpleDataSet = new SimpleDataSet();
        simpleDataSet.row("loanProducts", "id=1", "longName=long1",  "maxInterestRate=2.0", "minInterestRate=1.0", "shortName=short1", "status=ACTIVE", "deletedStatus=DELETED"); 
        String deletedLoanProductDataSetXml = simpleDataSet.toString();
        databaseTestUtils.verifyTable(deletedLoanProductDataSetXml, "loanProducts", this.dataSource);        
    }    

    private void insertLoanProductWithNoLoansDataSet() throws DataSetException, IOException, SQLException, DatabaseUnitException {
        SimpleDataSet simpleDataSet = new SimpleDataSet();
        simpleDataSet.row("loanProducts", "id=1", "longName=long1",  "maxInterestRate=2.0", "minInterestRate=1.0", "shortName=short1", "status=ACTIVE", "deletedStatus=VISIBLE"); 
        simpleDataSet.row("loans");
        simpleDataSet.insert(this.dataSource);
    }
    
    private void insertLoanProductWithLoansDataSet() throws DataSetException, IOException, SQLException, DatabaseUnitException {
        SimpleDataSet simpleDataSet = new SimpleDataSet();
        simpleDataSet.row("loanProducts", "id=1", "longName=long1",  "maxInterestRate=2.0", "minInterestRate=1.0", "shortName=short1", "status=ACTIVE", "deletedStatus=VISIBLE"); 
        simpleDataSet.row("loans", "id=1", "amount=10.1", "clientId=1", "interestRate=10", "loanProductId=1", "disbursalDate=2007-01-01");
        simpleDataSet.insert(this.dataSource);
    }
    
}
