package org.mifos.security.domain;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test(groups="Unit")
public class Md5PasswordEncoderTest {

    public void testMd5Encoding() {
        String expectedEncoding = "5f4dcc3b5aa765d61d8327deb882cf99";
        String password = "password";
        String actualEncoding = (new StandardPasswordEncoder()).encode(password);
        Assert.assertEquals(actualEncoding, expectedEncoding);
    }
}
