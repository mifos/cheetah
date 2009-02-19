/*
 * Copyright (c) 2005-2009 Grameen Foundation USA
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
package org.mifos.test.acceptance.security;

import org.mifos.test.acceptance.framework.HomePage;
import org.mifos.test.acceptance.framework.LoginPage;
import org.mifos.test.acceptance.framework.UiTestCaseBase;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


@ContextConfiguration(locations={"classpath:ui-test-context.xml"})
@Test(sequential=true, groups={"accessControlStory","acceptance","ui"})
public class AccessControlTest extends UiTestCaseBase {

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

    public void adminUserCanAccessAdminPage () {
         HomePage home = loginPage.loginAs("admin", "password");
         home.navigateToAdminPage();
         Assert.assertTrue(selenium.isTextPresent("Administrative Tasks"), "Didn't reach admin home page");
    }
    
    public void nonAdminUserCannotSeeAdminTab() {
        loginPage.loginAs("user1", "password");
        Assert.assertFalse(selenium.isElementPresent("id=header.tab.admin"), "Admin tab should have been absent");
    }
    
    public void adminUserCanOpenAdminPage() {
         userOpensUri("admin", "password", "adminHome.ftl");
         assertTextFoundOnPage("Administrative Tasks", "Admin user could not link to admin page");
    }
    
    public void nonAdminUserCannotOpen() {
        String[] invalidUris = {
                "viewLoanProducts.ftl?id=1",
               "adminHome.ftl", 
                "viewLoanProducts.ftl",
                "viewLoanproduct.ftl?id=1",
                "createLoanProduct.ftl",
                "updateLoanproduct.ftl?id=1"
                };
         assertNonAdminUserCannotOpen(invalidUris);
    }
    
    private void assertNonAdminUserCannotOpen (String[]uris) {
        for (String uri : uris) {
            userOpensUri("user1", "password", uri);
            assertTextFoundOnPage("Access Denied", "Accessed unauthorized uri \"" + uri + "\"");
            loginPage.logout();
        }
    }
    
    private void userOpensUri (String user, String password, String uri) {
        loginPage
        .loginAs(user, password)
        .openUri(uri);
    }
}
