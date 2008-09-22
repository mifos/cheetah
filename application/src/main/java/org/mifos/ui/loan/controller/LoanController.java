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

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mifos.loan.service.LoanDto;
import org.mifos.loan.service.LoanService;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

public class LoanController extends SimpleFormController {
	
    private static final Log LOG = LogFactory.getLog(LoanController.class);
    
    private LoanService loanService;
    
    public LoanService getLoanService() {
		return loanService;
	}

	public void setLoanService(LoanService loanService) {
		this.loanService = loanService;
	}

	@Override
    @SuppressWarnings("PMD.SignatureDeclareThrowsException") //rationale: This is the signature of the superclass's method that we're overriding
    @edu.umd.cs.findbugs.annotations.SuppressWarnings(value={"NP_UNWRITTEN_FIELD"}, justification="set by Spring dependency injection")
	protected ModelAndView onSubmit(Object command) throws Exception {
		LOG.debug ("entered LoanProductController.onSubmit()");
		LoanDto loanDto = loanService.createLoan((LoanDto) command);
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("loan", loanDto);
		return new ModelAndView("loanEditSuccess", "model", model);
	}

	/*
    @SuppressWarnings("PMD.SignatureDeclareThrowsException") //rationale: This is the signature of the superclass's method that we're overriding
    protected Map referenceData (HttpServletRequest request) throws Exception {
    	Map<String, Object> model = new HashMap<String, Object>();
    	//experiment with returning a map
    	Map<String, String> aMap = new HashMap<String, String>();
    	aMap.put("ACTIVE", "Active");
    	aMap.put("INACTIVE", "Inactive");
    	model.put("availableCategories", aMap);
        //model.put("availableCategories", LoanProductStatus.toStringList());
    	return model;
    }
    */
	
    @SuppressWarnings("PMD.SignatureDeclareThrowsException") //rationale: This is the signature of the superclass's method that we're overriding
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
    	return new LoanDto();
    }
}