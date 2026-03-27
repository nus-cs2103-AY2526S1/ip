package phuc.util;

import java.time.LocalDateTime;

import phuc.exception.PhucException;
import phuc.parser.DateTimeProcess;

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
            "Hold on — a todo task needs a description. Please type something after todo（>﹏<）";
    /** Error message for invalid event format */
    public static final String ERROR_EVENT_FORMAT =
            "Oops! An event must include both a start and an end time — don’t forget to add it (ಥ_ಥ)";
    /** Error message for invalid deadline format */
    public static final String ERROR_DEADLINE_FORMAT =
            "Oops! A deadline must include a date and time — don’t forget to add it (ಥ_ಥ)";
    /** Error message for unknown command */
    public static final String ERROR_UNKNOWN_COMMAND =
            "I don’t understand that command. Please try another command! (◕︵◕)";
    /** Error message for invalid number format */
    public static final String ERROR_NUMBER_FORMAT =
            "Please enter a valid number for the task index (＞﹏＜)";
    /** Error message for invalid direction format */
    public static final String ERROR_DIRECTION_FORMAT =
            "Please enter ascending or descending for the sorted order (×͡×)";
    /** Error message for invalid start date after end date */
    public static final String ERROR_DAY_FORMAT =
            "Oh no! The start date cannot be after the end date. "
                    + "Please make sure your event starts before it ends :)";
    /** Error message for invalid start date equal end date */
    public static final String ERROR_DAY_FORMAT_EQUALS =
            "Oh no! The start date and end date cannot be the same. :(";

    /**
     * Handles the cases where the direction is not in the right format.
     *
     * @param direction the order of the list
     * @throws PhucException if the direction is not ascending or descending
     */
    public static void validateDirection(String direction) throws PhucException {
        boolean isAscending = direction.equals("ascending");
        boolean isDescending = direction.equals("descending");
        boolean isValid = isAscending || isDescending;
        if (!isValid) {
            throw new PhucException(ERROR_DIRECTION_FORMAT);
        }
    }

    /**
     * Validates that a task index is within valid bounds.
     *
     * @param taskIndexInStringFormat the taskIndexInStringFormat string to validate
     * @param maxIndex the maximum valid index
     * @throws PhucException if the taskIndexInStringFormat is invalid or out of bounds
     */
    public static void validateTaskIndex(String taskIndexInStringFormat, int maxIndex) throws PhucException {
        boolean isNotValidTaskIndex = taskIndexInStringFormat == null || taskIndexInStringFormat.trim().isEmpty();
        assert maxIndex >= 0;

        if (isNotValidTaskIndex) {
            throw new PhucException(String.format(ERROR_INVALID_TASK_NUMBER, maxIndex - 1));
        }

        try {
            int index = Integer.parseInt(taskIndexInStringFormat.trim()) - 1;

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
     * @throws PhucException if the description is empty
     */
    public static void validateDescription(String description) throws PhucException {
        boolean isNotValidDescription = description.isEmpty() || description.trim().isEmpty();

        if (isNotValidDescription) {
            throw new PhucException(ERROR_EMPTY_DESCRIPTION);
        }
    }

    /**
     * Validates and parses event format eventInStringForm.
     * Returns a string that have parsed in form {description, startDate ,endDate}
     *
     * @param eventInStringForm the event eventInStringForm string
     * @return array containing description, start date, and end date
     * @throws PhucException if the event format is invalid
     */
    public static String[] validateEventFormat(String eventInStringForm) throws PhucException {
        boolean isNotValidEventFormat = eventInStringForm == null || eventInStringForm.trim().isEmpty();

        if (isNotValidEventFormat) {
            throw new PhucException(ERROR_EVENT_FORMAT);
        }

        String[] parts = eventInStringForm.split("/from", 2);

        if (parts.length != 2) {
            throw new PhucException(ERROR_EVENT_FORMAT);
        }

        String description = parts[0].trim();
        validateDescription(description);
        String[] dateParts = parts[1].split("/to", 2);

        if (dateParts.length != 2) {
            throw new PhucException(ERROR_EVENT_FORMAT);
        }

        String startDate = dateParts[0].trim();
        String endDate = dateParts[1].trim();

        if (startDate.isEmpty() || endDate.isEmpty()) {
            throw new PhucException(ERROR_EVENT_FORMAT);
        }

        if (validateAndParseDateTime(startDate).isAfter(validateAndParseDateTime(endDate))) {
            throw new PhucException(ERROR_DAY_FORMAT);
        }

        if (validateAndParseDateTime(startDate).isEqual(validateAndParseDateTime(endDate))) {
            throw new PhucException(ERROR_DAY_FORMAT_EQUALS);
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
        boolean isNotValidDeadline = arguments == null || arguments.trim().isEmpty();

        if (isNotValidDeadline) {
            throw new PhucException(ERROR_DEADLINE_FORMAT);
        }

        String[] parts = arguments.split("/by", 2);
        if (parts.length != 2) {
            throw new PhucException(ERROR_DEADLINE_FORMAT);
        }

        String description = parts[0].trim();
        validateDescription(description);
        String deadline = parts[1].trim();
        boolean isNotValidDeadlineDescription = deadline.isEmpty() || deadline.trim().isEmpty();

        if (isNotValidDeadlineDescription) {
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
