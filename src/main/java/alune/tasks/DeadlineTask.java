package alune.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

/**
 * This class inherits from Task for tasks with deadlines.
 * 
 * @author nghnaomi
 */
public class DeadlineTask extends Task {
    protected LocalDateTime deadline;

    public DeadlineTask(String name, LocalDateTime deadline) {
        super(name);
        this.deadline = deadline;
    }

    public DeadlineTask(Task other) {
        super(other);
    }

    @Override
    public Task cloneTask() {
        return new DeadlineTask(this);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "\n          (by: " +
                deadline.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT)
                        .withLocale(Locale.US))
                + ")";
    }
}
