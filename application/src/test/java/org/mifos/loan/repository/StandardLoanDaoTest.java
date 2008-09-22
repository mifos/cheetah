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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;

@ContextConfiguration(locations={"classpath:integrationTestContext.xml"})
@TransactionConfiguration(transactionManager="transactionManager")
@Test(groups = { "integration" })
public class StandardLoanDaoTest extends AbstractTransactionalTestNGSpringContextTests {

	@Autowired
	private LoanDao standardLoanDao;

	private static final Integer LOAN_PRODUCT_ID = 1;	
	private static final Integer CLIENT_ID = 1;
	private static final BigDecimal LOAN_AMOUNT = new BigDecimal("1200");
	private static final BigDecimal LOAN_INTEREST_RATE = new BigDecimal("12");
	
	private void verifyLoanData(Loan loan) {
		Assert.assertTrue(loan.getId() > 0, "Expected a positive Id to be generated.");
		Assert.assertEquals(loan.getLoanProductId().intValue(),1,"LoanProductId mismatch.");
		Assert.assertEquals(loan.getAmount(), LOAN_AMOUNT, "Loan amount mismatch.");
		Assert.assertEquals(loan.getInterestRate(), LOAN_INTEREST_RATE, "Loan interest rate mismatch.");
		
	}
	
	public void testCreateLoan() {

		Loan loan = standardLoanDao.createLoan(CLIENT_ID, LOAN_AMOUNT, LOAN_INTEREST_RATE, LOAN_PRODUCT_ID);
		
		verifyLoanData(loan);
	}

	public void testGetAll() {

		int initialSize = standardLoanDao.getAll().size();
		
		standardLoanDao.createLoan(CLIENT_ID, LOAN_AMOUNT, LOAN_INTEREST_RATE, LOAN_PRODUCT_ID);
		standardLoanDao.createLoan(CLIENT_ID, LOAN_AMOUNT, LOAN_INTEREST_RATE, LOAN_PRODUCT_ID);
		standardLoanDao.createLoan(CLIENT_ID, LOAN_AMOUNT, LOAN_INTEREST_RATE, LOAN_PRODUCT_ID);
		
		List<Loan> loans = standardLoanDao.getAll();
		
		Assert.assertEquals(loans.size(),initialSize + 3,"Returned an unexpected number of loans.");
		
		Loan loan = loans.get(loans.size()-1);
		
		verifyLoanData(loan);
	}
	
}
