package org.mifos.loan.repository;

import java.util.List;

import org.mifos.loan.domain.LoanProduct;

public interface LoanProductDao {
	
	void deleteLoanProduct (LoanProduct loanProduct);
	void saveLoanProduct (LoanProduct loanProduct);
	List<LoanProduct> getLoanProducts ();
	LoanProduct get (String longName);
}
