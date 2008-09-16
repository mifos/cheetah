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

import net.sf.dozer.util.mapping.MapperIF;

import org.mifos.loan.domain.Loan;
import org.mifos.loan.repository.LoanDao;


/**
 *
 */
public class DefaultLoanService implements LoanService {
	private LoanDao loanDao;
	private MapperIF beanMapper;
	
	@Override
	public LoanDto createLoan(LoanDto loanDto) {
		Loan loan = loanDao.createLoan(loanDto.getAmount(),loanDto.getInterestRate(),loanDto.getLoanProductId());

		// Use a net.sf.dozer.util.mapping.DozerBeanMapper to copy fields 
		// from the Loan domain object to the LoanDto.  Not so interesting
		// now, but it will be as we add fields
		LoanDto newLoanDto = (LoanDto) beanMapper.map(loan, LoanDto.class);

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

	@Override
	public void setBeanMapper(MapperIF mapper) {
		beanMapper = mapper;
	}

	protected MapperIF getBeanMapper() {
		return beanMapper;
	}

	
}