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
import java.util.List;

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

    private static final Integer LOAN_PRODUCT_ID = 1;        
    private static final Integer CLIENT_ID = 1;    
    private static final Integer UNUSED_CLIENT_ID = 8;
    private static final BigDecimal LOAN_AMOUNT1 = new BigDecimal("1200");
    private static final BigDecimal LOAN_AMOUNT2 = new BigDecimal("100");
    private static final BigDecimal LOAN_INTEREST_RATE = new BigDecimal("12");
	
	@BeforeMethod
	void setUp() {
		loanDao = new InMemoryLoanDao();
	}
	
	private void assertLoanIsExpected(Loan loan, int id, BigDecimal loanAmount) {
        Assert.assertEquals(loan.getId().intValue(), id, "Unexpected loan id.");
        Assert.assertEquals(loan.getLoanProduct().getId(),LOAN_PRODUCT_ID,"LoanProductId mismatch.");
        Assert.assertEquals(loan.getAmount(), loanAmount, "Loan amount mismatch.");
        Assert.assertEquals(loan.getInterestRate(), LOAN_INTEREST_RATE, "Loan interest rate mismatch.");
	    Assert.assertEquals(loan.getClientId(), CLIENT_ID,"Client id mismatch");
	}
	
	public void testCreateLoan() {
		
		LoanProduct loanProduct = new LoanProduct(LOAN_PRODUCT_ID,"long name", "name", 0.0, 20.0, LoanProductStatus.ACTIVE);
		
		Loan loan = loanDao.createLoan(CLIENT_ID, LOAN_AMOUNT1, LOAN_INTEREST_RATE, loanProduct);
		assertLoanIsExpected(loan, 1, LOAN_AMOUNT1);
		
		loan = loanDao.createLoan(CLIENT_ID, LOAN_AMOUNT2, LOAN_INTEREST_RATE, loanProduct);
		Assert.assertEquals(loan.getId().intValue(),2,"Unexpected loan id.");
	}
	
	public void testLoanLookupByClientId() {      
        LoanProduct loanProduct = new LoanProduct(LOAN_PRODUCT_ID,"long name", "name", 0.0, 20.0, LoanProductStatus.ACTIVE);
        List<Loan> loanCheck = loanDao.findLoansForClient(CLIENT_ID);
        Assert.assertEquals(loanCheck.size(), 0);
        
        loanDao.createLoan(CLIENT_ID, LOAN_AMOUNT1, LOAN_INTEREST_RATE, loanProduct);
        loanDao.createLoan(CLIENT_ID, LOAN_AMOUNT2, LOAN_INTEREST_RATE, loanProduct);
        
        List<Loan> loans = loanDao.findLoansForClient(CLIENT_ID);
        Assert.assertEquals(loans.size(), 2);
        assertLoanIsExpected(loans.get(0),1,LOAN_AMOUNT1);
        assertLoanIsExpected(loans.get(1),2,LOAN_AMOUNT2);

        List<Loan> loansNone = loanDao.findLoansForClient(UNUSED_CLIENT_ID);
        Assert.assertEquals(loansNone.size(), 0);
        
	}
}
