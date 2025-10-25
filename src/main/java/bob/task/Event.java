package bob.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represent the event task
 */
public class Event extends Task {
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");

    private LocalDate from;
    private LocalDate to;

    /**
     * Constructor for event
     * 
     * @param s Name of the event
     * @param from Starting duration of the event in LocalDate
     * @param to Ending duration of the event in LocaslDate
     * @param isDone Indicate whether the task is done
     * @param id Id of the task
     */
    public Event(String s, LocalDate from, LocalDate to, boolean isDone, int id) {
        super(s, "E", isDone, id);
        this.from = from;
        this.to = to;
    }

    /**
     * String for saving and loading of task
     * 
     * @return String used for saving and loading
     */
    @Override
    public String saveString() {
        return String.format("%s|%s|%s|%s|%s|%s", "E", super.getTaskName(), from.format(INPUT_FORMAT),
                to.format(INPUT_FORMAT), super.returnDone(), Integer.toString(super.getId()));
    }

    /**
     * toString method to return task string
     * 
     * @return Returns task string
     */
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(OUTPUT_FORMAT) + " to: " + to.format(OUTPUT_FORMAT)
                + ")";
    }

}
