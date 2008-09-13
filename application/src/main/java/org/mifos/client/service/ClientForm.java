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

package org.mifos.client.service;

import java.util.Date;
import org.joda.time.DateTime;
import org.springmodules.validation.bean.conf.loader.annotation.handler.Expression;
import org.springmodules.validation.bean.conf.loader.annotation.handler.Length;
import org.springmodules.validation.bean.conf.loader.annotation.handler.NotBlank;
import org.springmodules.validation.bean.conf.loader.annotation.handler.NotNull;

public class ClientForm implements MifosForm {

	private Integer id;

	@NotBlank  
	@Length(max = 80) 
	private String firstName;
	
	@NotBlank  
	@Length(max = 80) 
	private String lastName;
	
	@NotNull
	@Expression("dateOfBirth >= [1800-01-01]")
	private DateTime dateOfBirth;
	
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

	// needed because valang can only validate Date, not DateTime
	public Date getDateOfBirth() {
		return getDate(this.dateOfBirth);
	}

	public DateTime getDateTimeOfBirth() {
		return new DateTime(dateOfBirth);
	}

	public void setDateTimeOfBirth(DateTime dateOfBirth) {
		this.dateOfBirth = getDateTime(dateOfBirth);
	}
	
	@SuppressWarnings("PMD.DataflowAnomalyAnalysis")
	private Date getDate(DateTime dateTime) {
		Date result = null;
		if (dateTime != null) {
			result = dateTime.toDate();
		}
		return result;
	}

	@SuppressWarnings("PMD.DataflowAnomalyAnalysis")
	private DateTime getDateTime(DateTime date) {
		DateTime result = null;
		if (date != null) {
			result = new DateTime(date);
		}
		return result;
	}
	
}
