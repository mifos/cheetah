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

package org.mifos.user.domain;

import java.util.Set;

import org.mifos.core.MifosValidationException;

/**
 *
 */
public class UserDetailsValidator {
    
    public void validateNewUserDetails (String userId, String password, Set<String> roles)
            throws MifosValidationException {
        validateUserId (userId);
        validatePassword (password);
        validateRoles (roles);
    }
    
    private final void validatePassword(String password) throws MifosValidationException {
        if (password == null || password.length() == 0) {
            throw new MifosValidationException ("Password cannot be blank");
        } 
    }

    private final void validateRoles(Set<String> roles) throws MifosValidationException{
        if (roles == null || roles.isEmpty()) {
            throw new MifosValidationException("user must be given at lease one authorization role");
        }
    }

    private final void validateUserId(String userId)  throws MifosValidationException{
        if (userId == null || userId.length()==0) {
            throw new MifosValidationException("User ID cannot be blank");
        }
    }


}
