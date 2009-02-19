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

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.dbunit.DatabaseUnitException;
import org.dbunit.dataset.DataSetException;
import org.joda.time.LocalDate;
import org.mifos.client.service.ClientDto;
import org.mifos.client.service.ClientService;
import org.mifos.core.MifosServiceException;
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

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private DriverManagerDataSource dataSource;
    @Autowired
    private LoanProductService loanProductService;
    @Autowired
    private LoanService loanService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private DatabaseTestUtils databaseTestUtils;
    

    private static final String longName1 = "long name 1";
    private static final String shortName1 = "short name 1";
    private static final Double maxInt1 = 2.0;
    private static final Double minInt1 = 1.0;
    private final LoanProductStatus status1 = LoanProductStatus.ACTIVE;

    private static  final String longName2 = "long name 2";
    private static  final String shortName2 = "short name 2";
    private static final Double maxInt2 = 4.0;
    private static final Double minInt2 = 3.0;
    private final LoanProductStatus status2 = LoanProductStatus.INACTIVE;
    
    private LoanProductDto testLoanProductDto1;
    private LoanProductDto testLoanProductDto2;
    
    @BeforeMethod
    public void setUp() throws DataSetException, IOException, SQLException, DatabaseUnitException {
        databaseTestUtils.deleteDataFromTables(this.dataSource, "loanProducts", "loans", "clients");
        testLoanProductDto1 = new LoanProductDto(longName1, shortName1, minInt1, maxInt1, status1);
        testLoanProductDto2 = new LoanProductDto(longName2, shortName2, minInt2, maxInt2, status2);
    }    
    
    public void testCreateLoanProduct() {
        LoanProductServiceTestHelper.assertSameState (loanProductService.createLoanProduct(testLoanProductDto1), testLoanProductDto1);
        Assert.assertEquals(loanProductService.getAll().size(), 1, "Expected one loan product");
        LoanProductServiceTestHelper.assertSameState ((LoanProductDto)loanProductService.getAll().get(0), testLoanProductDto1);
        LoanProductServiceTestHelper.assertSameState (loanProductService.createLoanProduct(testLoanProductDto2), testLoanProductDto2);
        Assert.assertEquals(loanProductService.getAll().size(), 2, "Expected two loan products");
        LoanProductServiceTestHelper.assertSameState ((LoanProductDto)loanProductService.getAll().get(1), testLoanProductDto2);
    }
    
    public void testDeleteLoanProductWithNoLoans() throws MifosServiceException {
        LoanProductDto testProduct1WithId = loanProductService.createLoanProduct(testLoanProductDto1);
        LoanProductDto testProduct2WithId = loanProductService.createLoanProduct(testLoanProductDto2);
        Assert.assertEquals(loanProductService.getAll().size(), 2, "Expected two loan products");
        loanProductService.deleteLoanProduct(testProduct1WithId);
        Assert.assertEquals(loanProductService.getAll().size(), 1, "Expected one loan product");
        loanProductService.deleteLoanProduct(testProduct2WithId);
        Assert.assertEquals(loanProductService.getAll().size(), 0, "Expected zero loan products");
    }

    public void testDeleteLoanProductWithExistingLoans() throws MifosServiceException {
        testLoanProductDto1 = loanProductService.createLoanProduct(testLoanProductDto1);
        Assert.assertNotNull(testLoanProductDto1.getId());
        testLoanProductDto2 = loanProductService.createLoanProduct(testLoanProductDto2);
        ClientDto testClientDto = new ClientDto("John", "Ya-ya", new LocalDate());
        testClientDto = clientService.createClient(testClientDto);
        BigDecimal amount = new BigDecimal("1000");
        BigDecimal interestRate = new BigDecimal("1.2");
        Assert.assertNotNull(loanProductService.getLoanProduct(testLoanProductDto1.getId()));
        LoanDto testLoanDto = new LoanDto(testClientDto.getId(), amount, interestRate, testLoanProductDto1.getId());
        testLoanDto.setLoanProductDto(testLoanProductDto1);
        loanService.createLoan(testLoanDto);
        Assert.assertEquals(loanProductService.getAll().size(), 2, "Expected two loan products");
        loanProductService.deleteLoanProduct(testLoanProductDto2);
        Assert.assertEquals(loanProductService.getAll().size(), 1, "Expected one loan product");
        try {
            loanProductService.deleteLoanProduct(testLoanProductDto1);
            entityManager.flush();
            Assert.fail("Should have raised exception.");
        } catch (MifosServiceException e) {
            Assert.assertNotNull(e, "Expected to get an exception.");
        }
    }

}
