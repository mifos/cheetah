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
package org.mifos.loan.repository;

import java.util.List;

import junit.framework.Assert;

import org.mifos.loan.domain.LoanProduct;
import org.mifos.loan.domain.LoanProductStatus;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test(groups = { "unit" })
public class InMemoryLoanProductDaoTest {

	private LoanProductDao loanProductDao;

    private static final String longName = "long name 1";
    private static final Double maxInterestRate = 1.0;
    private static final Double minInterestRate = 2.0;

	
	@BeforeMethod
	void setUp() {
		loanProductDao = new InMemoryLoanProductDao();
	}
	
	
	@Test(groups = { "unit" })
	public void testCreateLoanProduct() {
		String shortName = "short name 1";
		LoanProductStatus status = LoanProductStatus.ACTIVE;
		
		LoanProduct newLoanProduct = loanProductDao.createLoanProduct(longName, shortName, minInterestRate, 
				                                                      maxInterestRate, status);
		assert(newLoanProduct.getId().equals(1));
		assert(newLoanProduct.getLongName().equals(longName));
		assert(newLoanProduct.getShortName().equals(shortName));
		assert(newLoanProduct.getMinInterestRate().equals(minInterestRate));
		assert(newLoanProduct.getMaxInterestRate().equals(maxInterestRate));
		assert(newLoanProduct.getStatus().equals(status));
		LoanProduct anotherLoanProduct = loanProductDao.createLoanProduct(longName, shortName, minInterestRate, 
				                                                          maxInterestRate, status);
		assert(anotherLoanProduct.getId().equals(2));
		}

    @Test(groups = { "unit" })
    public void testDeleteLoanProduct() {
        LoanProductStatus status = LoanProductStatus.ACTIVE;
        LoanProduct newLoanProduct = loanProductDao.createLoanProduct(longName, "short-name-1", minInterestRate, 
                                                                      maxInterestRate, status);
        LoanProduct anotherLoanProduct = loanProductDao.createLoanProduct(longName, "short-name-2", minInterestRate, 
                                                                          maxInterestRate, status);
        
        Assert.assertEquals(2, loanProductDao.getLoanProducts().size());
        loanProductDao.deleteLoanProduct(newLoanProduct);
        List<LoanProduct> loanProducts = loanProductDao.getLoanProducts();
        Assert.assertEquals(1, loanProducts.size());
        Assert.assertEquals(anotherLoanProduct.getId(), loanProducts.get(0).getId());
    }

}