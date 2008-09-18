package org.mifos.loan.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mifos.loan.domain.LoanProduct;
import org.mifos.loan.domain.LoanProductStatus;
import org.mifos.loan.repository.InMemoryLoanProductDao;
import org.mifos.loan.repository.LoanProductDao;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import edu.umd.cs.findbugs.annotations.SuppressWarnings;

@SuppressWarnings(value={"UrF"})
public class StandardLoanProductServiceTest {

	private StandardLoanProductService loanProductService;
	
	private final String longName1 = "long name 1";
	private final String shortName1 = "short name 1";
	private final Double maxInt1 = 1.0;
	private final Double minInt1 = 2.0;
	private final LoanProductStatus status1 = LoanProductStatus.ACTIVE;

	private final String longName2 = "long name 2";
	private final String shortName2 = "short name 2";
	private final Double maxInt2 = 3.0;
	private final Double minInt2 = 4.0;
	private final LoanProductStatus status2 = LoanProductStatus.INACTIVE;
	
	private LoanProductDto testProduct1;
	private LoanProductDto testProduct2;

	@BeforeMethod
	private void setUp() {
		loanProductService = new StandardLoanProductService();
		loanProductService.setLoanProductDao (new InMemoryLoanProductDao());
		testProduct1 = new LoanProductDto(longName1, shortName1, minInt1, maxInt1, status1);
		testProduct2 = new LoanProductDto(longName2, shortName2, minInt2, maxInt2, status2);
	}
	
	@Test(groups="unit")
	public void testCreateLoanProduct() {
		assertSameState (loanProductService.createLoanProduct(testProduct1),
							  testProduct1);
		Assert.assertEquals(loanProductService.getAll().size(), 1, "Expected one loan product");
		assertSameState ((LoanProductDto)loanProductService.getAll().get(0), testProduct1);
	}
	
	//@Test(groups="unit")
	/* implement later
	public void testUpdateLoanProduct() {
		LoanProductDto retrievedOnCreate = loanProductService.createLoanProduct(testProduct1);
		//change all attributes
		LoanProductDto changed = new LoanProductDto("new long name", "new short name",
													 3.5, 4.5, LoanProductStatus.INACTIVE);
		assertSameState(loanProductService.updateLoanProduct(changed), changed);
		assertSameState(loanProductService.getLoanProduct(retrievedOnCreate.getId()), changed);
	}
	*/
	
	//@Test(groups="unit")
	public void testDeleteLoanProduct() {
		LoanProductDto testLoanProductDto = createTestLoanProductDto1();
		loanProductService.deleteLoanProduct(testLoanProductDto);
		Assert.assertEquals(loanProductService.getAll().size(), 0);		
	}
	
	//@Test(groups="unit")
	public void testGetAllEmptyRepository() {
		Assert.assertEquals(loanProductService.getAll().size(), 0);
	}
	
	//@Test(groups="unit")
	public void testGetAllWithOneMember() {
		LoanProductDto testLoanProductDto = createTestLoanProductDto1();
		LoanProductDto retrievedLoanProductDto = loanProductService.createLoanProduct(testLoanProductDto);
		Assert.assertEquals(loanProductService.getAll().size(), 1);
		assertSameState(loanProductService.getLoanProduct(retrievedLoanProductDto.getId()), 
									   testLoanProductDto);
	}
	
	//@Test(groups="unit")
	public void testGetAllWithTwoMembers() {
		LoanProductDto testLoanProductDto1 = createTestLoanProductDto1();
		LoanProductDto testLoanProductDto2 = createTestLoanProductDto2();
		LoanProductDto retrievedLoanProductDto1 = loanProductService.createLoanProduct(testLoanProductDto1);
		LoanProductDto retrievedLoanProductDto2 = loanProductService.createLoanProduct(testLoanProductDto2);
		Assert.assertEquals(loanProductService.getAll().size(), 2);
		assertSameState(loanProductService.getLoanProduct(retrievedLoanProductDto1.getId()), 
				   					   testLoanProductDto1);
		assertSameState(loanProductService.getLoanProduct(retrievedLoanProductDto2.getId()), 
				   					   testLoanProductDto2);
	}

	private LoanProductDto createTestLoanProductDto1() {
		LoanProductDto loanProductDTO = new LoanProductDto(longName1, shortName1, minInt1, maxInt1, status1);
		return loanProductDTO;
	}

	private LoanProductDto createTestLoanProductDto2() {
		LoanProductDto loanProductDTO = new LoanProductDto(longName2, shortName2, minInt2, maxInt2, status2);
		return loanProductDTO;
	}
	
	private void assertSameState (LoanProductDto actual, LoanProductDto expected) {
		Assert.assertEquals(actual.getLongName(), expected.getLongName(), "Wrong long name");
		Assert.assertEquals(actual.getShortName(), expected.getShortName(), "Wrong short name");
		Assert.assertEquals(actual.getMinInterestRate(), expected.getMinInterestRate(), 0, "Wrong min interest rate");
		Assert.assertEquals(actual.getMaxInterestRate(), expected.getMaxInterestRate(), 0, "Wrong max interest rate");
		Assert.assertEquals(actual.getStatus(), expected.getStatus(), "Wrong status");
	}
}
