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

package framework.test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.DefaultSelenium;

import framework.pageobjects.LoginPage;
import framework.util.SeleniumTestUtils;
	
@ContextConfiguration(locations={"classpath:test-context.xml", "classpath:ui-test-context.xml"})
@Test(sequential=true)
public class UiTestCaseBase extends AbstractTestNGSpringContextTests {

	protected SeleniumTestUtils seleniumTestUtils;
	protected DefaultSelenium selenium;
	
	@BeforeMethod
	public void setUp() throws Exception {
		selenium = this.seleniumTestUtils.getSelenium();
	}
	
	@AfterSuite(groups={"acceptance", "ui"})
	public void stopSelenium() {
		selenium.stop();
	}

	@Autowired
	@Test(enabled=false)
	public void setSeleniumTestUtils(SeleniumTestUtils seleniumTestUtils) {
		this.seleniumTestUtils = seleniumTestUtils;
		assertNotNull(this.seleniumTestUtils);
	}
}
