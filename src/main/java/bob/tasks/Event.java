package bob.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that occurs within a time range (start → end).
 */
public class Event extends Task {
    LocalDate startDate;
    LocalDate endDate;
    private static final DateTimeFormatter DATE_PRINT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy");

    public Event(String description, LocalDate startDate, LocalDate endDate) throws IllegalArgumentException{
        this(description, false, startDate, endDate);
    }

    public Event(String description, boolean complete, LocalDate startDate, LocalDate endDate) throws IllegalArgumentException{
        super(description, complete);
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("Event dates cannot be null.");
        }
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("Event end date cannot be before start date.");
        }
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    protected String getTaskType() {
        return "E";
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * Returns String format of task for storage
     * @return taskString
     */
    @Override
    public String toFileString() {
        return String.format("%s /done %d /des %s /from %s /to %s",
                getTaskType(),
                isComplete() ? 1 : 0,
                getDescription(),
                startDate,
                endDate
        );
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)",
                super.toString(),
                startDate.format(DATE_PRINT_FORMAT),
                endDate.format(DATE_PRINT_FORMAT)
        );
    }
}
