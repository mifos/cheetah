package org.mifos.test.acceptance.framework;

import junit.framework.Assert;

import com.thoughtworks.selenium.Selenium;

public class ViewGroupsPage extends AbstractPage {

    public ViewGroupsPage(Selenium selenium) {
        super(selenium);
    }

    public void verifyPage() {
        Assert.assertEquals("View Groups", selenium.getTitle());
    }
    
}
