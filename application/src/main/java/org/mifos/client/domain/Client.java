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

import org.joda.time.DateTime;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springmodules.validation.bean.conf.loader.annotation.handler.Length;
import org.springmodules.validation.bean.conf.loader.annotation.handler.NotBlank;

@Entity
@Table(name="products")
public class Client implements Serializable {

	private static final long serialVersionUID = -8583711590279795781L;

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

	@NotBlank  
	@Length(max = 80) 
	private String firstName;

	@NotBlank  
	@Length(max = 80) 
	private String lastName;

	private DateTime dateOfBirth;

	public Client(String firstName, String lastName, DateTime dateOfBirth) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = new DateTime(dateOfBirth);
	}

    public Integer getId() {
    	return id;
    }
    
    public void setId (Integer id){
    	this.id = id;
    }

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public DateTime getDateOfBirth() {
		return new DateTime(this.dateOfBirth);
	}
	
	public void setDateOfBirth(DateTime dateOfBirth) {
		this.dateOfBirth = new DateTime(dateOfBirth);
	}
	
}
