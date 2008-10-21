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

import java.io.IOException;
import java.sql.SQLException;

import org.dbunit.DatabaseUnitException;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.mifos.test.framework.util.DatabaseTestUtils;
import org.mifos.test.framework.util.SimpleDataSet;
import org.springframework.security.providers.encoding.Md5PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
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
    
    private DatabaseTestUtils dbUtils;
    private LoginPage loginPage;
    private IDataSet savedUsers;
    private Md5PasswordEncoder passwordEncoder;
    
    private static final String TEST_ADMIN_USER_ID     = "adminuser";
    private static final String TEST_ADMIN_USER_PASSWORD = "adminpassword";
    private static final String TEST_USER_ID = "testuser";
    private static final String TEST_USER_PASSWORD = "userpassword";
    private static final String NEW_USER_ID       = "newUser";
    private static final String NEW_USER_PASSWORD   = "newpassword";
    private static final String USER_ID_80_CHARS  = 
        "TencharachTencharachTencharachTencharachTencharachTencharachTencharachTencharach";
    private static final String USER_ID_81_CHARS     = USER_ID_80_CHARS + "1";
    private static final String ROLE_ADMIN = "ROLE_ADMIN";
    private static final String ROLE_USER = "ROLE_USER";
    private static final String USER_TABLE_NAME = "users";
    private static final String AUTHORITIES_TABLE_NAME = "authorities";

    @BeforeClass
    public void initialize() {
        dbUtils = new DatabaseTestUtils();
        passwordEncoder = new Md5PasswordEncoder();
    }
    @BeforeMethod
    public void setUp() throws Exception {
        super.setUp();
        loginPage = new LoginPage(selenium);
        savedUsers = dbUtils.saveTables(dataSource, USER_TABLE_NAME, AUTHORITIES_TABLE_NAME);
        SimpleDataSet testUsers = new SimpleDataSet();
        addUser(testUsers, TEST_ADMIN_USER_ID, TEST_ADMIN_USER_PASSWORD, ROLE_ADMIN, ROLE_USER);
        addUser(testUsers, TEST_USER_ID, TEST_USER_PASSWORD, ROLE_USER);
    }

    @AfterMethod
    public void logOut() 
                    throws DataSetException, IOException, SQLException, DatabaseUnitException {
        loginPage.logout();
        dbUtils.restoreDataSet(savedUsers, dataSource);
    }
    
/*--------------------
 * Acceptance tests
 * -------------------
*/

    public void adminUserCanNavigateToCreateUserPageTest() {
        loginAndNavigateToCreateUserPage(TEST_ADMIN_USER_ID, TEST_ADMIN_USER_PASSWORD);
        Assert.assertTrue(selenium.isElementPresent("create-user-heading"), 
                                                    "Did not reach create user page");
    }

    public void adminUserCanCreateValidUser() {
        
        //create new user, check for success and that default user role displays
        createUserExpectSuccess(NEW_USER_ID, NEW_USER_PASSWORD);
        
        assertCreateSuccessful();
        
        loginPage.logout();
        
        //New user should be able to log in and see the home page (because they have user prvileges
        //but not see admin functions
        loginPage.loginAs(NEW_USER_ID, NEW_USER_PASSWORD);
        
        assertElementExistsOnPage("home-heading", "newUser failed to login successfully");
        assertElementDoesNotExistOnPage("adminTab", 
                                        "Admin tab should be absent for new user without admin role");
    }

    public void createUserFailsWithBlankUserId() {
        createUserExpectFailure("", NEW_USER_PASSWORD);
        assertErrorTextIncludes("Please enter a user name");
    }
    
    public void createUserSucceedsWithMaxLengthUserId() {
        createUserExpectSuccess(USER_ID_80_CHARS, NEW_USER_PASSWORD);
        assertCreateSuccessful();
    }
    
    public void createUserFailsWithTooLongUserId() {
        createUserExpectFailure(USER_ID_81_CHARS, NEW_USER_PASSWORD);
        assertErrorTextIncludes("User name must be at most");
    }
    
    public void createUserFailsWithBlankPassword() {
        createUserExpectFailure(NEW_USER_ID, "");
        assertErrorTextIncludes("Please enter a password");
    }
    
    public void createDuplicateUserFails() {
        createUserExpectSuccess(NEW_USER_ID, NEW_USER_PASSWORD);
        loginPage.logout();
        
        createUserExpectFailure(NEW_USER_ID, NEW_USER_PASSWORD);
        
        assertElementExistsOnPage("create-user-heading", "Did not return to create user page");
        assertErrorTextIncludes("User " + NEW_USER_ID + " already exists");
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
        return loginAndNavigateToCreateUserPage(TEST_ADMIN_USER_ID, TEST_ADMIN_USER_PASSWORD)
                    .createUserExpectSuccess(userName, password);
    }
    
    private CreateUserPage createUserExpectFailure (String userName, String password) {
        return loginAndNavigateToCreateUserPage(TEST_ADMIN_USER_ID, TEST_ADMIN_USER_PASSWORD)
                    .createUserExpectFailure(userName, password);
    }
    
    private void assertCreateSuccessful() {
        assertElementExistsOnPage("create-user-success-heading", 
                                  "Did not reach create user success page");
        assertElementExistsOnPage("user-name", 
                                  "New user's name does not appear on create user success page");
        assertElementTextExactMatch("ROLE_USER", "user-roles");
    }
    
    private void addUser (SimpleDataSet dataSet, String userId, String password, String...roles) {
        dataSet.row("users", "username=" + userId, 
                             "password=" + passwordEncoder.encodePassword(password, null), 
                             "enabled=1");
        for (String role : roles) {
            dataSet.row("authorities", "username=" + userId, "authority=" + role);
        }

    }
}
