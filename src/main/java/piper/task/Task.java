package piper.task;

public class Task {
    private final String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getTaskType() { return null; }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markDone () {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    public String getDescription() { return this.description; }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }

    public String toSerializedLine() {
        String doneField = this.isDone ? "1" : "0";
        return this.getTaskType() + " | " + doneField + " | " + this.description;
    }

}
