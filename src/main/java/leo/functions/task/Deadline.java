package leo.functions.task;

import leo.DateTimeParser;

/**
 * Represents a Deadline task.
 */
public class Deadline extends Task {
    private String deadline;

    /**
     * Instantiates a Deadline task.
     *
     * @param description The description of the Deadline task.
     * @param deadline The deadline of the task.
     */
    public Deadline(String description, String deadline) {
        super(description);

        DateTimeParser dateTimeParser = new DateTimeParser();
        this.deadline = dateTimeParser.formatDateTimeFromInput(deadline);
    }

    /**
     * Returns the formatted string of the task to be written to a file.
     *
     * @return A formatted string of the task.
     */
    @Override
    public String appendToFile() {
        return String.format(
                "Deadline | "
                + this.getStatusIcon()
                + " | "
                + this.description
                + " | "
                + this.deadline
        );
    }

    /**
     * Returns a formatted string of the task to be written to the UI.
     *
     * @return A formatted string of the task.
     */
    @Override
    public String toString() {
        return String.format(
                "[D]["
                + this.getStatusIcon()
                + "] "
                + this.description
                + " (by: "
                + this.deadline
                + ")"
        );
    }
}
