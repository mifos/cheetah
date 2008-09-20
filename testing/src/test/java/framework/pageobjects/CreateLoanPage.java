package framework.pageobjects;

public class CreateLoanPage extends AbstractPage {

	public CreateLoanSuccessPage createLoan(double loanAmount, double interestRate) {
		selenium.type("loanProductId", "1");
		selenium.type("amount", Double.toString(loanAmount));
		selenium.type("interestRate", Double.toString(interestRate));
		selenium.click("loan.form.submit");
		return new CreateLoanSuccessPage();
	}

	public CreateLoanPage createLoanExpectingError(double loanAmount,
			double interestRate) {
		selenium.type("loanProductId", "1");
		selenium.type("amount", Double.toString(loanAmount));
		selenium.type("interestRate", Double.toString(interestRate));
		selenium.click("loan.form.submit");
		
		return new CreateLoanPage();
	}

}
