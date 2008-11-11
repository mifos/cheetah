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

import org.mifos.loan.domain.LoanProduct;
import org.mifos.loan.domain.LoanProductStatus;
import org.testng.Assert;

/**
 *
 */
public class LoanProductDaoTestHelper {
    public static final String longName = "long name 1";
    public static final Double maxInterestRate = 1.0;
    public static final Double minInterestRate = 2.0;

    private final LoanProductDao loanProductDao;

    public LoanProductDaoTestHelper(LoanProductDao loanProductDao) {
        this.loanProductDao = loanProductDao;
    }
    
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
  
    public void testCreateOneProduct() {
        String longName = "long";
        String shortName = "short";
        Double minInterestRate = 1.0;
        Double maxInterestRate = 2.0;
        LoanProductStatus status = LoanProductStatus.ACTIVE;
        
        LoanProduct product = new LoanProduct(null, "long", "short", 1.0, 2.0, LoanProductStatus.ACTIVE);
        LoanProduct createdProduct = loanProductDao.createLoanProduct(longName, shortName, minInterestRate, 
                                                            maxInterestRate, status);
        assertSameState(createdProduct, product);
        Assert.assertNotNull(createdProduct.getId());
        LoanProduct retrievedProduct = loanProductDao.get(createdProduct.getId());
        assertSameState(retrievedProduct, product);
    }

    private void assertSameState (LoanProduct actual, LoanProduct expected) {
        Assert.assertEquals(actual.getLongName(), expected.getLongName(), "Wrong long name");
        Assert.assertEquals(actual.getShortName(), expected.getShortName(), "Wrong short name");
        Assert.assertEquals(actual.getMinInterestRate(), expected.getMinInterestRate(), 0, "Wrong min interest rate");
        Assert.assertEquals(actual.getMaxInterestRate(), expected.getMaxInterestRate(), 0, "Wrong max interest rate");
        Assert.assertEquals(actual.getStatus(), expected.getStatus(), "Wrong status");
    }
    
}
