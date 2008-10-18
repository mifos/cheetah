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

package org.mifos.test.framework.util;

import junit.framework.Assert;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test(groups = { "unit" })
public class SimpleDataSetTest {

    private SimpleDataSet simpleDataSet; 
    
    @BeforeMethod
    public void setUp() {
        this.simpleDataSet = new SimpleDataSet();
    }
    
	public void testEmptyDataSet() {
	    String expectedOutput = "<dataset>\n</dataset>";
	    Assert.assertEquals(this.simpleDataSet.toString(), expectedOutput);
	}

    public void testDataSetOneEmptyTable() {
        String expectedOutput = "<dataset>\n<firstTable />\n</dataset>";
        this.simpleDataSet.row("firstTable");
        Assert.assertEquals(expectedOutput, this.simpleDataSet.toString());
    }

    public void testDataSetTwoEmptyTables() {
        String expectedOutput = "<dataset>\n<firstTable />\n<secondTable />\n</dataset>";
        this.simpleDataSet.row("firstTable");
        this.simpleDataSet.row("secondTable");
        Assert.assertEquals(expectedOutput, this.simpleDataSet.toString());
    }

    public void testDataSetOneTableOneColumn() {
        String expectedOutput = "<dataset>\n<firstTable name=\"value\" />\n</dataset>";
        this.simpleDataSet.row("firstTable", "name=value");
        Assert.assertEquals(expectedOutput, this.simpleDataSet.toString());
    }

    public void testDataSetOneTableTwoColumns() {
        String expectedOutput = "<dataset>\n<firstTable name1=\"value1\" name2=\"value2\" />\n</dataset>";
        this.simpleDataSet.row("firstTable", "name1=value1", "name2=value2");
        Assert.assertEquals(expectedOutput, this.simpleDataSet.toString());
    }

    public void testDataSetOneTableNumericData() {
        String expectedOutput = "<dataset>\n<firstTable name1=\"10\" name2=\"3.145\" />\n</dataset>";
        this.simpleDataSet.row("firstTable", "name1=10", "name2=3.145");
        Assert.assertEquals(expectedOutput, this.simpleDataSet.toString());
    }

    public void testDataSetOneTableTwoRows() {
        String expectedOutput = "<dataset>\n" +
                "<firstTable name1=\"value00\" name2=\"value01\" />\n" +
                "<firstTable name1=\"value10\" name2=\"value11\" />\n" +
                "</dataset>";
        this.simpleDataSet.row("firstTable", "name1=value00", "name2=value01");
        this.simpleDataSet.row("firstTable", "name1=value10", "name2=value11");
        Assert.assertEquals(expectedOutput, this.simpleDataSet.toString());
    }

    public void testDataSetTwoTablesTwoRows() {
        String expectedOutput = "<dataset>\n" +
                "<firstTable name1=\"value00\" name2=\"value01\" />\n" +
                "<firstTable name1=\"value10\" name2=\"2.178\" />\n" +
                "<secondTable fooName1=\"10.00001\" barName=\"someValue\" />\n" +
                "</dataset>";
        this.simpleDataSet.row("firstTable", "name1=value00", "name2=value01");
        this.simpleDataSet.row("firstTable", "name1=value10", "name2=2.178");
        this.simpleDataSet.row("secondTable", "fooName1=10.00001", "barName=someValue");
        Assert.assertEquals(expectedOutput, this.simpleDataSet.toString());
    }

}
