package task;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an deadline task with a deadline date.
 * <p>
 * This class extends the <code>Task</code> class and implements the <code>GetDateable</code> interface.
 * It is used to represent tasks that must be finished by a certain date
 * </p>
 */
public class DeadlineTask extends Task implements GetDateable {

    private LocalDate deadline;

    /**
     * Constructs a new DeadlineTask with the specified name and deadline.
     *
     * @param name the name of the deadline task
     * @param deadline the deadline date by which the task should be completed
     */
    public DeadlineTask(String name, LocalDate deadline) {
        super(name);
        this.deadline = deadline;
    }

    /**
     * Constructs a new DeadlineTask with the specified name, completion status, and deadline.
     *
     * @param name the name of the deadline task
     * @param isCompleted the completion status of the task
     * @param deadline the deadline date by which the task should be completed
     */
    public DeadlineTask(String name, boolean isCompleted, LocalDate deadline) {
        super(name, isCompleted);
        this.deadline = deadline;
    }

    @Override
    public LocalDate getDate() {
        return deadline;
    }

    @Override
    public String save() {
        assert(deadline != null);
        return super.save() + "D#" + this.deadline;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");
        return "[D]" + super.toString() + " (by: " + deadline.format(formatter) + ")";
    }

    @Override
    public boolean isUpcoming(ChronoLocalDate today) {
        return deadline.isAfter(today);
    }
}
