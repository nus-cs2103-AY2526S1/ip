import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    LocalDateTime to;
    LocalDateTime from;

    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mma");
    private static final DateTimeFormatter SAVE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    // Events
    Event(String name, Boolean completed, LocalDateTime from, LocalDateTime to) {
        super(name, completed);
        this.to = to;
        this.from = from;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String print() {
        return "[E] [" + (this.completed ? "X" : " ") + "] " + this.name +
                " (from: " + this.from.format(FORMAT) + " to: " + this.to.format(FORMAT) + ")";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getAsCsv() {
        return "E," + this.completed.toString() + "," + this.name + "," + this.from.format(SAVE_FORMAT) + "," +
                this.to.format(SAVE_FORMAT);
    }
}
