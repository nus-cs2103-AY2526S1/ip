public class Task {
    private final String description;
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

    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }
}