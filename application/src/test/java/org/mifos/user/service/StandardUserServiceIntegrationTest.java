package org.mifos.user.service;

import java.util.HashSet;
import java.util.Set;

import org.mifos.core.MifosServiceException;
import org.mifos.core.MifosValidationException;
import org.mifos.security.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@ContextConfiguration(locations={"classpath:integrationTestContext.xml"})
@TransactionConfiguration(transactionManager="transactionManager")
@Test(groups="integration")
public class StandardUserServiceIntegrationTest 
                extends AbstractTransactionalTestNGSpringContextTests{
    
    private UserService userService;
    private SecurityService securityService;

    @Autowired
    @Test(enabled=false)
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    @Test(enabled=false)
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }
    
    @BeforeMethod
    public void clearData() {
        super.deleteFromTables(new String[] {"authorities", "users"});
    }
    
    /*
     * The integration tests
     */
    
    public void serviceCreatesValidUserWithOneRole() throws MifosServiceException {
        assertCreateValidUser("aUser", "aPassword", toStringSet("ROLE_ADMIN"));
    }
    
    public void serviceCreatesValidUserWithTwoRoles() throws MifosServiceException {
        assertCreateValidUser("anotherUser", "anotherPassword", toStringSet("ROLE_ADMIN", "ROLE_USER"));
    }
    
    @Test(expectedExceptions=MifosValidationException.class)
    public void createWithBlankUserId() throws MifosServiceException {
        createUser("", "aPassword", toStringSet("ROLE_ADMIN"));
    }
    
    @Test(expectedExceptions=MifosValidationException.class)
    public void createWithNullUserId() throws MifosServiceException {
        createUser(null, "aPassword", toStringSet("ROLE_ADMIN"));
    }
    
    @Test(expectedExceptions=MifosValidationException.class)
    public void createWithBlankPassword() throws MifosServiceException {
        createUser("userId1", "", toStringSet("ROLE_ADMIN"));        
    }
    
    @Test(expectedExceptions=MifosValidationException.class)
    public void createWithNullPassword() throws MifosServiceException {
        createUser("userId2", null, toStringSet("ROLE_ADMIN"));        
    }
    
    @Test(expectedExceptions=MifosValidationException.class)
    public void createWithEmptyRoleSet() throws MifosServiceException {
        createUser("userId3", "aPassword", new HashSet<String>());        
    }
    
    @Test(expectedExceptions=MifosValidationException.class)
    public void createWithNullRoleSet() throws MifosServiceException {
        createUser("userId", "aPassword", null);        
    }
    
    @Test(expectedExceptions=MifosValidationException.class)
    public void createDuplicateUser () throws MifosServiceException {
        String duplicateId = "newUserId";
        createUser (duplicateId, "pswd", toStringSet("A_ROLE"));
        createUser (duplicateId, "apswd", toStringSet("ROLE_B"));
    }
    
    /*
     * Helper utilities
     */
        
    private void createUser (String userId, String password, Set<String> roles) 
            throws MifosServiceException{
        userService.createUser(makeUserDto (userId, password, roles));
    }
   
    private void assertCreateValidUser(String userId, String password, Set<String> roles) 
            throws MifosServiceException{
        createUser (userId, password, roles);
        assertSameDtoWithEncodedPassword(
                userService.getUser(userId), 
                makeUserDto(userId, password, roles));
    }

    private void assertEqualRoles(Set<String> actualRoles, Set<String> expectedRoles) {
        Assert.assertTrue(difference(actualRoles, expectedRoles).isEmpty());
        Assert.assertTrue(difference(expectedRoles, actualRoles).isEmpty());
    }

    private UserDto makeUserDto (String userId, String password, Set<String> roles) {
        UserDto dto = new UserDto();
        dto.setUserId(userId);
        dto.setPassword(password);
        dto.setRoles(roles);
        return dto;
    }
    
    private void assertSameDtoWithEncodedPassword (UserDto actualDto, UserDto expectedDto) {
        Assert.assertEquals(actualDto.getUserId(), expectedDto.getUserId());
        Assert.assertEquals(actualDto.getPassword(), securityService.encodePassword(expectedDto.getPassword()));
        assertEqualRoles(actualDto.getRoles(), expectedDto.getRoles());

    }

    private Set<String> difference(Set<String> set1, Set<String> set2) {
        Set<String> set1Copy = copySet(set1);
        set1Copy.removeAll(set2);
        return set1Copy;
    }

    private Set<String> copySet(Set<String> set) {
        Set<String> copy = new HashSet<String>();
        for (String string : set) {
            copy.add(string);
        }
        return copy;
    }

    private Set<String> toStringSet(String...strings) {
        Set<String> stringSet = new HashSet<String>();
        for (String string : strings) {
            stringSet.add(string);
        }
        return stringSet;
    }
}
