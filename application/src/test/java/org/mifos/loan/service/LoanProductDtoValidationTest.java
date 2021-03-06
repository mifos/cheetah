/*
 * Copyright (c) 2005-2009 Grameen Foundation USA
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

import org.apache.log4j.Logger;
import org.mifos.core.AbstractDtoValidationTest;
import org.mifos.loan.domain.LoanProductStatus;
import org.springframework.validation.Errors;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test(groups = { "unit" })
public class LoanProductDtoValidationTest  extends AbstractDtoValidationTest {
	
    private static final Logger logger = Logger.getLogger(LoanProductDtoValidationTest.class);
    
    private static final String name20 = "01234567890123456789";
    private static final String name100 = name20 + name20 + name20 + name20 + name20;
    private static final String name255 = name100 + name100 + name20 + name20 + "012345678901234";
    private static final String name256 = name255 + "5";

	private LoanProductDto loanProductDto;
	
	@BeforeMethod
	public void setup() {
		loanProductDto = validLoanProductDto();
	}
	
	public void testValidInputs() {
		verifyNoErrors(loanProductDto);
	}

	public void testBlankLongName () {
		loanProductDto.setLongName("");
		verifyFieldError(loanProductDto, "longName", "not.blank");
	}

	public void testNullLongName () {
		loanProductDto.setLongName(null);
		verifyFieldError(loanProductDto, "longName", "not.null");
	}
	
	public void testLongestLegalLongName () {
	    loanProductDto.setLongName(name255);
	    verifyNoErrors(loanProductDto);
	}
    
    public void testTooLongLongName () {
        loanProductDto.setLongName(name256);
        verifyFieldError(loanProductDto, "longName", "max.length");
    }
    
    public void testTooLongShortName () {
        loanProductDto.setShortName("12345");
        verifyFieldError(loanProductDto, "shortName", "max.length");
    }

	public void testNullMinInterestRate () {
		loanProductDto.setMinInterestRate(null);
		verifyFieldError(loanProductDto, "minInterestRate", "not.null");
	}

	public void testNegativeMinInterestRate () {
		loanProductDto.setMinInterestRate(-1.1);
		verifyFieldError(loanProductDto, "minInterestRate", "min");
	}
	
	public void testNullMaxInterestRate () {
		loanProductDto.setMaxInterestRate(null);
		verifyFieldError(loanProductDto, "maxInterestRate", "not.null");
	}
	
	public void testNegativeMaxInterestRate () {
		loanProductDto.setMaxInterestRate(-1.1);
		verifyFieldError(loanProductDto, "maxInterestRate", "min");
	}
	
	public void testTooBigMaxInterestRate () {
        loanProductDto.setMaxInterestRate(1000.0);
        verifyFieldError(loanProductDto, "maxInterestRate", "max");	    
	}

	public void testNullStatus () {
		loanProductDto.setStatus(null);
		verifyFieldError(loanProductDto, "status", "not.null");
	}
	
	public void testMinInterestRate () {
		loanProductDto.setMinInterestRate(5.0);
		Errors errors = getErrors(loanProductDto);
		logger.info(errors);
		Assert.assertTrue(errors.getErrorCount() > 0, "Expected errors but got none.");
	}
	
	private LoanProductDto validLoanProductDto() {
		loanProductDto = new LoanProductDto();
		loanProductDto.setLongName("a long name");
		loanProductDto.setShortName("shrt");
		loanProductDto.setMinInterestRate(1.1);
		loanProductDto.setMaxInterestRate(2.2);
		loanProductDto.setStatus(LoanProductStatus.ACTIVE);
		return loanProductDto;
	}
	
}
