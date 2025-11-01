package yuan.task;

import java.time.LocalDate;

/**
 * Abstract base class representing a task with a description and completion status.
 *
 * <p>AI-Assisted: JavaDoc suggested by ChatGPT and refined manually.</p>
 */
@SuppressWarnings("checkstyle:Regexp")
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the given description and status.
     *
     * @param description
     * @param isDone
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a string representation of the task done using X
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Converts the task into a storage-friendly format.
     *
     * @return storage string representation
     */
    public abstract String toStorageFormat();

    /**
     * Reconstructs a Task object from its storage format string.
     *
     * @param line
     * @return
     */
    public static Task fromStorageFormat(String line) {
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (type) {
        case "T":
            return new Todo(description, isDone);

        case "D":
            LocalDate deadline = LocalDate.parse(parts[3]);
            return new Deadline(description, deadline, isDone);

        case "E":
            LocalDate from = LocalDate.parse(parts[3]);
            LocalDate to = LocalDate.parse(parts[4]);
            return new Event(description, from, to, isDone);

        default:
            throw new IllegalArgumentException("Wrong format in file: " + line);
        }
    }
}
