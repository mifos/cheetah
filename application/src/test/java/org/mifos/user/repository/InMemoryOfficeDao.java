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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mifos.user.domain.Office;
import org.mifos.user.domain.OfficeLevel;

/**
 *
 */
public class InMemoryOfficeDao implements OfficeDao {
    private int nextId = 1;
    
    private final Map<Integer, Office> store = new HashMap<Integer, Office>(); 
    private Office headOffice = null;
    
    
    public InMemoryOfficeDao(OfficeLevel headOfficeLevel, OfficeLevel branchOfficeLevel) {
        generateDefaultOffices(headOfficeLevel, branchOfficeLevel);
    }
    
    private void generateDefaultOffices(OfficeLevel headOfficeLevel, OfficeLevel branchOfficeLevel) {
        Office headOffice = new Office(Office.DEFAULT_HEAD_OFFICE_NAME, headOfficeLevel, null);
        headOffice.setId(generateNextId());
        
        store.put(headOffice.getId(), headOffice);
        this.headOffice = headOffice;

        Office branchOffice = new Office(Office.DEFAULT_BRANCH_OFFICE_NAME, branchOfficeLevel, headOffice);
        branchOffice.setId(generateNextId());   

        store.put(branchOffice.getId(), branchOffice);
    }
    
    private int generateNextId() {
        synchronized(this) {
            return nextId++;
        }
    }

    /* (non-Javadoc)
     * @see org.mifos.user.repository.OfficeDao#getHeadOffice()
     */
    @Override
    public Office getHeadOffice() {
        return headOffice;
    }

    @Override
    public List<Office> getAll() {
        return new ArrayList<Office>(store.values());
    }

}
