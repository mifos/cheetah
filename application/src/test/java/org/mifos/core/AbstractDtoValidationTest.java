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
	
	private Validator validator;
		
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
