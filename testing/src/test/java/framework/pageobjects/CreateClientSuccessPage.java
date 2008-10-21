package framework.pageobjects;

import junit.framework.Assert;

import com.thoughtworks.selenium.Selenium;

public class CreateClientSuccessPage extends AbstractPage {

	public CreateClientSuccessPage(Selenium selenium) {
		super(selenium);
	}

    public void verifyPage() {
        Assert.assertEquals("Create Client success", selenium.getTitle());
    }

    public void verifyMessage() {
        Assert.assertEquals("Client created successfully!", selenium.getText("createClientSuccess.message"));
    }

}



