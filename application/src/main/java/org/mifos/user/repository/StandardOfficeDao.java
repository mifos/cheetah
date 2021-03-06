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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.mifos.core.MifosRuntimeException;
import org.mifos.user.domain.Office;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 */
public class StandardOfficeDao implements OfficeDao {

    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    @Transactional(readOnly=true)
    public List<Office> getAll() {
        Query query = entityManager.createQuery("from Office");
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly=true)
    public Office getHeadOffice() {
        Query query = entityManager.createQuery("select office from Office office where office.parentOffice IS NULL");
        List<Office> result = query.getResultList();
        if (result.isEmpty()) {
            throw new MifosRuntimeException("Built in head office not found.");
        } else if (result.size() > 1) {
            throw new MifosRuntimeException("Multiple head offices found.");
        }
        return result.get(0);
    }
    
}
