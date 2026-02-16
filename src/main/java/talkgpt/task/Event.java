package talkgpt.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Represents an Event task in the TalkGPT application.
 * An Event has a description, a start datetime, and an end datetime.
 */
public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Constructs an Event task, which is unmarked by default.
     *
     * @param task Description of the task.
     * @param from Start datetime of the task in d/M/yyyy HHmm format.
     * @param to   End datetime of the task in d/M/yyyy HHmm format.
     */
    public Event(String task, String from, String to, String tag) {
        super(task, false, tag);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

        this.from = LocalDateTime.parse(from, formatter);
        this.to = LocalDateTime.parse(to, formatter);
    }

    /**
     * Constructs an Event task, which is marked depending on the boolean done.
     *
     * @param task Description of the task.
     * @param from Start datetime of the task in yyyy-MM-ddTHH:mm format.
     * @param to   End datetime of the task in yyyy-MM-ddTHH:mm format.
     * @param done Status of completion.
     */
    public Event(String task, String from, String to, boolean done, String tag) {
        super(task, done, tag);
        this.from = LocalDateTime.parse(from);
        this.to = LocalDateTime.parse(to);
    }

    /**
     * Reads the serialized string and constructs the corresponding Event task.
     *
     * @param parts Array of parsed string in [E, true, description, from, to].
     * @return Event task.
     */
    public static Event deserialize(String[] parts) {
        assert Objects.equals(parts[0], "E") : "The serialized task is not an Event";
        assert parts.length == 6 : "The serialized Event task should have 5 components";

        String completed = parts[1];
        String description = parts[2];
        String from = parts[3];
        String to = parts[4];
        String tag = parts[5];

        if (Objects.equals(completed, "true")) {
            return new Event(description, from, to, true, tag);
        } else {
            return new Event(description, from, to, false, tag);
        }
    }

    /**
     * Converts the Event Task into a serialized string.
     *
     * @return A serialized string in E|true|event|from|to format.
     */
    @Override
    public String serialize() {
        //E|true|event|03-12-2024T1800|03-12-2024T2000
        return String.format("E|%b|%s|%s|%s|%s", super.getStatus(), super.getDescription(),
                                this.from, this.to, super.getTag());
    }

    /**
     * Returns the string representation of the Event task,
     * including its type, status, description, and date range.
     *
     * @return The string representation of the Event task.
     */
    @Override
    public String toString() {
        String fromMsg = this.from.getDayOfWeek()
                + " " + this.from.format(DateTimeFormatter.ofPattern("d MMM yyyy HHmm"));
        String toMsg = this.from.format(DateTimeFormatter.ofPattern("d MMM yyyy HHmm"));

        return "[E]" + super.toString() + " (from: "
                + fromMsg + " to: " + toMsg + ")";
    }
}
