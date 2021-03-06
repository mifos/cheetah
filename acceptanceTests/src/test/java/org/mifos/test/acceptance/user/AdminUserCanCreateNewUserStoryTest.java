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

package org.mifos.test.acceptance.user;

import java.io.IOException;
import java.sql.SQLException;

import org.dbunit.DatabaseUnitException;
import org.dbunit.dataset.DataSetException;
import org.mifos.test.acceptance.framework.CreateUserPage;
import org.mifos.test.acceptance.framework.CreateUserSuccessPage;
import org.mifos.test.acceptance.framework.LoginPage;
import org.mifos.test.acceptance.framework.UiTestCaseBase;
import org.mifos.test.framework.util.DatabaseTestUtils;
import org.mifos.test.framework.util.SimpleDataSet;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.providers.encoding.Md5PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


/**
 * Corresponds to story 662 in mingle
 * http://mingle.mifos.org:7070/projects/cheetah/cards/678
 */
@ContextConfiguration(locations={"classpath:ui-test-context.xml"})
@Test(groups={"createUserStory","acceptance","ui"}, sequential=true)
public class AdminUserCanCreateNewUserStoryTest extends UiTestCaseBase {
    
    private LoginPage loginPage;
    private String savedUsers;
    private Md5PasswordEncoder passwordEncoder;
    private DatabaseTestUtils dbUtils;
    
    private static final String TEST_ADMIN_USER_ID          = "adminuser";
    private static final String TEST_ADMIN_USER_PASSWORD    = "adminpassword";
    private static final String TEST_USER_ID                = "testuser";
    private static final String TEST_USER_PASSWORD          = "userpassword";
    private static final String NEW_USER_ID                 = "newUser";
    private static final String NEW_USER_PASSWORD           = "newpassword";
    private static final String NEW_USER_PASSWORD_DIFFERENT = "newpassworddifferent";
    private static final String NEW_ADMIN_USER_ID           = "newAdminUser";
    private static final String NEW_ADMIN_USER_PASSWORD     = "newAdminPassword";
    private static final String USER_ID_20_CHARS            = "12345678901234567890";
    private static final String USER_ID_21_CHARS            = USER_ID_20_CHARS + "1";
    private static final String ROLE_ADMIN                  = "ROLE_ADMIN";
    private static final String ROLE_USER                   = "ROLE_USER";
    private static final String USER_TABLE_NAME             = "users";
    private static final String AUTHORITIES_TABLE_NAME      = "authorities";
    private static final String ADMIN_TAB_ELEMENT_ID        = "header.tab.admin";

    @BeforeClass
    public void initialize() {
        passwordEncoder = new Md5PasswordEncoder();
        dbUtils = new DatabaseTestUtils();
    }
    @BeforeMethod
    @SuppressWarnings("PMD.SignatureDeclareThrowsException") 
    //rationale: This is the signature of the superclass's method that we're overriding
    public void setUp() throws Exception {
        super.setUp();
        loginPage = new LoginPage(selenium);
        savedUsers = saveUserTables(dataSource, USER_TABLE_NAME, AUTHORITIES_TABLE_NAME);
        insertTestUsers();
    }

    @AfterMethod
    public void logOut() 
                    throws DataSetException, IOException, SQLException, DatabaseUnitException {
         loginPage.logout();
        restoreUserTables(savedUsers, dataSource);
    }
    
/*--------------------
 * Acceptance tests
 * -------------------
*/

    public void adminUserCanNavigateToCreateUserPageTest() {
        loginAndNavigateToCreateUserPage(TEST_ADMIN_USER_ID, TEST_ADMIN_USER_PASSWORD);
        Assert.assertTrue(selenium.isElementPresent("user.create.heading"), 
                                                    "Did not reach create user page");
    }

    //Default role should be user
    public void createValidUserWithDefaultRole() {
        
        createUserExpectSuccess(NEW_USER_ID, NEW_USER_PASSWORD, NEW_USER_PASSWORD);     
        assertCreateSuccessful(NEW_USER_ID);
        loginPage.logout();
        
        //New user should be able to log in and see the home page (because they have user prvileges
        //but not see admin functions
        loginPage.loginAs(NEW_USER_ID, NEW_USER_PASSWORD);
        assertElementExistsOnPage("homePageContent", "New user failed to reach home page");
        assertElementDoesNotExistOnPage(ADMIN_TAB_ELEMENT_ID, 
                                        "Admin tab should be absent for new user without admin role");
    }

