package org.mifos.loan.repository;

import org.mifos.loan.domain.LoanProduct;
import org.mifos.loan.domain.LoanProductStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

@ContextConfiguration(locations={"classpath:unitTestContext.xml",
									"classpath:repositoryContext.xml"})
public class StandardLoanProductDaoTest  
			extends	AbstractTransactionalTestNGSpringContextTests{
	
    @Autowired
	private LoanProductDao loanProductDao;
	
	@Test(groups="integration")
	public void testCreateOneProduct() {
        super.deleteFromTables(new String[] {"loanproducts"});
		String longName = "long";
		String shortName = "short";
		Double minInterestRate = 1.0;
		Double maxInterestRate = 2.0;
		LoanProductStatus status = LoanProductStatus.ACTIVE;
		
		LoanProduct product = new LoanProduct(null, "long", "short", 1.0, 2.0, LoanProductStatus.ACTIVE);
		LoanProduct createdProduct = loanProductDao.createLoanProduct(longName, shortName, minInterestRate, 
															maxInterestRate, status);
		assertSameState(createdProduct, product);
		Assert.assertNotNull(createdProduct.getId());
		LoanProduct retrievedProduct = loanProductDao.get(createdProduct.getId());
		assertSameState(retrievedProduct, product);
	}
		

	private void assertSameState (LoanProduct actual, LoanProduct expected) {
		Assert.assertEquals(actual.getLongName(), expected.getLongName(), "Wrong long name");
		Assert.assertEquals(actual.getShortName(), expected.getShortName(), "Wrong short name");
		Assert.assertEquals(actual.getMinInterestRate(), expected.getMinInterestRate(), 0, "Wrong min interest rate");
		Assert.assertEquals(actual.getMaxInterestRate(), expected.getMaxInterestRate(), 0, "Wrong max interest rate");
		Assert.assertEquals(actual.getStatus(), expected.getStatus(), "Wrong status");
	}
}


