package piper.task;

public class Task {
    private final String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markDone(){
        this.isDone = true;
    }

    @Override
    public String toString() {
        return description;
    }
}
