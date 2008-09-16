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
public class LoanProductServiceTest {

	private DefaultLoanProductService loanProductService;
	
	private String longName1 = "long name 1";
	private String shortName1 = "short name 1";
	private Double maxInt1 = 1.0;
	private Double minInt1 = 2.0;
	private LoanProductStatus status1 = LoanProductStatus.ACTIVE;

	private Object newMinInt;
	
	
	@BeforeMethod
	private void setUp() {
		loanProductService = new DefaultLoanProductService();
		loanProductService.setLoanProductDao (new InMemoryLoanProductDao());
	}
	
	@Test(groups="unit")
	public void testNewLoanProduct() {
		LoanProductDto testLoanProductDto = createTestLoanProductDto();
		assertSameProductDto (loanProductService.newLoanProduct(createTestLoanProductDto()),
							  testLoanProductDto);
		Assert.assertEquals(loanProductService.getLoanProducts().size(), 1, "Expected one loan product");
		assertSameProductDto ((LoanProductDto)loanProductService.getLoanProducts().get(0), testLoanProductDto);
	}
	
	@Test(groups="unit")
	public void testUpdateLoanProduct() {
		LoanProductDto testLoanProductDto = createTestLoanProductDto();
		loanProductService.newLoanProduct(testLoanProductDto);
		//change all but short name, the business key
		LoanProductDto changedLoanProductDto = new LoanProductDto("new long name", testLoanProductDto.getShortName(),
															3.5, 4.5, LoanProductStatus.INACTIVE);
		assertSameProductDto(loanProductService.updateLoanProduct(changedLoanProductDto), changedLoanProductDto);
		// just to be sure, try retrieving the object directly and comparing -- specifying short name should retrieve
		// the updated version.
		assertSameProductDto(loanProductService.getLoanProduct(testLoanProductDto.getShortName()), changedLoanProductDto);
	}
	
	@Test(groups="unit")
	public void testDeleteLoanProduct() {
		LoanProductDto testLoanProductDto = createTestLoanProductDto();
		loanProductService.newLoanProduct(testLoanProductDto);
		loanProductService.deleteLoanProduct(testLoanProductDto.getShortName());
		Assert.assertEquals(loanProductService.getLoanProducts().size(), 0);		
	}
	
	@Test(groups="unit")
	public void testReadEmptyRepository() {
		Assert.assertEquals(loanProductService.getLoanProducts().size(), 0);
	}

	private LoanProductDto createTestLoanProductDto() {
		LoanProductDto loanProductDTO = new LoanProductDto(longName1, shortName1, minInt1, maxInt1, status1);
		return loanProductDTO;
	}
	
	private void assertSameProductDto (LoanProductDto actual, LoanProductDto expected) {
		Assert.assertEquals(actual.getLongName(), expected.getLongName(), "Wrong long name");
		Assert.assertEquals(actual.getShortName(), expected.getShortName(), "Wrong short name");
		Assert.assertEquals(actual.getMinInterestRate(), expected.getMinInterestRate(), 0, "Wrong min interest rate");
		Assert.assertEquals(actual.getMaxInterestRate(), expected.getMaxInterestRate(), 0, "Wrong max interest rate");
		Assert.assertEquals(actual.getStatus(), expected.getStatus(), "Wrong status");
	}
}
