/*
 * Copyright (c) 2005-2009 Grameen Foundation USA
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

package org.mifos.test.acceptance.framework;

import com.thoughtworks.selenium.Selenium;

public class AdminPage extends AbstractPage {

	public AdminPage() {
		super();
	}

	public AdminPage(Selenium selenium) {
		super(selenium);
	}

	public CreateLoanProductPage navigateToCreateLoanProductPage() {
		selenium.click("id=create.loan.product");
		waitForPageToLoad();
		return new CreateLoanProductPage(selenium);
	}

    public ViewLoanProductsPage navigateToViewLoanProductsPage() {
        selenium.click("id=view.loan.products");
        waitForPageToLoad();
        return new ViewLoanProductsPage(selenium);
    }

    public CreateUserPage navigateToCreateUserPage() {
        selenium.click("id=create.user");
        waitForPageToLoad();
        return new CreateUserPage(selenium);
    }

    public ViewOfficesPage navigateToViewOfficesPage() {
        selenium.click("id=view.offices");
        waitForPageToLoad();
        return new ViewOfficesPage(selenium);        
    }

    public AppInfoPage navigateToAppInfoPage() {
        selenium.click("id=view.system.information");
        waitForPageToLoad();
        return new AppInfoPage(selenium);        
    }


}
