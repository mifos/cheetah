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

package org.mifos.ui.loan.controller;

import java.util.Map;

import org.mifos.core.AppInfo;
import org.mifos.ui.core.controller.AppInfoController;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test(groups = { "unit" })
public class AppInfoControllerTest {
	
	AppInfo appInfo;
	
	public void testHandleRequestView() throws Exception {
		appInfo = new AppInfo();
		String expectedSvnRevision = "123456";
		appInfo.setSvnRevision(expectedSvnRevision);
		String expectedBuildId = "fooId";
		appInfo.setBuildId(expectedBuildId);
		String expectedBuildTag = "bar-baz-tag-1";
		appInfo.setBuildTag(expectedBuildTag);
        AppInfoController controller = new AppInfoController();
        controller.setAppInfo(appInfo);
        MockHttpServletRequest mockRequest = new MockHttpServletRequest();
        mockRequest.setMethod("GET");
        mockRequest.setRequestURI("/appInfo.ftl");
        MockHttpServletResponse mockResponse = new MockHttpServletResponse();
        ModelAndView modelAndView = controller.handleRequest(mockRequest, mockResponse);
        
        Assert.assertNotNull(modelAndView.getModel());
        Map<String, Object> modelMap = (Map<String, Object>) modelAndView.getModel().get("model");
        Assert.assertNotNull(modelMap);
        AppInfo actualAppInfo = (AppInfo) modelMap.get("appInfo");
		Assert.assertNotNull(actualAppInfo);
		Assert.assertEquals(actualAppInfo.getSvnRevision(), expectedSvnRevision);
		Assert.assertEquals(actualAppInfo.getBuildId(), expectedBuildId);
		Assert.assertEquals(actualAppInfo.getBuildTag(), expectedBuildTag);
     }
}
