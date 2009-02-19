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

package org.mifos.user.service;

import java.util.ArrayList;
import java.util.List;

import org.mifos.user.domain.Office;
import org.mifos.user.repository.OfficeDao;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 */
public class StandardOfficeService implements OfficeService {

    private OfficeDao officeDao;
    
    /* (non-Javadoc)
     * @see org.mifos.user.service.OfficeService#getAll()
     */
    @Transactional(readOnly=true)
    @Override
    public List<OfficeDto> getAll() {
        List<Office> offices = officeDao.getAll();
        List<OfficeDto> officeDtos = new ArrayList<OfficeDto>();
        for (Office office: offices) {
            officeDtos.add(mapOfficeToOfficeDto(office));
        }
        return officeDtos;
    }

    /* (non-Javadoc)
     * @see org.mifos.user.service.OfficeService#getHeadOffice()
     */
    @Transactional(readOnly=true)
    @Override
    public OfficeDto getHeadOffice() {
        return mapOfficeToOfficeDto(officeDao.getHeadOffice());
    }

    private OfficeDto mapOfficeToOfficeDto(Office office) {
        return new OfficeDto(office.getName(), office.isHeadOffice());
    }
    @Override
    public void setOfficeDao(OfficeDao officeDao) {
        this.officeDao = officeDao;
    }
    
}
