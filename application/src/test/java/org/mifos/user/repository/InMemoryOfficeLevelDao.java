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

import java.util.HashMap;
import java.util.Map;

import org.mifos.user.domain.OfficeLevel;

/**
 *
 */
public class InMemoryOfficeLevelDao implements OfficeLevelDao {

    private int nextId = 1;
    
    private final Map<Integer, OfficeLevel> store = new HashMap<Integer, OfficeLevel>(); 
    private OfficeLevel headOfficeLevel = null;
    private OfficeLevel branchOfficeLevel = null;
    
    public InMemoryOfficeLevelDao() {
        generateDefaultOffices();
    }
    
    private void generateDefaultOffices() {
        OfficeLevel headOfficeLevel = new OfficeLevel("Default Head Office Name", null, null);
        headOfficeLevel.setId(generateNextId());
        
        store.put(headOfficeLevel.getId(), headOfficeLevel);
        this.headOfficeLevel = headOfficeLevel;

        OfficeLevel branchOfficeLevel = new OfficeLevel("Default Branch Office Name", headOfficeLevel, null);
        branchOfficeLevel.setId(generateNextId());   
        headOfficeLevel.setLevelBelow(branchOfficeLevel);

        store.put(branchOfficeLevel.getId(), branchOfficeLevel);
        this.branchOfficeLevel = branchOfficeLevel;
    }
    
    private int generateNextId() {
        synchronized(this) {
            return nextId++;
        }
    }

    
    /* (non-Javadoc)
     * @see org.mifos.user.repository.OfficeLevelDao#getBranchOfficeLevel()
     */
    @Override
    public OfficeLevel getBranchOfficeLevel() {
        return branchOfficeLevel;
    }

    /* (non-Javadoc)
     * @see org.mifos.user.repository.OfficeLevelDao#getHeadOfficeLevel()
     */
    @Override
    public OfficeLevel getHeadOfficeLevel() {
        return headOfficeLevel;
    }

}
