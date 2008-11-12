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

package org.mifos.core.service;


import org.mifos.core.AppInfo;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 */
public class ApplicationInformationServiceTest {

    private static final String BUILD_ID = "ID_1";
    private static final String BUILD_NUMBER = "BUILD_1";
    private static final String SVN_REVISION = "REVISION_3";
    
    ApplicationInformationService applicationInfoService;
    
    @BeforeMethod
    public void setUp() {
        applicationInfoService = new StandardApplicationInformationService();
        AppInfo appInfo = new AppInfo();
        appInfo.setBuildId(BUILD_ID);
        appInfo.setBuildTag(BUILD_NUMBER);
        appInfo.setSvnRevision(SVN_REVISION);
        applicationInfoService.setAppInfo(appInfo);
    }
    @Test(groups = { "unit" })
    public void testGetAppInfo() {
        Assert.assertEquals(applicationInfoService.getAppInfo().getBuildId(), BUILD_ID);
        Assert.assertEquals(applicationInfoService.getAppInfo().getBuildTag(), BUILD_NUMBER);
        Assert.assertEquals(applicationInfoService.getAppInfo().getSvnRevision(), SVN_REVISION);
    }
}
