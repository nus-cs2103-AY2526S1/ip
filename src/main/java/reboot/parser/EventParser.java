package reboot.parser;

import static reboot.parser.Parser.isBeforeToday;
import static reboot.parser.Parser.isNotValidRecurrence;
import static reboot.parser.Parser.isOneElement;
import static reboot.parser.Parser.parseDateAndTime;
import static reboot.parser.Parser.parseDateOnly;

import java.time.LocalDate;
import java.time.LocalDateTime;

import reboot.RebootException;
import reboot.command.AddCommand;
import reboot.command.Command;
import reboot.command.ErrorCommand;
import reboot.task.Event;
import reboot.task.recurring.RecurringEvent;

/**
 * Represents a parser that will make sense of user event input.
 */
public class EventParser {

    /**
     * Returns an AddCommand for the chatbot to complete from specified input.
     * If unable to understand input, throw IllegalArgumentException.
     * @param input Input of user to be converted to chatbot command.
     */
    public static Command parse(String[] input) throws RebootException {

        if (isOneElement(input)) {
            throw new RebootException(
                    "Proper usage: event {description} /from {start date} /to {end date}");
        }

        assert input[1] != null : "Description should not be null";

        // Separate the description and the start/end dates
        String[] words = input[1].split(" /from ");

        if (isOneElement(words)) {
            throw new RebootException(
                    "Proper usage: event {description} /from {start date} /to {end date}");
        }

        assert words[1] != null : "Dates should not be null";

        String description = words[0];

        String[] dates = words[1].split(" /to ");

        if (isOneElement(dates)) {
            throw new RebootException(
                    "Proper usage: event {description} /from {start date} /to {end date}");
        }

        String[] eventStrings = dates[1].split(" /recurring ", 2);

        boolean isRecurringEvent = !isOneElement(eventStrings);

        if (isRecurringEvent) {
            if (isNotValidRecurrence(eventStrings[1])) {
                return new ErrorCommand("Only daily/weekly/monthly recurrence is supported");
            }
            try {
                // Try to parse with datetime
                LocalDateTime startDateTime = parseDateAndTime(dates[0]);
                LocalDateTime endDateTime = parseDateAndTime(eventStrings[0]);
                if (isBeforeToday(startDateTime) || isBeforeToday(endDateTime)) {
                    return new ErrorCommand("Event is already over");
                }
                return new AddCommand(new RecurringEvent(
                        description, false, startDateTime, endDateTime, eventStrings[1]));
            } catch (Exception e) {
                // Otherwise parse as date only
                LocalDate startDate = parseDateOnly(dates[0]);
                LocalDate endDate = parseDateOnly(eventStrings[0]);
                if (isBeforeToday(startDate) || isBeforeToday(endDate)) {
                    return new ErrorCommand("Event is already over");
                }
                return new AddCommand(new RecurringEvent(
                        description, false, startDate, endDate, eventStrings[1]));
            }
        } else {
            try {
                // Try to parse with datetime
                LocalDateTime startDateTime = parseDateAndTime(dates[0]);
                LocalDateTime endDateTime = parseDateAndTime(eventStrings[0]);
                if (isBeforeToday(startDateTime) || isBeforeToday(endDateTime)) {
                    return new ErrorCommand("Event is already over");
                }
                return new AddCommand(new Event(description, false, startDateTime, endDateTime));
            } catch (Exception e) {
                // Otherwise parse as date only
                LocalDate startDate = parseDateOnly(dates[0]);
                LocalDate endDate = parseDateOnly(eventStrings[0]);

                return new AddCommand(new Event(description, false, startDate, endDate));
            }
        }
    }
}
