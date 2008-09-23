/*
 * Copyright (c) 2005-2008 Grameen Foundation USA
 * All rights reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * permissions and limitations under the License.
 * 
 * See also http://www.apache.org/licenses/LICENSE-2.0.html for an
 * explanation of the license and how it is applied.
 */

package org.mifos.loan.repository;

import java.math.BigDecimal;

import org.mifos.loan.domain.Loan;
import org.mifos.loan.domain.LoanProduct;
import org.mifos.loan.domain.LoanProductStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 */
@Test(groups = { "unit" })
public class InMemoryLoanDaoTest {

	private LoanDao loanDao;
	
	@BeforeMethod
	void setUp() {
		loanDao = new InMemoryLoanDao();
	}
	
	
	public void testCreateLoan() {
		Integer LOAN_PRODUCT_ID = 1;		
		Integer CLIENT_ID = 1;
		BigDecimal LOAN_AMOUNT = new BigDecimal("1200");
		BigDecimal LOAN_INTEREST_RATE = new BigDecimal("12");
		
		LoanProduct loanProduct = new LoanProduct(LOAN_PRODUCT_ID,"long name", "name", 0.0, 20.0, LoanProductStatus.ACTIVE);
		
		Loan loan = loanDao.createLoan(CLIENT_ID, LOAN_AMOUNT, LOAN_INTEREST_RATE, loanProduct);
		Assert.assertEquals(loan.getId().intValue(), 1, "Unexpected loan id.");
		Assert.assertEquals(loan.getLoanProduct().getId().intValue(),1,"LoanProductId mismatch.");
		Assert.assertEquals(loan.getAmount(), LOAN_AMOUNT, "Loan amount mismatch.");
		Assert.assertEquals(loan.getInterestRate(), LOAN_INTEREST_RATE, "Loan interest rate mismatch.");
		
		loan = loanDao.createLoan(CLIENT_ID, LOAN_AMOUNT, LOAN_INTEREST_RATE, loanProduct);
		Assert.assertEquals(loan.getId().intValue(),2,"Unexpected loan id.");
	}
}
