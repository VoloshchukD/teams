package by.voloshchuk.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringFormatter {

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private static final String COMMAND_SEPARATOR = "-";

    private static final String ENUM_COMMAND_SEPARATOR = "_";

    public static Date parseDate(String stringDate) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        return dateFormat.parse(stringDate);
    }

    public static String parseEnumStringValue(String value) {
        return value.toUpperCase().replaceAll(
                COMMAND_SEPARATOR, ENUM_COMMAND_SEPARATOR);
    }

    public static String transformEnumString(String value) {
        return value.toLowerCase().replaceAll(
                ENUM_COMMAND_SEPARATOR, COMMAND_SEPARATOR);
    }

}
