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

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.mifos.core.MifosRuntimeException;
import org.mifos.user.domain.OfficeLevel;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 */
public class StandardOfficeLevelDao implements OfficeLevelDao {

    @PersistenceContext
    private EntityManager entityManager;
    
        
    /* (non-Javadoc)
     * @see org.mifos.user.repository.OfficeLevelDao#getBranchOfficeLevel()
     */
    @Override
    @Transactional(readOnly=true)
    public OfficeLevel getBranchOfficeLevel() {
        Query query = entityManager.createQuery("select officeLevel from OfficeLevel officeLevel where officeLevel.levelBelow IS NULL");
        List<OfficeLevel> result = query.getResultList();
        if (result.isEmpty()) {
            throw new MifosRuntimeException("Built in branch office level not found.");
        } else if (result.size() > 1) {
            throw new MifosRuntimeException("Multiple branch office levels found.");
        }
        return result.get(0);
    }

    /* (non-Javadoc)
     * @see org.mifos.user.repository.OfficeLevelDao#getHeadOfficeLevel()
     */
    @Override
    @Transactional(readOnly=true)
    public OfficeLevel getHeadOfficeLevel() {
        Query query = entityManager.createQuery("select officeLevel from OfficeLevel officeLevel where officeLevel.levelAbove IS NULL");
        List<OfficeLevel> result = query.getResultList();
        if (result.isEmpty()) {
            throw new MifosRuntimeException("Built in head office level not found.");
        } else if (result.size() > 1) {
            throw new MifosRuntimeException("Multiple head office levels found.");
        }
        return result.get(0);
    }

    /* TODO: 2008/10/22 work in progress
    @Override
    public OfficeLevel create(String name) {
        OfficeLevel officeLevel = new OfficeLevel(name,null,null);
        entityManager.persist(officeLevel);
        return officeLevel;
    }
    */
}
