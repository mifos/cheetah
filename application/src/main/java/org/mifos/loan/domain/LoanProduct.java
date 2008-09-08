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

public class LoanProduct {
	
	private String longName;
	private String shortName;
	private Double minInterestRate;
	private Double maxInterestRate;
	private LoanProductStatus status;
	
	public String getLongName() {
		return longName;
	}
	public void setLongName(String longName) {
		this.longName = longName;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public Double getMinInterestRate() {
		return minInterestRate;
	}
	public void setMinInterestRate(Double minInterestRate) {
		this.minInterestRate = minInterestRate;
	}
	public Double getMaxInterestRate() {
		return maxInterestRate;
	}
	public void setMaxInterestRate(Double maxInterestRate) {
		this.maxInterestRate = maxInterestRate;
	}
	public LoanProductStatus getStatus() {
		return status;
	}
	public void setStatus(LoanProductStatus status) {
		this.status = status;
	}

	public LoanProduct (String longName, String shortName, Double minInterestRate,
						Double maxInterestRate, LoanProductStatus status) {
		this.longName = longName;
		this.shortName = shortName;
		this.minInterestRate = minInterestRate;
		this.maxInterestRate = maxInterestRate;
		this.status = status;
	}
	
}