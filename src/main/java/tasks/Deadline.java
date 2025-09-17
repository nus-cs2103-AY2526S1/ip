package tasks;


import java.time.LocalDateTime;
import java.util.Objects;
import java.util.StringJoiner;

import misc.PepeException;

/**
 * Deadline class that wraps a by datetime for the Task class.
 */
public class Deadline extends Task {

    private final LocalDateTime by;

    public Deadline(String name, LocalDateTime by) {
        this(name, by, false);
    }

    private Deadline(String name, LocalDateTime by, boolean isDone) {
        super(name, isDone);
        this.by = by;
    }

    /**
     * Factory method to construct a Deadline class from the user input
     * @param inputs A list of user-input strings
     * @return An instance of the Deadline object
     * @throws PepeException if an exception occurred while parsing user input or constructing Deadline class
     */
    public static Deadline fromInput(String[] inputs) throws PepeException {
        int index = 0;

        StringJoiner name = new StringJoiner(" ");
        for (; index < inputs.length; index++) {
            String input = inputs[index];
            if (input.equals("/by")) {
                index++; // advance past /by
                break;
            } else {
                name.add(input);
            }
        }

        if (name.length() == 0) {
            throw new PepeException("Empty name for deadline.");
        }

        // 0 1 2
        // /by m d
        // Deadline format is expected to be in yyyy-MM-dd HHmm
        if (index + 1 >= inputs.length) {
            throw new PepeException("Expected deadline date formatted string yyyy-MM-dd HHmm.");
        }
        String deadlineString = inputs[index++] + " " + inputs[index];
        LocalDateTime byTimeObject = LocalDateTime.parse(deadlineString, SERDE_FORMATTER);

        if (index + 1 != inputs.length) {
            throw new PepeException("Expected format: <task name> /by <deadline>");
        }

        return new Deadline(name.toString(), byTimeObject);
    }

    /**
     * Deserialize Deadline fromFileInput.
     * Expected format is {Type} | {Active} | {Name} | {Deadline}
     * @param inputs List of file inputs delimited by |
     * @return a deserialized Deadline object
     * @throws PepeException in event of parse error
     */
    public static Deadline fromFileInput(String[] inputs) throws PepeException {
        if (inputs.length != 4) {
            throw new PepeException("Invalid number of arguments.");
        }

        boolean isDone;
        if (inputs[1].equals("0")) {
            isDone = false;
        } else if (inputs[1].equals("1")) {
            isDone = true;
        } else {
            throw new PepeException("Invalid deadline format. Expected 1/0 for second argument but got " + inputs[1]);
        }

        if (inputs[2].isEmpty()) {
            throw new PepeException("Empty deadline name.");
        }

        if (inputs[3].isEmpty()) {
            throw new PepeException("Empty deadline by.");
        }

        LocalDateTime byTimeObject = LocalDateTime.parse(inputs[3], SERDE_FORMATTER);

        return new Deadline(inputs[2], byTimeObject, isDone);
    }

    /**
     * Serializes the Deadline class into a string suitable for saving to the file.
     * @return the serialized string for file saving.
     */
    @Override
    public String toFileInput() {
        return String.format("D | %s | %s | %s", this.getStatusFileIcon(), this.getName(), by.format(SERDE_FORMATTER));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(USER_FORMATTER) + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Deadline otherDeadline = (Deadline) o;
        return Objects.equals(by, otherDeadline.by);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(by);
    }

    @Override
    public int compareTo(Task o) {
        if (o instanceof Todo) {
            // A TODO has no deadline and is always going to be lesser than
            return 1;
        } else if (o instanceof Deadline otherDeadline) {
            return by.compareTo(otherDeadline.by);
        } else {
            Event event = (Event) o;
            return by.compareTo(event.getTo());
        }
    }

    /**
     * Getter for by field.
     * @return by
     */
    public LocalDateTime getBy() {
        return by;
    }
}
