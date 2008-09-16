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
import java.util.HashMap;
import java.util.Map;

import org.mifos.loan.domain.Loan;


/**
 *
 */
public class InMemoryLoanDao implements LoanDao {

	private int nextLoanId = 1;
	
	private final Map<Integer, Loan> clients = new HashMap<Integer, Loan>(); 

	@Override
	public Loan createLoan(BigDecimal loanAmount, BigDecimal interestRate,
			Integer loanProductId) {
		Loan loan = new Loan(loanAmount, interestRate, loanProductId);
		loan.setId(generateNextLoanId());
		clients.put(loan.getId(), loan);
		return loan;
	}

	synchronized private int generateNextLoanId() {
		return nextLoanId++;
	}
}