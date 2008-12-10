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

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;

/**
 *
 */
public class DbUnitDataExport {

    @SuppressWarnings("PMD.SystemPrintln")
    public static void main(String[] args) throws ClassNotFoundException, SQLException, DatabaseUnitException, FileNotFoundException, IOException {
        
        // create the Options
        Options options = new Options();
        
        String outfileOptionName = "o";
        Option outputFile   = OptionBuilder.withArgName( "file name" )
            .withLongOpt("outputFile")
            .hasArg()
            .withDescription( "use given file for dbunit output" )
            .create( outfileOptionName );
        
        String userOptionName = "u";
        Option userOption   = OptionBuilder.withArgName( "user name" )
            .withLongOpt("user")
            .hasArg()
            .withDescription( "database user name" )
            .create( userOptionName );
    
        String passwordOptionName = "p";
        Option passwordOption   = OptionBuilder.withArgName( "password" )
            .withLongOpt("password")
            .hasArg()
            .withDescription( "database password" )
            .create( passwordOptionName );
        
        String helpOptionName = "h";
        Option helpOption   = OptionBuilder
            .withLongOpt("help")
            .withDescription( "display help" )
            .create( helpOptionName );
        
    
        options.addOption(outputFile);
        options.addOption(userOption);
        options.addOption(passwordOption);
        options.addOption(helpOption);

        String outputFileName = "dbunit.xml";
        String password = "";
        String user = "";
        
        // create the command line parser
        CommandLineParser parser = new PosixParser();
        try {
            // parse the command line arguments
            CommandLine line = parser.parse( options, args );
            if( line.hasOption( helpOptionName ) ) {
                showHelp(options);
                System.exit(0);
            }
            if( line.hasOption( outfileOptionName ) ) {
                outputFileName = line.getOptionValue(outfileOptionName);
            } 
            if( line.hasOption( userOptionName ) ) {
                user = line.getOptionValue(userOptionName);
            } else {
                missingOption(userOption);
            }
            if( line.hasOption( passwordOptionName ) ) {
                password = line.getOptionValue(passwordOptionName);
            } else {
                missingOption(passwordOption);
            }
        }
        catch( ParseException exp ) {
            fail( "Parsing failed.  Reason: " + exp.getMessage() );
        }      
        
        // database connection
        Class.forName("com.mysql.jdbc.Driver");
        Connection jdbcConnection = null;
        IDataSet fullDataSet;
        try {
            jdbcConnection = DriverManager.getConnection(
                    "jdbc:mysql://localhost/mifos", user, password);
            IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);

            // full database export
            fullDataSet = connection.createDataSet();
            System.out.println("dumping data...");
            FlatXmlDataSet.write(fullDataSet, new FileOutputStream(outputFileName));
        } finally {
            if (jdbcConnection != null) {
                jdbcConnection.close();
            }
        }
        System.out.println("done");
    }

    private static void showHelp(Options options) {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp( "DbUnitDataExport", options );    
    }

    private static void missingOption(Option option) {
        fail("Missing required option: " + option.getArgName());
    }

    @SuppressWarnings("PMD.SystemPrintln")
    @edu.umd.cs.findbugs.annotations.SuppressWarnings(value={"DM_EXIT"}, justification="Command line tool exit")
    private static void fail(String string) {
        System.err.println( string );
        System.exit(0);
    }
}


