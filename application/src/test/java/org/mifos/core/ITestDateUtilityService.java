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
import org.mifos.core.IDateUtilityService;

/**
 * An attempt to wrap technicalities of setting and restoring system date
 * using JodaTime.
 * <p>
 * However, I could not get time-setting to work using this interface --
 * see @link springap.service.utils.TestDateUtilityService} for an attempted
 * implementation and {@link org.mifos.core.TestDateUtilityServiceTests}
 * for tests that are failing.
 * </p>
 * 
 * @author kpierce
 *
 */
public interface ITestDateUtilityService extends IDateUtilityService {

	void setCurrentDateTime (DateTime time);
	void restoreCurrentDateTime();
	
}
