package chatbot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import chatbot.parser.DeadlineParsers;

public class Deadline extends Task {
    private LocalDateTime by;
    private String byStr;
    private DeadlineParsers parsers;

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    public Deadline(String description, String by) {
        super(description);
        this.by = parsers.parseToDateTime(by);
        this.byStr = getByStr(this.by);
    }
    public String getByStr(LocalDateTime by) {

        return by.format(FORMATTER);
    }
    public static Deadline parse(String arguments) {
        String[] deadlineParts = arguments.split(" /by ", 2);
        String by = (deadlineParts.length > 1) ? deadlineParts[1] : "";
        return new Deadline(deadlineParts[0], by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " by: " + by.toString();
    }

    public String toStringDisplay() {
        String formatted;
        if (this.by.getHour() == 0 && by.getMinute() == 0) {
            formatted = by.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } else {
            formatted = by.format(FORMATTER);
        }
        return "[D]" + super.toString() + " by: " + formatted;
    }
}

