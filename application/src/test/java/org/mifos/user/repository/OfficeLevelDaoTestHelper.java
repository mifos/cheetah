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

import org.mifos.user.domain.OfficeLevel;
import org.testng.Assert;

/**
 *
 */
public class OfficeLevelDaoTestHelper {

	private final OfficeLevelDao officeLevelDao;

	public OfficeLevelDaoTestHelper(OfficeLevelDao officeLevelDao) {
	    this.officeLevelDao = officeLevelDao;
	}
	
	public void testGetHeadOfficeLevel() {
	    OfficeLevel officeLevel = officeLevelDao.getHeadOfficeLevel();
	    Assert.assertNotNull(officeLevel,"Expected head office level but got none.");
	    
	    Assert.assertNull(officeLevel.getLevelAbove(), "Head office should have no office level above it.");
	    Assert.assertTrue(officeLevel.isHeadOfficeLevel(), "Expected this to be the head office level.");	    
	}
	
    public void testGetBranchOfficeLevel() {
        OfficeLevel officeLevel = officeLevelDao.getBranchOfficeLevel();
        Assert.assertNotNull(officeLevel,"Expected branch office level but got none.");
        
        Assert.assertNull(officeLevel.getLevelBelow(), "The branch office should have no office level below it.");
        Assert.assertTrue(officeLevel.isBranchOfficeLevel(), "Expected this to be the branch office level.");     
    }
    

}
