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

package org.mifos.user.repository;

import java.util.List;

import org.mifos.user.domain.Office;
import org.testng.Assert;

/**
 *
 */
public class OfficeDaoTestHelper {

    private final OfficeDao officeDao;

	public OfficeDaoTestHelper(OfficeDao officeDao) {
	    this.officeDao = officeDao;
	}
	
	public void testGetHeadOffice() {
	    Office office = officeDao.getHeadOffice();
	    Assert.assertNotNull(office,"Expected head office but got none.");
	    
	    Assert.assertTrue(office.isHeadOffice(), "Expected this to be the head office .");
	    Assert.assertEquals(Office.DEFAULT_HEAD_OFFICE_NAME, office.getName());
	}

    public void testGetAll() {
        List<Office> offices = officeDao.getAll();
        Assert.assertEquals(offices.size(), 2);
        for (Office office : offices) {
            if (office.isHeadOffice()) {
                Assert.assertEquals(office.getName(), Office.DEFAULT_HEAD_OFFICE_NAME);
            } else {
                Assert.assertEquals(office.getName(), Office.DEFAULT_BRANCH_OFFICE_NAME);
            }
        }
    }
	
}
