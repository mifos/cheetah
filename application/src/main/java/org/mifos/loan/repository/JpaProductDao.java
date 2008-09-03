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

import org.mifos.loan.domain.Product;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository("productDao")
public class JpaProductDao implements ProductDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional(readOnly=true)
	public List<Product> getProductList() {
		Query query = entityManager.createQuery("from Product");
		return query.getResultList();
	}

	@Transactional
	public void saveProduct(Product prod) {
		if (prod.getId() == null) {
			entityManager.persist(prod);
		}
		else {
			entityManager.merge(prod);
		}
	}

}
