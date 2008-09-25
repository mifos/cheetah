package org.mifos.ui.client.controller;

import java.beans.PropertyEditorSupport;

import org.joda.time.LocalDate;

public class LocalDateEditor extends PropertyEditorSupport {

	public void setAsText(String text) throws java.lang.IllegalArgumentException {
        LocalDate localDate = new LocalDate(text);
        this.setValue(localDate);
    }

	@SuppressWarnings("PMD.OnlyOneReturn")
    public String getAsText() {
		LocalDate dateTime = (LocalDate) getValue();
		if (dateTime == null) {
    	    return "";
    	} else {
    		return dateTime.toString();
    	}
    }
}