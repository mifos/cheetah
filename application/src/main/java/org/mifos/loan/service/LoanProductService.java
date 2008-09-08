package org.mifos.loan.service;

import java.util.List;

import org.mifos.loan.domain.LoanProduct;

public interface LoanProductService {

	List getLoanProducts();
	LoanProduct getLoanProduct (String longName);
	void newLoanProduct(LoanProduct loanProduct);
	void updateLoanProduct (LoanProduct loanProduct);
	void deleteLoanProduct (LoanProduct loanProduct);
}
