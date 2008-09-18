package org.mifos.loan.service;

import java.util.ArrayList;
import java.util.List;

import org.mifos.loan.domain.LoanProduct;
import org.mifos.loan.repository.LoanProductDao;

//@edu.umd.cs.findbugs.annotations.SuppressWarnings(value={"NP"})
public class StandardLoanProductService implements LoanProductService {

	LoanProductDao loanProductDao;
	
	@Override
	public void deleteLoanProduct(LoanProductDto product) {
		loanProductDao.deleteLoanProduct(disAssembleLoanProduct(product));
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

	/* implement for a later story
	@Override
	public LoanProductDto updateLoanProduct(LoanProductDto loanProductDto) {
		LoanProduct currentLoanProduct = loanProductDao.get(loanProductDto.getId());
		LoanProduct changedLoanProduct = updateLoanProductFromDto(currentLoanProduct, loanProductDto);
		loanProductDao.updateLoanProduct(changedLoanProduct);
		return assembleDto(changedLoanProduct);
	}
	*/
	
	private LoanProductDto assembleDto (LoanProduct loanProduct) {
		return new LoanProductDto(loanProduct.getLongName(), loanProduct.getShortName(), loanProduct.getMinInterestRate(),
				                                loanProduct.getMaxInterestRate(), loanProduct.getStatus());
	}
	
	private LoanProduct disAssembleLoanProduct (LoanProductDto dto) {
		return new LoanProduct(dto.getId(), dto.getLongName(), dto.getShortName(), dto.getMinInterestRate(),
				               dto.getMaxInterestRate(), dto.getStatus());
	}
	
	void setLoanProductDao (LoanProductDao dao) {
		this.loanProductDao = dao;
	}
	
	/* implement for a later story
		current.setLongName(changedDto.getLongName());
		current.setMinInterestRate(changedDto.getMinInterestRate());
		current.setMaxInterestRate(changedDto.getMaxInterestRate());
		current.setStatus(changedDto.getStatus());
		return current;
	}
	*/
}
