package framework.pageobjects;

import com.thoughtworks.selenium.Selenium;

public class ViewLoanProductDetailsPage extends AbstractPage {
    
    public EditLoanProductPage navigateToEditLoanProductPage() {
        selenium.click("id=edit-loan-product");
        return new EditLoanProductPage(selenium);
    }

    public ViewLoanProductDetailsPage(Selenium selenium) {
        super(selenium);
    }
}
