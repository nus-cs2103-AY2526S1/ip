package sora.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Deadline extends Task {
    protected LocalDateTime by;
    public Deadline(String description, LocalDateTime by){
        super(Task.TaskType.DEADLINE, description);
        this.by =by;
    }

    public String byToFormat() {
        return by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm", Locale.ENGLISH));
    }
    @Override
    public String toString(){
        return super.toString() + " (by: " + this.byToFormat() + ")";
    }
}
