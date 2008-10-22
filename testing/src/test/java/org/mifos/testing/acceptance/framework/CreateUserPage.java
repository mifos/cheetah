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

package org.mifos.testing.acceptance.framework;

import com.thoughtworks.selenium.Selenium;

public class CreateUserPage extends AbstractPage {

    public CreateUserPage(Selenium selenium) {
        super(selenium);
    }

    public CreateUserSuccessPage createUserExpectSuccess (String userId, String password) {
        fillAndSubmitForm(userId, password);
        return new CreateUserSuccessPage(selenium);
         }

    public CreateUserPage createUserExpectFailure(String userId, String password) {
        fillAndSubmitForm(userId, password);
        return new CreateUserPage(selenium);
    }

    private void fillAndSubmitForm (String userId, String password) {
        selenium.type("userId", userId);
        selenium.type("password", password);
        selenium.click("user-form-submit"); 
    }
}
