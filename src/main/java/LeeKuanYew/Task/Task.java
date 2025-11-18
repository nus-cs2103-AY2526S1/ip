package LeeKuanYew.Task;

public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    public boolean checkDone() {
        return this.isDone;
    }

    public String getDescription() {
        return this.description;
    }

    @Override public String toString() {
        return (this.isDone ? "[X] " : "[ ] ") + this.description;
    }
}