    public void createValidUserWithAdminRole() {
        
        //note admin user must have both ROLE_ADMIN and ROLE_USER roles to be able to access Mifos
        createUserExpectSuccess(
                NEW_ADMIN_USER_ID, NEW_ADMIN_USER_PASSWORD, 
                NEW_ADMIN_USER_PASSWORD, ROLE_ADMIN, ROLE_USER);
        assertCreateSuccessful(NEW_ADMIN_USER_ID);
        loginPage.logout();
        
        //New user should be able to log in and see the home page (because they have user privileges
        //but not see admin functions
        loginPage.loginAs(NEW_ADMIN_USER_ID, NEW_ADMIN_USER_PASSWORD);
        assertElementExistsOnPage("homePageContent", "New user failed to reach home page");
        assertElementExistsOnPage(ADMIN_TAB_ELEMENT_ID, 
                                        "Admin tab should be present for new user with admin role. ");
    }

    public void createUserFailsWithBlankUserId() {
        createUserExpectFailure("", NEW_USER_PASSWORD, NEW_USER_PASSWORD);
        assertErrorTextIncludes("Please enter a user id");
    }
    
    public void createUserFailsWithNoRoles() {
        createUserExpectFailure(NEW_USER_ID, NEW_USER_PASSWORD, NEW_USER_PASSWORD, "");
        assertErrorTextIncludes("User must be assigned at least one security role");
    }
    
    public void createUserSucceedsWithMaxLengthUserId() {
        createUserExpectSuccess(USER_ID_20_CHARS, NEW_USER_PASSWORD, NEW_USER_PASSWORD);
        assertCreateSuccessful(USER_ID_20_CHARS);
    }
    
    public void createUserFailsWithTooLongUserId() {
        createUserExpectFailure(USER_ID_21_CHARS, NEW_USER_PASSWORD, NEW_USER_PASSWORD);
        assertErrorTextIncludes("User id must be at most");
    }
    
    public void createUserFailsWithBlankPassword() {
        createUserExpectFailure(NEW_USER_ID, "", "", ROLE_USER);
        assertErrorTextIncludes("Please enter a password");
    }
    
    public void createDuplicateUserFails() {
        createUserExpectSuccess(NEW_USER_ID, NEW_USER_PASSWORD, NEW_USER_PASSWORD);
        loginPage.logout();
        
        createUserExpectFailure(NEW_USER_ID, NEW_USER_PASSWORD, NEW_USER_PASSWORD);
        
        assertElementExistsOnPage("user.create.heading", "Did not return to create user page");
        assertErrorTextIncludes("User id already exists");
    }
    
    public void userFailsToConfirmPassword () {
        createUserExpectFailure(NEW_USER_ID, NEW_USER_PASSWORD, NEW_USER_PASSWORD_DIFFERENT);
        assertErrorTextIncludes("Passwords did not match");

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
    
    private CreateUserSuccessPage createUserExpectSuccess (
            String userName, String password, String confirmPassword, String...roles) {
        return loginAndNavigateToCreateUserPage(TEST_ADMIN_USER_ID, TEST_ADMIN_USER_PASSWORD)
                    .createUserExpectSuccess(userName, password, confirmPassword, roles);
    }
    
    private CreateUserPage createUserExpectFailure (
            String userName, String password, String confirmPassword, String...roles) {
        return loginAndNavigateToCreateUserPage(TEST_ADMIN_USER_ID, TEST_ADMIN_USER_PASSWORD)
                    .createUserExpectFailure(userName, password, confirmPassword, roles);
    }
    
    private void assertCreateSuccessful(String userId) {
        assertElementExistsOnPage("user.create.success.heading", 
                                  "Did not reach create user success page");
        assertTextFoundOnPage(userId, "Did not find user id on create user success page");
    }
    
    private void addUserToDataSet (SimpleDataSet dataSet, String userId, String password, String...roles) {
        dataSet.row("users", "username=" + userId, 
                             "password=" + passwordEncoder.encodePassword(password, null), 
                             "enabled=1");
        for (String role : roles) {
            dataSet.row("authorities", "username=" + userId, "authority=" + role);
        }

    }
    
    private String saveUserTables(DriverManagerDataSource dataSource, String...tableNames) 
                    throws IOException, DataSetException, SQLException, DatabaseUnitException {
        return dbUtils.saveTables(dataSource, tableNames);
   }

    private void restoreUserTables (String savedXmlDataSet, DriverManagerDataSource dataSource) 
                    throws IOException, DataSetException, SQLException, DatabaseUnitException{
        dbUtils.cleanAndInsertDataSet(savedXmlDataSet, dataSource);
    }

    private void insertTestUsers() throws DataSetException, IOException, SQLException, DatabaseUnitException {
        SimpleDataSet testUsers = new SimpleDataSet();
        addUserToDataSet(testUsers, TEST_ADMIN_USER_ID, TEST_ADMIN_USER_PASSWORD, ROLE_ADMIN, ROLE_USER);
        addUserToDataSet(testUsers, TEST_USER_ID, TEST_USER_PASSWORD, ROLE_USER);
        testUsers.insert(dataSource);
    }
}
