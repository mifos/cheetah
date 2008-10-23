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

package org.mifos.user.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * OfficeLevel represents one "level" in a hierarchy of OfficeLevels.
 * The root of the hierarchy is the head office level (with no levels above)
 * and the single leaf of the hierarchy is the branch office level (with
 * no levels below it).
 */
@Entity
@Table(name="OFFICE_LEVEL")
public class OfficeLevel {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)    
    private Integer id;
    private String name;
    @OneToOne
    @JoinColumn(name="LEVEL_ABOVE_ID", nullable=true)
    private OfficeLevel levelAbove;
    @OneToOne
    @JoinColumn(name="LEVEL_BELOW_ID", nullable=true)
    private OfficeLevel levelBelow;
    
    protected OfficeLevel() {
        // for hibernate
    }
    
    public OfficeLevel(String name, OfficeLevel levelAbove, OfficeLevel levelBelow) {
        this.name = name;
        this.levelAbove = levelAbove;
        this.levelBelow = levelBelow;
    }

    public OfficeLevel getLevelAbove() {
        return levelAbove;
    }

    public boolean isHeadOfficeLevel() {
        return levelAbove == null && levelBelow != null;
    }

    public Object getLevelBelow() {
        return levelBelow;
    }

    public boolean isBranchOfficeLevel() {
        return levelBelow == null && levelAbove != null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLevelAbove(OfficeLevel levelAbove) {
        this.levelAbove = levelAbove;
    }

    public void setLevelBelow(OfficeLevel levelBelow) {
        this.levelBelow = levelBelow;
    }

    
}
