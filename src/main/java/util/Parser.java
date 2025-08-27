//helper class for useful fns
package util;

import exceptions.*;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    //obtaining task index from user input to for access tasks in taskList
    public static int parseIndex(String input, String command, int taskCount) throws InvalidIndexException {
        try {
            int index = Integer.parseInt(input
                    .substring(command.length())
                    .trim()) - 1;
            if (index < 0 || index >= taskCount) throw new InvalidIndexException();
            return index;
        } catch (NumberFormatException e) {
            throw new InvalidIndexException();
        }
    }
    //obtaining date and time from user input
    public static LocalDateTime parseDateTime(String input) throws InvalidDateTimeException {
        input = input.trim();
        //date + time
        DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

        //date only
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        //time only
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HHmm");

        try {
            if (input.contains(" ")) {  //input has both date + time

                return LocalDateTime.parse(input, dtFormat);

            } else if (input.contains("/")) { //input only has date
                LocalDate date = LocalDate.parse(input, dateFormat);
                return date.atStartOfDay();

            } else {    //input only has time
                LocalTime time = LocalTime.parse(input, timeFormat);
                return time.atDate(LocalDate.now());
            }
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException();
        }
    }

    public static LocalDate parseDate(String input) throws InvalidDateTimeException {
        input = input.trim();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return LocalDate.parse(input, formatter);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException();
        }
    }
}
