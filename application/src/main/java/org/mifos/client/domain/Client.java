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

package org.mifos.client.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

@Entity
@Table(name="clients")
public class Client implements Serializable {

	private static final long serialVersionUID = -8583711590279795781L;

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

	private String firstName;
	private String lastName;

	@Column
	@Type(type="org.joda.time.contrib.hibernate.PersistentLocalDate")
	private LocalDate dateOfBirth;

	protected Client() {
		// empty constructor for Hibernate
	}
	
	public Client(Integer id, String firstName, String lastName, LocalDate dateOfBirth) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = new LocalDate(dateOfBirth);
	}

    public Integer getId() {
    	return id;
    }
    
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
	public LocalDate getDateOfBirth() {
		return new LocalDate(this.dateOfBirth);
	}
	
}
