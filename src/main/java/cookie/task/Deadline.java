package cookie.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import cookie.exception.CookieException;

/**
 * Represents a task with a specific deadline.
 * Adds date and time functionality to base Task class.
 */
public class Deadline extends Task {

    private static final DateTimeFormatter FORMAT_FOR_INPUT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter FORMAT_FOR_OUTPUT = DateTimeFormatter.ofPattern("MMM d yyyy HHmm");

    protected LocalDateTime by;

    /**
     * Creates new deadline task with the given description and due date.
     *
     * @param description Description of task.
     * @param by Due date and time for task.
     * @throws CookieException If format of due date is not yyyy-MM-dd HHmm.
     */
    public Deadline(String description, String by) throws CookieException {
        super(description);
        try {
            this.by = LocalDateTime.parse(by.strip(), FORMAT_FOR_INPUT);
        } catch (DateTimeParseException e) {
            throw new CookieException("Please specify date in the following format: yyyy-MM-dd HHmm");
        }
    }

    /**
     * Converts deadline task into format for saving.
     *
     * @return Saved format of deadline task.
     */
    @Override
    public String toStoredFormat() {
        int status = isDone ? 1 : 0;
        return "D | " + status + " | " + description + " | " + by.format(FORMAT_FOR_INPUT);
    }

    /**
     * Updates deadline task with updated information.
     *
     * @param newInformation  Updated information.
     * @throws CookieException if input not in proper format.
     */
    @Override
    public void update(String newInformation) throws CookieException {
        String newDescription;
        String newBy;

        if (newInformation.contains("/by")) {
            String[] split = newInformation.split("/by");
            newDescription = split[0].strip();
            newBy = split[1].strip();

            if (!newDescription.isEmpty()) {
                this.description = newDescription;
            }
            if (!newBy.isEmpty()) {
                try {
                    this.by = LocalDateTime.parse(newBy, FORMAT_FOR_INPUT);
                } catch (DateTimeParseException e) {
                    throw new CookieException("Please specify date in the following format: yyyy-MM-dd HHmm");
                }
            }
        } else {
            if (newInformation.isBlank()) {
                throw new CookieException("Update details cannot be empty.");
            }
            this.description = newInformation.strip();
        }
    }

    /**
     * Returns deadline task in String format with its type, description,
     * and due date and time.
     *
     * @return String form of deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(FORMAT_FOR_OUTPUT) + ")";
    }
}

