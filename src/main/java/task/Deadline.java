package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import exception.BaymaxException;

/**
 * Represents a deadline-type task in the Baymax task management system.
 * A Deadline task has a description, a status (done or not done), and a
 * due date represented as a {@link LocalDate}.
 * <p>
 * This class extends {@link Task} and provides functionality to
 * retrieve the due date and display the task in a human-readable format.
 */
public class Deadline extends Task {
    private LocalDate by;

    /**
     * Constructs a Deadline task with the given name, type, and due date.
     *
     * @param taskName the description of the task
     * @param taskType the type of the task (should be TaskType.DEADLINE)
     * @param byStr    the due date as a string in ISO_LOCAL_DATE format (yyyy-MM-dd)
     */
    public Deadline(String taskName, TaskType taskType, String byStr) throws BaymaxException{
        super(taskName, taskType);
        try {
            // Accepts dd/MM/yyyy format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
            this.by = LocalDate.parse(byStr.split(" ")[0], formatter); // ignore time if present
        } catch (DateTimeParseException e) {
            throw new BaymaxException("I'm confused... invalid date format! Please use dd/MM/yyyy, e.g., 2/12/2019");
        }
    }

    /**
     * Constructs a Deadline task with the given name, type, due date, and completion status.
     *
     * @param taskName the description of the task
     * @param taskType the type of the task (should be TaskType.DEADLINE)
     * @param byStr    the due date as a string in ISO_LOCAL_DATE format (yyyy-MM-dd)
     * @param isDone   the completion status of the task
     */
    public Deadline(String taskName, TaskType taskType, String byStr, boolean isDone) throws BaymaxException {
        super(taskName, taskType, isDone);
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
            this.by = LocalDate.parse(byStr.split(" ")[0], formatter); // ignore time if present
        } catch (DateTimeParseException e) {
            throw new BaymaxException("I'm confused... invalid date format! Please use dd/MM/yyyy, e.g., 2/12/2019");
        }
    }

    /**
     * Returns a string representation of the Deadline task, including its type,
     * completion status, description, and formatted due date.
     *
     * @return the string representation of the Deadline task
     */
    @Override
    public String toString() {
        String formattedDate = by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + super.toString() + " (by: " + formattedDate + ")";
    }

    /**
     * Returns the due date of this Deadline task.
     *
     * @return the due date as a {@link LocalDate}
     */
    public LocalDate getBy() {
        return this.by;
    }

}
