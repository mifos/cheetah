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

package org.mifos.client.service;

import java.util.Date;
import org.joda.time.LocalDate;
import org.springmodules.validation.bean.conf.loader.annotation.handler.Expression;
import org.springmodules.validation.bean.conf.loader.annotation.handler.Length;
import org.springmodules.validation.bean.conf.loader.annotation.handler.NotBlank;
import org.springmodules.validation.bean.conf.loader.annotation.handler.NotNull;

public class ClientDto {

	private Integer id;

	@NotNull
	@NotBlank  
	@Length(max = 80) 
	private String firstName;
	
	@NotNull
	@NotBlank  
	@Length(max = 80) 
	private String lastName;
	
	@NotNull
	@Expression("dateOfBirth >= [1879-12-31]")
	private LocalDate dateOfBirth;
	
    public ClientDto() {
        // for dozer mapping
    }

    public ClientDto(String firstName, String lastName, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = this.getLocalDateNullSafe(dateOfBirth);
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	// java.util.Date because valang can only validate Date, not LocalDate
	public Date getDateOfBirth() {
		return getDateNullSafe(this.dateOfBirth);
	}

	public LocalDate getLocalDateOfBirth() {
		return getLocalDateNullSafe(this.dateOfBirth);
	}

	public void setLocalDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = getLocalDateNullSafe(dateOfBirth);
	}
	
	@SuppressWarnings("PMD.OnlyOneReturn")
	private Date getDateNullSafe(LocalDate localDate) {
		if (localDate == null) {
			return null;
		} else {
			return localDate.toDateMidnight().toDate();
		}
	}

	@SuppressWarnings("PMD.OnlyOneReturn")
	private LocalDate getLocalDateNullSafe(LocalDate localDate) {
		if (localDate == null) {
			return null;
		} else {
			return new LocalDate(localDate);
		}
	}
	
}
