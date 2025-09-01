import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    LocalDateTime by;

    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mma");
    private static final DateTimeFormatter SAVE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    // Deadlines
    Deadline(String name, Boolean completed, LocalDateTime by) {
        super(name, completed);
        this.by = by;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String print() {
        return "[D] [" + (this.completed ? "X" : " ") + "] " + this.name +
                " (by: " + this.by.format(FORMAT) + ")";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getAsCsv() {
        return "D," + this.completed.toString() + "," + this.name + "," + this.by.format(SAVE_FORMAT);
    }
}
