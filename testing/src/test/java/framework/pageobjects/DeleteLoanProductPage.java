package framework.pageobjects;

import com.thoughtworks.selenium.Selenium;

public class DeleteLoanProductPage extends AbstractPage {

    public DeleteLoanProductPage(Selenium selenium) {
        super(selenium);
    }
    
    public DeleteLoanProductSuccessPage deleteLoanProduct (String shortName) {
        selenium.click("id=delete");
        waitForPageToLoad();
        return new DeleteLoanProductSuccessPage(selenium);
    }

}
