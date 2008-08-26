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


package springapp.web.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import springapp.service.ProductService;

public class InventoryController implements Controller {

    private static final Log LOG = LogFactory.getLog(InventoryController.class);
    private ProductService productService;
    
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


    	DateTime timeNow = new DateTime();
        LOG.info("returning hello view with " + timeNow);

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("now", timeNow.toDate());
        model.put("products", getProductService().getProducts());
        
        return new ModelAndView("hello", "model", model);
    }

//    public Boolean thisWillCauseAFindBugsBuildFailure() {
//    	return null;
//    }
    
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    private ProductService getProductService() {
		return productService;
	}

    
}
