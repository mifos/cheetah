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

package org.mifos.loan.service;

import java.util.List;

import net.sf.dozer.util.mapping.MapperIF;

import org.mifos.core.MifosServiceException;
import org.mifos.loan.repository.LoanDao;
import org.mifos.loan.repository.LoanProductDao;
import org.springframework.validation.Validator;


public interface LoanService {

	LoanDto createLoan(LoanDto loanDto) throws MifosServiceException;

	LoanDao getLoanDao();

	void setLoanDao(LoanDao loanDao);

	void setLoanProductDao(LoanProductDao loanProductDao);
	
	void setBeanMapper(MapperIF mapper);

	void setValidator(Validator validator);

    List<LoanDto> findLoansForClient(Integer clientId);

    LoanDto getLoan(Integer id);

    void updateLoan(LoanDto loanDto);

}
