package snowy.task;

import java.time.format.DateTimeFormatter;

public class FixedDuration extends Task {
    private String duration;

    public FixedDuration(String task, String duration) {
        this(task, false, duration);
    }

    public FixedDuration(String task, boolean isDone, String duration) {
        super(task, isDone);
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "[F]" +
                (this.checkStatus() ? "[X] " : "[ ] ") +
                super.toString() +
                "(" +
                this.duration +
                ")";
    }
}
