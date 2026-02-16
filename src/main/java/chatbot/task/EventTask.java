package chatbot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import chatbot.exception.BotException;
import chatbot.exception.InvalidArgumentException;
import chatbot.exception.InvalidEventEndDateException;
import chatbot.util.DateTimeParser;

/**
 * EventTask is a subclass of Task.
 * The class handles creation of an event task when user uses the event command.
 */
public class EventTask extends Task {
    // Date format when displayed to the user
    private static final DateTimeFormatter DISPLAY_FORMAT =
            DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");
    // Date format when stored in Storage
    private static final DateTimeFormatter STORAGE_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Event Task constructor.
     *
     * @param taskName Name of task; must not be null or empty.
     * @param from Start datetime of the event.
     * @param to Ending datetime of the event.
     * @throws BotException If the datetime of to is before from.
     */
    public EventTask(String taskName, String from, String to) throws BotException {
        super(taskName);
        assert taskName != null && !taskName.isEmpty() : "Task name must not be null or empty";

        this.from = parseFromDateTime(from);
        this.to = parseToDateTime(from, to);
        validateDates(this.from, this.to);
    }

    @Override
    public String stringFormatCompleteStatus() {
        return "[E]" + super.stringFormatCompleteStatus();
    }

    @Override
    public boolean existsInTaskDescription(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return false;
        }

        String keywordLowerCase = keyword.toLowerCase();
        String taskNameLowerCase = getTaskName().toLowerCase();
        String fromLowerCase = from.format(DISPLAY_FORMAT).toLowerCase();
        String toLowerCase = to.format(DISPLAY_FORMAT).toLowerCase();

        boolean taskNameContainsKeyword = taskNameLowerCase.contains(keywordLowerCase);
        boolean fromContainsKeyword = fromLowerCase.contains(keywordLowerCase);
        boolean toContainsKeyword = toLowerCase.contains(keywordLowerCase);
        boolean startOrEndDateContainsKeyword = fromContainsKeyword || toContainsKeyword;

        return taskNameContainsKeyword || startOrEndDateContainsKeyword;
    }


    @Override
    public String toSaveFormat() {
        return "E | "
                + super.stringFormatCompleteStatus() + "| "
                + getTaskName() + " | "
                + this.from.format(STORAGE_FORMAT) + " to " + this.to.format(STORAGE_FORMAT);
    }

    @Override
    public String toString() {
        return super.toString()
                + " (from: "
                + this.from.format(DISPLAY_FORMAT)
                + " to: "
                + this.to.format(DISPLAY_FORMAT)
                + ")";
    }

    // parseFromDateTime, parseToDateTime and validateDates are helper methods used in the constructor
    private LocalDateTime parseFromDateTime(String from) throws BotException {
        try {
            return DateTimeParser.parseDateTime(from);
        } catch (DateTimeParseException e) {
            String errorMessage = "Invalid Start Date: " + from + "\nExpected format: yyyy-MM-dd HH:mm\n";
            throw new InvalidArgumentException(errorMessage);
        }
    }

    private LocalDateTime parseToDateTime(String from, String to) throws BotException {
        try {
            if (to.matches("\\d{1,2}:\\d{2}(:\\d{2})?")) {
                String[] date = from.split(" ");
                return DateTimeParser.combineDateAndTime(date[0], to);
            }

            return DateTimeParser.parseDateTime(to);
        } catch (DateTimeParseException e) {
            String errorMessage = "Invalid Start Date: " + to + "\nExpected format: yyyy-MM-dd HH:mm\n";
            throw new InvalidArgumentException(errorMessage);
        }
    }

    private void validateDates(LocalDateTime from, LocalDateTime to) throws BotException {
        if (from.isAfter(to)) {
            throw new InvalidEventEndDateException("Invalid End Date: You got a time machine?");
        }
    }
}
