package dukeychatbot.tasktypes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Constructs Deadline class which inherits from Task class.
 * Overrides {@code toString()} method.
 */
public class Deadline extends Task {

    public Deadline(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * {@inheritDoc}
     *
     * Deadline {@code toString()} method includes "[D]" at the front of the string, and
     * the date the task has to be completed by.
     */
    @Override
    public String toString() {
        String description = this.getDescription();
        String formattedDescription;

        String deadlineTime = description.split("/by")[1].trim();
        boolean isFormatted = false;

        String datePattern = "\\d{4}-\\d{2}-\\d{2}";

        if (deadlineTime.length() == 10 && deadlineTime.matches(datePattern)) {
            try {
                LocalDate dateInput = LocalDate.parse(deadlineTime);
                deadlineTime = dateInput.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                isFormatted = true;
            } catch (DateTimeParseException e) {
                System.out.println("Date parsed failed. Error message: " + e.getMessage());
            }
        }

        if (isFormatted) {
            description = description.split("/by")[0].trim() + " /by " + deadlineTime;
        }

        if (description.contains("/by")) {
            String[] splitDescription = description.split("/by");
            formattedDescription = splitDescription[0] + "(by:" + splitDescription[1] + ")";
        } else {
            formattedDescription = this.getDescription();
        }

        return "[D] " + "[" + this.getStatusIcon() + "] " + formattedDescription;
    }
}
