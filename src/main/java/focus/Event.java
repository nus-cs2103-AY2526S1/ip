package focus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event that spans a time period with a start and an end.
 * The start and end are stored in the format used by user input.
 */
public class Event extends Task {

    protected LocalDateTime eventStart;
    protected LocalDateTime eventEnd;

    /** User input and storage format. */
    private DateTimeFormatter inputFormat =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Constructs an Event with the given description and time range.
     *
     * @param description Description of this event.
     * @param eventStart Start text (e.g., code Mon 2pm).
     * @param eventEnd End text (e.g., 4pm).
     */
    public Event(String description, LocalDateTime eventStart, LocalDateTime eventEnd) {
        super(description);
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
    }

    @Override
    public String toString() {
        DateTimeFormatter outputDateFormat = DateTimeFormatter.ofPattern("d MMMM yyyy h:mm a");
        if (isTagged()) {
            return String.format("       [E]%s (from: %s to: %s) %s", super.toString(),
                    this.eventStart.format(outputDateFormat),
                    this.eventEnd.format(outputDateFormat), getTag().toString());
        }
        return String.format("       [E]%s (from: %s to: %s)", super.toString(),
                this.eventStart.format(outputDateFormat), this.eventEnd.format(outputDateFormat));
    }

    /**
     * Returns a storage-friendly representation of this event.
     *
     * @return Encoded string to be written to disk.
     */
    @Override
    public String toStorageString() {
        if (isTagged()) {
            return String.format("E %s | %s - %s | %s", super.toStorageString(),
                    this.eventStart.format(inputFormat), this.eventEnd.format(inputFormat), getTag().toString());
        }
        return String.format("E %s | %s - %s", super.toStorageString(),
                this.eventStart.format(inputFormat), this.eventEnd.format(inputFormat));
    }

}
