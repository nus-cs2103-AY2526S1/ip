package bob.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represent the deadline task
 */
public class Deadline extends Task {
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private LocalDate by;

    /**
     * Constructor for deadline
     * 
     * @param s Name of the task
     * @param by LocalDate of the deadline of the task
     * @param isDone Indicate whether task is done
     * @param id id of the task
     */
    public Deadline(String s, LocalDate by, boolean isDone, int id) {
        super(s, "D", isDone, id);
        this.by = by;
    }

    /**
     * String for saving and loading of task
     * 
     * @return String used for saving and loading
     */
    @Override
    public String saveString() {
        return String.format("%s|%s|%s|%s|%s", "D", super.getTaskName(), by.format(INPUT_FORMAT), super.returnDone(),
                Integer.toString(super.getId()));
    }

    /**
     * toString method to return task string
     * 
     * @return Returns task string
     */
    public String toString() {
        return "[D]" + super.toString() + " " + "(by: " + by.format(OUTPUT_FORMAT) + ")";
    }
}
