package org.mifos.loan.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.mifos.loan.domain.DeletedStatus;
import org.mifos.loan.domain.LoanProduct;
import org.mifos.loan.domain.LoanProductStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(locations={"classpath:unitTestContext.xml"})
public class StandardLoanProductDao implements LoanProductDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public LoanProduct createLoanProduct (String longName, String shortName, Double minInterestRate,
			Double maxInterestRate, LoanProductStatus status) {
		LoanProduct product = new LoanProduct(null, longName,shortName,
				                              minInterestRate, maxInterestRate, status);
		entityManager.persist(product);
		return product;
	}

	@Override
	@Transactional
	public void deleteLoanProduct(LoanProduct loanProduct) {
		LoanProduct loanProductToBeRemoved = entityManager.find(LoanProduct.class, loanProduct.getId());
		loanProductToBeRemoved.setDeletedStatus(DeletedStatus.DELETED);
		entityManager.persist(loanProductToBeRemoved);

	}

	@Override
	@Transactional(readOnly=true)
	public LoanProduct get(Integer id) {
		return entityManager.find(LoanProduct.class, id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<LoanProduct> getLoanProducts() {
		Query query = entityManager.createQuery("from LoanProduct where deletedStatus='VISIBLE'");
		return query.getResultList();
	}

	@Override
	@Transactional
	public void updateLoanProduct(LoanProduct loanProduct) {
		entityManager.merge(loanProduct);
	}

	
}
