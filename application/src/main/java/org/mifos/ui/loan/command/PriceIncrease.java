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


package org.mifos.ui.loan.command;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * A simple command object holding the percent increase specified by the user in
 * the price increase form.
 * 
 * @author kpierce
 *
 */
public class PriceIncrease {

    /** Logger for this class and subclasses */
    private static final Log LOG = LogFactory.getLog(PriceIncrease.class);

    private int percentage;

    public void setPercentage(int i) {
        percentage = i;
        LOG.info("Percentage set to " + i);
    }

    public int getPercentage() {
        return percentage;
    }

}

