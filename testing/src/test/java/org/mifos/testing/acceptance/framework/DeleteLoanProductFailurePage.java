package org.mifos.testing.acceptance.framework;

import junit.framework.Assert;

import com.thoughtworks.selenium.Selenium;

public class DeleteLoanProductFailurePage extends DeleteLoanProductResultPage {

    public DeleteLoanProductFailurePage(Selenium selenium) {
        super(selenium);
    }
    
    public void verifyPage() {
        Assert.assertEquals("Delete Loan Product failure", selenium.getTitle());
    }

    public void verifyMessage(String longName) {
        Assert.assertEquals("Could not delete loan product '" + longName + "' because there are loans that use this product.", selenium.getText("deleteLoanProductFailure.message"));
    }
    
}
