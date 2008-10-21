package framework.pageobjects;

import junit.framework.Assert;

import com.thoughtworks.selenium.Selenium;

public class DeleteLoanProductPage extends AbstractPage {

    public DeleteLoanProductPage(Selenium selenium) {
        super(selenium);
    }
    
    public DeleteLoanProductResultPage deleteLoanProduct () {
        selenium.click("id=client.form.submit.delete");
        waitForPageToLoad();
        DeleteLoanProductResultPage result = null;
        if (selenium.getTitle().contains("success")) {
            result = new DeleteLoanProductSuccessPage(selenium);
        } else {
            result = new DeleteLoanProductFailurePage(selenium);
        }
        return result;
    }

    public void verifyPage() {
        Assert.assertEquals(selenium.getTitle(), "Delete Loan Product");
    }


}
