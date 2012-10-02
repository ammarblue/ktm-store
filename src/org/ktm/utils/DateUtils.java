package org.ktm.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    private static final String DATE_FORMAT = "dd-MM-yyyy";

    public static Date formatString(String sdate) throws ParseException {
        if (!sdate.isEmpty()) {
            SimpleDateFormat sf = new SimpleDateFormat(DATE_FORMAT);
            return sf.parse(sdate);
        }
        return null;
    }

    public static String formatDate(Date date) throws ParseException {
        if (date != null) {
            SimpleDateFormat sf = new SimpleDateFormat(DATE_FORMAT);
            return sf.format(date);
        }
        return null;
    }

    public static String formatNowDate() throws ParseException {
        java.text.SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy", java.util.Locale.getDefault());
        return sdf.format(new Date());
    }
}
