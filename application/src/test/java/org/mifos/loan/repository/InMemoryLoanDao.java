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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.mifos.core.MifosNotImplementedException;
import org.mifos.loan.domain.Loan;
import org.mifos.loan.domain.LoanProduct;

public class InMemoryLoanDao implements LoanDao {

	private int nextLoanId = 1;
	
	private final Map<Integer, Loan> loans = new HashMap<Integer, Loan>(); 

	@Override
	public Loan createLoan(Integer clientId, BigDecimal loanAmount, BigDecimal interestRate,
			LoanProduct loanProduct) {
		Loan loan = new Loan(clientId, loanAmount, interestRate, loanProduct);
		loan.setId(generateNextLoanId());
		loans.put(loan.getId(), loan);
		return loan;
	}

	private int generateNextLoanId() {
		synchronized(this) {
			return nextLoanId++;
		}
	}

	@Override
	public List<Loan> getAll() {
	    throw new MifosNotImplementedException();
	}

    @Override
    public List<Loan> findLoansForClient(Integer clientId) {
        ArrayList loanList = new ArrayList<Loan>();
        for (Entry<Integer,Loan> entry : loans.entrySet()) {
            if (entry.getValue().getClientId().equals(clientId)) {
                loanList.add(entry.getValue());
            }           
        }
        return loanList;
    }

    @Override
    public Loan getLoan(Integer id) {
        return loans.get(id);
    }

    @Override
    public void updateLoan(Loan loan) {
        Loan loanRetrieved = getLoan(loan.getId());
        loanRetrieved.setAmount(loan.getAmount());
        loanRetrieved.setDisbursalDate(loan.getDisbursalDate());
        loanRetrieved.setInterestRate(loan.getInterestRate());
    }

    @Override
    public Boolean loansExistForLoanProduct(Integer loanProductId) {
        Boolean result = Boolean.FALSE;
        for (Loan loan:loans.values()) {
            if (loan.getLoanProduct().getId().equals(loanProductId)) {
                result = Boolean.TRUE;
                break;
            }
        }
        return result;
    }

}
