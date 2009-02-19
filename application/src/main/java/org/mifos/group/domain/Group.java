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

package org.mifos.group.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="groups")
public class Group implements Serializable {

    private static final long serialVersionUID = -9118811711434470557L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
	private String name;

	protected Group() {
		// empty constructor for Hibernate
	}
	
	public Group(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

    public Integer getId() {
    	return id;
    }
    
	public String getName() {
		return this.name;
	}

}
