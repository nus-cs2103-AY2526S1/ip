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
import reboot.task.Deadline;
import reboot.task.recurring.RecurringDeadline;

/**
 * Represents a parser that will make sense of user deadline input.
 */
public class DeadlineParser {

    /**
     * Returns an AddCommand for the chatbot to complete from specified input.
     * If unable to understand input, throw IllegalArgumentException.
     * @param input Input of user to be converted to chatbot command.
     */
    public static Command parse(String[] input) throws RebootException {
        if (isOneElement(input)) {
            throw new RebootException("Proper usage: deadline {description} /by {due date}");
        }

        assert input[1] != null : "Description should not be null";

        // Get the description and due date of task
        String[] words = input[1].split(" /by ");

        if (isOneElement(words)) {
            throw new RebootException("Proper usage: deadline {description} /by {due date}");
        }

        String[] deadlineStrings = words[1].split(" /recurring ", 2);

        boolean isRecurringDeadline = !isOneElement(deadlineStrings);

        if (isRecurringDeadline) {
            if (isNotValidRecurrence(deadlineStrings[1])) {
                return new ErrorCommand("Only daily/weekly/monthly recurrence is supported");
            }
            try {
                // Try to parse with datetime
                LocalDateTime dueDateTime = parseDateAndTime(deadlineStrings[0]);
                if (isBeforeToday(dueDateTime)) {
                    return new ErrorCommand("Deadline task is already over");
                }
                return new AddCommand(new RecurringDeadline(
                        words[0], false, dueDateTime, deadlineStrings[1]));
            } catch (Exception e) {
                // Otherwise parse as date only
                LocalDate dueDate = parseDateOnly(deadlineStrings[0]);
                if (isBeforeToday(dueDate)) {
                    return new ErrorCommand("Deadline task is already over");
                }
                return new AddCommand(new RecurringDeadline(
                        words[0], false, dueDate, deadlineStrings[1]));
            }
        } else {
            try {
                // Try to parse with datetime
                LocalDateTime dueDateTime = parseDateAndTime(deadlineStrings[0]);
                if (isBeforeToday(dueDateTime)) {
                    return new ErrorCommand("Deadline task is already over");
                }
                return new AddCommand(new Deadline(words[0], false, dueDateTime));
            } catch (Exception e) {
                // Otherwise parse as date only
                LocalDate dueDate = parseDateOnly(deadlineStrings[0]);
                if (isBeforeToday(dueDate)) {
                    return new ErrorCommand("Deadline task is already over");
                }
                return new AddCommand(new Deadline(words[0], false, dueDate));
            }
        }
    }
}
