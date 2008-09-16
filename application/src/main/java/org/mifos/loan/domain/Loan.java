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

package org.mifos.loan.domain;

import java.math.BigDecimal;

/**
 *
 */
public class Loan {

	private Integer id;
	private Integer loanProductId;
	private BigDecimal amount;
	private BigDecimal interestRate;
	
	public Loan(BigDecimal amount, BigDecimal interestRate,
			Integer loanProductId) {
		this.amount = amount;
		this.interestRate = interestRate;
		this.loanProductId = loanProductId;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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

	
}
