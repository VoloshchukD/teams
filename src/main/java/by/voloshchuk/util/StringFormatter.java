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

    public static String parseCommand(String command) {
        return command.toUpperCase().replaceAll(
                COMMAND_SEPARATOR, ENUM_COMMAND_SEPARATOR);
    }

    public static String transformCommand(String command) {
        return command.toLowerCase().replaceAll(
                ENUM_COMMAND_SEPARATOR, COMMAND_SEPARATOR);
    }

}
