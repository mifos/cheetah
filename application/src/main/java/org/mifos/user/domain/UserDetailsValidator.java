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

package org.mifos.user.domain;

import java.util.Set;

import org.mifos.user.service.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.userdetails.UserDetailsManager;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UserDetailsValidator implements Validator {
    
    @edu.umd.cs.findbugs.annotations.SuppressWarnings(value={"NP_UNWRITTEN_FIELD"}, justification="set by Spring dependency injection")
    private UserDetailsManager userDetailsManager;

    @Override
    public boolean supports(Class clazz) {
        return UserDto.class.equals(clazz);
    }
    
    @Autowired
    public void setUserDetailsManager (UserDetailsManager manager) {
        this.userDetailsManager = manager;
    }

    @Override
    public void validate(Object obj, Errors errors) {
        UserDto userDto = (UserDto) obj;
        validateId(userDto.getUserId(), errors);
        validatePassword(errors);
        validateRoles(userDto.getRoles(), errors);
    }
            

    private void validateRoles(Set<String> roles, Errors errors) {
        if (null==roles || roles.isEmpty()) {
            errors.rejectValue("roles", "user.roles.[not.empty]", "User must be assigned at least one security role");
        }
        
    }

    /**
     * Simple password validation simply checks whether password is not blank.
     * TODO: strengthen password validation.
     * @param errors
     */
    private void validatePassword(Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "password", "User.password.[not.blank]", "Password must be specified");
    }

    private void validateId(String userId, Errors errors) {
       ValidationUtils.rejectIfEmpty(errors, "userId", "User.userId[not.blank]", "User Id must not be missing");
       if (userDetailsManager.userExists(userId)) {
            errors.rejectValue("userId", "User.userId[not.exist]", "User id already exists.");
        }
    }
}
