/**
 * Represents an event task with a description,
 * a start datetime, and an end datetime.
 */

package salah;

import java.time.LocalDateTime;

/**
 * Event task with start and end times.
 */
public class Events extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;
    
    /**
     * Constructs an {@code Events} task.
     *
     * @param description description of the event
     * @param from start date/time
     * @param to end date/time
     */
    public Events(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /** @return the start date/time of this event */
    public LocalDateTime getFrom() {
        return this.from;
    }

    /** @return the start date/time of this event */
    public LocalDateTime getTo() {
        return this.to;
    }

    /**
     * Parses a user input string into an {@code Events} task.
     * Expected shape: {@code event <desc> /from <start> /to <end>}.
     *
     * @param input raw user input
     * @return a new {@code Events}
     * @throws EmptyDescriptionException if description/from/to are empty
     * @throws TaskFormattingException if the format is invalid or start is after end
     */
     public static Events parser(String input) throws EmptyDescriptionException, TaskFormattingException{
        if (!input.contains("/from") || !input.contains("/to")) {
            throw new TaskFormattingException("Invalid event format. Make sure to include /from and /to");
        }
        // Assumption: input starts with "event"
        assert input.toLowerCase().startsWith("event") : "Input must start with 'event' keyword";
        
        String[] parts = input.substring(6).split("/from|/to");
        
        // Assumption: parts must have exactly 3 elements: {description, from, to}
        assert parts.length == 3 : "Event input should split into exactly 3 parts";
        
        String description = parts[0].trim();
        String from = parts[1].trim();
        String to = parts[2].trim();

        if (description.isEmpty()) {
            throw new EmptyDescriptionException("Description cannot be empty.");
        }
        if (from.isEmpty()) {
            throw new EmptyDescriptionException("Start time cannot be empty.");
        }
        if (to.isEmpty()) {
            throw new EmptyDescriptionException("End time cannot be empty.");
        }

        LocalDateTime fromDate = DateTimeParser.parseFlexible(from);
        LocalDateTime toDate = DateTimeParser.parseFlexible(to);

        // Assumption: end date/time should not be before start
        assert !toDate.isBefore(fromDate) : "Event end time must be after start time";

        return new Events(description, fromDate, toDate);
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "[E]" + (this.getIsComplete() ? "[X] " : "[ ] ")
                + super.toString()
                + " (from: " + DateTimeParser.formatPretty(this.from)
                + " to: " + DateTimeParser.formatPretty(this.to) + ")";
    }

    /**
     * Serializes this event to a line suitable for saving to disk.
     * Format: {@code E | doneFlag | description | fromISO | toISO}
     */
    @Override
    public String serialize() {
        return "E | " + (getIsComplete() ? "1" : "0") + " | " + getDescription() + " | " + from + " | " + to;
    }

    /**
     * Reconstructs an {@code Events} task from serialized parts.
     *
     * @param parts tokenized line from storage
     * @return reconstructed event
     * @throws IllegalArgumentException if the datetime tokens are not parseable
     */
    public static Events fromData(String[] parts) {
        boolean done = parts[1].trim().equals("1");
        String desc = parts[2].trim();

        LocalDateTime from = LocalDateTime.parse(parts[3].trim());
        LocalDateTime to   = LocalDateTime.parse(parts[4].trim());

        Events e = new Events(desc, from, to);
        if (done) {
            e.markAsComplete();
        }
        return e;
    }
}