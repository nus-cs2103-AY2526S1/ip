package borat.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Parses user input into command words and arguments, and handles date-time
 * parsing/formatting for commands.
 */
public class Parser {

    /**
     * Splits a raw command into the first word and the remaining description.
     *
     * @param command Raw user input.
     * @return Two elements: command word and description.
     */
    public String[] parseCommand(String command) {
        assert command != null : "Command cannot be null";

        String[] split = command.trim().split("\\s", 2);
        assert split.length >= 1 : "Split must have at least one element";

        String firstWord = split[0];
        String description = split.length > 1 ? split[1] : "";

        assert firstWord != null : "First word cannot be null";
        assert description != null : "Description cannot be null";

        return new String[] {firstWord, description};
    }

    /**
     * Parses a deadline description of the form "<desc> /by <d/M/yyyy HHmm>".
     *
     * @param description Description and due date/time.
     * @return Two elements: description and formatted due date/time.
     * @throws IllegalArgumentException If parts are missing or date is invalid.
     */
    public String[] parseDeadline(String description) {
        assert description != null : "Description cannot be null";
        assert !description.trim().isEmpty() : "Description cannot be empty";

        String[] parts = description.split("/by", 2);

        // handle exception
        if (parts.length != 2) {
            throw new IllegalArgumentException("Deadline command must include /by followed by date and time");
        }

        String desc = parts[0].trim();
        String dateTimeStr = parts[1].trim();

        assert !desc.isEmpty() : "Description part cannot be empty";
        assert !dateTimeStr.isEmpty() : "DateTime string cannot be empty";

        try {
            LocalDateTime deadline = parseDateTime(dateTimeStr);
            String formattedDeadline = formatDateTime(deadline);
            assert !formattedDeadline.isEmpty() : "Formatted deadline cannot be empty";

            return new String[] {desc, formattedDeadline};
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date/time format. Use format: d/M/yyyy HHmm (e.g., 2/12/2019 1800)");
        }
    }

    /**
     * Parses an event description of the form
     * "<desc> /from <d/M/yyyy HHmm> /to <d/M/yyyy HHmm>".
     *
     * @param description Description and time range.
     * @return Three elements: description, formatted start, formatted end.
     * @throws IllegalArgumentException If parts are missing or dates are invalid.
     */
    public String[] parseEvent(String description) {
        assert description != null : "Description cannot be null";
        assert !description.trim().isEmpty() : "Description cannot be empty";

        String[] parts = description.split("\\s*/from\\s*|\\s*/to\\s*", 3);

        // handle exception
        if (parts.length != 3) {
            throw new IllegalArgumentException("Event command must include /from and /to followed by date and time");
        }

        String desc = parts[0].trim();
        String startStr = parts[1].trim();
        String endStr = parts[2].trim();

        assert !desc.isEmpty() : "Description part cannot be empty";
        assert !startStr.isEmpty() : "Start time string cannot be empty";
        assert !endStr.isEmpty() : "End time string cannot be empty";

        try {
            LocalDateTime start = parseDateTime(startStr);
            LocalDateTime end = parseDateTime(endStr);

            String formattedStart = formatDateTime(start);
            String formattedEnd = formatDateTime(end);
            assert !formattedStart.isEmpty() : "Formatted start time cannot be empty";
            assert !formattedEnd.isEmpty() : "Formatted end time cannot be empty";

            return new String[] {desc, formattedStart, formattedEnd};
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date/time format. Use format: d/M/yyyy HHmm (e.g., 2/12/2019 1800)");
        }
    }

    /**
     * Parses a date-time string using pattern {@code d/M/yyyy HHmm}.
     *
     * @param dateTimeString Input string.
     * @return Parsed LocalDateTime.
     * @throws DateTimeParseException If the input does not match the pattern.
     */
    private LocalDateTime parseDateTime(String dateTimeString) throws DateTimeParseException {
        assert dateTimeString != null : "DateTime string cannot be null";
        assert !dateTimeString.trim().isEmpty() : "DateTime string cannot be empty";

        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

        LocalDateTime result = LocalDateTime.parse(dateTimeString, inputFormatter);
        return result;
    }

    /**
     * Formats a date-time using pattern {@code MMM dd yyyy HH:mm}.
     *
     * @param dateTime Date-time to format.
     * @return Formatted string.
     */
    private String formatDateTime(LocalDateTime dateTime) {
        assert dateTime != null : "DateTime cannot be null";

        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

        String result = dateTime.format(outputFormatter);
        assert !result.isEmpty() : "Formatted string cannot be empty";
        return result;
    }
}