package org.mifos.user.domain;

import java.util.Set;

import org.mifos.user.service.UserService;

public class User {
    
    @SuppressWarnings("UnusedPrivateField")
    private Integer id;
    
    private final String userId;
    private final transient String password;
    private final String passwordHash;
    private final Set<UserRole> roles;
    private boolean persisted;
    private UserService userService;
    
    
    public User(Integer id, String userId, String password, Set<UserRole> roles, boolean isPersisted) {

        super();
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.roles = roles;
        this.persisted = isPersisted;
        validate();
        passwordHash = getUserService().getPasswordHash(password);
    }
    

    private UserService getUserService() {
        return userService;
    }

    private void validate() {
        
        if (userId == null) {
            throw new IllegalArgumentException("user id cannot be null");
        }
        if (passwordHash == null) {
            throw new IllegalArgumentException ("password digest cannot be null");
        }
        if (roles.isEmpty()) {
            throw new IllegalArgumentException ("user must be assigned at least one role");
        }
    }

    public String getUserId() {
        return this.userId;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public Integer getId() {
        return this.id;
    }
    
    public void setId (Integer id) {
        this.id = id;
    }
        
    public Set<UserRole> getRoles() {
        return this.roles;
    }

    public void markPersisted() {
        this.persisted = true;
    }
    
    public boolean isPersisted() {
        return this.persisted;
    }
    
    public void setUserService (UserService userService) {
        this.userService = userService;
    }


}
