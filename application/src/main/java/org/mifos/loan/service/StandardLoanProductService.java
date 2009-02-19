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

import java.util.ArrayList;
import java.util.List;

import org.mifos.core.MifosServiceException;
import org.mifos.loan.domain.LoanProduct;
import org.mifos.loan.repository.LoanDao;
import org.mifos.loan.repository.LoanProductDao;

public class StandardLoanProductService implements LoanProductService {

	private LoanProductDao loanProductDao;
	private LoanDao loanDao;
	
	@Override
	public void deleteLoanProduct(LoanProductDto loanProductDto) throws MifosServiceException {
        LoanProduct loanProduct = disAssembleLoanProduct(loanProductDto);
	    Integer loanProductId = loanProduct.getId();
        if (loanDao.loansExistForLoanProduct(loanProductId)) {
	        throw new MifosServiceException("Could not delete loan product - loans exist that use this product.");
	    }
        loanProductDao.deleteLoanProduct(loanProduct);
	}

	@Override
	public LoanProductDto getLoanProduct(Integer id) {
		return assembleDto (loanProductDao.get(id));
	}

	@Override
	public List<LoanProductDto> getAll() {
		List<LoanProduct> loanProducts = loanProductDao.getLoanProducts();
		List<LoanProductDto> loanProductDTOs = new ArrayList<LoanProductDto>();
		for (LoanProduct loanProduct: loanProducts) {
			loanProductDTOs.add(assembleDto(loanProduct));
		}
		return loanProductDTOs;
	}

	@Override
	public LoanProductDto createLoanProduct(LoanProductDto loanProductDto) {
		LoanProduct product = loanProductDao.createLoanProduct(loanProductDto.getLongName(), loanProductDto.getShortName(),
															   loanProductDto.getMinInterestRate(), loanProductDto.getMaxInterestRate(), 
															   loanProductDto.getStatus());
		return assembleDto(product);
	}

	@Override
	public LoanProductDto updateLoanProduct(LoanProductDto dto) {
		LoanProduct product = loanProductDao.get(dto.getId());
		product.update(dto.getLongName(), dto.getShortName(), 
		               dto.getMinInterestRate(), dto.getMaxInterestRate(),
		               dto.getStatus());
		loanProductDao.updateLoanProduct(product);
		return assembleDto(product);
	}
	
    public void setLoanDao(LoanDao loanDao) {
        this.loanDao = loanDao;
    }

    public void setLoanProductDao (LoanProductDao dao) {
        this.loanProductDao = dao;
    }

    private LoanProductDto assembleDto (LoanProduct loanProduct) {
		LoanProductDto loanProductDto = new LoanProductDto(loanProduct.getLongName(), loanProduct.getShortName(), loanProduct.getMinInterestRate(),
				                                loanProduct.getMaxInterestRate(), loanProduct.getStatus());
		loanProductDto.setId(loanProduct.getId());
		return loanProductDto;
	}
	
	private LoanProduct disAssembleLoanProduct (LoanProductDto dto) {
		return new LoanProduct(dto.getId(), dto.getLongName(), dto.getShortName(), dto.getMinInterestRate(),
				               dto.getMaxInterestRate(), dto.getStatus());
	}

}
