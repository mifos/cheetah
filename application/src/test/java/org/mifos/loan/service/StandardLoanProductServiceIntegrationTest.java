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

import java.io.IOException;
import java.sql.SQLException;

import org.dbunit.DatabaseUnitException;
import org.dbunit.dataset.DataSetException;
import org.mifos.loan.domain.LoanProductStatus;
import org.mifos.test.framework.util.DatabaseTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@TransactionConfiguration(transactionManager="transactionManager")
@ContextConfiguration(locations={"classpath:integrationTestContext.xml"})
@Test(groups = { "integration" })
public class StandardLoanProductServiceIntegrationTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private LoanProductService loanProductService;
    @Autowired
    private DatabaseTestUtils databaseTestUtils;
    @Autowired
    private DriverManagerDataSource dataSource;

    private static final String longName1 = "long name 1";
    private static final String shortName1 = "short name 1";
    private static final Double maxInt1 = 1.0;
    private static final Double minInt1 = 2.0;
    private final LoanProductStatus status1 = LoanProductStatus.ACTIVE;

    private static  final String longName2 = "long name 2";
    private static  final String shortName2 = "short name 2";
    private static final Double maxInt2 = 3.0;
    private static final Double minInt2 = 4.0;
    private final LoanProductStatus status2 = LoanProductStatus.INACTIVE;
    
    private LoanProductDto testProduct1;
    private LoanProductDto testProduct2;
    
    @BeforeMethod
    public void setUp() throws DataSetException, IOException, SQLException, DatabaseUnitException {
        databaseTestUtils.deleteDataFromTables(this.dataSource, "loanProducts", "loans");
        testProduct1 = new LoanProductDto(longName1, shortName1, minInt1, maxInt1, status1);
        testProduct2 = new LoanProductDto(longName2, shortName2, minInt2, maxInt2, status2);
    }    
    
    public void testCreateLoanProduct() {
        assertSameState (loanProductService.createLoanProduct(testProduct1), testProduct1);
        Assert.assertEquals(loanProductService.getAll().size(), 1, "Expected one loan product");
        assertSameState ((LoanProductDto)loanProductService.getAll().get(0), testProduct1);
        assertSameState (loanProductService.createLoanProduct(testProduct2), testProduct2);
        Assert.assertEquals(loanProductService.getAll().size(), 2, "Expected two loan products");
        assertSameState ((LoanProductDto)loanProductService.getAll().get(1), testProduct2);
    }
    
    private void assertSameState (LoanProductDto actual, LoanProductDto expected) {
        Assert.assertEquals(actual.getLongName(), expected.getLongName(), "Wrong long name");
        Assert.assertEquals(actual.getShortName(), expected.getShortName(), "Wrong short name");
        Assert.assertEquals(actual.getMinInterestRate(), expected.getMinInterestRate(), 0, "Wrong min interest rate");
        Assert.assertEquals(actual.getMaxInterestRate(), expected.getMaxInterestRate(), 0, "Wrong max interest rate");
        Assert.assertEquals(actual.getStatus(), expected.getStatus(), "Wrong status");
    }

}
