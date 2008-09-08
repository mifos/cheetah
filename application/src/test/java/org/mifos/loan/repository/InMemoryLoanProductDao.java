package org.mifos.loan.repository;

import java.util.HashMap;
import java.util.List;

import org.mifos.loan.domain.LoanProduct;

public class InMemoryLoanProductDao implements LoanProductDao {

	private HashMap<String, LoanProduct> loanProductStore;

	@Override
	public void deleteLoanProduct(LoanProduct loanProduct) {
		loanProductStore.remove(loanProduct.getLongName());			
	}

	@Override
	public LoanProduct get(String longName) {
		return loanProductStore.get(longName);
	}

	@Override
	public List<LoanProduct> getLoanProducts() {
		return (List<LoanProduct>) loanProductStore.values();
	}

	@Override
	public void saveLoanProduct(LoanProduct loanProduct) {
		loanProductStore.put(loanProduct.getLongName(), loanProduct);			
	}

}
