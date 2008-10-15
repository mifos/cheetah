package org.mifos.user.domain;

import java.util.Set;

import org.mifos.core.MifosValidationException;
import org.mifos.security.service.SecurityService;
import org.mifos.security.service.StandardSecurityService;

public class User {
    
    @SuppressWarnings("UnusedPrivateField")
    private Integer id;
    
    private final String userId;
    private final String encodedPassword;
    private final Set<String> roles;
    private final SecurityService securityService = new StandardSecurityService();
    
    
    public User(Integer id, String userId, String password, Set<String> roles) 
                    throws MifosValidationException {

        super();
        this.id = id;
        this.userId = userId;
        this.roles = roles;
        validateFields (userId, roles);
        validatePassword(password);
        encodedPassword = getSecurityService().encodePassword(password);
    }
    

    private final void validateFields (String userId, Set<String> roles) 
                    throws MifosValidationException {
        
        validateUserId(userId);
        validateRoles(roles);
    }

    private final void validatePassword(String password) throws MifosValidationException {
        if (password == null || password.length() == 0) {
            throw new MifosValidationException ("Password cannot be blank");
        } 
    }

    //TODO: fully validate assigned roles against the list of roles defined by security
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

    public String getUserId() {
        return this.userId;
    }
    
    public Integer getId() {
        return this.id;
    }
    
    private void setId (Integer id) {
        this.id = id;
    }
        
    public Set<String> getRoles() {
        return this.roles;
    }
    
    public String getEncodedPassword() {
        return this.encodedPassword;
    }

    private SecurityService getSecurityService() {
        return securityService;
    }
    
    public void makePersistentWithId (Integer id) {
        setId (id);
    }

}
