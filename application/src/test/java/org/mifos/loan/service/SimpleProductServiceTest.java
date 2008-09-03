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
package org.mifos.loan.service;

import java.util.ArrayList;
import java.util.List;

import org.mifos.loan.domain.Product;
import org.mifos.loan.repository.InMemoryProductDao;
import org.mifos.loan.repository.ProductDao;
import org.mifos.loan.service.SimpleProductService;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


@Test(groups = { "unit" })
public class SimpleProductServiceTest {

    private SimpleProductService productService;
    
    private static final int PRODUCT_COUNT = 2;
    
    private static final Double CHAIR_PRICE = Double.valueOf(20.50);
    private static final String CHAIR_DESCRIPTION = "Chair";
    
    private static final String TABLE_DESCRIPTION = "Table";
    private static final Double TABLE_PRICE = Double.valueOf(150.10);         
    
    private static final int POSITIVE_PRICE_INCREASE = 10;
    
    @BeforeMethod(groups = { "unit" })
    protected void setUp() {
        productService = new SimpleProductService();
        List<Product> products = new ArrayList<Product>();
        
        // stub up a list of products
        Product product = new Product();
        product.setDescription("Chair");
        product.setPrice(CHAIR_PRICE);
        products.add(product);
        
        product = new Product();
        product.setDescription("Table");
        product.setPrice(TABLE_PRICE);
        products.add(product);
        
        ProductDao productDao = new InMemoryProductDao(products);
        productService.setProductDao(productDao);
    }

    public void testGetProductsWithNoProducts() {
        productService = new SimpleProductService();
        productService.setProductDao(new InMemoryProductDao(null));
        Assert.assertNull(productService.getProducts());
    }
    
    public void testGetProducts() {
        List<Product> products = productService.getProducts();
        Assert.assertNotNull(products);        
        Assert.assertEquals(PRODUCT_COUNT, productService.getProducts().size());
    
        Product product = products.get(0);
        Assert.assertEquals(CHAIR_DESCRIPTION, product.getDescription());
        Assert.assertEquals(CHAIR_PRICE, product.getPrice());
        
        product = products.get(1);
        Assert.assertEquals(TABLE_DESCRIPTION, product.getDescription());
        Assert.assertEquals(TABLE_PRICE, product.getPrice());      
    }   
    
    @SuppressWarnings("PMD.AvoidCatchingNPE")
    public void testIncreasePriceWithNullListOfProducts() {
        productService = new SimpleProductService();
        productService.setProductDao(new InMemoryProductDao(null));
        productService.increasePrice(POSITIVE_PRICE_INCREASE);
    }
    
    public void testIncreasePriceWithEmptyListOfProducts() {
            productService = new SimpleProductService();
            productService.setProductDao(new InMemoryProductDao(new ArrayList<Product>()));
            productService.increasePrice(POSITIVE_PRICE_INCREASE);
    }
    
    public void testIncreasePriceWithPositivePercentage() {
        productService.increasePrice(POSITIVE_PRICE_INCREASE);
        double expectedChairPriceWithIncrease = 22.55;
        double expectedTablePriceWithIncrease = 165.11;
        
        List<Product> products = productService.getProducts();      
        Product product = products.get(0);
        Assert.assertEquals(expectedChairPriceWithIncrease, product.getPrice());
        
        product = products.get(1);      
        Assert.assertEquals(expectedTablePriceWithIncrease, product.getPrice());       
    }
}
