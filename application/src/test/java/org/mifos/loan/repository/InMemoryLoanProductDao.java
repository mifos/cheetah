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
	private Map<Integer, LoanProduct> loanProductStore = new HashMap<Integer, LoanProduct>();

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
