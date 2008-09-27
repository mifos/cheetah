package framework.pageobjects;

import junit.framework.Assert;

import com.thoughtworks.selenium.Selenium;

public class CreateClientPage extends AbstractPage {

	public CreateClientPage(Selenium selenium) {
		super(selenium);
	}

	public CreateClientSuccessPage createValidClient(String firstName, String lastName, String dateOfBirth) {
		submitForm(firstName, lastName, dateOfBirth);
		return new CreateClientSuccessPage(selenium);
	}

	public CreateClientPage createClientExpectingError(String firstName, String lastName, String dateOfBirth) {
		submitForm(firstName, lastName, dateOfBirth);
		return new CreateClientPage(selenium);
	}

	private void submitForm(String firstName, String lastName,
			String dateOfBirth) {
		selenium.type("firstName", firstName);
		selenium.type("lastName", lastName);
		selenium.type("localDateOfBirth", dateOfBirth);
		selenium.click("client.form.submit");
		waitForPageToLoad();
	}

	public void verifyPage() {
		Assert.assertTrue(selenium.isTextPresent("Create client"));
	}

	public void verifyErrorExists(String errorMessage) {
		Assert.assertTrue(selenium.isTextPresent(errorMessage));
	}

}
