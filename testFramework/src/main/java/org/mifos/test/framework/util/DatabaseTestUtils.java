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

import java.io.IOException;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.SQLException;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class DatabaseTestUtils {

    @SuppressWarnings("PMD.InsufficientStringBufferDeclaration") // test method doesn't need performance optimization yet
    public void deleteDataFromTables(DriverManagerDataSource dataSource, String...tableNames) 
    throws IOException, DataSetException, SQLException, DatabaseUnitException {
        StringBuffer dataSet = new StringBuffer();
        dataSet.append("<dataset>");
        for (String tableName:tableNames) {
            dataSet.append("<" + tableName + "/>");
        }
        dataSet.append("</dataset>");
        cleanAndInsertDataSet(dataSet.toString(), dataSource);
    }

    /**
     * Execute a DbUnit CLEAN_INSERT. Parameter xmlString must be formatted as a DBUnit
     * xml dataset. This method can be safely invoked inside a Spring-managed transaction.
     * @param xmlString
     * @param dataSource
     * @throws IOException
     * @throws DataSetException
     * @throws SQLException
     * @throws DatabaseUnitException
     */
    @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
    //Rationale: You cannot define new local variables in the try block because the finally block must reference it.
    public void cleanAndInsertDataSet(String xmlString, DriverManagerDataSource dataSource) 
                    throws IOException, DataSetException, SQLException, DatabaseUnitException {
        StringReader dataSetXmlStream = new StringReader(xmlString);
        Connection jdbcConnection = null;
        try {
            IDataSet dataSet = new FlatXmlDataSet(dataSetXmlStream);
            jdbcConnection = DataSourceUtils.getConnection(dataSource);
            IDatabaseConnection databaseConnection = new DatabaseConnection(jdbcConnection);
            DatabaseOperation.CLEAN_INSERT.execute(databaseConnection, dataSet);
        }
        finally {
            jdbcConnection.close();
            DataSourceUtils.releaseConnection(jdbcConnection, dataSource);
        }
    }
}
