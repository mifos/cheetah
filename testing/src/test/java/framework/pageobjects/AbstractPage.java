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
package framework.pageobjects;

import com.thoughtworks.selenium.DefaultSelenium;

/**
 * Encapsulates the GUI based actions that can
 * be done from the Login page and the page 
 * that will be navigated to.
 *
 */
public class AbstractPage {

	private static final String MAX_WAIT_FOR_PAGE_TO_LOAD_IN_MILLISECONDS = "30000";
	protected DefaultSelenium selenium;

	public AbstractPage() {
		// do nothing
	}
	
	public AbstractPage(DefaultSelenium selenium) {
		setSelenium(selenium);
	}
	
	protected void waitForPageToLoad() {
		selenium.waitForPageToLoad(MAX_WAIT_FOR_PAGE_TO_LOAD_IN_MILLISECONDS);
	}
	
	final public void setSelenium(DefaultSelenium selenium) {
		this.selenium = selenium;
	}

	public DefaultSelenium getSelenium() {
		return this.selenium;
	}

	public LoginPage logout() {
		selenium.open("	j_spring_security_logout");
		waitForPageToLoad();
		return new LoginPage(selenium);
	}

}
