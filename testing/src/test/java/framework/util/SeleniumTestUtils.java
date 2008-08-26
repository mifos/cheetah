/**
 * Copyright (c) 2005-2008 Grameen Foundation USA
 * 1029 Vermont Avenue, NW, Suite 400, Washington DC 20005
 * All rights reserved.
 *
 * Apache License
 * Copyright (c) 2005-2008 Grameen Foundation USA
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you
 * may not use this file except in compliance with the License. You
 * may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.  See the License for the specific language governing
 * permissions and limitations under the License.
 *
 * See also http://www.apache.org/licenses/LICENSE-2.0.html for an
 * explanation of the license and how it is applied.
 *
 */      

package framework.util;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.DefaultSelenium;

public class SeleniumTestUtils {

	private DefaultSelenium selenium = null;

	public SeleniumTestUtils() {
		createSelenium();
	}
	
	public synchronized DefaultSelenium getSelenium() {
		return selenium;
	}
	
	private synchronized void createSelenium() {
		selenium = new DefaultSelenium("localhost", 4444, "*firefox","http://localhost:8080/springapp3/");
		selenium.start();
	}
	
	@Test
	@AfterSuite(groups={"ui"})
	public synchronized void stopSelenium() {
		selenium.stop();
	}

}
