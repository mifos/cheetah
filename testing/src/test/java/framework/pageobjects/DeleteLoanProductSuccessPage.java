package framework.pageobjects;

import junit.framework.Assert;

import com.thoughtworks.selenium.Selenium;

public class DeleteLoanProductSuccessPage extends DeleteLoanProductResultPage {

    public DeleteLoanProductSuccessPage(Selenium selenium) {
        super(selenium);
    }

    public void verifyPage() {
        Assert.assertEquals("Delete Loan Product success", selenium.getTitle());
    }

    public void verifyMessage(String longName) {
        Assert.assertEquals(selenium.getText("deleteLoanProductSuccess.message"), "Successfully deleted loan product '" + longName + "'.");
    }
}
