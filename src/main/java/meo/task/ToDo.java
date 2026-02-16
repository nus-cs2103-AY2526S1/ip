package meo.task;

public class ToDo extends Task {
    public ToDo(String text) {
        super(text, null);
    }

    @Override
    public String toString() {
        String mark = isDone ? "X" : " ";
        return "[T][" + mark + "] " + description;
    }
}
