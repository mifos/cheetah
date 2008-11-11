package org.mifos.security.service;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test(groups="Unit")
public class StandardSecurityServiceTest {
    
    public void testEncodePassword() {
        assertCorrectMd5Hash("password", "5f4dcc3b5aa765d61d8327deb882cf99");
        assertCorrectMd5Hash("anotherPassword", "ed333d20bb5968ac3fca2598b636028f");
        assertCorrectMd5Hash("My dog hates fleas", "888e850caa8cd982e87648ea0815a395");
    }

    private void assertCorrectMd5Hash (String password, String expectedEncoding) {
        Assert.assertEquals(
                new StandardSecurityService().encodePassword(password), 
                expectedEncoding);
        
    }
}
