package org.mifos.loan.domain;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoanProductTest {
	
	@Test
	public void testConstructAValidLoanProduct() {
		String longName = "A Very Long Name";
		String shortName = "sn";
		Double minInterestRate = 1.2;
		Double maxInterestRate = 10.6;
		LoanProductStatus status = LoanProductStatus.ACTIVE;
		LoanProduct loanProduct = new LoanProduct(longName,shortName,minInterestRate, maxInterestRate,status);
		Assert.assertEquals(loanProduct.getLongName(), longName);
		Assert.assertEquals(loanProduct.getShortName(), shortName);
		Assert.assertEquals(loanProduct.getMinInterestRate(), minInterestRate);
		Assert.assertEquals(loanProduct.getMaxInterestRate(), maxInterestRate);
		Assert.assertEquals(loanProduct.getStatus(), status);
	}

}
