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

package org.mifos.user.service;

import junit.framework.Assert;

import org.mifos.user.repository.InMemoryOfficeDao;
import org.mifos.user.repository.InMemoryOfficeLevelDao;
import org.mifos.user.repository.OfficeDao;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 */
@Test(groups={ "unit", "workInProgress" } )
public class StandardOfficeServiceTest {

    private static final String DEFAULT_HEAD_OFFICE_NAME = "Head Office";
    OfficeService standardOfficeService;
    
    @BeforeMethod
    void setUp() {
        InMemoryOfficeLevelDao officeLevelDao = new InMemoryOfficeLevelDao();
        OfficeDao officeDao = new InMemoryOfficeDao(officeLevelDao.getHeadOfficeLevel(), officeLevelDao.getBranchOfficeLevel());
        
        standardOfficeService = new StandardOfficeService();
        standardOfficeService.setOfficeDao(officeDao);
    }
    
    @Test
    public void testGetHeadOffice() {
        OfficeDto officeDto = standardOfficeService.getHeadOffice();
        Assert.assertTrue(officeDto.isHeadOffice());
        Assert.assertEquals(DEFAULT_HEAD_OFFICE_NAME, officeDto.getName());
    }
}
