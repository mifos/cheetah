package framework.pageobjects;

import junit.framework.Assert;

import com.thoughtworks.selenium.Selenium;

public class CreateClientSuccessPage extends AbstractPage {

	public CreateClientSuccessPage(Selenium selenium) {
		super(selenium);
	}

	public void verifyPage() {
		Assert.assertTrue(selenium.isTextPresent("Client created successfully!"));
	}

}



