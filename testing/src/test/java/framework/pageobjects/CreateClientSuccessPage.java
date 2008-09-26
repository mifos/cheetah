package framework.pageobjects;

import junit.framework.Assert;

import com.thoughtworks.selenium.DefaultSelenium;

public class CreateClientSuccessPage extends AbstractPage {

	public CreateClientSuccessPage(DefaultSelenium selenium) {
		super(selenium);
	}

	public void verifyPage() {
		Assert.assertTrue(selenium.isTextPresent("Client created successfully!"));
	}

}



