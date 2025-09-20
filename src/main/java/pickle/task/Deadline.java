package pickle.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A task with a deadline.
 * Displays the deadline date in a more readable fashion.
 */
public class Deadline extends Task {
    // SINGLE accepted input/storage format: "yyyy-MM-dd HHmm" (e.g., 2019-12-02 1800)
    private static final DateTimeFormatter IN_OUT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    // Display formats
    private static final DateTimeFormatter OUT_DATE = DateTimeFormatter.ofPattern("MMM d yyyy");
    private static final DateTimeFormatter OUT_TIME = DateTimeFormatter.ofPattern("h:mma");

    private LocalDateTime date;
    /**
     * Creates a deadline.
     * @param description information of the task.
     * @param date deadline due date in LocalDateTime format.
     */
    public Deadline(String description, LocalDateTime date) {
        super(description);
        this.date = date;
    }

    /**
     * Creates a deadline.
     * @param description information of the task.
     * @param date deadline due date in string format.
     */
    public Deadline(String description, String date) {
        super(description);

        try {
            this.date = LocalDateTime.parse(date.trim(), IN_OUT).withSecond(0).withNano(0);
        } catch (DateTimeParseException ignore) {
            return;
        }
    }

    public String type() {
        return "D";
    }

    /**
     * Format to save the file into the storage text file.
     * @return a string to save the file
     */
    public String writeToFile() {

        String state = super.isComplete() ? "1" : "0";
        return "D | " + state + " | " + getDescription().trim() + " | " + this.date.format(IN_OUT);
    }

    @Override
    public String toString() {
        String output = date.toLocalDate().format(OUT_DATE) + ", " + date.toLocalTime().format(OUT_TIME);
        return "[D]" + super.toString() + " (by: " + output + ")";
    }
}
