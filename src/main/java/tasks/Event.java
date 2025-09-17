package tasks;


import java.time.LocalDateTime;
import java.util.Objects;
import java.util.StringJoiner;

import misc.PepeException;

/**
 * Event class that wraps a from and a to duration for the Task class.
 */
public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    public Event(String name, LocalDateTime from, LocalDateTime to) {
        this(name, from, to, false);
    }

    private Event(String name, LocalDateTime from, LocalDateTime to, boolean isDone) {
        super(name, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Factory method to construct an Event class from the user input
     * @param inputs A list of user-input strings
     * @return An instance of the Event object
     * @throws PepeException if an exception occurred while parsing user input or constructing Event class
     */
    public static Event fromInput(String[] inputs) throws PepeException {
        int index = 0;

        StringJoiner name = new StringJoiner(" ");
        for (; index < inputs.length; index++) {
            String input = inputs[index];
            if (input.equals("/from")) {
                index++; // advance past /from
                break;
            } else if (input.equals("/to")) {
                throw new PepeException("Missing /from");
            } else {
                name.add(input);
            }
        }

        if (name.length() == 0) {
            throw new PepeException("Empty name for event.");
        }

        // Expect yyyy-MM-dd HHmm and /to after /from
        if (index + 2 >= inputs.length) {
            throw new PepeException("Expected /from date formatted string yyyy-MM-dd HHmm.");
        }
        String fromString = inputs[index++] + " " + inputs[index++];
        LocalDateTime fromDate = LocalDateTime.parse(fromString, SERDE_FORMATTER);

        if (!inputs[index].equals("/to")) {
            throw new PepeException("Expected /to but got " + inputs[index]);
        }

        index++; // skip /to
        // Expect yyyy-MM-dd HHmm after /to
        if (index + 1 >= inputs.length) {
            throw new PepeException("Expected /to date formatted string yyyy-MM-dd HH:mm");
        }
        String toString = inputs[index++] + " " + inputs[index];
        LocalDateTime toDate = LocalDateTime.parse(toString, SERDE_FORMATTER);

        // Check no more entries afterwards
        if (index + 1 != inputs.length) {
            throw new PepeException("Expected format: <task name> /from <from> /to <to>");
        }

        return new Event(name.toString(), fromDate, toDate);
    }

    /**
     * Deserializes Event fromFileInput.
     * Expected format is {Type} | {Active} | {Name} | {From} | {To}
     * Example: E | 0 | project meeting | 2019-08-6 1400 | 2019-08-6 1600
     * @param inputs List of file inputs delimited by |
     * @return a deserialized Event object
     * @throws PepeException in event of parse error
     */
    public static Event fromFileInput(String[] inputs) throws PepeException {
        if (inputs.length != 5) {
            throw new PepeException("Invalid number of arguments.");
        }

        boolean isDone;
        if (inputs[1].equals("0")) {
            isDone = false;
        } else if (inputs[1].equals("1")) {
            isDone = true;
        } else {
            throw new PepeException("Invalid event format. Expected 1 or 0 for second argument but got " + inputs[1]);
        }

        if (inputs[2].isEmpty()) {
            throw new PepeException("Empty event name.");
        }

        if (inputs[3].isEmpty()) {
            throw new PepeException("Empty event from.");
        }

        if (inputs[4].isEmpty()) {
            throw new PepeException("Empty event to.");
        }

        LocalDateTime fromObject = LocalDateTime.parse(inputs[3], SERDE_FORMATTER);
        LocalDateTime toObject = LocalDateTime.parse(inputs[4], SERDE_FORMATTER);

        return new Event(inputs[2], fromObject, toObject, isDone);
    }

    /**
     * Serializes the Event class into a string suitable for saving to the file.
     * @return the serialized string for file saving.
     */
    @Override
    public String toFileInput() {
        return String.format("E | %s | %s | %s | %s",
                this.getStatusFileIcon(),
                this.getName(),
                from.format(SERDE_FORMATTER),
                to.format(SERDE_FORMATTER));
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)",
                super.toString(),
                from.format(USER_FORMATTER),
                to.format(USER_FORMATTER));
    }

    /**
     * Getter method for the to field.
     * @return to
     */
    public LocalDateTime getTo() {
        return to;
    }

    @Override
    public int compareTo(Task o) {
        if (o instanceof Event otherEvent) {
            return to.compareTo(otherEvent.to);
        } else if (o instanceof Deadline deadline) {
            return to.compareTo(deadline.getBy());
        } else {
            // o is a Todo with no deadline and is there always lesser than this Event
            return 1;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Event event = (Event) o;
        return Objects.equals(from, event.from) && Objects.equals(to, event.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }
}
