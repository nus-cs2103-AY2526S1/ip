package luna.task;

import java.time.Duration;

/**
 * Represents a {@code Task} that takes a fixed amount of time
 */
public class FixedDurationTask extends Task {
    private Duration duration;

    public FixedDurationTask(String name, Duration duration) {
        super(name);
        this.duration = duration;
    }

    @Override
    public String getTaskType() {
        return "task";
    }

    @Override
    public String toString() {
        return String.format("[T]%s (needs %s hours)", super.toString(), duration.toHours());
    }
}
