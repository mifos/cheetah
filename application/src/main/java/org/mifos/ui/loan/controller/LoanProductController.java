package org.mifos.ui.loan.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.mifos.ui.loan.command.LoanProductDto;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;


public class LoanProductController extends SimpleFormController{
	
	public ModelAndView onSubmit(Object command)
	throws ServletException {

		return null;
	}

	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		return new LoanProductDto();
	}


}
