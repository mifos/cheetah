package org.mifos.loan.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.mifos.loan.domain.LoanProduct;
import org.springframework.transaction.annotation.Transactional;

public class JpaLoanProductDao implements LoanProductDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public void deleteLoanProduct(LoanProduct loanProduct) {
		LoanProduct retrievedLoanProduct = entityManager.find(LoanProduct.class, loanProduct.getId());
		entityManager.remove(retrievedLoanProduct);

	}

	@Override
	@Transactional(readOnly=true)
	public LoanProduct get(Integer id) {
		return entityManager.find(LoanProduct.class, id);
	}

	@Override
	@Transactional(readOnly=true)
	public LoanProduct getByShortName(String shortName) {
		return (LoanProduct) entityManager.createQuery("SELECT lp from LoanProduct lp WHERE lp.shortName = :shortName")
					.setParameter("shortName", shortName)
					.getSingleResult();
	}

	@Override
	@Transactional(readOnly=true)
	public List<LoanProduct> getLoanProducts() {
		Query query = entityManager.createQuery("from LoanProduct");
		return query.getResultList();
	}

	@Override
	@Transactional
	public void saveLoanProduct(LoanProduct loanProduct) {
		if (loanProduct.getId() == null) {
			entityManager.persist(loanProduct);
		}
		else {
			entityManager.merge(loanProduct);
		}
	}

}
