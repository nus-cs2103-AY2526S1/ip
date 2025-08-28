package sam.task;

public class Task {
    protected final String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false; // default not done
    }

    public void markDone() { 
        this.isDone = true; 
    }
    public void unmark() {
         this.isDone = false; 
    }

    protected String kind() {
        return "";
    }

    protected String status() {
        return "[" + (isDone ? "X" : " ") + "] ";
    }

    @Override
    public String toString() {
        return kind() + status() + " " + description;
    }
}
