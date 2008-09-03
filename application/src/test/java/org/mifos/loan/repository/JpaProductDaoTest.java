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

import org.mifos.loan.domain.Product;
import org.mifos.loan.repository.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


@ContextConfiguration(locations= {"/applicationContext.xml", 
									"/repositoryContext.xml"})
@Test(groups = { "integration" })    
public class JpaProductDaoTest extends
		AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private ProductDao productDao;
    
    private Product testProduct1;
    private Product testProduct2;
    
    private Product createProduct (String description, double price) {
    	Product aProduct = new Product();
    	aProduct.setDescription(description);
    	aProduct.setPrice(price);
    	return aProduct;
    }
    @BeforeMethod
    private void instantiateTestProducts() {
    	testProduct1 = createProduct("d1", 1.2);
    	testProduct2 = createProduct("d2", 3.4);
    }
    
    @AfterMethod
    private void cleanUpTestProducts() {
    	testProduct2 = null;
    	testProduct1 = null;
    }

    protected void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }
    
    protected void clearProductTable() {
        super.deleteFromTables(new String[] {"products"});
    }

    public void testClearProductTable() {
    	clearProductTable();
    	Assert.assertEquals(countRowsInTable("products"), 0);
    }
    
    public void testGetProductsFromEmptyTable() {
        clearProductTable();
        List<Product> products = productDao.getProductList();
        Assert.assertEquals(products.size(), 0);
    }
    public void testGetOneProduct() {    	
        clearProductTable();
    	productDao.saveProduct(testProduct1);
    	List<Product> products = productDao.getProductList();
    	Assert.assertEquals(products.size(), 1);
    	Product retrievedProduct = products.get(0);
    	Assert.assertEquals(retrievedProduct, testProduct1);
    }
    
    public void testGetTwoProducts() {
        clearProductTable();
    	productDao.saveProduct(testProduct1);
    	productDao.saveProduct(testProduct2);
    	List<Product> products = productDao.getProductList();
    	Assert.assertEquals(products.size(), 2);
    	Product product1 = products.get(0);
    	Product product2 = products.get(1);
    	Assert.assertTrue( (product1.equals(testProduct1) && product2.equals(testProduct2))
    					|| (product1.equals(testProduct2) && product2.equals(testProduct1)));
    }
    
    public void testUpdateProduct() {
        clearProductTable();
    	productDao.saveProduct(testProduct1);
    	List<Product> products = productDao.getProductList();
    	Product prod = products.get(0);
    	double newPrice = 10.34;
    	prod.setPrice(newPrice);
    	productDao.saveProduct(prod);
    	Product newProd = (productDao.getProductList().get(0));
    	Assert.assertEquals(newProd.getPrice(), newPrice, 0);
    }
}
