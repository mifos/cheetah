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

import java.io.IOException;
import java.sql.SQLException;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.dbunit.DatabaseUnitException;
import org.dbunit.dataset.DataSetException;
import org.mifos.test.framework.util.SimpleDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@ContextConfiguration(locations={"classpath:integrationTestContext.xml"})
@TransactionConfiguration(transactionManager="transactionManager")
@Test(groups = { "integration" })
public class StandardOfficeLevelDaoTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private OfficeLevelDao officeLevelDao;
    @Resource(name="integrationTestDataSource")
    private DriverManagerDataSource dataSource;

    private OfficeLevelDaoTestHelper officeLevelDaoTestHelper;

    @BeforeMethod
    void setUp() throws DataSetException, IOException, SQLException, DatabaseUnitException {
        officeLevelDaoTestHelper = new OfficeLevelDaoTestHelper(officeLevelDao);
        Assert.assertNotNull(this.dataSource);
        insertOfficeLevelDataSet();
    }
    
    public void testGetHeadOfficeLevel() {
        officeLevelDaoTestHelper.testGetHeadOfficeLevel();
    }
    
    public void testGetBranchOfficeLevel() {
        officeLevelDaoTestHelper.testGetBranchOfficeLevel();
    }
    
    private void insertOfficeLevelDataSet() throws DataSetException, IOException, SQLException, DatabaseUnitException {
        SimpleDataSet simpleDataSet = new SimpleDataSet();
        simpleDataSet.enableColumnSensing();
        simpleDataSet.row("OFFICE_LEVEL", "ID=2", "NAME=Branch Office", "LEVEL_BELOW_ID=1"); 
        simpleDataSet.row("OFFICE_LEVEL", "ID=1", "NAME=Head Office", "LEVEL_ABOVE_ID=2"); 
        simpleDataSet.insert(this.dataSource);
    }
    
}
