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

import java.util.ArrayList;
import java.util.List;

import net.sf.dozer.util.mapping.MapperIF;

import org.mifos.core.MifosServiceException;
import org.mifos.loan.domain.Loan;
import org.mifos.loan.domain.LoanProduct;
import org.mifos.loan.repository.LoanDao;
import org.mifos.loan.repository.LoanProductDao;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Validator;


/**
 *
 */
public class StandardLoanService implements LoanService {
	private LoanDao loanDao;
	private LoanProductDao loanProductDao;
	private MapperIF beanMapper;
	private Validator validator;
	
    @Transactional
	@Override
	public LoanDto createLoan(LoanDto loanDto) throws MifosServiceException {
		validate(loanDto);
		LoanProduct loanProduct = loanProductDao.get(loanDto.getLoanProductId());
		Loan loan = loanDao.createLoan(loanDto.getClientId(), loanDto.getAmount(),loanDto.getInterestRate(),loanProduct);

		// Use a net.sf.dozer.util.mapping.DozerBeanMapper to copy fields 
		// from the Loan domain object to the LoanDto.  Not so interesting
		// now, but it will be as we add fields
		LoanDto newLoanDto = (LoanDto) beanMapper.map(loan, LoanDto.class);

		return newLoanDto;
	}

    @Transactional(readOnly=true)
    @Override
    public List<LoanDto> findLoansForClient(Integer clientId) {
        List<Loan> loans = loanDao.findLoansForClient(clientId);
        
        List<LoanDto> loanDtos = new ArrayList<LoanDto>();
        for (Loan loan : loans) {
            loanDtos.add((LoanDto)beanMapper.map(loan,LoanDto.class));            
        }
        
        return loanDtos;
    }

    @Transactional(readOnly=true)
    @Override
    public LoanDto getLoan(Integer id) {
        Loan loan = loanDao.getLoan(id);
        LoanDto loanDto;
        if (loan == null) {
            loanDto = new LoanDto();
        } else {
            loanDto = (LoanDto) beanMapper.map(loan, LoanDto.class);
        }
        return loanDto;        
    }

    @Transactional
    @Override
    public void updateLoan(LoanDto loanDto) {
        Loan loan = loanDao.getLoan(loanDto.getId());
        loan.setAmount(loanDto.getAmount());
        loan.setDisbursalDate(loanDto.getDisbursalDate());
        loan.setInterestRate(loanDto.getInterestRate());
        loanDao.updateLoan(loan);
    }
        
	private void validate(LoanDto loanDto) throws MifosServiceException {
		BeanPropertyBindingResult errors = new BeanPropertyBindingResult(loanDto, "loanDto");
		validator.validate(loanDto, errors);
		if (errors.getErrorCount() > 0) {
			throw new MifosServiceException("Loan validation failed.", errors);
		}
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

	@Override
	public void setValidator(Validator validator) {
		this.validator = validator;
	}

	public void setLoanProductDao(LoanProductDao loanProductDao) {
		this.loanProductDao = loanProductDao;
	}


}
