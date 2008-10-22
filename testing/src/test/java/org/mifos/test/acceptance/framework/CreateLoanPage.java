package org.mifos.test.acceptance.framework;

import com.thoughtworks.selenium.Selenium;

public class CreateLoanPage extends AbstractPage {

	public CreateLoanPage(Selenium selenium) {
		super(selenium);
	}

	public CreateLoanSuccessPage createLoan(double loanAmount, double interestRate) {
		selenium.type("amount", Double.toString(loanAmount));
		selenium.type("interestRate", Double.toString(interestRate));
		selenium.click("loan.form.submit");
		waitForPageToLoad();
		return new CreateLoanSuccessPage(selenium);
	}

	public CreateLoanPage createLoanExpectingError(double loanAmount,
			double interestRate) {
		selenium.type("amount", Double.toString(loanAmount));
		selenium.type("interestRate", Double.toString(interestRate));
		selenium.click("loan.form.submit");
		waitForPageToLoad();
		return new CreateLoanPage(selenium);
	}

}
