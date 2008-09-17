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

import net.sf.dozer.util.mapping.DozerBeanMapper;

import org.mifos.core.MifosServiceException;
import org.mifos.loan.repository.InMemoryLoanDao;
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

    
	static final int LOAN_PRODUCT_ID = 1;
	static final BigDecimal LOAN_AMOUNT = new BigDecimal("1200");
	static final BigDecimal LOAN_INTEREST_RATE = new BigDecimal("12");
	
	@SuppressWarnings("PMD.UrF")
	@BeforeMethod
	public void setUp() {
		loanService = new StandardLoanService();
		loanService.setLoanDao(new InMemoryLoanDao());
		loanService.setBeanMapper(new DozerBeanMapper());
		loanService.setValidator(validator);
	}
	
	public void testCreateValidLoan() throws MifosServiceException {

		LoanDto inputLoanDto = new LoanDto(LOAN_AMOUNT, LOAN_INTEREST_RATE, LOAN_PRODUCT_ID);
		
		LoanDto loanDto = loanService.createLoan(inputLoanDto);
		
		assert(loanDto.getId() == 1);
		assert(loanDto.getLoanProductId() == LOAN_PRODUCT_ID);
		assert(loanDto.getAmount() == LOAN_AMOUNT);
		assert(loanDto.getInterestRate() == LOAN_INTEREST_RATE);
	}
	
	public void testCreateLoanWithInvalidLoanProduct() {
		LoanDto inputLoanDto = new LoanDto(LOAN_AMOUNT, LOAN_INTEREST_RATE, null);
		
		try {
			loanService.createLoan(inputLoanDto);
			Assert.fail("Null LoanProductId should have failed validation.");
		} catch (MifosServiceException mifosServiceException) {
			assert(mifosServiceException.getErrors().getErrorCount() == 1);
			FieldError error = (FieldError)mifosServiceException.getErrors().getFieldErrors().get(0);
			assert(error.getField().equals("loanProductId"));
			assert(error.getDefaultMessage().equals("not.null"));
		}
	}

	public void testCreateLoanWithInvalidLoanAmount() {
		LoanDto inputLoanDto = new LoanDto(new BigDecimal("-1"), LOAN_INTEREST_RATE, LOAN_PRODUCT_ID);
		
		try {
			loanService.createLoan(inputLoanDto);
			Assert.fail("Bad loan amount should have failed validation.");
		} catch (MifosServiceException mifosServiceException) {
			assert(mifosServiceException.getErrors().getErrorCount() == 1);
			FieldError error = (FieldError)mifosServiceException.getErrors().getFieldErrors().get(0);
			assert(error.getField().equals("amount"));
			assert(error.getDefaultMessage().equals("min"));
		}
	}

	public void testCreateLoanWithInvalidInterestRate() {
		LoanDto inputLoanDto = new LoanDto(LOAN_AMOUNT, new BigDecimal("-1"), LOAN_PRODUCT_ID);
		
		try {
			loanService.createLoan(inputLoanDto);
			Assert.fail("Bad interest rate should have failed validation.");
		} catch (MifosServiceException mifosServiceException) {
			assert(mifosServiceException.getErrors().getErrorCount() == 1);
			FieldError error = (FieldError)mifosServiceException.getErrors().getFieldErrors().get(0);
			assert(error.getField().equals("interestRate"));
			assert(error.getDefaultMessage().equals("min"));
		}
	}
	
	@Autowired
    @Test(enabled = false)
	public void setValidator(Validator validator) {
		this.validator = validator;
	}
	
}
