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

package org.mifos.user.repository;

import java.util.HashMap;
import java.util.Map;

import org.mifos.core.DuplicatePersistedObjectException;
import org.mifos.core.MifosRepositoryException;
import org.mifos.user.domain.User;

public class InMemoryUserRepository implements UserRepository {

    private int nextKey = 0;
    
    private final Map<Integer, User> userStore = new HashMap<Integer, User>();

    public User makePersistent (User user) throws MifosRepositoryException {
        if (userExists(user)) {
            throw new DuplicatePersistedObjectException(user);
        }
        User persistedUser = new User(++nextKey,user.getUserId(),user.getPassword(), user.getRoles(), true);
        userStore.put(nextKey, persistedUser);
        return persistedUser;
    }

    private boolean userExists(User user) {
        return userStore.containsValue(user);
    }
    
    Map<Integer, User> getStore() {
        return userStore;
    }

    @Override
    public User findByUserId(String userId) {
        for (Integer key : userStore.keySet()) {
            User user = userStore.get(key);
            if (user.getUserId().equals(userId)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User get(Integer id) {
        userStore.get(id);
        return null;
    }
    

}
