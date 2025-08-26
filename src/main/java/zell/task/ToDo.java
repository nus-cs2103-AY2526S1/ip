package zell.task;

public class ToDo extends Task{
    public ToDo(String name) {
        super(name);
    }

    public ToDo(String name, boolean isDone) {
        this(name);
        setDone(isDone);
    }

    @Override
    public String taskToString() {
        return String.format("%s | %b | %s", "T", getDone(), getName());
    }

    @Override
    public String toString() {
        return String.format("[%s]%s", "T", super.toString());
    }
}

