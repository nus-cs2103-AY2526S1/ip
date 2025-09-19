package LunarBot.Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    LocalDateTime to;
    LocalDateTime from;

    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mma");
    private static final DateTimeFormatter SAVE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    // Events
    public Event(String name, Boolean isCompleted, LocalDateTime from, LocalDateTime to) {
        super(name, isCompleted);
        this.to = to;
        this.from = from;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String print() {
        return "[E] [" + (this.isCompleted ? "X" : " ") + "] " + this.name +
                " (from: " + this.from.format(FORMAT) + " to: " + this.to.format(FORMAT) + ")";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getAsCsv() {
        return "E," + this.isCompleted.toString() + "," + this.name + "," + this.from.format(SAVE_FORMAT) + "," +
                this.to.format(SAVE_FORMAT);
    }
}
