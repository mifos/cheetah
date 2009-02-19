/*
 * Copyright (c) 2005-2009 Grameen Foundation USA
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;
import org.testng.Assert;
import org.testng.annotations.Test;

@ContextConfiguration(locations={"classpath:unitTestContext.xml"})
public abstract class AbstractDtoValidationTest  extends AbstractTestNGSpringContextTests{
	
	protected Validator validator;
		
	@Autowired
    @Test(enabled = false)
	public void setValidator(Validator validator) {
		this.validator = validator;
	}

	protected void verifyNoErrors(Object dto) {
		Assert.assertEquals(getErrors(dto).getErrorCount(), 0);
	}

	protected void verifyFieldError (Object dto, String fieldName, String errorMessage) {
		Errors errors = getErrors(dto);
		Assert.assertTrue(errors.getErrorCount() > 0, "Expected errors but got none.");
		FieldError fieldError = errors.getFieldError(fieldName);
		Assert.assertNotNull(fieldError, "Expected error on field " + fieldName + ", but got none");
		Assert.assertEquals(fieldError.getDefaultMessage(), errorMessage, "Incorrect validation error message.");
	}

	protected Errors getErrors(Object dto) {
		BeanPropertyBindingResult errors = new BeanPropertyBindingResult(dto, dto.getClass().getName());
		validator.validate(dto, errors);
		return errors;
	}
}
