package org.mifos.loan.service;

import java.util.ArrayList;
import java.util.List;

import org.mifos.loan.domain.LoanProduct;
import org.mifos.loan.repository.LoanProductDao;

//@edu.umd.cs.findbugs.annotations.SuppressWarnings(value={"NP"})
public class DefaultLoanProductService implements LoanProductService {

	LoanProductDao loanProductDao;
	
	@Override
	public void deleteLoanProduct(String shortName) {
		loanProductDao.deleteLoanProduct(loanProductDao.getByShortName(shortName));
	}

	@Override
	public LoanProductDto getLoanProduct(String shortName) {
		return assembleDto (loanProductDao.getByShortName(shortName));
	}

	@Override
	public List<LoanProductDto> getLoanProducts() {
		List<LoanProduct> loanProducts = loanProductDao.getLoanProducts();
		List<LoanProductDto> loanProductDTOs = new ArrayList<LoanProductDto>();
		for (LoanProduct loanProduct: loanProducts) {
			loanProductDTOs.add(assembleDto(loanProduct));
		}
		return loanProductDTOs;
	}

	@Override
	public LoanProductDto newLoanProduct(LoanProductDto loanProductDto) {
		LoanProduct loanProduct = createLoanProduct (loanProductDto);
		loanProductDao.saveLoanProduct(loanProduct);
		return loanProductDto;
	}

	@Override
	public LoanProductDto updateLoanProduct(LoanProductDto loanProductDto) {
		LoanProduct currentLoanProduct = loanProductDao.getByShortName(loanProductDto.getShortName());
		LoanProduct changedLoanProduct = updateLoanProductFromDto(currentLoanProduct, loanProductDto);
		loanProductDao.saveLoanProduct(changedLoanProduct);
		return assembleDto(changedLoanProduct);
	}
	
	private LoanProductDto assembleDto (LoanProduct loanProduct) {
		return new LoanProductDto(loanProduct.getLongName(), loanProduct.getShortName(), loanProduct.getMinInterestRate(),
				                                loanProduct.getMaxInterestRate(), loanProduct.getStatus());
	}
	
	private LoanProduct createLoanProduct (LoanProductDto dto) {
		return new LoanProduct(dto.getLongName(), dto.getShortName(), dto.getMinInterestRate(),
				               dto.getMaxInterestRate(), dto.getStatus());
	}
	
	void setLoanProductDao (LoanProductDao dao) {
		this.loanProductDao = dao;
	}
	
	private LoanProduct updateLoanProductFromDto (LoanProduct current, LoanProductDto changedDto) {
		current.setLongName(changedDto.getLongName());
		current.setMinInterestRate(changedDto.getMinInterestRate());
		current.setMaxInterestRate(changedDto.getMaxInterestRate());
		current.setStatus(changedDto.getStatus());
		return current;
	}
}
