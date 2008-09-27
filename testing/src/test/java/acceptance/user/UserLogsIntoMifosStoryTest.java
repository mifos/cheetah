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

import framework.pageobjects.LoginPage;
import framework.test.UiTestCaseBase;

/*
 * Corresponds to story 660 in mingle
 * http://mingle.mifos.org:7070/projects/cheetah/cards/660
 * 
 * Note that other acceptance tests are already covered in 
 * {@link}DefaultAdminUserCanLoginStoryTest
 */
@ContextConfiguration(locations={"classpath:ui-test-context.xml"})
@Test(sequential=true, groups={"userLoginStory","acceptance","ui"})
public class UserLogsIntoMifosStoryTest extends UiTestCaseBase {

	private LoginPage loginPage;
	
	@BeforeMethod
	public void setUp() throws Exception {
		super.setUp();
		loginPage = new LoginPage(selenium);
	}

	@AfterMethod
	public void logOut() {
		loginPage.logout();
	}
	
	public void userLoginFailureBadUserNameTest() {
		loginPage.loginAs("zzz", "zzz");
		Assert.assertEquals(selenium.getText("login.errormessage"), "Your username or password is incorrect");
	}
	
}

