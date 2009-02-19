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

package org.mifos.user.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 */
@Entity
@Table(name="OFFICE")
public class Office {
    public static final String DEFAULT_HEAD_OFFICE_NAME = "Head Office";
    public static final String DEFAULT_BRANCH_OFFICE_NAME = "Branch Office";  
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ID")    
    private Integer id;
    @Column(name="NAME")
    private String name;
    @ManyToOne
    @JoinColumn(name="OFFICE_LEVEL_ID")
    private OfficeLevel officeLevel;
    @ManyToOne
    @JoinColumn(name="PARENT_OFFICE_ID", nullable=true)
    private Office parentOffice;
    
    protected Office() {
        // for hibernate
    }
    
    public Office(String name, OfficeLevel level, Office parentOffice) {
        this.name = name;
        this.officeLevel = level;
        this.parentOffice = parentOffice;
    }

    public boolean isHeadOffice() {
        return officeLevel.isHeadOfficeLevel();
    }

    public OfficeLevel getOfficeLevel() {
        return officeLevel;
    }

    public void setOfficeLevel(OfficeLevel level) {
        this.officeLevel = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Office getParentOffice() {
        return parentOffice;
    }

    public void setParentOffice(Office parentOffice) {
        this.parentOffice = parentOffice;
    }  

    
}
