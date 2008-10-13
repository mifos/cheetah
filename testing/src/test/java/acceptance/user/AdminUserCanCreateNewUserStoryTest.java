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

package acceptance.user;

import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import framework.pageobjects.CreateUserPage;
import framework.pageobjects.CreateUserSuccessPage;
import framework.pageobjects.LoginPage;
import framework.test.UiTestCaseBase;

/**
 * Corresponds to story 662 in mingle
 * http://mingle.mifos.org:7070/projects/cheetah/cards/678
 */
@ContextConfiguration(locations={"classpath:ui-test-context.xml"})
@Test(groups={"createUserStory","acceptance","ui", "workInProgress"})
public class AdminUserCanCreateNewUserStoryTest extends UiTestCaseBase {
    
    private LoginPage loginPage;
    
    private static final String ADMIN_USER_NAME     = "admin";
    private static final String ADMIN_USER_PASSWORD = "password";
    private static final String NEW_USER_NAME       = "newUser";
    private static final String NEW_USER_PASSWORD   = "tempPassword";
    private static final String USER_NAME_80_CHARS  = 
        "TencharachTencharachTencharachTencharachTencharachTencharachTencharachTencharach";
    private static final String UserName81Chars     = USER_NAME_80_CHARS + "1";

    @BeforeMethod
    public void setUp() throws Exception {
        super.setUp();
        loginPage = new LoginPage(selenium);
    }

    @AfterMethod
    public void logOut() {
        loginPage.logout();
    }
    
/*--------------------
 * Acceptance tests
 * -------------------
*/

    public void canNavigateToCreateUserPageTest() {
        loginAndNavigateToCreateUserPage(ADMIN_USER_NAME, ADMIN_USER_PASSWORD);
        Assert.assertTrue(selenium.isElementPresent("create-user-heading"), 
                                                    "Did not reach create user page");
    }

    public void createValidUserTest() {
        
        //create new user, check for success and that default user role displays
        createUserExpectSuccess(NEW_USER_NAME, NEW_USER_PASSWORD);
        
        assertCreateSuccessful();
        
        loginPage.logout();
        
        //try new user logging in and that they can't see admin functions
        loginPage.loginAs(NEW_USER_NAME, NEW_USER_PASSWORD);
        
        assertElementExistsOnPage("home-heading", "newUser failed to login successfully");
        assertElementDoesNotExistOnPage("adminTab", 
                                        "Admin tab should be absent for new user without admin role");
    }

    public void createUserBlankUserNameTest() {
        createUserExpectFailure("", NEW_USER_PASSWORD);
        assertErrorTextIncludes("Please enter a user name");
    }
    
    public void createUserMaxLengthUserNameTest() {
        createUserExpectSuccess(USER_NAME_80_CHARS, 
                                NEW_USER_PASSWORD);
        assertCreateSuccessful();
    }
    
    public void createUserTooLongUserNameTest() {
        createUserExpectSuccess(UserName81Chars, 
                                NEW_USER_PASSWORD);
        assertErrorTextIncludes("User name must be at most");
    }
    
    public void createUserBlankPasswordTest() {
        createUserExpectFailure(NEW_USER_NAME, "");
        assertErrorTextIncludes("Please enter a password");
    }
    
    public void createDuplicateUserTest() {
        createUserExpectSuccess(NEW_USER_NAME, NEW_USER_PASSWORD);
        loginPage.logout();
        
        createUserExpectFailure(NEW_USER_NAME, NEW_USER_PASSWORD);
        
        assertElementExistsOnPage("create-user-heading", "Did not return to create user page");
        assertErrorTextIncludes("User newUser already exists");
    }
    
    /*--------------------
     * Helper functions
     * -------------------
    */
    
    private CreateUserPage loginAndNavigateToCreateUserPage (String userName, String password) {
        return loginPage
                .loginAs(userName, password)
                .navigateToAdminPage()
                .navigateToCreateUserPage();
    }
    
    private CreateUserSuccessPage createUserExpectSuccess (String userName, String password) {
        return loginAndNavigateToCreateUserPage(ADMIN_USER_NAME, ADMIN_USER_PASSWORD)
                    .createUserExpectSuccess(userName, password);
    }
    
    private CreateUserPage createUserExpectFailure (String userName, String password) {
        return loginAndNavigateToCreateUserPage(ADMIN_USER_NAME, ADMIN_USER_PASSWORD)
                    .createUserExpectFailure(userName, password);
    }
    
    private void assertCreateSuccessful() {
        assertElementExistsOnPage("create-user-success-heading", 
                                  "Did not reach create user success page");
        assertElementExistsOnPage("user-name", 
                                  "New user's name does not appear on create user success page");
        assertElementTextExactMatch("ROLE_USER", "user-roles");
    }
}
