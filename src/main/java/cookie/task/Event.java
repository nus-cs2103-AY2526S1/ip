package cookie.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import cookie.exception.CookieException;


/**
 * Represents a task with a specific start and end date and time.
 * Adds date and time functionality to base Task class.
 */
public class Event extends Task {

    private static final DateTimeFormatter FORMAT_FOR_INPUT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter FORMAT_FOR_OUTPUT = DateTimeFormatter.ofPattern("MMM d yyyy HHmm");

    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Creates new event task with the given description, start and end date and time.
     *
     * @param description Description of task.
     * @param from Start date and time for task.
     * @param to End date and time for task.
     * @throws CookieException If format of date and time is not yyyy-MM-dd HHmm.
     */
    public Event(String description, String from, String to) throws CookieException {
        super(description);
        try {
            this.from = LocalDateTime.parse(from.strip(), FORMAT_FOR_INPUT);
            this.to = LocalDateTime.parse(to.strip(), FORMAT_FOR_INPUT);

        } catch (DateTimeParseException e) {
            throw new CookieException("Please specify date in the following format: yyyy-MM-dd HHmm");
        }
    }

    /**
     * Converts event task into format for saving.
     *
     * @return Saved format of event task.
     */
    @Override
    public String toStoredFormat() {
        int status = isDone ? 1 : 0;
        return "E | " + status + " | " + description + " | " + from.format(FORMAT_FOR_INPUT)
                + " | " + to.format(FORMAT_FOR_INPUT);
    }

    /**
     * Updates event task with updated information.
     *
     * @param newInformation  Updated information.
     * @throws CookieException if input not in proper format.
     */
    @Override
    public void update(String newInformation) throws CookieException {
        String newDescription;
        String newFrom = null;
        String newTo = null;

        if (newInformation.contains("/from")) {
            String[] firstPhraseSplit = newInformation.split("/from", 2);
            newDescription = firstPhraseSplit[0].strip();
            String phraseWithDates = firstPhraseSplit[1];

            if (newInformation.contains("/to")) {
                String[] secondPhraseSplit = phraseWithDates.split("/to", 2);
                newFrom = secondPhraseSplit[0].strip();
                newTo = secondPhraseSplit[1].strip();
            } else {
                newFrom = phraseWithDates.strip();
            }
        } else if (newInformation.contains("/to")) {
            String[] firstPhraseSplit = newInformation.split("/from", 2);
            newDescription = firstPhraseSplit[0].strip();
            newTo = firstPhraseSplit[1].strip();
        } else {
            newDescription = newInformation.strip();
        }

        if (!newDescription.isEmpty()) {
            this.description = newDescription;
        }

        if (newFrom != null && !newFrom.isEmpty()) {
            try {
                this.from = LocalDateTime.parse(newFrom, FORMAT_FOR_INPUT);
            } catch (DateTimeParseException e) {
                throw new CookieException("Please specify date in the following format: yyyy-MM-dd HHmm");
            }
        }

        if (newTo != null && !newTo.isEmpty()) {
            try {
                this.to = LocalDateTime.parse(newTo, FORMAT_FOR_INPUT);
            } catch (DateTimeParseException e) {
                throw new CookieException("Please specify date in the following format: yyyy-MM-dd HHmm");
            }
        }
    }

    /**
     * Returns event task in String format with its type, description,
     * from and to date and time.
     *
     * @return String form of event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(FORMAT_FOR_OUTPUT)
                + " to: " + to.format(FORMAT_FOR_OUTPUT) + ")";
    }
}

