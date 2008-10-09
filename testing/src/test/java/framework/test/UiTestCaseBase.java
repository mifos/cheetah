/*
 * Copyright (c) 2005-2008 Grameen Foundation USA
 * All rights reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * permissions and limitations under the License.
 * 
 * See also http://www.apache.org/licenses/LICENSE-2.0.html for an
 * explanation of the license and how it is applied.
 */
package framework.test;

import java.io.IOException;
import java.sql.SQLException;

import org.testng.Assert;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dbunit.DatabaseUnitException;
import org.mifos.test.framework.util.DatabaseTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.Selenium;
	
@ContextConfiguration(locations={"classpath:test-context.xml", "classpath:ui-test-context.xml"})
@Test(sequential=true)
public class UiTestCaseBase extends AbstractTestNGSpringContextTests {

    private static final Log LOG = LogFactory.getLog(UiTestCaseBase.class);
    private static Boolean seleniumServerIsRunning = Boolean.FALSE;
    private static final String ERROR_ELEMENT_ID = "*.errors";


    @Autowired
    protected DriverManagerDataSource dataSource;
    
    private DatabaseTestUtils dbUtils = new DatabaseTestUtils();
   
	protected Selenium selenium;
	
	@BeforeMethod
	public void setUp() throws Exception {
		// do nothing
	}
	
	@AfterGroups(groups={"ui"})
	public void stopSelenium() {
        synchronized(UiTestCaseBase.class) {
            if (seleniumServerIsRunning.booleanValue() && this.selenium != null) {
                this.selenium.stop();
            }
        }
	}

	@Autowired
	@Test(enabled=false)
	public void setSelenium(Selenium selenium) {
		this.selenium = selenium;
		synchronized(UiTestCaseBase.class) {
			if (!seleniumServerIsRunning.booleanValue()) {
				selenium.start();
				seleniumServerIsRunning = Boolean.TRUE;
			}
		}
	}

	@Test(enabled=false)
	public Selenium getSelenium() {
        synchronized(UiTestCaseBase.class) {
            return selenium;
        }
	}

    protected void assertTextFoundOnPage (String text, String message) {
        Assert.assertTrue(selenium.isTextPresent(text), message);
    }

    protected void assertTextFoundOnPage (String text) {
        assertTextFoundOnPage(text, "Text \"" + text + "\" was not found.");
    }

    
    protected void assertElementTextExactMatch(String text, String elementId) {
        Assert.assertEquals(
                selenium.getText(elementId),
                text,
                "Text \"" + text + "\" does not match element \"" + elementId + "\":");
    }
    
    protected void assertErrorTextExactMatch(String text) {
        assertElementTextExactMatch(text, ERROR_ELEMENT_ID);
    }
    
    protected void assertElementTextIncludes(String text, String elementId) {
        Assert.assertTrue(
                selenium.getText(elementId).indexOf(text) >= 0,
                "Expected text \"" + text + "\" not included in element \"" + elementId + "\"");
    }
    
    protected void assertErrorTextIncludes(String text) {
        assertElementTextIncludes(text, ERROR_ELEMENT_ID);
    }
    
    protected void deleteDataFromTables(String...tableNames) 
                    throws IOException, DatabaseUnitException, SQLException {
        dbUtils.deleteDataFromTables(dataSource, tableNames);
    }
    
    protected void cleanAndInsertDataSet(String xmlString) 
                    throws IOException, DatabaseUnitException, SQLException {
        dbUtils.cleanAndInsertDataSet(xmlString, dataSource);
    }

}
