package pickle.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Creates an event task
 * Displays the event date in a more readable fashion.
 */
public class Event extends Task {
    // SINGLE accepted input/storage format: "yyyy-MM-dd HHmm" (e.g., 2019-12-02 1800)
    private static final DateTimeFormatter IN_OUT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    // Display formats
    private static final DateTimeFormatter OUT_DATE = DateTimeFormatter.ofPattern("MMM d yyyy");
    private static final DateTimeFormatter OUT_TIME = DateTimeFormatter.ofPattern("h:mma");

    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Creates an instance of an event.
     * @param description information of event.
     * @param from timing of start of event.
     * @param to timing of end of event.
     */
    public Event(String description, String from, String to) {
        super(description);
        try {
            this.from = LocalDateTime.parse(from.trim(), IN_OUT).withSecond(0).withNano(0);
            this.to = LocalDateTime.parse(to.trim(), IN_OUT).withSecond(0).withNano(0);
        } catch (DateTimeParseException ignore) {
            return;
        }
    }

    public String type() {
        return "E";
    }

    /**
     * Format to save the file into the storage text file.
     * @return a string to save the file
     */
    public String writeToFile() {
        String state = super.isComplete() ? "1" : "0";
        return "E | " + state + " | " + getDescription() + " | " + this.from.format(IN_OUT) + " | "
                + this.to.format(IN_OUT);
    }

    @Override
    public String toString() {
        String outputFrom = from.toLocalDate().format(OUT_DATE) + ", " + from.toLocalTime().format(OUT_TIME);
        String outputTo = to.toLocalDate().format(OUT_DATE) + ", " + to.toLocalTime().format(OUT_TIME);
        return "[E]" + super.toString() + " (from: " + outputFrom + " to: " + outputTo + ")";
    }
}
