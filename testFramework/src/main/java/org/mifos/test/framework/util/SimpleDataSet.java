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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dbunit.DatabaseUnitException;
import org.dbunit.dataset.DataSetException;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class SimpleDataSet {

    private final List<Row> rows;
    
    public SimpleDataSet() {
        this.rows = new ArrayList<Row>();
    }
    
    public void row(String tableName, String...columnAndValuePairs) {
        rows.add(new Row(tableName, columnAndValuePairs));
    }
    
    @SuppressWarnings("PMD.InsufficientStringBufferDeclaration") // test method doesn't need performance optimization yet
    public String toString() {
        StringBuffer dataSet = new StringBuffer();
        dataSet.append("<dataset>\n");
        for (Row row : this.rows) {
            dataSet.append(row.toString());
        }
        dataSet.append("</dataset>");
        return dataSet.toString();
    }

    public void insert(DriverManagerDataSource dataSource) throws DataSetException, IOException, SQLException, DatabaseUnitException {
        (new DatabaseTestUtils()).cleanAndInsertDataSet(this.toString(), dataSource);
    }
    
    private static class Row {
        
        private final String tableName;
        private final List<ColumnAndValuePair> columnAndValuePairs;
        
        public Row(String tableName, String...columnAndValuePairs) {
            this.tableName = tableName;
            this.columnAndValuePairs = new ArrayList<ColumnAndValuePair>();
            for (String columnAndValuePairString:columnAndValuePairs ) {
                this.columnAndValuePairs.add(this.getColumnAndValuePair(columnAndValuePairString));
            }
        }

        public String getTableName() {
            return tableName;
        }

        public List<ColumnAndValuePair> getColumnAndValuePairs() {
            return new ArrayList<ColumnAndValuePair>(columnAndValuePairs);
        }

        private ColumnAndValuePair getColumnAndValuePair(
                String columnAndValuePairString) {
            String[] tokens = columnAndValuePairString.split("=");
            return new ColumnAndValuePair(tokens[0], tokens[1]);
        }

        @SuppressWarnings("PMD.InsufficientStringBufferDeclaration") // test method doesn't need performance optimization yet
        public String toString() {
            StringBuffer rowDataSet = new StringBuffer();
            rowDataSet.append('<');
            rowDataSet.append(this.tableName);
            rowDataSet.append(' ');
            for (ColumnAndValuePair columnAndValuePair: this.columnAndValuePairs) {
                rowDataSet.append(columnAndValuePair.toString());
            }
            rowDataSet.append("/>\n");
            return rowDataSet.toString();
        }
    }
    
    private static class ColumnAndValuePair {
     
        private final String column;
        private final String value;

        public ColumnAndValuePair(String column, String value) {
            this.column = column;
            this.value = value;
        }

        public String getColumn() {
            return column;
        }

        public String getValue() {
            return value;
        }

        @SuppressWarnings("PMD.InsufficientStringBufferDeclaration") // test method doesn't need performance optimization yet
        public String toString() {
            StringBuffer columnValuePairDataSet = new StringBuffer();
            columnValuePairDataSet.append(this.column);
            columnValuePairDataSet.append("=\"");
            columnValuePairDataSet.append(this.value);
            columnValuePairDataSet.append("\" ");
            return columnValuePairDataSet.toString();
        }
        
    }
    
}
