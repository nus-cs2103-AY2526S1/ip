package sophia;

import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Represents a task with a deadline.
 * <p>
 * A {@code DeadlineTask} extends {@link Task} and stores a description
 * and a deadline date.
 * </p>
 */
public final class DeadlineTask extends Task implements TaskWithDate {
    private final String taskDeadline;
    /**
     * Constructs a new {@code DeadlineTask}.
     *
     * @param input the task description
     * @param taskDeadline   the deadline date (expected format: yyyy-MM-dd or free text)
     */
    public DeadlineTask(String input, String taskDeadline) {
        super(input);
        assert taskDeadline != null && !taskDeadline.isBlank();
        this.taskDeadline = taskDeadline;
    }

    /**
     * Returns a boolean that indicates if a task is overdue/deadline is in three days
     * @return a boolean
     */
    public boolean isDueWithinThreeDays() {
        LocalDate currentDate = LocalDate.now();
        LocalDateTime deadline = Parser.stringToLocalDateTime(taskDeadline);
        assert deadline != null;
        return currentDate.isAfter(deadline.toLocalDate().minusDays(3));
    }

    public String sendReminder() {
        return this + " is due soon / is overdue!";
    }
    /**
     * Writes this deadline task to the given {@link BufferedWriter}.
     * <p>
     * Format: {@code D | <done> | <name> | <deadline>}
     * </p>
     *
     * @param bw the writer to output task data
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void write(BufferedWriter bw) throws IOException {
        bw.write("D | " + (isDone() ? 1 : 0) + " | "
                + getName() + " | " + taskDeadline + "\n");
        bw.flush();
    }

    /**
     * Returns a human-readable string representation of the deadline task.
     *
     * @return formatted string with description and deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + parseDate(taskDeadline) + ")";
    }
}
