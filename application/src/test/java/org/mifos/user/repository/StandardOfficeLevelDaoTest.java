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

package org.mifos.user.repository;

import java.math.BigDecimal;
import java.util.List;

import org.joda.time.LocalDate;
import org.mifos.loan.domain.Loan;
import org.mifos.loan.domain.LoanProduct;
import org.mifos.loan.domain.LoanProductStatus;
import org.mifos.user.domain.OfficeLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 */

@ContextConfiguration(locations={"classpath:integrationTestContext.xml"})
@TransactionConfiguration(transactionManager="transactionManager")
@Test(groups = { "integration","workInProgress" })
public class StandardOfficeLevelDaoTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private OfficeLevelDao officeLevelDao;
    private OfficeLevelDaoTestHelper officeLevelDaoTestHelper;

    @BeforeMethod
    void setUp() {
        officeLevelDaoTestHelper = new OfficeLevelDaoTestHelper(officeLevelDao);
    }
    
    public void testGetHeadOfficeLevel() {
        officeLevelDaoTestHelper.testGetHeadOfficeLevel();
    }
    
    public void testGetBranchOfficeLevel() {
        officeLevelDaoTestHelper.testGetBranchOfficeLevel();
    }
    

}
