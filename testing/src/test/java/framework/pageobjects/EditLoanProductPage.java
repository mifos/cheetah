package framework.pageobjects;

import com.thoughtworks.selenium.Selenium;

public class EditLoanProductPage extends AbstractPage {

    public EditLoanProductPage(Selenium selenium) {
        super(selenium);
    }
    
    public EditLoanProductSuccessPage modifyLoanProduct (String longName, String shortName, 
                                                         String minInterestRate, String maxInterestRate,
                                                         String status) {
        selenium.type("longName", longName);
        selenium.type("shortName", shortName);
        selenium.type("minInterestRate", minInterestRate);
        selenium.type("maxInterestRate", maxInterestRate);
        selenium.select("status", "value=" + status);
        selenium.click("login.form.submit");
        waitForPageToLoad();
        return new EditLoanProductSuccessPage(selenium);
    }

}
