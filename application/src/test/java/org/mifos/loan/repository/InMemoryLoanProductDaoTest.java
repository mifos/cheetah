package org.mifos.loan.repository;

import org.mifos.loan.domain.LoanProduct;
import org.mifos.loan.domain.LoanProductStatus;
import org.mifos.loan.repository.InMemoryLoanProductDao;
import org.mifos.loan.repository.LoanProductDao;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test(groups = { "unit" })
public class InMemoryLoanProductDaoTest {

	private LoanProductDao loanProductDao;
	
	@BeforeMethod
	void setUp() {
		loanProductDao = new InMemoryLoanProductDao();
	}
	
	
	@Test(groups = { "unit" })
	public void testCreateLoan() {
		String longName = "long name 1";
		String shortName = "short name 1";
		Double maxInterestRate = 1.0;
		Double minInterestRate = 2.0;
		LoanProductStatus status = LoanProductStatus.ACTIVE;
		
		LoanProduct newLoanProduct = loanProductDao.createLoanProduct(longName, shortName, minInterestRate, 
				                                                      maxInterestRate, status);
		assert(newLoanProduct.getId().equals(1));
		assert(newLoanProduct.getLongName().equals(longName));
		assert(newLoanProduct.getShortName().equals(shortName));
		assert(newLoanProduct.getMinInterestRate().equals(minInterestRate));
		assert(newLoanProduct.getMaxInterestRate().equals(maxInterestRate));
		assert(newLoanProduct.getStatus().equals(status));
		LoanProduct anotherLoanProduct = loanProductDao.createLoanProduct(longName, shortName, minInterestRate, 
				                                                          maxInterestRate, status);
		assert(anotherLoanProduct.getId().equals(2));
		}
}