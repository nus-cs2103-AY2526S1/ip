package sophia;

import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Represents a task with a defined start and end date (an event).
 * <p>
 * An {@code EventTask} extends {@link Task} and stores a description,
 * a start date, and an end date.
 * </p>
 */
public final class EventTask extends Task implements TaskWithDate {
    private final String startDate;
    private final String endDate;

    /**
     * Constructs a new {@code EventTask}.
     *
     * @param input     the task description
     * @param startDate the event start date (expected format: yyyy-MM-dd or free text)
     * @param endDate   the event end date (expected format: yyyy-MM-dd or free text)
     */
    public EventTask(String input, String startDate, String endDate) {
        super(input);
        this.startDate = startDate;
        this.endDate = endDate;
    }
    /**
     * Returns a boolean value indicating if task is due in 3 days
     * @return
     */
    public boolean isDueWithinThreeDays() {
        LocalDate currentDate = LocalDate.now();
        LocalDateTime deadline = Parser.stringToLocalDateTime(startDate);
        assert deadline != null;
        return currentDate.isAfter(deadline.toLocalDate().minusDays(3));
    }

    public String sendReminder() {
        return this + " starts at " + startDate;
    }

    /**
     * Writes this event task to the given {@link BufferedWriter}.
     * <p>
     * Format: {@code E | <done> | <name> | <startDate> | <endDate>}
     * </p>
     *
     * @param bw the writer to output task data
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void write(BufferedWriter bw) throws IOException {
        bw.write("E | " + (isDone() ? 1 : 0)
                + " | " + getName()
                + " | " + startDate
                + " | " + endDate
                + "\n");
        bw.flush();
    }


    /**
     * Returns a human-readable string representation of the event task.
     *
     * @return formatted string with description and dates
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + parseDate(startDate)
                + " to: " + parseDate(endDate)
                + ")";
    }
}
