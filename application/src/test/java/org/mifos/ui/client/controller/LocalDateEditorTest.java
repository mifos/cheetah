package org.mifos.ui.client.controller;

import java.util.Locale;

import junit.framework.Assert;

import org.joda.time.LocalDate;
import org.testng.annotations.Test;

@Test(groups = { "unit" })
public class LocalDateEditorTest {

    @edu.umd.cs.findbugs.annotations.SuppressWarnings(value={"NP_LOAD_OF_KNOWN_NULL_VALUE"}, justification="testing behavior when localDate is null")
    public void testGetAsTextNull() {
        Locale locale = new Locale("en", "US");
        LocalDateEditor dateTimeEditor = new LocalDateEditor(locale);
        LocalDate localDate = null;
        dateTimeEditor.setValue(localDate);
        Assert.assertEquals("", dateTimeEditor.getAsText());
    }

    public void testDatePattern() {
        Locale locale = new Locale("en", "US");
        LocalDateEditor localDateEditor = new LocalDateEditor(locale);
        LocalDate localDate = new LocalDate("2007-02-09") ;
        localDateEditor.setValue(localDate);
        Assert.assertEquals("2/9/2007", localDateEditor.getAsText());
    }

    public void testGetDatePattern() {
        Locale locale = new Locale("en", "US");
        LocalDateEditor localDateEditor = new LocalDateEditor(locale);
        String actualDatePattern = localDateEditor.getDatePattern();
        Assert.assertEquals("M/d/yyyy", actualDatePattern);
    }

    public void testGetDatePatternFrLocale() {
        Locale locale = new Locale("fr", "FR");
        LocalDateEditor localDateEditor = new LocalDateEditor(locale);
        String actualDatePattern = localDateEditor.getDatePattern();
        Assert.assertEquals("dd/MM/yyyy", actualDatePattern);
    }

}
