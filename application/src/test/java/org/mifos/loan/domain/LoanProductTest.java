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

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoanProductTest {
	
	@Test
	public void testConstructAValidLoanProduct() {
		Integer id = 3;
		String longName = "A Very Long Name";
		String shortName = "sn";
		Double minInterestRate = 1.2;
		Double maxInterestRate = 10.6;
		LoanProductStatus status = LoanProductStatus.ACTIVE;
		LoanProduct loanProduct = new LoanProduct(id, longName,shortName,minInterestRate, maxInterestRate,status);
		Assert.assertEquals(loanProduct.getId(), id);
		Assert.assertEquals(loanProduct.getLongName(), longName);
		Assert.assertEquals(loanProduct.getShortName(), shortName);
		Assert.assertEquals(loanProduct.getMinInterestRate(), minInterestRate);
		Assert.assertEquals(loanProduct.getMaxInterestRate(), maxInterestRate);
		Assert.assertEquals(loanProduct.getStatus(), status);
	}

}
