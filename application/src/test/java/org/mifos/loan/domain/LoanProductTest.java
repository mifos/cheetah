package org.mifos.loan.domain;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoanProductTest {
	
	@Test
	public void testConstructAValidLoanProduct() {
		Integer id = 3;
		String longName = "A Very Long Name";
		String shortName = "sn";
		Double minInterestRate = 1.2;
		Double maxInterestRate = 10.6;
		LoanProductStatus status = LoanProductStatus.ACTIVE;
		LoanProduct loanProduct = new LoanProduct(id, longName,shortName,minInterestRate, maxInterestRate,status);
		Assert.assertEquals(loanProduct.getId(), id);
		Assert.assertEquals(loanProduct.getLongName(), longName);
		Assert.assertEquals(loanProduct.getShortName(), shortName);
		Assert.assertEquals(loanProduct.getMinInterestRate(), minInterestRate);
		Assert.assertEquals(loanProduct.getMaxInterestRate(), maxInterestRate);
		Assert.assertEquals(loanProduct.getStatus(), status);
	}

}
