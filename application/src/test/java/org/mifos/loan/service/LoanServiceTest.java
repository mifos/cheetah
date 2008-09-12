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

package org.mifos.loan.service;

import java.math.BigDecimal;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test(groups = { "unit" })
public class LoanServiceTest {

	private LoanService loanService;
	
	@SuppressWarnings("PMD.UrF")
	@BeforeMethod
	void setUp() {
		loanService = new BasicLoanService();
	}
	
	public void testCreateLoan() {
		Integer LOAN_PRODUCT_ID = 1;
		BigDecimal LOAN_AMOUNT = new BigDecimal("1200");
		BigDecimal LOAN_INTEREST_RATE = new BigDecimal("12");
		
		LoanDTO loanDTO = loanService.createLoan(LOAN_PRODUCT_ID, LOAN_AMOUNT, LOAN_INTEREST_RATE);
		assert(loanDTO.getId() == 1);
		assert(loanDTO.getLoanProductId() == 1);
		assert(loanDTO.getAmount() == LOAN_AMOUNT);
		assert(loanDTO.getInterestRate() == LOAN_INTEREST_RATE);
	}
}
