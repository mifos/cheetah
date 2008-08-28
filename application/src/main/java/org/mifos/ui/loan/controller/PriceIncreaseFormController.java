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


package org.mifos.ui.loan.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import org.mifos.loan.service.ProductService;
import org.mifos.ui.loan.command.PriceIncrease;

/**
Illustrates how to use Spring MVC's form controllers, command objects,
and command validators, while implementing the user's request to increase
prices of all products.
<p>
The end-to-end flow is as follows (see war/WEB-INF/web.xml for servlet-mapping
settings, and war/WEB-INF/springapp-servlet.xml for controller configuration
entries):
<ol>
	<li>User enters URL .../priceIncrease.htm</li>
	<li>All URLs of form *.htm are mapped to org.springframework.web.servlet.DispatcherServlet...
	<li>...which forwards the request to this class.</li>
	<li>Because it is a Form Controller instance,
		it forwards the request to its configured formView, "priceincrease".</li>
	<li>The ViewResolver resolves this by appending the suffix ".jsp" and we see the page
	<code>priceincrease.jsp</code>.</li>
	<li>The user enters a percentage and presses "execute".</li>
	<li>The Spring framework 
		<ol type="a">
			<li>fills out the configured command object {@link springapp.web.commands.PriceIncrease}
			<li>invokes the configured command validator, {@link springapp.web.commands.PriceIncreaseValidator}
			<li>and invokes onSubmit(), passing the validated command object.
		</ol>
	</li>
	<li>This class asks the (Spring-injected) ProductService implementation to increase prices..</li>
	<li> and then redirects to the configures successView, "hello.htm".</li>
	<li> The dispatch servlet takes over and resolves this view to "hello.jsp"</li>
	<li> The user sees the list of updated prices.</li>
</ol>

@author kpierce
*/
public class PriceIncreaseFormController extends SimpleFormController {

    private static final Log LOG = LogFactory.getLog(PriceIncreaseFormController.class);

    private ProductService productService;
    
    public ModelAndView onSubmit(Object command)
            throws ServletException {

        int increase = ((PriceIncrease) command).getPercentage();
        LOG.info("Increasing prices by " + increase + "%.");
        productService.increasePrice(increase);
        LOG.info("returning from PriceIncreaseForm view to " + getSuccessView());
        return new ModelAndView(new RedirectView(getSuccessView()));
    }

    protected Object formBackingObject(HttpServletRequest request) throws ServletException {
        PriceIncrease priceIncrease = new PriceIncrease();
        priceIncrease.setPercentage(20);
        return priceIncrease;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public ProductService getProductService() {
        return productService;
    }

}