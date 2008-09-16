package org.mifos.loan.service;

import java.util.List;

public interface LoanProductService {

	List getLoanProducts();
	LoanProductDto getLoanProduct (String shortName);
	LoanProductDto newLoanProduct(LoanProductDto loanProductDto);
	LoanProductDto updateLoanProduct (LoanProductDto loanProductDto);
	void deleteLoanProduct (String shortname);
}
