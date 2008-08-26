/**
 * Copyright (c) 2005-2008 Grameen Foundation USA
 * 1029 Vermont Avenue, NW, Suite 400, Washington DC 20005
 * All rights reserved.
 *
 * Apache License
 * Copyright (c) 2005-2008 Grameen Foundation USA
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you
 * may not use this file except in compliance with the License. You
 * may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.  See the License for the specific language governing
 * permissions and limitations under the License.
 *
 * See also http://www.apache.org/licenses/LICENSE-2.0.html for an
 * explanation of the license and how it is applied.
 *
 */      


package springapp.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import springapp.domain.Product;

/**
Illustrates use of Spring's capabilities to unit-test database
functions using a JDBCTemplate.
<p>
Note that by default each unit test is transparently transactional -- the test is
run in the context of a database transaction that is rolled back at the end of the
test. Using this pattern will both simplify unit tests and help to minimize
dependencies between tests. When needed, you can turn off this feature with the
&#64;NotTransactional annotation.
</p>

<p>
In order to get these tests to work, I had to modify the class
defined in the <a href="http://static.springframework.org/docs/Spring-MVC-step-by-step/">
tutorial</a> in several ways:
</p>

<ul>
    <li> Subclass AbstractTransactionalTestNGSpringContextTests.
    <li> annotate the class with @ContextConfiguration to specify the location
         of the xml file that describes the Spring application context for the test.
    <li> Remove method getConfigurationLocations.
    <li> Annotate productDao with @Autowired to get the DAO injected.
</ul>

 */

@ContextConfiguration(locations= {"classpath:test-context.xml"})
@Test(groups = { "unit" })    
public class JdbcProductDaoTest extends AbstractTransactionalTestNGSpringContextTests {

	@Autowired
    private ProductDao productDao;

    
    @Autowired
    protected void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    /*
    protected String[] getConfigLocations() {
        return new String[] {"classpath:test-context.xml"};
    }
	*/
    
    protected void onSetUpInTransaction() {
        super.deleteFromTables(new String[] {"products"});
        super.executeSqlScript("file:db/load_data.sql", true);
    }

    public void testGetProductList() {
        
        List<Product> products = productDao.getProductList();
        assertEquals(2, products.size());
        
    }
    
    public void testSaveProduct() {
        
        List<Product> products = productDao.getProductList();
        
        for (Product p : products) {
            p.setPrice(200.12);
            productDao.saveProduct(p);
        }
        
        List<Product> updatedProducts = productDao.getProductList();
        for (Product p : updatedProducts) {
            Assert.assertEquals(200.12, p.getPrice());
        }

    }

}