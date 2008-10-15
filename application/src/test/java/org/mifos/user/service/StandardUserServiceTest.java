package org.mifos.user.service;

import java.util.HashSet;
import java.util.Set;

import org.mifos.core.MifosServiceException;
import org.mifos.core.MifosValidationException;
import org.mifos.user.repository.InMemoryUserRepository;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Integration test using in-memory repository
 */
@Test(groups="unit")
public class StandardUserServiceTest {
    
    private StandardUserService userService;
    
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
    private void setUp () {
        userService = new StandardUserService();
        userService.setUserRepository(new InMemoryUserRepository());
    }

    public void testCreateValidUser() throws Exception {
        UserDto dto = makeDto("userId", "password", adminRoleSet);
        UserDto createdDto = userService.createUser(dto);
        int actualId = createdDto.getId();
        Assert.assertEquals(actualId, 1);
        Assert.assertEquals(createdDto.getUserId(), "userId");
        Assert.assertNull(createdDto.getPassword());
    }
    
    public void testCreateUserNullUserId() throws Exception {
        assertCaughtValidationException(null, "password", adminRoleSet, "User ID cannot be blank");
    }

    public void testCreateUserBlankUserId() throws Exception {
        assertCaughtValidationException("", "password", adminRoleSet, "User ID cannot be blank");
    }
    
    public void testCreateUserNullPassword() {
        assertCaughtValidationException("userId", null, adminRoleSet, "Password cannot be blank");
        
    }
     
    public void testCreateUserBlankPassword() {
        assertCaughtValidationException("userId", "", adminRoleSet, "Password cannot be blank");
    }
     
    public void testCreateUserNullRoles() {
        assertCaughtValidationException("userId", "password", null, 
                "user must be given at lease one authorization role");
    }
    
    public void testCreateUserEmptyRoles() {
        assertCaughtValidationException("userId", "password", new HashSet<String>(), 
                "user must be given at lease one authorization role");
    }
   
   private void assertCaughtValidationException(String userId, String password,
            Set<String> roleSet, String errorMessage) {
        
        UserDto dto = makeDto (userId, password, roleSet);
        try {
            userService.createUser(dto);
            Assert.fail("Should have thrown MifosServiceException");
        }
        catch (MifosServiceException serviceException) {
             Throwable cause = serviceException.getCause();
             if (!(cause instanceof MifosValidationException)) {
                 Assert.fail("Should have wrapped MifosValidationException");
             }
             Assert.assertEquals(
                     ((MifosValidationException) cause).getMessage(), errorMessage);
        }
    }

    private UserDto makeDto(String userId, String password,
            Set<String> userRoles) {
        UserDto dto = new UserDto();
        dto.setUserId(userId);
        dto.setPassword(password);
        dto.setRole(userRoles);
        return dto;
    }
    
}
