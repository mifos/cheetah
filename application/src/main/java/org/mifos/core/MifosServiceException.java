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

import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

public class MifosServiceException extends MifosException {

	private static final long serialVersionUID = -7431390954369329626L;
	private BeanPropertyBindingResult errors = null;
	
	public MifosServiceException(String message, BeanPropertyBindingResult errors) {
		super(message);
		this.errors = errors;
	}
	
    public MifosServiceException(String message, Throwable cause, BeanPropertyBindingResult errors) {
        super(message, cause);
        this.errors = errors;
    }

    public MifosServiceException(Throwable cause, BeanPropertyBindingResult errors) {
        super(cause);
        this.errors = errors;
    }
    
    public MifosServiceException(String message) {
        super(message);
    }

    public BindingResult getErrors() {
    	return this.errors;
    }
}

