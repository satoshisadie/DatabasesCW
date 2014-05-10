package helpers;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Locale;

public class DateUtils {
    public static String formatDate(DateTime dateTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("d MMMM yyyy HH:mm:ss");
        return dateTimeFormatter.withLocale(Locale.US).print(dateTime);
    }
}
