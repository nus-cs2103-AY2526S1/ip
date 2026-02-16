package talkgpt.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Represents a Deadline task in the TalkGPT application.
 * A Deadline has a description and a due date.
 */
public class Deadline extends Task {
    private final LocalDateTime dueDate;

    /**
     * Constructs a Deadline task, which is unmarked by default.
     *
     * @param task Description of the task.
     * @param dueDate Due date of the task in d/M/yyyy HHmm format.
     */
    public Deadline(String task, String dueDate, String tag) {
        super(task, false, tag);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

        this.dueDate = LocalDateTime.parse(dueDate, formatter);

    }

    /**
     * Constructs a Deadline task, which is marked depending on the boolean done.
     *
     * @param task Description of the task.
     * @param dueDate Due date of the task in yyyy-MM-ddTHH:mm format.
     * @param done Status of completion.
     */
    public Deadline(String task, String dueDate, boolean done, String tag) {
        super(task, done, tag);
        this.dueDate = LocalDateTime.parse(dueDate);
    }

    /**
     * Reads the serialized string and constructs the corresponding Deadline task.
     *
     * @param parts Array of parsed string in [D, true, description, dueDate].
     * @return Deadline task.
     */
    public static Deadline deserialize(String[] parts) {
        assert Objects.equals(parts[0], "D") : "The serialized task is not a Deadline";
        assert parts.length == 5 : "The serialized Deadline task should have 4 components";
        String completed = parts[1];
        String description = parts[2];
        String due = parts[3];
        String tag = parts[4];

        if (Objects.equals(completed, "true")) {
            return new Deadline(description, due, true, tag);
        } else {
            return new Deadline(description, due, false, tag);
        }
    }

    /**
     * Converts the Deadline Task into a serialized string.
     *
     * @return A serialized string in D|true|deadline|dueDate format.
     */
    @Override
    public String serialize() {
        //D|true|return book|03-12-2024T1800
        return String.format("D|%b|%s|%s|%s", super.getStatus(), super.getDescription(), this.dueDate, super.getTag());
    }

    /**
     * Returns the string representation of the Deadline task,
     * including its type, status, description, and due date.
     *
     * @return The string representation of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (By "
                + this.dueDate.getDayOfWeek() + " "
                + this.dueDate.format(DateTimeFormatter.ofPattern("d MMM yyyy HHmm"))
                + ")";
    }
}
