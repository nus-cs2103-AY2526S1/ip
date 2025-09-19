package sheares.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * represents a Deadline Type Task
 */
public class Deadline extends Task {

    private final LocalDate deadline;

    /**
     * Constructor for new deadline with des and a deadline represented as LocalDate as parameters
     * @param des
     * @param deadline
     */
    public Deadline(String des, LocalDate deadline) {
        super(des);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        String formattedDeadline = this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + super.toString() + " (by: " + formattedDeadline + ")";
    }

    /**
     * returns String representation that we write to data file
     */
    @Override
    public String taskToStr() {
        String doneIndicator = super.isDone ? "1" : "0";
        StringBuilder sb = new StringBuilder();
        sb.append('D').append(" | ").append(doneIndicator).append(" | ")
                .append(super.des).append(" | ").append(this.deadline);
        return sb.toString();
    }
}
