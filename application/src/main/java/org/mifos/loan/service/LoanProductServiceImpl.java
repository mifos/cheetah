package org.mifos.loan.service;

import java.util.List;

import org.mifos.loan.domain.LoanProduct;
import org.mifos.loan.repository.LoanProductDao;

@edu.umd.cs.findbugs.annotations.SuppressWarnings(value={"NP"})
public class LoanProductServiceImpl implements LoanProductService {

	LoanProductDao loanProductDao;
	
	@Override
	public void deleteLoanProduct(LoanProduct loanProduct) {
		loanProductDao.deleteLoanProduct(loanProduct);
	}

	@Override
	public LoanProduct getLoanProduct(String longName) {
		return loanProductDao.get(longName);
	}

	@Override
	public List getLoanProducts() {
		return loanProductDao.getLoanProducts();
	}

	@Override
	public void newLoanProduct(LoanProduct loanProduct) {
		loanProductDao.saveLoanProduct(loanProduct);
	}

	@Override
	public void updateLoanProduct(LoanProduct loanProduct) {
		loanProductDao.saveLoanProduct(loanProduct);
	}

}
