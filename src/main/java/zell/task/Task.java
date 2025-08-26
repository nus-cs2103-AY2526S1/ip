package zell.task;

import zell.exception.ZellException;

public abstract class Task {
    private final String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public static Task stringToTask(String stringTask) throws ZellException{
        String[] components = stringTask.split(" \\| ");
        switch (components[0]) {
        case "T":
            return new ToDo(components[2], Boolean.parseBoolean(components[1]));
        case "D":
            return new Deadline(components[2], components[3], Boolean.parseBoolean(components[1]));
        case "E":
            return new Event(components[2], components[3], components[4], Boolean.parseBoolean(components[1]));
        default:
            throw new ZellException("Unknown task type encountered when converting tasks");
        }
    }

    public abstract String taskToString();


    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getName() {
        return this.name;
    }

    public boolean getDone() {
        return this.isDone;
    }

    @Override
    public String toString() {
        String checked = this.isDone ? "X" : " ";
        return String.format("[%s] %s", checked, this.name);
    }
}
