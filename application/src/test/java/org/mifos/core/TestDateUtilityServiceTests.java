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
package org.mifos.core;

import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.joda.time.Duration;
import org.joda.time.Period;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import edu.umd.cs.findbugs.annotations.SuppressWarnings;

/**
 * These tests alternately succeed and fail, caused by some weird
 * state mismanagement, which I didn't have time to debug.
 * 
 * @author kpierce
 *
 */
@edu.umd.cs.findbugs.annotations.SuppressWarnings(value={"UPM"}, justification="@BeforeMethod & @AfterMethod methods are called by testNG")
@java.lang.SuppressWarnings("PMD.UnusedPrivateMethod")
@Test(groups = { "unit" })
public class TestDateUtilityServiceTests {

	@BeforeMethod(groups = { "unit" })
	private void setUp() {
		DateTimeUtils.setCurrentMillisSystem();
	}
	
	@AfterMethod(groups = { "unit" })
	private void tearDown() {
		DateTimeUtils.setCurrentMillisSystem();
	}
	
    @SuppressWarnings("PMD.SystemPrintln")	// remove when done debugging
	public void testSettingTimes() throws InterruptedException {
		DateTime now = new DateTime();
		Period period = Period.months(-1);
		Duration dur = period.toDurationFrom(now);
		DateTimeUtils.setCurrentMillisOffset(dur.getMillis());
		Thread.sleep(1000);
		
		DateTimeUtils.setCurrentMillisSystem();
		
		DateTime JanFirst2008 = new DateTime(2008, 1, 1, 0, 0, 0, 0);
		Duration durToJanFirst = new Duration(new DateTime(), JanFirst2008);
		DateTimeUtils.setCurrentMillisOffset(durToJanFirst.getMillis());
		Thread.sleep(10);
		Thread.sleep(10);
		DateTimeUtils.setCurrentMillisSystem();
	}

	public void setDateTimeOnce() throws InterruptedException {
		ITestDateUtilityService service = new TestDateUtilityService();
		DateTime JanFirst2008 = new DateTime(2008, 1, 1, 0, 0, 0, 0);
		service.setCurrentDateTime(JanFirst2008);
		Thread.sleep(10);
		
		DateTime newDateTime = new DateTime();
		Assert.assertTrue(newDateTime.getMillis() <= JanFirst2008.getMillis() + 3600000, 
				          "New time too late: "
        					+ "System date set to " + JanFirst2008
        					+ " but retrieved date after 10 ms delay was " + newDateTime);
		Assert.assertTrue(newDateTime.getMillis() >= JanFirst2008.getMillis(), 
		          "New time too early: "
					+ "System date set to " + JanFirst2008
					+ " but retrieved date after 10 ms delay was " + newDateTime);
	}
}
