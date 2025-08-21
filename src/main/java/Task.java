public class Task {
    private String description;
    private boolean isDone = false;

    public Task(String description) {
        this.description = description;
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }

    public boolean getDone() {
        return this.isDone;
    }

    public String getIcon() {
        return isDone ? "X" : " ";
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "[" + getIcon() + "] " + description;
    }
}