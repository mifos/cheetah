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
package org.mifos.test.acceptance.framework;

import com.thoughtworks.selenium.Selenium;

/**
 * Encapsulates the GUI based actions that can
 * be done from the Login page and the page 
 * that will be navigated to.
 *
 */
public class LoginPage extends AbstractPage {

	public LoginPage() {
		super();
	}
	
	public LoginPage(Selenium selenium) {
		super(selenium);
	}
	
	public HomePage loginAs(String userName, String password) {
		selenium.open("login.ftl");
		selenium.type("login.form.username", userName);
		selenium.type("login.form.password", password);
		selenium.click("login.form.submit");
		waitForPageToLoad();
		return new HomePage(selenium);
	}

	public LoginPage logout() {
		selenium.open("	j_spring_security_logout");
		waitForPageToLoad();
		return new LoginPage(selenium);
	}

}
