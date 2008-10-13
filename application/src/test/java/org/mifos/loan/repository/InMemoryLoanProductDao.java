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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mifos.loan.domain.LoanProduct;
import org.mifos.loan.domain.LoanProductStatus;

public class InMemoryLoanProductDao implements LoanProductDao {

	private int nextKey = 0;
	
	//@SuppressWarnings("PMD.ImmutableField")
	private final Map<Integer, LoanProduct> loanProductStore = new HashMap<Integer, LoanProduct>();

	public LoanProduct createLoanProduct (String longName, String shortName, Double minInterestRate,
			Double maxInterestRate, LoanProductStatus status) {
		LoanProduct loanProduct = new LoanProduct(++nextKey, longName, shortName, minInterestRate, 
												  maxInterestRate, status);
		loanProductStore.put(nextKey, loanProduct);
		return loanProduct;
	}
	
	public void deleteLoanProduct(LoanProduct loanProduct) {
		loanProductStore.remove(loanProduct.getId());			
	}

	public LoanProduct get(Integer id) {
		return loanProductStore.get(id);
	}

	public List<LoanProduct> getLoanProducts() {
		return new ArrayList<LoanProduct> (loanProductStore.values());
	}

	public void updateLoanProduct(LoanProduct loanProduct) {
		loanProductStore.put(loanProduct.getId(), loanProduct);			
	}
}
