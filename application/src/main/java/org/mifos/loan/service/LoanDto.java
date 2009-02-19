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

import java.math.BigDecimal;

import org.joda.time.LocalDate;
import org.springmodules.validation.bean.conf.loader.annotation.handler.Min;
import org.springmodules.validation.bean.conf.loader.annotation.handler.NotNull;
import org.springmodules.validation.bean.conf.loader.annotation.handler.Validator;

@Validator(LoanDtoValidator.class)
public class LoanDto {
	private Integer id;
	
	@NotNull
	private Integer clientId;
	@NotNull
	private Integer loanProductId;
	@NotNull
	@Min(value=0)
	private BigDecimal amount;
	@NotNull
	private BigDecimal interestRate;
    private LocalDate disbursalDate;

	private LoanProductDto loanProductDto;
	
	public LoanDto() {
		// empty constructor for dozer mapping
	}
	
	public LoanDto(Integer clientId, BigDecimal amount, BigDecimal interestRate,
			Integer loanProductId) {
		this.clientId = clientId;
		this.amount = amount;
		this.interestRate = interestRate;
		this.loanProductId = loanProductId;
	}

	public Integer getId() {
		return id;
	}

	public Integer getLoanProductId() {
		return loanProductId;
	}

	public void setLoanProductId(Integer loanProductId) {
		this.loanProductId = loanProductId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(BigDecimal interestRate) {
		this.interestRate = interestRate;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public LoanProductDto getLoanProductDto() {
		return loanProductDto;
	}

	public void setLoanProductDto(LoanProductDto loanProductDto) {
		this.loanProductDto = loanProductDto;
	}

    public LocalDate getDisbursalDate() {
        return disbursalDate;
    }

    public void setDisbursalDate(LocalDate disbursalDate) {
        this.disbursalDate = disbursalDate;
    }

	
	
}
