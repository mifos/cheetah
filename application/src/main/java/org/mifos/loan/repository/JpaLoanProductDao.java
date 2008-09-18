package org.mifos.loan.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.mifos.loan.domain.LoanProduct;
import org.mifos.loan.domain.LoanProductStatus;
import org.springframework.transaction.annotation.Transactional;

public class JpaLoanProductDao implements LoanProductDao {

	@PersistenceContext
	private EntityManager entityManager;

	public LoanProduct createLoanProduct (String longName, String shortName, Double minInterestRate,
			Double maxInterestRate, LoanProductStatus status) {
		return null;
	}

	@Override
	@Transactional
	public void deleteLoanProduct(LoanProduct loanProduct) {
		LoanProduct loanProductToBeRemoved = entityManager.find(LoanProduct.class, loanProduct.getId());
		entityManager.remove(loanProductToBeRemoved);

	}

	@Override
	@Transactional(readOnly=true)
	public LoanProduct get(Integer id) {
		return entityManager.find(LoanProduct.class, id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<LoanProduct> getLoanProducts() {
		Query query = entityManager.createQuery("from LoanProduct");
		return query.getResultList();
	}

	@Override
	@Transactional
	public void updateLoanProduct(LoanProduct loanProduct) {
		entityManager.merge(loanProduct);
	}

	
}
