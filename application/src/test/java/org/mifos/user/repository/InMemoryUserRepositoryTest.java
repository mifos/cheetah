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


import java.util.HashSet;
import java.util.Set;

import org.mifos.core.DuplicatePersistedObjectException;
import org.mifos.core.MifosRepositoryException;
import org.mifos.user.domain.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test(groups = { "unit" })
public class InMemoryUserRepositoryTest {

    private UserRepository userRepository;
    
    private static Set<String> adminRoleSet;
    private static Set<String> userRoleSet;
    
    @BeforeClass
    private static void initialize() {
        adminRoleSet = new HashSet<String>();
        adminRoleSet.add("ROLE_ADMIN");
        userRoleSet = new HashSet<String>();
        userRoleSet.add("ROLE_USER");
        
    }
    
    @BeforeMethod
    void setUp() {
        userRepository = new InMemoryUserRepository();
    }
    
    
    public void testMakePersistentWithEmptyRepository() throws Exception{
        User newUser = new User (null, "userId", "password", adminRoleSet);
        verifyMakePersistent (newUser);
    }

    @Test(expectedExceptions=DuplicatePersistedObjectException.class)
    public void testThrowsDuplicateException () throws Exception{
        
        userRepository.add(new User (null, "userId", "password1", adminRoleSet));
        userRepository.add(new User (null, "userId", "password2", userRoleSet));
    }
    
    public void testMakePersistentWithNonEmptyRepository() throws Exception {
        
        User user1 = new User (null, "userId1", "password1", adminRoleSet);
        User user2 = new User (null, "userId2", "password2", userRoleSet);
        
        verifyMakePersistent(user1);
        verifyMakePersistent(user2);
    }
    
    private void assertIdenticalUsers(User actualUser, User expectedUser) {
        Assert.assertEquals(
                actualUser.getUserId(), 
                expectedUser.getUserId(), 
                "Unexpected user id");
        Assert.assertEquals(
                actualUser.getEncodedPassword(), 
                expectedUser.getEncodedPassword(), 
                "Unexpected encoded password");
        assertSameRoles (actualUser.getRoles(), expectedUser.getRoles());
    }

    private void verifyMakePersistent(User user) throws MifosRepositoryException {
        User persistedUser = userRepository.add(user);
        User retrievedUser = userRepository.get(persistedUser.getId());
        assertIdenticalUsers(persistedUser, user);
        assertIdenticalUsers(retrievedUser, user);
    }

    private void assertSameRoles(Set<String> actualRoles, Set<String> expectedRoles) {
        //not yet implemented
    }
}
