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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class SystemInfo {

    private static final Log LOG = LogFactory.getLog(SystemInfo.class);

	public SystemInfo(AppInfo appInfo, DriverManagerDataSource driverManagerDataSource) {
        LOG.info("Mifos Cheetah system information:");
        LOG.info("  Software:");
        LOG.info("    Build Id: " + appInfo.getBuildId());
        LOG.info("    SVN revision: " + appInfo.getSvnRevision());
        LOG.info("    Build Tag: " + appInfo.getBuildTag());
        LOG.info("  Data source:");
        LOG.info("    JDBC URL: " + driverManagerDataSource.getUrl());
        LOG.info("    Database username: " + driverManagerDataSource.getUsername());
	}
}	
