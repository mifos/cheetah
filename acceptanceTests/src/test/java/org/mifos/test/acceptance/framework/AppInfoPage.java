package org.mifos.test.acceptance.framework;

import org.testng.Assert;

import com.thoughtworks.selenium.Selenium;

public class AppInfoPage extends AbstractPage {

    public AppInfoPage(Selenium selenium) {
        super(selenium);
    }

    public void verifyPage() {
        Assert.assertEquals("System Information", this.selenium.getTitle());
    }

    public void verifySystemInformation() {
        Assert.assertEquals(this.selenium.getText("id=appInfo.svn.revision.message"), "SVN revision number");
        Assert.assertTrue(this.selenium.getText("id=appInfo.svn.revision").length() > 0, "Empty svn revision number.");
        Assert.assertEquals(this.selenium.getText("id=appInfo.build.tag.message"), "Build tag");
        Assert.assertTrue(this.selenium.getText("id=appInfo.build.tag").length() > 0, "Empty build tag.");
        Assert.assertEquals(this.selenium.getText("id=appInfo.build.id.message"), "Build ID");
        Assert.assertTrue(this.selenium.getText("id=appInfo.build.id").length() > 0, "Empty build ID.");
    }

}
