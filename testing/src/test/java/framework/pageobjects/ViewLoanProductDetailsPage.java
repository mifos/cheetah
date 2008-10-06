package framework.pageobjects;

import com.thoughtworks.selenium.Selenium;

public class ViewLoanProductDetailsPage extends AbstractPage {
    
    public EditLoanProductPage navigateToEditLoanProductPage() {
        selenium.click("link=Update");
        waitForPageToLoad();
        return new EditLoanProductPage(selenium);
    }

    public ViewLoanProductDetailsPage(Selenium selenium) {
        super(selenium);
    }
}
