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

package org.mifos.core;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 */
public class GeneratePseudoLocalization {
    private static final Log LOG = LogFactory.getLog(GeneratePseudoLocalization.class);
	private static final String PREFIX = "@@@";
	private static final String SUFFIX = "###";

	/**
	 * Work in progress on a pseudolocalizer that can generate
	 * a _is properties file on the fly.
	 * 
	 *   TODO: pseudolocalizer work
	 *   * refactor code below into multiple methods
	 *   * take multiple properties files as inputs and loop on them
	 *   * integrate this in the maven build using the exec plugin
	 *   * pass prop files in from maven and autogenerate the _is localization
	 *   * remove _is localization from version control
	 */
	public static void main(String[] args) throws IOException {

	    Properties defaultProps = new Properties();
		FileInputStream in;
		
		String directory = "G:/Documents and Settings/Van/My Documents/workspace3/mifos-cheetah/application/src/main/resources/";
		String fileName = "messages";

		in = new FileInputStream(directory + fileName + ".properties");
		defaultProps.load(in);
		in.close();
		
		for (Entry<Object,Object> entry : defaultProps.entrySet()) {
			String key = (String)entry.getKey();
			String value = (String)entry.getValue();
			LOG.info(key + "=" + value);
			StringBuffer buffer = new StringBuffer(PREFIX); // NOPMD by Van on 9/24/08 10:22 PM
			buffer.append(value);
			buffer.append(SUFFIX);
			defaultProps.setProperty(key, buffer.toString());
		}
		for (Entry<Object,Object> entry : defaultProps.entrySet()) {
			String key = (String)entry.getKey();
			String value = (String)entry.getValue();
			LOG.info( key + "=" + value);
		}
		
		FileOutputStream out = new FileOutputStream(directory + fileName + "_is.properties");
		defaultProps.store(out, "---Auto Generated Properties---");
		out.close();

		
	}

}
