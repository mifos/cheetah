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

package acceptance.loan;

import java.io.IOException;
import java.io.StringReader;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import framework.pageobjects.CreateLoanProductPage;
import framework.pageobjects.LoginPage;
import framework.pageobjects.ViewLoanProductsPage;
import framework.test.UiTestCaseBase;

/*
 * Corresponds to story 679 in mingle
 * http://mingle.mifos.org:7070/projects/cheetah/cards/679
 */
@ContextConfiguration(locations={"classpath:ui-test-context.xml"})
@Test(groups={"ViewLoanProductsStory","acceptance","ui"})
public class AdminUserCanViewLoanProductsStoryTest extends UiTestCaseBase {
	

	private LoginPage loginPage;

	@Autowired
	private DataSource dataSource;
	    
    private static final String loanProductDataSetTwoProductsXml = 
    		"<dataset>\r\n" + 
    		"  <loanproducts id=\"1\" longName=\"long1\" maxInterestRate=\"2.0\" minInterestRate=\"1.0\" shortName=\"short1\" status=\"0\"/>\r\n" + 
    		"  <loanproducts id=\"2\" longName=\"long2\" maxInterestRate=\"2.0\" minInterestRate=\"1.0\" shortName=\"short2\" status=\"0\"/>\r\n" + 
    		"  <loans/>\r\n" + 
    		"</dataset>\r\n";

	    
    private static final String loanProductDataSetZeroProductsXml = 
    		"<dataset>\r\n" + 
    		"  <loanproducts/>\r\n" + 
    		"  <loans/>\r\n" + 
    		"</dataset>\r\n";

    @BeforeMethod
    public void setUp() throws Exception {
        super.setUp();
        loginPage = new LoginPage(selenium);
    }

    @AfterMethod
    public void logOut() {
        loginPage.logout();
    }

	private void primeLoanProductData (String xmlString) throws IOException, DataSetException, SQLException, DatabaseUnitException {
		StringReader dataSetXmlStream = new StringReader(xmlString);
		IDataSet dataSet = new FlatXmlDataSet(dataSetXmlStream);
		IDatabaseConnection databaseConnection = new DatabaseDataSourceConnection(this.getDataSource());
		DatabaseOperation.CLEAN_INSERT.execute(databaseConnection, dataSet);
	}
		
	@Test(enabled=false)
	public void testLoadLoanProductsFromString() throws IOException, DataSetException, SQLException, DatabaseUnitException {
		primeLoanProductData(loanProductDataSetTwoProductsXml);
	}
	
	public void viewTwoLoanProducts() throws Exception {
	    primeLoanProductData(loanProductDataSetTwoProductsXml);
	    navigateToViewLoanProductsPage();
	    assertElementTextIncludes("short1", "short-name-1");
	    assertElementTextIncludes("short2", "short-name-2");
	}

	public void viewZeroLoanProducts() throws Exception {
	    primeLoanProductData(loanProductDataSetZeroProductsXml);
	    navigateToViewLoanProductsPage();
	    Assert.assertTrue(selenium.isTextPresent("No loan products have been defined"));
	}
	
    private ViewLoanProductsPage navigateToViewLoanProductsPage (){
        return
            loginPage
                .loginAs("mifos", "testmifos")
                .navigateToAdminPage()
                .navigateToViewLoanProductsPage();
    }

    private void assertElementTextIncludes(String text, String elementId) {
        Assert.assertTrue(selenium.getText(elementId).indexOf(text) >= 0, 
                          "Expected text \"" + text + "\" not included in element \"" + elementId + "\"");
    }

	@Test(enabled=false)
	public DataSource getDataSource() {
		return dataSource;
	}

	@Test(enabled=false)
	public void setDataSource(DataSource datasource) {
		this.dataSource = datasource;
	}
}
