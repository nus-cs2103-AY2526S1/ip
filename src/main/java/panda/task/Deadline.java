package panda.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

import panda.PandaException;

/**
 * Deadline is a subclass of Task, with additional information of
 * task type and deadline.
 */
public class Deadline extends Task {
    protected String type;
    protected LocalDateTime deadline;

    /**
     * Constructs Deadline object based on info from user.
     *
     * @param description Description of task.
     * @param deadline Deadline to complete task.
     * @throws PandaException If the date format is wrong.
     */
    public Deadline(String description, String deadline) throws PandaException {
        super(description);
        this.type = "[D]";
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm")
                    .withResolverStyle(ResolverStyle.STRICT);
            this.deadline = LocalDateTime.parse(deadline, formatter);
        } catch (DateTimeParseException e) {
            throw new PandaException("wrongDateTimeFormat", "");
        }
    }

    /**
     * Constructs Deadline object based on info from text file.
     *
     * @param status Status of task completion.
     * @param description Description of task.
     * @param deadline Deadline to complete task.
     */
    public Deadline(String status, String description, String deadline) {
        super(status, description);
        this.type = "[D]";
        this.deadline = LocalDateTime.parse(deadline, DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm"));
    }

    private String formatDate() {
        return deadline.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm"));
    }

    public String writeToFile() {
        return type + "|" + super.status + "|" + super.description + "|" + formatDate() + "\n";
    }

    @Override
    public String toString() {
        return type + super.toString() + " (by: " + formatDate() + ")";
    }
}
