package framework.pageobjects;

import com.thoughtworks.selenium.Selenium;

public class ViewLoanProductsPage extends AbstractPage {

    public ViewLoanProductsPage(Selenium selenium) {
        super(selenium);
    }

    public ViewLoanProductDetailsPage navigateToViewLoanProductDetailsPage(int id) {
        selenium.click("id=short-name-" + id);
        waitForPageToLoad();
        return new ViewLoanProductDetailsPage(selenium);
    }

    public ViewLoanProductDetailsPage navigateToViewLoanProductDetailsPage(String linkName) {
        selenium.click("id=" + linkName);
        waitForPageToLoad();
        return new ViewLoanProductDetailsPage(selenium);
    }

}
