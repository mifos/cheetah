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
 * be done from the Home page and the page 
 * that will be navigated to.
 *
 */
public class HomePage extends AbstractPage {

	public HomePage() {
		super();
	}

	public HomePage(Selenium selenium) {
		super(selenium);
	}

	public CreateLoanPage navigateToCreateLoanPage() {
		selenium.click("id=left.pane.create.loan");
		waitForPageToLoad();
		return new CreateLoanPage(selenium);
	}

	public AdminPage navigateToAdminPage() {
		openHomePage();
		selenium.click("id=header.tab.admin");
		waitForPageToLoad();
		return new AdminPage(selenium);		
	}

	public ClientsAndAccountsPage navigateToClientsAndAccountsPage() {
		openHomePage();
		selenium.click("id=header.tab.clientsAndAccounts");
		waitForPageToLoad();
		return new ClientsAndAccountsPage(selenium);		
	}

	public ClientSearchResultsPage searchForClient(String clientName) {
        selenium.type("//input[@id='searchString']", clientName);
        selenium.click("clientSearch.form.submit");
        waitForPageToLoad();
        
		return new ClientSearchResultsPage(selenium);
	}

    public ViewGroupsPage navigateToViewGroupsPage() {
        openHomePage();
        selenium.click("id=home.list.groups");
        waitForPageToLoad();
        return new ViewGroupsPage(selenium);     
    }

    private void openHomePage() {
        selenium.open("/mifos/home.ftl");
    }
}
