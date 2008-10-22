package org.mifos.testing.acceptance.framework;

import com.thoughtworks.selenium.Selenium;

public class ViewLoanProductDetailsPage extends AbstractPage {
    
    public EditLoanProductPage navigateToEditLoanProductPage() {
        selenium.click("id=update");
        waitForPageToLoad();
        return new EditLoanProductPage(selenium);
    }

    public DeleteLoanProductPage navigateToDeleteLoanProductPage() {
        selenium.click("id=delete");
        waitForPageToLoad();
        return new DeleteLoanProductPage(selenium);
    }

    public ViewLoanProductDetailsPage(Selenium selenium) {
        super(selenium);
    }
}
