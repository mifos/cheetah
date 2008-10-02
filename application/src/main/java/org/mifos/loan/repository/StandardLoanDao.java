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

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.mifos.loan.domain.Loan;
import org.mifos.loan.domain.LoanProduct;
import org.springframework.transaction.annotation.Transactional;

public class StandardLoanDao  implements LoanDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@Transactional
	public Loan createLoan(Integer clientId, BigDecimal loanAmount, BigDecimal interestRate,
			LoanProduct loanProduct) {
		Loan loan = new Loan(clientId, loanAmount, interestRate, loanProduct);
		entityManager.persist(loan);
		return loan;
	}

    @Transactional(readOnly=true)
	public List<Loan> getAll() {
		Query query = entityManager.createQuery("from Loan");
		return query.getResultList();
	}

    @Override
    @Transactional(readOnly=true)
    public List<Loan> findLoansForClient(Integer clientId) {
        Query query = entityManager.createQuery("SELECT loan from Loan loan where loan.clientId = :clientId");
        query.setParameter("clientId", clientId);
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly=true)
    public Loan getLoan(Integer id) {
        return entityManager.find(Loan.class, id);
    }

}
