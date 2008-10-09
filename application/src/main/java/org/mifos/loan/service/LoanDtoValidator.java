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


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validates the interest rate min and max based on the loan product.
 *
 */
public class LoanDtoValidator implements Validator {

    private static final Log LOG = LogFactory.getLog(LoanDtoValidator.class);

    public boolean supports(Class clazz) {
        return LoanDto.class.equals(clazz);
    }

    public void validate(Object obj, Errors errors) {
        LoanDto loanDto = (LoanDto) obj;
        if (loanDto == null) {
            errors.rejectValue("loanDto", "error.not-specified", null, "error.not-specified");
        } else {
            LOG.info("Validating using LoanDtoValidator");
            if (loanDto.getInterestRate() == null) {
                LOG.info("Found null interest rate-- this should be covered by an annotation based check.");
            	return;
            } else {
            	if (loanDto.getInterestRate().doubleValue() > loanDto.getLoanProductDto().getMaxInterestRate()) {
            		errors.rejectValue("interestRate", "LoanDto.interestRateIsTooHigh",
            				new Object[] {loanDto.getLoanProductDto().getMaxInterestRate()},"LoanDto.interestRateIsTooHigh");
            	}
            	if (loanDto.getInterestRate().doubleValue() < loanDto.getLoanProductDto().getMinInterestRate()) {
            		errors.rejectValue("interestRate", "LoanDto.interestRateIsTooLow",
            				new Object[] {loanDto.getLoanProductDto().getMinInterestRate()},"LoanDto.interestRateIsTooLow");
            	}
            }
        }
    }

}

