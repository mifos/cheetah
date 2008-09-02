/**
 * Copyright (c) 2005-2008 Grameen Foundation USA
 * 1029 Vermont Avenue, NW, Suite 400, Washington DC 20005
 * All rights reserved.
 *
 * Apache License
 * Copyright (c) 2005-2008 Grameen Foundation USA
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you
 * may not use this file except in compliance with the License. You
 * may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.  See the License for the specific language governing
 * permissions and limitations under the License.
 *
 * See also http://www.apache.org/licenses/LICENSE-2.0.html for an
 * explanation of the license and how it is applied.
 *
 */      

package acceptance.user;

import static org.testng.Assert.assertEquals;

import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import framework.pageobjects.LoginPage;
import framework.test.UiTestCaseBase;

/*
 * Corresponds to story 661 in mingle
 * http://mingle.mifos.org:7070/projects/cheetah/cards/661
 */
@ContextConfiguration(locations={"classpath:ui-test-context.xml"})
@Test(sequential=true, groups={"userLoginStory","acceptance","ui"})
public class DefaultAdminUserCanLoginStoryTest extends UiTestCaseBase {

	private LoginPage loginPage;
	
	@BeforeMethod
	public void setUp() throws Exception {
		super.setUp();
		loginPage = new LoginPage(selenium);
	}

	@AfterMethod
	public void logOut() throws Exception {
		loginPage.logout();
	}
	
	// Selenium doesn't shut down cleanly without running at least one test
	public void dummy() {
		
	}

	@Test(groups="workInProgress")
	public void userLoginSuccessTest() throws Exception {
		loginPage.loginAs("mifos", "testmifos");
		assertEquals(selenium.getText("hello.heading"), "Welcome to Mifos");
		loginPage.logout();
	}

	@Test(groups="workInProgress")
	public void userLoginFailureTest() throws Exception {
		loginPage.loginAs("mifos", "mifos3");
		assertEquals(selenium.getText("login.errorcaption"), "Login error:");
		assertEquals(selenium.getText("login.errormessage"), "Your username or password is incorrect");
		loginPage.logout();
	}

}

