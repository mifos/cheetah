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

package org.mifos.test.acceptance.group;

import java.io.IOException;
import java.sql.SQLException;

import org.dbunit.DatabaseUnitException;
import org.dbunit.dataset.DataSetException;
import org.mifos.test.acceptance.framework.LoginPage;
import org.mifos.test.acceptance.framework.UiTestCaseBase;
import org.mifos.test.acceptance.framework.ViewGroupsPage;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


/*
 * Corresponds to story 988 in mingle
 * http://mingle.mifos.org:7070/projects/cheetah/cards/988
 */
@ContextConfiguration(locations={"classpath:ui-test-context.xml"})
@Test(sequential=true, groups={"userCanCreateBasicClientStoryTest","acceptance","ui"})
public class UserCanViewListOfGroupsStoryTest extends UiTestCaseBase {

	private LoginPage loginPage;

    @SuppressWarnings("PMD.SignatureDeclareThrowsException") // one of the dependent methods throws Exception
	@BeforeMethod
	public void setUp() throws Exception {
		super.setUp();
		loginPage = new LoginPage(selenium);
	}

	@AfterMethod
	public void logOut() {
		loginPage.logout();
	}
	
    public void testCanReachViewLoanProductDetailsPage () throws DataSetException, IOException, SQLException, DatabaseUnitException {
        insertTwoGroupsDataSet();
        ViewGroupsPage viewGroupsPage = navigateToViewGroupsPage();
        viewGroupsPage.verifyPage();
    }
    
    private ViewGroupsPage navigateToViewGroupsPage (){
        return
            loginPage
                .loginAs("mifos", "testmifos")
                .navigateToViewGroupsPage();
    }
    
    private void insertTwoGroupsDataSet() {
        // do nothing
    }



}

