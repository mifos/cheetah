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

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="loanproducts")
public class LoanProduct {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String longName;
	private String shortName;
	private Double minInterestRate;
	private Double maxInterestRate;
	@Enumerated(value=EnumType.STRING)
	private LoanProductStatus status;
	@Enumerated(value=EnumType.STRING)
    private DeletedStatus deletedStatus;
	
    public Integer getId() {
		return id;
	}

	public String getLongName() {
		return longName;
	}

	public String getShortName() {
		return shortName;
	}

	public Double getMinInterestRate() {
		return minInterestRate;
	}

	public Double getMaxInterestRate() {
		return maxInterestRate;
	}

	public LoanProductStatus getStatus() {
		return status;
	}
    
    public DeletedStatus getDeletedStatus() {
        return deletedStatus;
    }

    public void setDeletedStatus(DeletedStatus deleted) {
        this.deletedStatus = deleted;
    }

    public LoanProduct() {
    	//Spring framework needs a no-argument constructor
    	//By including this comment, PMD will not flag the empty constructor
}
 
	public LoanProduct (Integer id, String longName, String shortName, Double minInterestRate,
						Double maxInterestRate, LoanProductStatus status) {
		this.id = id;
		this.longName = longName;
		this.shortName = shortName;
		this.minInterestRate = minInterestRate;
		this.maxInterestRate = maxInterestRate;
		this.status = status;
		this.deletedStatus = DeletedStatus.VISIBLE;
	}
	
	public void update (String longName, String shortName, Double minInterestRate,
						Double maxInterestRate, LoanProductStatus status) {
        this.longName = longName;
        this.shortName = shortName;
        this.minInterestRate = minInterestRate;
        this.maxInterestRate = maxInterestRate;
        this.status = status;
	}
	
}
