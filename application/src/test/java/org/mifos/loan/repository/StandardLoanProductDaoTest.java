package org.mifos.loan.repository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.dbunit.DatabaseUnitException;
import org.dbunit.dataset.DataSetException;
import org.mifos.core.MifosException;
import org.mifos.loan.domain.LoanProduct;
import org.mifos.loan.domain.LoanProductStatus;
import org.mifos.test.framework.util.SimpleDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import edu.umd.cs.findbugs.annotations.SuppressWarnings;

@ContextConfiguration(locations={"classpath:integrationTestContext.xml"})
@TransactionConfiguration(transactionManager="transactionManager")
@Test(groups = { "integration" })
@edu.umd.cs.findbugs.annotations.SuppressWarnings(value={"UPM"}, justification="@BeforeMethod & @AfterMethod methods are called by testNG")
@java.lang.SuppressWarnings("PMD.SignatureDeclareThrowsException") // for test cases, throwing Exception is ok
public class StandardLoanProductDaoTest  
			extends	AbstractTransactionalTestNGSpringContextTests{
	
    @Autowired
	private LoanProductDao loanProductDao;
    @Autowired
    private DriverManagerDataSource dataSource;
    
    private LoanProductDaoTestHelper loanProductDaoTestHelper;
    
    @BeforeMethod
    @SuppressWarnings("PMD.UnusedPrivateMethod")    
    private void setUp() {
        super.deleteFromTables(new String[] {"loans"});
        super.deleteFromTables(new String[] {"loanproducts"});
        loanProductDaoTestHelper = new LoanProductDaoTestHelper(loanProductDao);
    }
	
	public void testCreateOneProduct() {
	    loanProductDaoTestHelper.testCreateOneProduct();
	}
	
	@SuppressWarnings("PMD.SignatureDeclareThrowsException") // for test cases, throwing Exception is ok
	public void testGetProducts() throws Exception {
	    insertLoanProductTwoProductsDataSet();
	    List<LoanProduct> products = loanProductDao.getLoanProducts ();
	    Assert.assertEquals(products.size(), 2);
	    Collections.sort(products, loanProductComparator());
	    Assert.assertEquals(products.get(0).getLongName(), "long1", "First product retrieved has incorrect long name");
        Assert.assertEquals(products.get(1).getLongName(), "long2", "Second product retrieved has incorrect long name");
	}
	
    @SuppressWarnings("PMD.SignatureDeclareThrowsException") // for test cases, throwing Exception is ok
	public void testGet() throws Exception {
	    insertLoanProductTwoProductsDataSet();
	    LoanProduct product1 = loanProductDao.get(1);
        Assert.assertEquals(product1.getLongName(), "long1", "Didn't get expected loan product");
        LoanProduct product2 = loanProductDao.get(2);
        Assert.assertEquals(product2.getLongName(), "long2", "Didn't get expected loan product");
	}
	
    @SuppressWarnings("PMD.SignatureDeclareThrowsException") // for test cases, throwing Exception is ok
	public void testUpdate() throws Exception {
	    insertLoanProductTwoProductsDataSet();
	    LoanProduct product = new LoanProduct(Integer.valueOf(1), "long3", "short3", 9.0, 10.0, LoanProductStatus.INACTIVE);
        loanProductDao.updateLoanProduct(product);
        LoanProduct retrievedProduct = loanProductDao.get(1);
        Assert.assertEquals(retrievedProduct.getLongName(), "long3", "Didn't update long name");
        Assert.assertEquals(retrievedProduct.getShortName(), "short3", "Didn't update short name");
        Assert.assertEquals(retrievedProduct.getMaxInterestRate(), 10.0, "Didn't update maxInterestRate");
        Assert.assertEquals(retrievedProduct.getMinInterestRate(), 9.0, "Didn't update maxInterestRate");
        Assert.assertEquals(retrievedProduct.getStatus(), LoanProductStatus.INACTIVE, "Didn't update status");
	}
		
    public void testDeleteLoanProduct() throws MifosException {
        loanProductDaoTestHelper.testDeleteLoanProduct();
    }
    
    @Test(enabled=false)
    public DriverManagerDataSource getDataSource() {
        return dataSource;
    }

    @Test(enabled=false)
    public void setDataSource(DriverManagerDataSource dataSource) {
        this.dataSource = dataSource;
    }

    private void insertLoanProductTwoProductsDataSet() throws DataSetException, IOException, SQLException, DatabaseUnitException {
        SimpleDataSet simpleDataSet = new SimpleDataSet();
        simpleDataSet.row("loanProducts", "id=1", "longName=long1",  "maxInterestRate=2.0", "minInterestRate=1.0", "shortName=lp1", "status=ACTIVE", "deletedStatus=VISIBLE"); 
        simpleDataSet.row("loanProducts", "id=2", "longName=long2",  "maxInterestRate=2.0", "minInterestRate=1.0", "shortName=lp2", "status=ACTIVE", "deletedStatus=VISIBLE"); 
        simpleDataSet.row("loans");
        simpleDataSet.insert(this.dataSource);
    }

    private Comparator loanProductComparator() {
        Comparator c = new Comparator() {
            @Override
            public int compare(Object loanProduct1, Object loanProduct2) {
                int id1 = ((LoanProduct)loanProduct1).getId();
                int id2 = ((LoanProduct)loanProduct2).getId();
                return id1 - id2;
            }
        };
        return c;
    }
}


