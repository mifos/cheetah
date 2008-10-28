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

import javax.annotation.Resource;

import org.mifos.core.MifosServiceException;
import org.mifos.core.MifosValidationException;
import org.mifos.security.service.SecurityService;
import org.mifos.security.util.SecurityUtils;
import org.mifos.user.domain.UserDetailsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.userdetails.User;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UserDetailsManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

/**
 * An internal implementation of UserService that uses Spring (Acegi) security
 * to manage users and permissions.
 */
public class StandardUserService implements UserService {
    
    private UserDetailsManager userDetailsManager;
    private SecurityService securityService;
    
    @Autowired
    @Resource
    private final UserDetailsValidator userDetailsValidator = new UserDetailsValidator();
    private static final SecurityUtils securityUtils = new SecurityUtils();
    
    public UserDetailsManager getUserDetailsManager() {
        return userDetailsManager;
    }

    public void setUserDetailsManager(UserDetailsManager userDetailsManager) {
        this.userDetailsManager = userDetailsManager;
    }

    public SecurityService getSecurityService() {
        return securityService;
    }

    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }

    public UserDetailsValidator getUserDetailsValidator() {
        return userDetailsValidator;
    }

    @Override
    @Transactional
    public void createUser(UserDto dto) throws MifosValidationException {
        validateDetails(dto);
        userDetailsManager.createUser(new User(
                dto.getUserId(), 
                securityService.encodePassword(dto.getPassword()), 
                true, true, true, true, 
                securityUtils.rolesToGrantedAuthorityArray(dto.getRoles())));
    }

    private void validateDetails (UserDto userDto) throws MifosValidationException {
        BindingResult errors = new BeanPropertyBindingResult(userDto, "user");
        userDetailsValidator.validate(userDto, errors);
        if (errors.getErrorCount() > 0) {
            throw new MifosValidationException("User validation error", errors);
        }
    }

    public UserDto getUser(String userId) throws MifosServiceException {
        return assembleDto(userDetailsManager.loadUserByUsername(userId));
    }

    private UserDto assembleDto(UserDetails user) {
        UserDto dto = new UserDto();
        dto.setUserId(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setRoles(securityUtils.authoritiesToStringSet(user.getAuthorities()));
        return dto;
    }
 }
