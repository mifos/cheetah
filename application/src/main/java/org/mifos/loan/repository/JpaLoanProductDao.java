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
