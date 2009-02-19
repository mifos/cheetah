package org.mifos.security.util;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;

public class SecurityUtils {
    

    public Set<String> authoritiesToStringSet(GrantedAuthority[] authorities) {
        Set<String> roles = new HashSet<String>();
        for (int i = 0; i < authorities.length; i++) {
             roles.add(authorities[i].toString());

        }
        return roles;    }
    
    @SuppressWarnings("PMD.AvoidInstantiatingObjectsInLoops")//We're building a collection of new objects
    public GrantedAuthority[] rolesToGrantedAuthorityArray (Set<String> roles) {
        GrantedAuthority[] authorities = new GrantedAuthorityImpl[roles.size()];
        int i = 0;
        for (String roleName : roles) {
            authorities[i] = new GrantedAuthorityImpl(roleName);
            i = i + 1;
        }
        return authorities;
    }


}
