package phuc.errorhandle;

import phuc.exception.PhucException;
import phuc.parser.DateTimeProcess;
import java.time.LocalDateTime;

/**
 * Utility class for handling and validating various error conditions in the application.
 * Provides static methods for input validation and error message generation.
 */
public class ErrorHandler {
    /** Error message for invalid date format */
    public static final String ERROR_DATE_FORMAT =
            "Oops! Date format should be like: DD/MM/YYYY HHMM or YYYY-MM-DD HHMM (◕︵◕)";
    /** Error message for invalid task number */
    public static final String ERROR_INVALID_TASK_NUMBER =
            "Please enter a number between 1 and %d (T_T)";
    /** Error message for empty description */
    public static final String ERROR_EMPTY_DESCRIPTION =
            "Hold on — a %s needs a description. Please type something after '%s'（>﹏<）";
    /** Error message for invalid event format */
    public static final String ERROR_EVENT_FORMAT =
            "Oops! An event must include both a start and an end time — don’t forget to add it (ಥ_ಥ)";
    /** Error message for invalid deadline format */
    public static final String ERROR_DEADLINE_FORMAT =
            "Oops! A deadline must include a date and time — don’t forget to add it (ಥ_ಥ)";
    /** Error message for unknown command */
    public static final String ERROR_UNKNOWN_COMMAND =
            "I don’t understand that command. Please try another command! ༼☯﹏☯༽";
    /** Error message for invalid number format */
    public static final String ERROR_NUMBER_FORMAT =
            "Please enter a valid number for the task index (＞﹏＜)";
    /** Error message for empty command */
    public static final String ERROR_EMPTY_COMMAND =
            "Please enter a command (・人・)";

    /**
     * Throws an exception for an error command input.
     *
     * @throws PhucException
     */
    public static void setErrorEmptyCommand() throws PhucException {
        throw new PhucException(ERROR_EMPTY_COMMAND);
    }

    /**
     * Validates that a task index is within valid bounds.
     *
     * @param input the input string to validate
     * @param maxIndex the maximum valid index
     * @throws PhucException if the input is invalid or out of bounds
     */
    public static void validateTaskIndex(String input, int maxIndex) throws PhucException {
        if (input == null || input.trim().isEmpty()) {
            throw new PhucException(String.format(ERROR_INVALID_TASK_NUMBER, maxIndex - 1));
        }

        try {
            int index = Integer.parseInt(input.trim()) - 1;

            if (index < 0 || index >= maxIndex) {
                throw new PhucException(String.format(ERROR_INVALID_TASK_NUMBER, maxIndex - 1));
            }

        } catch (NumberFormatException e) {
            throw new PhucException(ERROR_NUMBER_FORMAT);
        }
    }

    /**
     * Validates that a task description is not empty.
     *
     * @param description the description to validate
     * @param taskType the type of task (for error message)
     * @param command the command used (for error message)
     * @throws PhucException if the description is empty
     */
    public static void validateDescription(String description, String taskType, String command)
            throws PhucException {
        if (description == null || description.trim().isEmpty()) {
            throw new PhucException(String.format(ERROR_EMPTY_DESCRIPTION, taskType, command));
        }
    }

    /**
     * Validates and parses event format arguments.
     * Returns a string that have parsed in form {description, startDate ,endDate}
     *
     * @param arguments the event arguments string
     * @return array containing description, start date, and end date
     * @throws PhucException if the event format is invalid
     */
    public static String[] validateEventFormat(String arguments) throws PhucException {
        if (arguments == null || arguments.trim().isEmpty()) {
            throw new PhucException(ERROR_EVENT_FORMAT);
        }

        String[] parts = arguments.split("/from", 2);
        if (parts.length != 2) {
            throw new PhucException(ERROR_EVENT_FORMAT);
        }

        String description = parts[0].trim();
        validateDescription(description, "event", "event");

        String[] dateParts = parts[1].split("/to", 2);
        if (dateParts.length != 2) {
            throw new PhucException(ERROR_EVENT_FORMAT);
        }

        String startDate = dateParts[0].trim();
        String endDate = dateParts[1].trim();

        if (startDate.isEmpty() || endDate.isEmpty()) {
            throw new PhucException(ERROR_EVENT_FORMAT);
        }

        String[] eventParts = new String[3];
        eventParts[0] = description;
        eventParts[1] = startDate;
        eventParts[2] = endDate;
        return eventParts;
    }

    /**
     * Validates and parses deadline format arguments.
     * Returns a string that have parsed in form {description,  deadline}
     *
     * @param arguments the deadline arguments string
     * @return array containing description and deadline
     * @throws PhucException if the deadline format is invalid
     */
    public static String[] validateDeadlineFormat(String arguments) throws PhucException {
        if (arguments == null || arguments.trim().isEmpty()) {
            throw new PhucException(ERROR_DEADLINE_FORMAT);
        }

        String[] parts = arguments.split("/by", 2);
        if (parts.length != 2) {
            throw new PhucException(ERROR_DEADLINE_FORMAT);
        }

        String description = parts[0].trim();
        validateDescription(description, "deadline", "deadline");

        String deadline = parts[1].trim();
        if (deadline.isEmpty()) {
            throw new PhucException(ERROR_DEADLINE_FORMAT);
        }

        String[] deadlineParts = new String[2];
        deadlineParts[0] = description;
        deadlineParts[1] = deadline;

        return deadlineParts;
    }

    /**
     * Validates and parses a date-time string.
     *
     * @param dateTimeStr the date-time string to parse
     * @return the parsed LocalDateTime object
     * @throws PhucException if the date-time format is invalid
     */
    public static LocalDateTime validateAndParseDateTime(String dateTimeStr) throws PhucException {
        try {
            return DateTimeProcess.parseDateTime(dateTimeStr);
        } catch (PhucException e) {
            throw new PhucException(ERROR_DATE_FORMAT);
        }
    }
}
