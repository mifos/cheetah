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

    public CreateClientPage changeLocale(String locale) {
        selenium.open("createClient.ftl?siteLanguage="+locale);
        waitForPageToLoad();
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
        Assert.assertEquals(selenium.getTitle(), "Create Client");
    }

	public void verifyErrorExists(String errorMessage) {
		Assert.assertTrue(selenium.isTextPresent(errorMessage));
	}

    public void verifyDatePatternMessage(String datePattern) {
        Assert.assertEquals(datePattern, selenium.getText("id=datePattern"));
    }

}
