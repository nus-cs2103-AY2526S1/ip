import java.time.LocalDateTime;

public class ErrorHandler {
    public static final String ERROR_DATE_FORMAT =
            "Oops! Date format should be like: DD/MM/YYYY HHMM or YYYY-MM-DD HHMM (◕︵◕)";
    public static final String ERROR_INVALID_TASK_NUMBER =
            "Please enter a number between 1 and %d (T_T)";
    public static final String ERROR_EMPTY_DESCRIPTION =
            "Hold on — a %s needs a description. Please type something after '%s'（>﹏<）";
    public static final String ERROR_EVENT_FORMAT =
            "Oops! An event must include both a start and an end time — don’t forget to add it (ಥ_ಥ)";
    public static final String ERROR_DEADLINE_FORMAT =
            "Oops! A deadline must include a date and time — don’t forget to add it (ಥ_ಥ)";
    public static final String ERROR_UNKNOWN_COMMAND =
            "I don’t understand that command. Please try another command! ༼☯﹏☯༽";
    public static final String ERROR_NUMBER_FORMAT =
            "Please enter a valid number for the task index (＞﹏＜)";
    public static final String ERROR_EMPTY_COMMAND =
            "Please enter a command (・人・)";

    public static void setErrorEmptyCommand() throws PhucException {
        throw new PhucException(ERROR_EMPTY_COMMAND);
    }

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

    public static void validateDescription(String description, String taskType, String command)
            throws PhucException {
        if (description == null || description.trim().isEmpty()) {
            throw new PhucException(String.format(ERROR_EMPTY_DESCRIPTION, taskType, command));
        }
    }

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

    public static LocalDateTime validateAndParseDateTime(String dateTimeStr) throws PhucException {
        try {
            return DateTimeProcess.parseDateTime(dateTimeStr);
        } catch (PhucException e) {
            throw new PhucException(ERROR_DATE_FORMAT);
        }
    }
}
