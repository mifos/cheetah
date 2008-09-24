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

import java.math.BigDecimal;

import junit.framework.Assert;
import net.sf.dozer.util.mapping.MapperIF;

import org.mifos.core.MifosServiceException;
import org.mifos.loan.domain.LoanProduct;
import org.mifos.loan.domain.LoanProductStatus;
import org.mifos.loan.repository.InMemoryLoanDao;
import org.mifos.loan.repository.InMemoryLoanProductDao;
import org.mifos.loan.repository.LoanProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@ContextConfiguration(locations={"classpath:unitTestContext.xml"})
@Test(groups = { "unit" })
public class StandardLoanServiceTest extends AbstractTestNGSpringContextTests {

	private LoanService loanService;
    Validator validator;

    @Autowired
    private MapperIF beanMapper;

	int loanProductId = 1;	
	LoanProductDto loanProductDto = null;
	static final int CLIENT_ID = 1;
	static final BigDecimal LOAN_AMOUNT = new BigDecimal("1200");
	static final BigDecimal LOAN_INTEREST_RATE = new BigDecimal("12");
	
	static final String LOAN_PRODUCT_LONG_NAME = "loan prod 1";
	static final String LOAN_PRODUCT_SHORT_NAME = "lp1";
	static final Double lOAN_PRODUCT_MIN_INTEREST = 1.0;
	static final Double lOAN_PRODUCT_MAX_INTEREST = 20.0;
	
	@SuppressWarnings("PMD.UrF")
	@BeforeMethod
	public void setUp() {
		LoanProductDao loanProductDao = new InMemoryLoanProductDao();
		LoanProduct loanProduct = loanProductDao.createLoanProduct(LOAN_PRODUCT_LONG_NAME, LOAN_PRODUCT_SHORT_NAME,
				lOAN_PRODUCT_MIN_INTEREST, lOAN_PRODUCT_MAX_INTEREST, LoanProductStatus.ACTIVE);
		loanProductDto = new LoanProductDto(loanProduct.getLongName(), loanProduct.getShortName(), loanProduct.getMinInterestRate(),
				loanProduct.getMaxInterestRate(), loanProduct.getStatus());
		loanProductId = loanProduct.getId();
		
		loanService = new StandardLoanService();
		loanService.setLoanDao(new InMemoryLoanDao());
		loanService.setLoanProductDao(loanProductDao);
		loanService.setBeanMapper(beanMapper);
		loanService.setValidator(validator);
	}
	
	private LoanDto createValidLoanDto() {
		LoanDto loanDto = new LoanDto(CLIENT_ID, LOAN_AMOUNT, LOAN_INTEREST_RATE, loanProductId);
		loanDto.setLoanProductDto(loanProductDto);
		return loanDto;
	}
	
	public void testCreateValidLoan() throws MifosServiceException {

		LoanDto inputLoanDto = createValidLoanDto();
		
		LoanDto loanDto = loanService.createLoan(inputLoanDto);
		
		Assert.assertEquals("Unexpected loan id assigned.", loanDto.getId().intValue(),1);
		Assert.assertEquals("Didn't get clientId back.", loanDto.getClientId().intValue(),CLIENT_ID);		
		Assert.assertEquals("Didn't get loanProductId back.",loanDto.getLoanProductId().intValue(),loanProductId);
		Assert.assertEquals("Didn't get loan amount back.",loanDto.getAmount(), LOAN_AMOUNT);
		Assert.assertEquals("Didn't get interestRate back.",loanDto.getInterestRate(), LOAN_INTEREST_RATE);
	}

	private void assertExpectedError(MifosServiceException mifosServiceException, String fieldName, String defaultErrorMessage) {
		Assert.assertEquals(1, mifosServiceException.getErrors().getErrorCount());
		FieldError error = (FieldError)mifosServiceException.getErrors().getFieldErrors().get(0);
		Assert.assertEquals(error.getField(), fieldName);
		Assert.assertEquals(error.getDefaultMessage(), defaultErrorMessage);		
	}
	
	public void testCreateLoanWithInvalidClient() {
		LoanDto inputLoanDto = createValidLoanDto();
		inputLoanDto.setClientId(null);
		
		try {
			loanService.createLoan(inputLoanDto);
			Assert.fail("Null ClientId should have failed validation.");
		} catch (MifosServiceException mifosServiceException) {
			assertExpectedError(mifosServiceException, "clientId", "not.null");
		}
	}

	public void testCreateLoanWithInvalidLoanProduct() {
		LoanDto inputLoanDto = createValidLoanDto();
		inputLoanDto.setLoanProductId(null);
		
		try {
			loanService.createLoan(inputLoanDto);
			Assert.fail("Null LoanProductId should have failed validation.");
		} catch (MifosServiceException mifosServiceException) {
			assertExpectedError(mifosServiceException, "loanProductId", "not.null");
		}
	}

	public void testCreateLoanWithInvalidLoanAmount() {
		LoanDto inputLoanDto = createValidLoanDto();
		inputLoanDto.setAmount(new BigDecimal("-1"));
		
		try {
			loanService.createLoan(inputLoanDto);
			Assert.fail("Bad loan amount should have failed validation.");
		} catch (MifosServiceException mifosServiceException) {
			assertExpectedError(mifosServiceException, "amount", "min");
		}
	}

	public void testCreateLoanWithInvalidLowInterestRate() {
		LoanDto inputLoanDto = createValidLoanDto();
		inputLoanDto.setInterestRate(new BigDecimal("0.5"));
		
		try {
			loanService.createLoan(inputLoanDto);
			Assert.fail("Bad interest rate should have failed validation.");
		} catch (MifosServiceException mifosServiceException) {
			assertExpectedError(mifosServiceException, "interestRate", "LoanDto.interestRateIsTooLow");
		}
	}

	public void testCreateLoanWithInvalidHighInterestRate() {
		LoanDto inputLoanDto = createValidLoanDto();
		inputLoanDto.setInterestRate(new BigDecimal("30"));
		
		try {
			loanService.createLoan(inputLoanDto);
			Assert.fail("Bad interest rate should have failed validation.");
		} catch (MifosServiceException mifosServiceException) {
			assertExpectedError(mifosServiceException, "interestRate", "LoanDto.interestRateIsTooHigh");
		}
	}
	
	@Autowired
    @Test(enabled = false)
	public void setValidator(Validator validator) {
		this.validator = validator;
	}
	
}
