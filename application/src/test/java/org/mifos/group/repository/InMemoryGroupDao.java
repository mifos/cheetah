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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mifos.core.MifosException;
import org.mifos.group.domain.Group;
import org.springframework.validation.Validator;

public class InMemoryGroupDao implements GroupDao {

	private int nextId = 1;
	private final Map<Integer, Group> groups = new HashMap<Integer, Group>(); 

    @Override
    public Group create(String name) throws MifosException {
        Group group = new Group(generateNextId(), name);
        groups.put(group.getId(), group);
        return group;
    }

	@Override
	public Group get(Integer groupId) {
		return groups.get(groupId);
	}

	private Validator validator; 

	public Validator getValidator() {
		return validator;
	}

	public void setValidator(Validator validator) {
		this.validator = validator;
	}

	@Override
	public List<Group> getAll() {
		ArrayList<Group> groupList = new ArrayList<Group>();
		groupList.addAll(groups.values());
		return groupList;
	}
	
	private Integer generateNextId() {
		synchronized(this) {
			return Integer.valueOf(nextId++);
		}
	}
}
