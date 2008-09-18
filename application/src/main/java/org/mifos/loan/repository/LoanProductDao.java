package org.mifos.loan.repository;

import java.util.List;

import org.mifos.loan.domain.LoanProduct;
import org.mifos.loan.domain.LoanProductStatus;

public interface LoanProductDao {
	
	LoanProduct createLoanProduct (String longName, String shortName, Double minInterestRate,
									Double maxInterestRate, LoanProductStatus status);
	void deleteLoanProduct (LoanProduct loanProduct);
	void updateLoanProduct (LoanProduct loanProduct);
	List<LoanProduct> getLoanProducts ();
	LoanProduct get (Integer id);
}
