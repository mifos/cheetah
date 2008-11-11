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

package org.mifos.group.repository;

import java.util.List;

import junit.framework.Assert;

import org.mifos.core.MifosException;
import org.mifos.group.domain.Group;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test(groups = { "unit" })
public class InMemoryGroupDaoTest {

	private GroupDao groupDao;
	
	@BeforeMethod
	void setUp() {
		groupDao = new InMemoryGroupDao();
	}
	
	public void testCreateClient() throws MifosException {
		String expectedName = "Green Group";
		Group group = groupDao.create(expectedName);
		Assert.assertEquals(group.getName(), expectedName);
	}

	public void testGetClient() throws MifosException {
		Group group = groupDao.create("Yellow Group");
		Group newGroup = groupDao.get(group.getId());
		Assert.assertEquals(group.getName(), newGroup.getName());
	}

	public void testGetAll() throws MifosException {
        groupDao.create("Brown Group");
        groupDao.create("Orange Group");
        groupDao.create("Blue Group");
		List<Group> groupList = groupDao.getAll();
		Assert.assertEquals(3, groupList.size());
	}
}
