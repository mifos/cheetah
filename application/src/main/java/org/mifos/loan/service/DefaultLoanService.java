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

package org.mifos.loan.service;

import org.mifos.loan.domain.Loan;
import org.mifos.loan.repository.LoanDao;
//import net.sf.dozer.util.mapping.DozerBeanMapper;

/**
 *
 */
public class DefaultLoanService implements LoanService {
	// TODO: TDD work in progress, refactor to map 

	LoanDao loanDao;
	//private DozerBeanMapper beanMapper;
	
	@Override
	public LoanDto createLoan(LoanDto loanDto) {
		Loan loan = loanDao.createLoan(loanDto.getAmount(),loanDto.getInterestRate(),loanDto.getLoanProductId());
		LoanDto newLoanDto = new LoanDto(loan.getAmount(), loan.getInterestRate(), loan.getLoanProductId());
		newLoanDto.setId(loan.getId());

		return newLoanDto;
	}

	@Override
	public LoanDao getLoanDao() {
		return loanDao;
	}

	@Override
	public void setLoanDao(LoanDao loanDao) {
		this.loanDao = loanDao;
	}

}
