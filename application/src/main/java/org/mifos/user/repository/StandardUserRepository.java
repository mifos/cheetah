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

import org.mifos.core.MifosRepositoryException;
import org.mifos.user.domain.User;

/**
 *
 */
public class StandardUserRepository implements UserRepository {

    /**
     * @see org.mifos.user.repository.UserRepository#findByUserId(java.lang.String)
     */
    @Override
    public User findByUserId(String userId) {
        // TODO Auto-generated method stub
        return null;
    }

    /**)
     * @see org.mifos.user.repository.UserRepository#get(java.lang.Integer)
     */
    @Override
    public User get(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @see org.mifos.user.repository.UserRepository#makePersistent(org.mifos.user.domain.User)
     */
    @Override
    public User add(User user) throws MifosRepositoryException {
        // TODO Auto-generated method stub
        return null;
    }

}
