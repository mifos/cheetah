package org.mifos.loan.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mifos.loan.domain.LoanProduct;

public class InMemoryLoanProductDao implements LoanProductDao {

	@SuppressWarnings("PMD.ImmutableField")
	private Map<Integer, LoanProduct> loanProductStore;
	private int nextKey = 0;
	
	public InMemoryLoanProductDao() {
		loanProductStore = new HashMap<Integer, LoanProduct>();
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

	public void saveLoanProduct(LoanProduct loanProduct) {
		if (loanProduct.getId()==null) {
			loanProduct.setId(nextKey++);
		}
		loanProductStore.put(loanProduct.getId(), loanProduct);			
	}
		
	public LoanProduct getByShortName (String shortName){
		List<LoanProduct> loanProducts = new ArrayList<LoanProduct> (loanProductStore.values());
		return loanProducts.get(0);
		
	}
}
