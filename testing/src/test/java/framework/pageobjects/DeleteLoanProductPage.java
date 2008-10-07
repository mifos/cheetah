package framework.pageobjects;

import junit.framework.Assert;

import com.thoughtworks.selenium.Selenium;

public class DeleteLoanProductPage extends AbstractPage {

    public DeleteLoanProductPage(Selenium selenium) {
        super(selenium);
    }
    
    public DeleteLoanProductSuccessPage deleteLoanProduct () {
        selenium.click("id=client.form.submit.delete");
        waitForPageToLoad();
        return new DeleteLoanProductSuccessPage(selenium);
    }

    public void verifyPage() {
        Assert.assertEquals(selenium.getTitle(), "Delete Loan Product");
    }


}
