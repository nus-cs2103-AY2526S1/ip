package task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    @Override
    public String toString() {
        return isDone ? "[X] " + description : "[ ] " + description;
    }

    public abstract void complete();

    public String getDescription() {
        return description;
    }
}
