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

package acceptance.product;

import static org.testng.Assert.assertEquals;


import framework.pageobjects.LoginPage;
import framework.test.UiTestCaseBase;

import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@ContextConfiguration(locations= {"classpath:ui-test-context.xml"})
@Test(sequential=true, groups = {"userLoginStory","acceptance","ui"})
public class IncreasePriceStoryTest extends UiTestCaseBase {
	private static final int goodPercent = 10;
	private static final int badPercent = 51;
	
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

	public void increasePriceSuccessTest() {
		loginPage.loginAs("admin", "password")
			.navigateToIncreasePricesPage()
			.increasePricesBy(goodPercent);
		assertEquals(selenium.getText("hello.heading"), "Welcome to Mifos");
	}

	public void increasePriceFailureTest() {
		loginPage.loginAs("admin", "password")
			.navigateToIncreasePricesPage()
			.increasePricesBy(badPercent);
		assertEquals(selenium.getText("priceincrease.errors"), "Don't be greedy - you can't raise prices by more than 50%!");
	}
}
