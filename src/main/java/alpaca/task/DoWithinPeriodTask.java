package alpaca.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that needs to be done within a certain period.
 */
public class DoWithinPeriodTask extends Task {
    private final LocalDate startDate;
    private final LocalDate endDate;

    /**
     * Constructs a DoWithinPeriodTask.
     * @param description The task description.
     * @param startDate Start date in yyyy-MM-dd format.
     * @param endDate End date in yyyy-MM-dd format.
     */
    public DoWithinPeriodTask(String description, String startDate, String endDate) {
        super(description);
        this.startDate = LocalDate.parse(startDate);  // expects yyyy-MM-dd
        this.endDate = LocalDate.parse(endDate);      // expects yyyy-MM-dd
    }

    @Override
    public String toSaveFormat() {
        return "P | " + (isDone ? "1" : "0") + " | " + description + " | "
                + startDate + " | " + endDate;
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MMM d yyyy");
        return "[P]" + super.toString() + " (from: " + startDate.format(fmt)
                + " to: " + endDate.format(fmt) + ")";
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}
