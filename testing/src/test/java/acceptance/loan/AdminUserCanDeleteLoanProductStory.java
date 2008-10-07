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

import framework.pageobjects.DeleteLoanProductPage;
import framework.pageobjects.EditLoanProductPage;
import framework.pageobjects.LoginPage;
import framework.test.UiTestCaseBase;

/*
 * Corresponds to story 681 in Mingle
 * {@link http://mingle.mifos.org:7070/projects/cheetah/cards/681 }
 */
@ContextConfiguration(locations={"classpath:ui-test-context.xml"})
@Test(groups={"ModifyLoanProductsStory","acceptance","ui", "workInProgress"})
public class AdminUserCanDeleteLoanProductStory extends UiTestCaseBase {

    private LoginPage loginPage;

    @Autowired
    private DriverManagerDataSource dataSource;
        
    private static final DatabaseTestUtils dbTestUtils = new DatabaseTestUtils();
    
    private static final String loanProductDataSetXml = 
            "<dataset>\r\n" + 
            "  <loanproducts id=\"1\" longName=\"long1\" maxInterestRate=\"2.0\" minInterestRate=\"1.0\" shortName=\"short1\" status=\"0\"/>\r\n" + 
            "  <loans/>\r\n" + 
            "</dataset>\r\n";

    @BeforeMethod
    public void setUp() throws Exception {
        super.setUp();
        loginPage = new LoginPage(selenium);
        dbTestUtils.cleanAndInsertDataSet(loanProductDataSetXml, dataSource); 
    }

    @AfterMethod
    public void logOut() {
        loginPage.logout();
    }            

    public void testDeleteLoanProduct () throws Exception{
        (new DatabaseTestUtils()).cleanAndInsertDataSet(loanProductDataSetXml, dataSource);
        DeleteLoanProductPage editPage = navigateToEditLoanProductDetailsPage("short1");
        editPage.deleteLoanProduct("short2");      
        assertTextFoundOnPage("Loan product \"long2\" was successfully deleted.", "Didn't get to loanProductDeleteSuccess.ftl");
    }
    
    private DeleteLoanProductPage navigateToEditLoanProductDetailsPage (String linkName){
        return
            loginPage
                .loginAs("mifos", "testmifos")
                .navigateToAdminPage()
                .navigateToViewLoanProductsPage()
                .navigateToViewLoanProductDetailsPage(linkName)
                .navigateToDeleteLoanProductPage();
    }

    

}
