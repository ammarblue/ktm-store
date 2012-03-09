package org.ktm.helper;

import java.sql.Blob;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ConvertUtils {

    private static Date defaultDate = null;

    public static Date convertDate(String value, Locale locale) {
        if (locale == null) {
            locale = Locale.getDefault();
        }

        Date date = null;

        // Guessing the default format that java.util.Date uses.
        SimpleDateFormat fmt = new SimpleDateFormat("EEE MMM dd hh:mm:ss z yyyy",locale);

        try {
            date = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, locale).parse(value);
        }
        catch (java.text.ParseException e1) {
            try {
                date = DateFormat.getTimeInstance(DateFormat.DEFAULT, locale).parse(value);
            }
            catch (java.text.ParseException e2) {
                try {
                    date = DateFormat.getDateInstance(DateFormat.DEFAULT, locale).parse(value);
                }
                catch (java.text.ParseException e3) {
                    try {
                        date = fmt.parse(value);
                    }
                    catch (java.text.ParseException e4) {
                        try {
                            fmt = new SimpleDateFormat("dd-MM-yyyy");
                            date = fmt.parse(value);
                        }
                        catch (java.text.ParseException e5) {}
                    }
                }

            }
        }

        if (date == null) {
            date = defaultDate;
        }
        return date;
    }


    public static void setDefaultDate(Date newDefaultDate) {
        defaultDate = newDefaultDate;
    }


    public static char string2char(String str) {
        if (str == null || str.length() < 1) {
            throw new IllegalArgumentException("Cannot convert string '" + str + "' to char");
        }

        return str.charAt(0);
    }

    public static Character string2character(String str) {
        if (str == null || str.length() < 1) {
            return null;
        }

        return new Character(str.charAt(0));
    }

    public static byte[] blob2Bytes(Blob blob) throws SQLException {
        if (blob == null) {
            return null;
        }

        return blob.getBytes(1, (int)blob.length());
    }
}