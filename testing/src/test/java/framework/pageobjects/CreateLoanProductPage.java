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

package framework.pageobjects;

import com.thoughtworks.selenium.DefaultSelenium;

public class CreateLoanProductPage extends AbstractPage {

	public CreateLoanProductPage(DefaultSelenium selenium) {
		super(selenium);
	}

	public CreateLoanProductSuccessPage createValidLoanProduct (String longName, String shortName, Double minInterestRate,
														 Double maxInterestRate) {
		selenium.type("longName", longName);
		selenium.type("shortName", shortName);
		selenium.type("minInterestRate", minInterestRate.toString());
		selenium.type("maxInterestRate", maxInterestRate.toString());
		selenium.click("login.form.submit");
		waitForPageToLoad();
		return new CreateLoanProductSuccessPage(selenium);
	}

	public CreateLoanProductPage createInvalidLoanProduct (String longName, String shortName, Double minInterestRate,
														 Double maxInterestRate) {
		selenium.type("longName", longName);
		selenium.type("shortName", shortName);
		selenium.type("minInterestRate", minInterestRate.toString());
		selenium.type("maxInterestRate", maxInterestRate.toString());
		selenium.click("login.form.submit");
		waitForPageToLoad();
		return new CreateLoanProductPage(selenium);
	}
}
