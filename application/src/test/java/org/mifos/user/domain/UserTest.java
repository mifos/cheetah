package org.mifos.user.domain;

import java.util.HashSet;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test
public class UserTest {

    private static Set<String> adminRoleSet;
    private static Set<String> userRoleSet;
    
    @BeforeClass
    private static void initialize() {
        adminRoleSet = new HashSet<String>();
        adminRoleSet.add("ROLE_ADMIN");
        userRoleSet = new HashSet<String>();
        userRoleSet.add("ROLE_USER");
    }
    

    public void testCreateValidUser () throws Exception{
        User user = new User (3, "userId", "password", adminRoleSet);
        Assert.assertEquals (user.getId().intValue(), 3, "Wrong id");
        Assert.assertEquals (user.getUserId(), "userId", "Wrong userId");
        Assert.assertEquals(user.getEncodedPassword(),"5f4dcc3b5aa765d61d8327deb882cf99", "Wrong encoded password");
        //TODO: write test for same roles
    }
}
