package framework.pageobjects;

import junit.framework.Assert;

import com.thoughtworks.selenium.Selenium;

public class CreateClientPage extends AbstractPage {

	public CreateClientPage(Selenium selenium) {
		super(selenium);
	}

	public CreateClientSuccessPage createValidClient(String firstName, String lastName, String dateOfBirth) {
		selenium.type("firstName", firstName);
		selenium.type("lastName", lastName);
		selenium.type("localDateOfBirth", dateOfBirth);
		selenium.click("client.form.submit");
		waitForPageToLoad();
		return new CreateClientSuccessPage(selenium);
	}

	public CreateClientPage createClientExpectingError(String firstName, String lastName, String dateOfBirth) {
		waitForPageToLoad();
		return new CreateClientPage(selenium);
	}

	public void verifyPage() {
		Assert.assertTrue(selenium.isTextPresent("Create client"));
	}

}
