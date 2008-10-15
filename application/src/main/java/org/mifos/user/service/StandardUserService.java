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

package org.mifos.user.service;

import org.mifos.core.DuplicatePersistedObjectException;
import org.mifos.core.MifosRepositoryException;
import org.mifos.core.MifosServiceException;
import org.mifos.core.MifosValidationException;
import org.mifos.user.domain.User;
import org.mifos.user.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;

/**
 * An internal implementation of UserService that uses Mifos's operational database
 * to manage user information, including passwords and permissions.
 */
public class StandardUserService implements UserService {
    
    private UserRepository userRepository;

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDto createUser(UserDto dto) throws MifosServiceException {
        
        User newUser = null;
        User persistedUser = null;
        
        try {
            newUser = new User(null, dto.getUserId(), dto.getPassword(), dto.getRoles());
        } 
        catch (MifosValidationException validationException) {
            throw new MifosServiceException(
                    "Creating user from this Dto is not valid",
                    validationException,
                    new BeanPropertyBindingResult(dto, "dto"));
        }
        
        try {
            persistedUser = userRepository.add (newUser);
        }
        catch (DuplicatePersistedObjectException dupException) {
            throw new MifosServiceException (
                    "User already exists: " + dto,
                    dupException, 
                    new BeanPropertyBindingResult(dto, "dto"));
        }
        catch (MifosRepositoryException otherException) {
            throw new MifosServiceException (
                    "Unknown exception from repository when persisting " + dto,
                    otherException,
                    new BeanPropertyBindingResult (dto, "dto"));
        }
        
        return assembleDto(persistedUser);
    }

    private UserDto assembleDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUserId(user.getUserId());
        //don't return the password
        dto.setPassword(null);
        dto.setRole(user.getRoles());
        return dto;
    }
}