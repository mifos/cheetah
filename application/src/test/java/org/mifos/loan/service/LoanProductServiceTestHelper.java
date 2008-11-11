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

package org.mifos.loan.service;

import org.testng.Assert;

/**
 *
 */
public class LoanProductServiceTestHelper {
    public static void assertSameState (LoanProductDto actual, LoanProductDto expected) {
        Assert.assertEquals(actual.getLongName(), expected.getLongName(), "Wrong long name");
        Assert.assertEquals(actual.getShortName(), expected.getShortName(), "Wrong short name");
        Assert.assertEquals(actual.getMinInterestRate(), expected.getMinInterestRate(), 0, "Wrong min interest rate");
        Assert.assertEquals(actual.getMaxInterestRate(), expected.getMaxInterestRate(), 0, "Wrong max interest rate");
        Assert.assertEquals(actual.getStatus(), expected.getStatus(), "Wrong status");
    }

}
