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
package org.mifos.ui.loan.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.mifos.loan.domain.Product;
import org.mifos.loan.service.ProductService;
import org.mifos.ui.loan.controller.InventoryController;
import org.springframework.web.servlet.ModelAndView;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;




@SuppressWarnings("PMD.UnusedPrivateMethod")
@edu.umd.cs.findbugs.annotations.SuppressWarnings(value={"UPM"}, justification="@AfterMethod will be called by testNG")
@Test(groups = { "unit" })
public class InventoryControllerTest {
	
	ProductService productService;
	DateTime janFirst2008;
	String productDescription = "a Product";
	
    @AfterMethod()
    private void tearDown() {
    	DateTimeUtils.setCurrentMillisSystem();
    }
    
    /**
     * force current time Jan 1, 2008 to test the controller
     */
    private void setUpTime() throws InterruptedException {
		janFirst2008 = new DateTime(2008, 1, 1, 0, 0, 0, 0);
		DateTimeUtils.setCurrentMillisOffset(janFirst2008.getMillis() 
												- (new DateTime()).getMillis());
   }
    
    /**
     * Set up a mock ProductService implementation that returns
     * a list with just one product that we define here.
     */
    @SuppressWarnings("PMD.AvoidFinalLocalVariable") // needed for inner class
    @edu.umd.cs.findbugs.annotations.SuppressWarnings(value={"SIC"}, justification="No need to worry about inner class optimization for tests")
    private void setUpProductService() {
        Product product = new Product();
        product.setDescription(productDescription);
        final List<Product> products = new ArrayList<Product>();
        products.add(product);
        productService = new ProductService() {
			private static final long serialVersionUID = 1L;
			public List<Product> getProducts() {
        		return products;
        	}
        	public void increasePrice(int percentage) { /* empty on purpose */ }
        };	
    }
    
    private void verifyModelProducts(Object productsModel) {
        List productsModelList = (List) productsModel;
        Assert.assertEquals(productsModelList.size(), 1);
        Product productModel = (Product) productsModelList.get(0);
        Assert.assertEquals(productModel.getDescription(), productDescription);
    }
    
    private void verifyModelTime(Object nowValue) {
        Assert.assertTrue(nowValue instanceof java.util.Date);
		DateTime nowTime = (DateTime) new DateTime(nowValue);

		Assert.assertTrue(janFirst2008.getMillis() <= nowTime.getMillis(),
				          "new time must be later than system time that was set."
				          + "System date set to " + janFirst2008
				          + " but retrieved date was " + nowTime);
    }
    
	public void testHandleRequestView() throws InterruptedException, ServletException, IOException {
    	
        InventoryController controller = new InventoryController();
        
		setUpTime();
		setUpProductService();
        controller.setProductService(productService);
		
        ModelAndView modelAndView = controller.handleRequest(null, null);
        
        Assert.assertEquals("hello", modelAndView.getViewName());
        Assert.assertNotNull(modelAndView.getModel());
        Map<String, Object> modelMap = (Map<String, Object>) modelAndView.getModel().get("model");
        
        verifyModelProducts(modelMap.get("products"));
        
        verifyModelTime(modelMap.get("now"));
     }
}
