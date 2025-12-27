package ip.commands;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Class to check if String is a valid date
 */
public class DateValidator {

    /**
     * Returns a boolean based on if the string is in a valid date format
     * @param dateStr User input date string
     * @return true if date is valid, false otherwise
     */
    public static boolean isValidDate(String dateStr) {
        try {
            LocalDate.parse(dateStr);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
}
