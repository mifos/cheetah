package org.mifos.ui.client.controller;

import java.beans.PropertyEditorSupport;

import org.joda.time.DateTime;

public class DateTimeEditor extends PropertyEditorSupport {

	public void setAsText(String text) throws java.lang.IllegalArgumentException {
        DateTime dateTime = new DateTime(text);
        this.setValue(dateTime);
    }

	@SuppressWarnings("PMD.OnlyOneReturn")
    public String getAsText() {
    	DateTime dateTime = (DateTime) getValue();
		if (dateTime == null) {
    	    return "";
    	} else {
    		return dateTime.toString();
    	}
    }
}