package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import app.Messages;
import errors.BoopError;

/**
 * Represents a task that has a specific deadline date.
 */
public class Deadline extends Task {
    private LocalDate deadline;

    /**
     * Constructs a Deadline task with a name, completion status, and deadline date.
     *
     * @param name       Name of the task
     * @param isComplete Whether the task is completed
     * @param deadline   The date by which the task must be completed
     */
    public Deadline(String name, boolean isComplete, LocalDate deadline) {
        super(name, isComplete);
        this.deadline = deadline;
    }

    /**
     * Constructs a Deadline task with a name and deadline date.
     * Task is marked as incomplete by default.
     *
     * @param name     Name of the task
     * @param deadline The date by which the task must be completed
     */
    public Deadline(String name, LocalDate deadline) {
        this(name, false, deadline);
    }

    @Override
    public String toString() {
        return "[D]%s (by: %s)".formatted(
                super.toString(),
                this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    @Override
    public String toSaveString() {
        return "D | %s | %s".formatted(super.toSaveString(), this.deadline.toString());
    }

    @Override
    public Deadline copy() {
        return new Deadline(getName(), isComplete(), deadline);
    }

    /**
     * Converts a Save String format of a Deadline back into a Deadline instance
     *
     * @param saveString Deadline in format written in save file
     * @return Deadline using data from save file
     * @throws BoopError
     */
    public static Deadline fromSaveString(String saveString) throws BoopError {
        String[] parts = saveString.split(" \\| ");
        String type = parts[0];

        if (!type.equals("D")) {
            throw new BoopError(String.format(Messages.ERROR_WRONG_TYPE_TASKSAVESTRING, "Deadline", type));
        }

        if (parts.length < 4) {
            throw new BoopError(Messages.ERROR_SAVE_CORRUPTED);
        }

        boolean isDone = parts[1].equals("X");
        String name = parts[2];
        assert name != null && !name.isEmpty() : "Task name must not be null or empty";
        LocalDate deadline;
        try {
            deadline = LocalDate.parse(parts[3]);
        } catch (DateTimeParseException e) {
            throw new BoopError(Messages.ERROR_SAVE_CORRUPTED);
        }

        return new Deadline(name, isDone, deadline);
    }
}
