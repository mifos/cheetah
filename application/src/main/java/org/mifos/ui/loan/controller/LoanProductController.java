package org.mifos.ui.loan.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mifos.loan.domain.LoanProductStatus;
import org.mifos.ui.loan.command.LoanProductCommand;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

public class LoanProductController extends SimpleFormController {
	
    private static final Log LOG = LogFactory.getLog(LoanProductController.class);

    @Override
    @SuppressWarnings("PMD.SignatureDeclareThrowsException") //rationale: This is the signature of the superclass's method that we're overriding
	protected ModelAndView onSubmit(Object command) throws Exception {
		LOG.debug ("entered LoanProductController.onSubmit()");

		return new ModelAndView("loanProductEditSuccess", "model", new HashMap<String, Object>());
	}

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
    
    @SuppressWarnings("PMD.SignatureDeclareThrowsException") //rationale: This is the signature of the superclass's method that we're overriding
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
    	LoanProductCommand loanProduct = new LoanProductCommand();
    	loanProduct.setStatus(LoanProductStatus.ACTIVE);
    	return loanProduct;
    }
}
