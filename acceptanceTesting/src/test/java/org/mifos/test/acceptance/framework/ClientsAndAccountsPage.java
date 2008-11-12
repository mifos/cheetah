package org.mifos.test.acceptance.framework;

import com.thoughtworks.selenium.Selenium;

public class ClientsAndAccountsPage extends AbstractPage {

	public ClientsAndAccountsPage(Selenium selenium) {
		super(selenium);
	}

	public CreateClientPage navigateToCreateClientPage() {
		selenium.click("id=leftPane.createClient");
		waitForPageToLoad();
		return new CreateClientPage(selenium);
	}
}
