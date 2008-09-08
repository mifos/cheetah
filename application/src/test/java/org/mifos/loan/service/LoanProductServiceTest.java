package org.mifos.loan.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mifos.loan.domain.LoanProduct;
import org.mifos.loan.repository.LoanProductDao;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import edu.umd.cs.findbugs.annotations.SuppressWarnings;

@SuppressWarnings(value={"UrF"})
public class LoanProductServiceTest {

	private LoanProductService loanProductService = new LoanProductServiceImpl();
	
	@BeforeMethod
	private void setUp() {
	}
	
	@Test
	public void testNewLoanProduct() {
		
	}

}
