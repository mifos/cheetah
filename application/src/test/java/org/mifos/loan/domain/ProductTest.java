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
package org.mifos.loan.domain;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import org.mifos.loan.domain.Product;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test(groups = { "unit" })
public class ProductTest {

    private Product product;

    @BeforeMethod(groups = { "unit" })
	protected void setUp() {
        product = new Product();
    }


	public void testSetAndGetDescription() {
        String testDescription = "aDescription";
        assertNull(product.getDescription());
        product.setDescription(testDescription);
        assertEquals(testDescription, product.getDescription());
    }

	public void testSetAndGetPrice() {
        double testPrice = 100.00;
        assertEquals(0, 0, 0);    
        product.setPrice(testPrice);
        assertEquals(testPrice, product.getPrice(), 0);
    }
    
    public void testSomething() {
    	assertTrue(true);
    }
  
}
