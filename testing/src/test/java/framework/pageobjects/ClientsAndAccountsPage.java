package framework.pageobjects;

import com.thoughtworks.selenium.DefaultSelenium;

public class ClientsAndAccountsPage extends AbstractPage {

	public ClientsAndAccountsPage(DefaultSelenium selenium) {
		super(selenium);
	}

	public CreateClientPage navigateToCreateClientPage() {
		selenium.click("id=leftPane.createClient");
		waitForPageToLoad();
		return new CreateClientPage(selenium);
	}
}
