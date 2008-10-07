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

//import java.io.IOException;
import org.mifos.test.framework.util.DatabaseTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import framework.pageobjects.EditLoanProductPage;
import framework.pageobjects.LoginPage;
import framework.test.UiTestCaseBase;

/*
 * Corresponds to story 680 in Mingle
 * http://mingle.mifos.org:7070/projects/cheetah/cards/680
 */
@ContextConfiguration(locations={"classpath:ui-test-context.xml"})
@Test(groups={"ModifyLoanProductsStory","acceptance","ui"})
public class AdminUserCanModifyLoanProductStory extends UiTestCaseBase {

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

    
    public void canNavigateToEditLoanProductPage () throws Exception{
        (new DatabaseTestUtils()).cleanAndInsertDataSet(loanProductDataSetXml, dataSource);
        navigateToEditLoanProductDetailsPage("short1");
        assertTextFoundOnPage("Update loan product \"long1\"", "Didn't reach page editLoanProduct.ftl");
    }
    
    public void testModifyLoanProduct () throws Exception{
        (new DatabaseTestUtils()).cleanAndInsertDataSet(loanProductDataSetXml, dataSource);
        EditLoanProductPage editPage = navigateToEditLoanProductDetailsPage("short1");
        editPage.modifyLoanProduct("long2", "short2","9.0", "10.0", "INACTIVE");      
        assertTextFoundOnPage("Loan product \"long2\" was successfully updated.", "Didn't get to loanProductEditSuccess.ftl");
    }
    
    private EditLoanProductPage navigateToEditLoanProductDetailsPage (String linkName){
        return
            loginPage
                .loginAs("mifos", "testmifos")
                .navigateToAdminPage()
                .navigateToViewLoanProductsPage()
                .navigateToViewLoanProductDetailsPage(linkName)
                .navigateToEditLoanProductPage();
                
    }

    

}
