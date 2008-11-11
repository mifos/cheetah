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

import org.mifos.core.AbstractDtoValidationTest;
import org.mifos.loan.domain.LoanProductStatus;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test(groups = { "unit" })
public class LoanDtoValidationTest  extends AbstractDtoValidationTest {
	private LoanDto loanDto;
	
	@BeforeMethod
	public void setup() {
		loanDto = validLoanDto();
	}
	
	public void testValidInputs() {
		verifyNoErrors(loanDto);
	}

	public void testNegativeAmount () {
		loanDto.setAmount(new BigDecimal("-1"));
		verifyFieldError(loanDto, "amount", "min");
	}

	public void testNullAmount () {
		loanDto.setAmount(null);
		verifyFieldError(loanDto, "amount", "not.null");
	}

	public void testNullInterestRate () {
		loanDto.setInterestRate(null);
		verifyFieldError(loanDto, "interestRate", "not.null");
	}

	
	public void testLowInterestRate() {
		loanDto.setInterestRate(BigDecimal.ONE);
		verifyFieldError(loanDto, "interestRate", "LoanDto.interestRateIsTooLow");
	}
	
	public void testHighInterestRate() {
		loanDto.setInterestRate(new BigDecimal("11"));
		verifyFieldError(loanDto, "interestRate", "LoanDto.interestRateIsTooHigh");
	}
	
	private LoanDto validLoanDto() {

		LoanProductDto loanProductDto = new LoanProductDto();
		loanProductDto.setId(1);
		loanProductDto.setLongName("long");
		loanProductDto.setShortName("short");
		loanProductDto.setMinInterestRate(5.0);
		loanProductDto.setMaxInterestRate(10.0);
		loanProductDto.setStatus(LoanProductStatus.ACTIVE);
		
		loanDto = new LoanDto();
		loanDto.setAmount(new BigDecimal("1000"));
		loanDto.setClientId(1);
		loanDto.setInterestRate(new BigDecimal("7"));
		loanDto.setLoanProductDto(loanProductDto);
		loanDto.setLoanProductId(loanProductDto.getId());
		
		return loanDto;
	}
	
}
