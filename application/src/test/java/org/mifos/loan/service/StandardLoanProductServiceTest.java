/*
 * Copyright (c) 2005-2008 Grameen Foundation USA
 * All rights reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * permissions and limitations under the License.
 * 
 * See also http://www.apache.org/licenses/LICENSE-2.0.html for an
 * explanation of the license and how it is applied.
 */
package org.mifos.loan.service;

import org.mifos.core.MifosServiceException;
import org.mifos.loan.domain.LoanProductStatus;
import org.mifos.loan.repository.InMemoryLoanDao;
import org.mifos.loan.repository.InMemoryLoanProductDao;
import org.mifos.loan.repository.LoanDao;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import edu.umd.cs.findbugs.annotations.SuppressWarnings;

@SuppressWarnings(value={"UrF"})
@Test(groups="unit")
public class StandardLoanProductServiceTest {

	private StandardLoanProductService loanProductService;
	
	private static final String longName1 = "long name 1";
	private static final String shortName1 = "short name 1";
	private static final Double maxInt1 = 1.0;
	private static final Double minInt1 = 2.0;
	private final LoanProductStatus status1 = LoanProductStatus.ACTIVE;

	private static  final String longName2 = "long name 2";
	private static  final String shortName2 = "short name 2";
	private static final Double maxInt2 = 3.0;
	private static final Double minInt2 = 4.0;
	private final LoanProductStatus status2 = LoanProductStatus.INACTIVE;
	
	private LoanProductDto testProduct1;
	private LoanProductDto testProduct2;

	@BeforeMethod
	private void setUp() {
		loanProductService = new StandardLoanProductService();
		LoanDao loanDao = new InMemoryLoanDao();
		loanProductService.setLoanDao(loanDao);
		loanProductService.setLoanProductDao (new InMemoryLoanProductDao());
		testProduct1 = new LoanProductDto(longName1, shortName1, minInt1, maxInt1, status1);
		testProduct2 = new LoanProductDto(longName2, shortName2, minInt2, maxInt2, status2);
	}
	
	public void testCreateLoanProduct() {
		assertSameState (loanProductService.createLoanProduct(testProduct1),
							  testProduct1);
		Assert.assertEquals(loanProductService.getAll().size(), 1, "Expected one loan product");
		assertSameState ((LoanProductDto)loanProductService.getAll().get(0), testProduct1);
	}
	
	public void testUpdateLoanProduct() {
		LoanProductDto retrievedOnCreate = loanProductService.createLoanProduct(testProduct1);
		//change all attributes
		LoanProductDto changed = new LoanProductDto("new long name", "new short name",
													 3.5, 4.5, LoanProductStatus.INACTIVE);
		changed.setId(retrievedOnCreate.getId());											 
		assertSameState(loanProductService.updateLoanProduct(changed), changed);
		assertSameState(loanProductService.getLoanProduct(retrievedOnCreate.getId()), changed);
	}
	
	public void testDeleteLoanProduct() throws MifosServiceException {
		LoanProductDto testLoanProductDto = createTestLoanProductDto1();
		loanProductService.deleteLoanProduct(testLoanProductDto);
		Assert.assertEquals(loanProductService.getAll().size(), 0);		
	}
	
	public void testGetAllEmptyRepository() {
		Assert.assertEquals(loanProductService.getAll().size(), 0);
	}
	
	public void testGetWithOneMember() {
		LoanProductDto testLoanProductDto = createTestLoanProductDto1();
		LoanProductDto retrievedLoanProductDto = loanProductService.createLoanProduct(testLoanProductDto);
		Assert.assertEquals(loanProductService.getAll().size(), 1);
		assertSameState(loanProductService.getLoanProduct(retrievedLoanProductDto.getId()), 
									   testLoanProductDto);
	}
	
	public void testGetWithTwoMembers() {
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
