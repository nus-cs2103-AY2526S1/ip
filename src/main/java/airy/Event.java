package airy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


/**
 * This class is a subclass from task
 * A task with a starting date and an end date
 */
public class Event extends Task {
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private final LocalDate startDate;
    private final LocalDate endDate;

    /**
     * Constructs a new Event task with the specified name, start date, and end date.
     *
     * @param taskName the description or name of the event task
     * @param startDate the start date of the event in {yyyy-MM-dd} format
     * @param endDate the end date of the event in {yyyy-MM-dd} format
     * @throws AiryException if either date string cannot be parsed using the expected format
     */
    public Event(String taskName, String startDate, String endDate) {
        super(taskName);
        assert taskName != null && !taskName.isBlank() : "Task name must not be empty";
        try {
            this.startDate = LocalDate.parse(startDate, INPUT_FORMAT);
            this.endDate = LocalDate.parse(endDate, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new AiryException("Invalid date format, please use yyyy-MM-dd, e.g. 2025-06-02");
        }
    }

    /**
     * Returns a string representation of the Event task for display to the user.
     *
     * @return a formatted string representation of the event task
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)",
                super.toString(),
                startDate.format(OUTPUT_FORMAT),
                endDate.format(OUTPUT_FORMAT));
    }

    /**
     * Provides the date information in a format suitable for storage.
     * The dates are returned in the input format ({yyyy-MM-dd}).
     *
     * @return a string containing the start and end dates in storage format, separated by " | "
     */
    @Override
    public String getExtraDetailsForStorage() {
        return startDate.format(INPUT_FORMAT) + " | " + endDate.format(INPUT_FORMAT);
    }
}
