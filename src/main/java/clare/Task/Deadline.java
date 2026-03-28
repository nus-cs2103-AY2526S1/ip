package clare.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

import clare.exception.StringConvertExceptions;

/**
 * Represents a task type with a deadline
 */
public class Deadline extends Todo {
    private final LocalDate deadlineDate;
    private LocalTime deadlineTime;

    /**
     * Constructor for Deadline class
     *
     * @param title the task title
     * @param deadline the deadline of the task
     * @param isDone the status
     * @throws StringConvertExceptions if the format is invalid
     */
    public Deadline(String title, String deadline, boolean isDone) throws StringConvertExceptions {
        super(title, isDone);
        try {
            String[] d = deadline.split(" ");
            if (Objects.equals(d[0], "now")) {
                deadlineDate = LocalDate.now();
                this.deadlineTime = LocalTime.now();
                return;
            }
            deadlineDate = LocalDate.parse(d[0]);
            if (d.length > 1) {
                deadlineTime = LocalTime.parse(d[1]);
            }
        } catch (DateTimeParseException | StringIndexOutOfBoundsException e) {
            throw new StringConvertExceptions("Invalid deadline: "
                    + deadline + " Please follow this format YYYY-MM-DD HH:MM");
        }
    }

    @Override
    String getTypeString() {
        return "D";
    }

    @Override
    public TaskType getType() {
        return TaskType.D;
    }

    /**
     * Checks if the date give is same as the task deadline
     *
     * @param date the given date to compare
     * @return true if equals, false otherwise
     */
    public boolean checkDeadline(LocalDate date) {
        return deadlineDate.equals(date);
    }

    public int compareDeadline(Deadline b) {
        return deadlineDate.isAfter(b.deadlineDate) ? 1 : deadlineDate.isBefore(b.deadlineDate) ? -1 : 0;
    }

    /**
     * Gets the string of deadline
     *
     * @return the string of deadline
     */
    protected String getDeadlineString() {
        return deadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + (deadlineTime == null ? "" : (" " + deadlineTime));
    }

    @Override
    public String toString() {
        return "[" + getTypeString() + "]" + super.toString().substring(3)
                + " (by: " + getDeadlineString() + ")";
    }

    @Override
    public String toSaveString() {
        return getTypeString() + super.toSaveString().substring(1)
                + "|" + deadlineDate + (deadlineTime == null ? "" : (" " + deadlineTime));
    }
}
