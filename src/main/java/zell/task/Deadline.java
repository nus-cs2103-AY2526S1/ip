package zell.task;

import zell.util.DateOrTime;
import zell.exception.ZellException;

public class Deadline extends Task{
    private final DateOrTime dueBy;

    public Deadline(String name, String dueBy) throws ZellException {
        super(name);
        this.dueBy = new DateOrTime(dueBy);
    }

    public Deadline(String name, String dueBy, boolean isDone) throws ZellException {
        this(name, dueBy);
        setDone(isDone);
    }

    @Override
    public String taskToString() {
        return String.format("%s | %b | %s | %s", "D", getDone(), getName(), this.dueBy.originalFormat());
    }


    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)", "D", super.toString(), this.dueBy.toString());
    }
}

