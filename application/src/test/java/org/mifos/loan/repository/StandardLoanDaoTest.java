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

import org.joda.time.LocalDate;
import org.mifos.loan.domain.Loan;
import org.mifos.loan.domain.LoanProduct;
import org.mifos.loan.domain.LoanProductStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@ContextConfiguration(locations={"classpath:integrationTestContext.xml"})
@TransactionConfiguration(transactionManager="transactionManager")
@Test(groups = { "integration" })
public class StandardLoanDaoTest extends AbstractTransactionalTestNGSpringContextTests {

	@Autowired
	private LoanDao standardLoanDao;

	@Autowired
	private LoanProductDao standardLoanProductDao;
	
	private LoanProduct loanProduct;
	
	private static final Integer CLIENT_ID = 1;
    private static final Integer UNUSED_CLIENT_ID = 8;
    private static final BigDecimal LOAN_AMOUNT1 = new BigDecimal("1200");
    private static final BigDecimal LOAN_AMOUNT2 = new BigDecimal("100");
	private static final BigDecimal LOAN_INTEREST_RATE = new BigDecimal("12");

    private static final int YEAR = 1998;
    private static final int MONTH = 12;
    private static final int DAY_OF_MONTH = 3;
	
	private void verifyLoanData(Loan loan) {
		Assert.assertTrue(loan.getId() > 0, "Expected a positive Id to be generated.");
		Assert.assertEquals(loan.getLoanProduct().getId(), loanProduct.getId(),"LoanProductId mismatch.");
		Assert.assertEquals(loan.getAmount(), LOAN_AMOUNT1, "Loan amount mismatch.");
		Assert.assertEquals(loan.getInterestRate(), LOAN_INTEREST_RATE, "Loan interest rate mismatch.");
		
	}
	
    private void verifyLoanData(Loan loan, BigDecimal loanAmount) {
        Assert.assertTrue(loan.getId() > 0, "Expected a positive Id to be generated.");
        Assert.assertEquals(loan.getLoanProduct().getId(),loanProduct.getId(),"LoanProductId mismatch.");
        Assert.assertEquals(loan.getAmount(), loanAmount, "Loan amount mismatch.");
        Assert.assertEquals(loan.getInterestRate(), LOAN_INTEREST_RATE, "Loan interest rate mismatch.");
        Assert.assertEquals(loan.getClientId(), CLIENT_ID,"Client id mismatch");
    }
    
	
	@java.lang.SuppressWarnings("PMD.UnusedPrivateMethod")
	@edu.umd.cs.findbugs.annotations.SuppressWarnings(value={"UPM"}, justification="@BeforeMethod & @AfterMethod methods are called by testNG")
	@BeforeMethod
	private void setUp() {
		loanProduct = standardLoanProductDao.createLoanProduct("loan prod 1", "prod1", 0.0, 20.0, LoanProductStatus.ACTIVE);		
	}
	
	public void testCreateLoan() {
		Loan loan = standardLoanDao.createLoan(CLIENT_ID, LOAN_AMOUNT1, LOAN_INTEREST_RATE, loanProduct);
		
		verifyLoanData(loan);
	}

	public void testGetAll() {

		int initialSize = standardLoanDao.getAll().size();
		
		standardLoanDao.createLoan(CLIENT_ID, LOAN_AMOUNT1, LOAN_INTEREST_RATE, loanProduct);
		standardLoanDao.createLoan(CLIENT_ID, LOAN_AMOUNT1, LOAN_INTEREST_RATE, loanProduct);
		standardLoanDao.createLoan(CLIENT_ID, LOAN_AMOUNT1, LOAN_INTEREST_RATE, loanProduct);
		
		List<Loan> loans = standardLoanDao.getAll();
		
		Assert.assertEquals(loans.size(),initialSize + 3,"Returned an unexpected number of loans.");
		
		Loan loan = loans.get(loans.size()-1);
		
		verifyLoanData(loan);
	}
	
    public void testLoanLookupByClientId() {      
        List<Loan> loanCheck = standardLoanDao.findLoansForClient(CLIENT_ID);
        Assert.assertEquals(loanCheck.size(), 0);
        
        standardLoanDao.createLoan(CLIENT_ID, LOAN_AMOUNT1, LOAN_INTEREST_RATE, loanProduct);
        standardLoanDao.createLoan(CLIENT_ID, LOAN_AMOUNT2, LOAN_INTEREST_RATE, loanProduct);
        
        List<Loan> loans = standardLoanDao.findLoansForClient(CLIENT_ID);
        Assert.assertEquals(loans.size(), 2);
        verifyLoanData(loans.get(0),LOAN_AMOUNT1);
        verifyLoanData(loans.get(1),LOAN_AMOUNT2);

        List<Loan> loansNone = standardLoanDao.findLoansForClient(UNUSED_CLIENT_ID);
        Assert.assertEquals(loansNone.size(), 0);
    }
    
    public void testGetLoan() {
        Loan loanCreated = standardLoanDao.createLoan(CLIENT_ID, LOAN_AMOUNT1, LOAN_INTEREST_RATE, loanProduct);
        Loan loanRetrieved = standardLoanDao.getLoan(loanCreated.getId());

        Assert.assertNotNull(loanRetrieved);
        Assert.assertEquals(loanRetrieved.getId(), loanCreated.getId(), "Loan ids do not match.");
        verifyLoanData(loanRetrieved,LOAN_AMOUNT1);
    }
	
    public void testGetLoanNotFound() {
        Loan loanCreated = standardLoanDao.createLoan(CLIENT_ID, LOAN_AMOUNT1, LOAN_INTEREST_RATE, loanProduct);
        Loan loanRetrieved = standardLoanDao.getLoan(loanCreated.getId()+1);
        
        Assert.assertNull(loanRetrieved);
    }

    public void testUpdateLoanDisbursalDate() {
        Loan loanCreated = standardLoanDao.createLoan(CLIENT_ID, LOAN_AMOUNT1, LOAN_INTEREST_RATE, loanProduct);

        LocalDate now = new LocalDate(YEAR,MONTH,DAY_OF_MONTH);
        loanCreated.setDisbursalDate(now);
        standardLoanDao.updateLoan(loanCreated);
               
        Loan loanRetrieved = standardLoanDao.getLoan(loanCreated.getId());
        Assert.assertEquals(loanRetrieved.getDisbursalDate(), loanCreated.getDisbursalDate());
    }
    
}
