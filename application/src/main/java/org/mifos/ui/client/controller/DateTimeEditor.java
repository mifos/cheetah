package org.mifos.ui.client.controller;

import java.beans.PropertyEditorSupport;

import org.joda.time.DateTime;

public class DateTimeEditor extends PropertyEditorSupport {

    public void setAsText(String text) {
        DateTime dateTime = new DateTime(text);
        setValue(dateTime);
    }

	@SuppressWarnings("PMD.OnlyOneReturn")
    public String getAsText(DateTime dateTime) {
    	if (dateTime == null) {
    		return "";
    	} else {
    		return dateTime.toString();
    	}
    }
}