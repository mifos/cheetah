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

package org.mifos.client.domain;

import org.joda.time.LocalDate;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ClientTest {
	
	@Test
	public void testConstructValidClient() {
		Integer id = 5;
		String firstName = "John";
		String lastName = "Smallberries";
		LocalDate dateOfBirth = new LocalDate("1971-03-15");
		Client client = new Client(id, firstName, lastName, dateOfBirth);
        Assert.assertEquals(client.getId(), id);
        Assert.assertEquals(client.getFirstName(), firstName);
        Assert.assertEquals(client.getLastName(), lastName);
        Assert.assertEquals(client.getDateOfBirth(), dateOfBirth);
	}

}
