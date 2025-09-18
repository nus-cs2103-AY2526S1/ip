package alune.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This class parses string input to determine the program's actions.
 * 
 * @author nghnaomi
 */

public class Parser {
    public static int parseMarkCommand(String input) throws NumberFormatException {
        return Integer.parseInt(input.substring(5).trim()) - 1;
    }

    public static int parseUnmarkCommand(String input) throws NumberFormatException {
        return Integer.parseInt(input.substring(7).trim()) - 1;
    }

    public static String getDeadlineDescription(String input) {
        String key = " /";
        int keyIndex = input.indexOf(key, 9);
        return input.substring(9, keyIndex).trim();
    }

    public static String getEventDescription(String input) {
        String key = " /";
        int keyIndex = input.indexOf(key, 6);
        return input.substring(6, keyIndex).trim();
    }

    public static String getDeadlineString(String input) {
        return input.split("/by ")[1];
    }

    public static String getEventString(String input, boolean start) {
        int keyIndex1 = input.indexOf(" /from", 9);
        int keyIndex2 = input.indexOf(" /to", keyIndex1);
        if (start) {
            return input.substring(keyIndex1 + 6, keyIndex2).trim();
        } else {
            return input.substring(keyIndex2 + 4).trim();
        }
    }

    public static LocalDateTime getDateTime(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        try {
            return LocalDateTime.parse(input, formatter);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public static String parseFindCommand(String input) {
        return (input.substring(5).trim());
    }
}
