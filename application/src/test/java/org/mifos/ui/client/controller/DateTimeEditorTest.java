package org.mifos.ui.client.controller;

import junit.framework.Assert;

import org.joda.time.DateTime;
import org.testng.annotations.Test;

@Test(groups = { "unit" })
public class DateTimeEditorTest {
	
	public void testGetAsTextNull() {
		
		DateTimeEditor dateTimeEditor = new DateTimeEditor();
		DateTime dateTime = null;
		dateTimeEditor.setSource(dateTime);
		Assert.assertEquals("", dateTimeEditor.getAsText());
	}

}
