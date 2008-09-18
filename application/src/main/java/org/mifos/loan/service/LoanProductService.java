package org.mifos.loan.service;

import java.util.List;

public interface LoanProductService {

	List getAll();
	LoanProductDto getLoanProduct (Integer id);
	LoanProductDto createLoanProduct(LoanProductDto product);
	//LoanProductDto updateLoanProduct (LoanProductDto product);
	void deleteLoanProduct (LoanProductDto product);
}
