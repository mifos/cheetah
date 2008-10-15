package org.mifos.security.service;

import org.mifos.security.domain.StandardPasswordEncoder;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test(groups="Unit")
public class StandardSecurityServiceTest {
    
    public void testEncodePassword() {
        String expectedEncoding = "5f4dcc3b5aa765d61d8327deb882cf99";
        String password = "password";
        String actualEncoding = (new StandardSecurityService()).encodePassword(password);
        Assert.assertEquals(actualEncoding, expectedEncoding);
    }

}
