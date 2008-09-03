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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mifos.loan.domain.Product;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;



public class JdbcProductDao extends SimpleJdbcDaoSupport implements ProductDao {

    /** Logger for this class and subclasses */
    private static final Log LOG = LogFactory.getLog(JdbcProductDao.class);


    public List<Product> getProductList() {
        LOG.info("Getting products!");
        List<Product> products = getSimpleJdbcTemplate().query(
                "select id, description, price from products", 
                new ProductMapper());
        return products;
    }

    public void saveProduct(Product prod) {
        LOG.info("Saving product: " + prod.getDescription());
        int count = getSimpleJdbcTemplate().update(
            "update products set description = :description, price = :price where id = :id",
            new MapSqlParameterSource()
            	.addValue("description", prod.getDescription())
                .addValue("price", prod.getPrice())
                .addValue("id", prod.getId()));
        LOG.info("Rows affected: " + count);
    }
    
    private static class ProductMapper implements ParameterizedRowMapper<Product> {

        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            Product prod = new Product();
            prod.setId(rs.getInt("id"));
            prod.setDescription(rs.getString("description"));
            prod.setPrice(Double.valueOf(rs.getDouble("price")));
            return prod;
        }

    }

}
