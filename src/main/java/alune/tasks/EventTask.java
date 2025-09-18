package alune.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class EventTask extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;

    /**
     * This class inherits from Task for tasks with start and end times.
     * 
     * @author nghnaomi
     */
    public EventTask(String name, LocalDateTime start, LocalDateTime end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    public EventTask(Task other) {
        super(other);
    }

    @Override
    public Task cloneTask() {
        return new EventTask(this);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "\n          (from: " +
                start.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT)
                        .withLocale(Locale.US))
                +
                ";\n          to: " +
                end.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT)
                        .withLocale(Locale.US))
                + ")";
    }
}
