public class Task {
    protected final String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false; // default not done
    }
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }
    
    public boolean isDone() {
        return isDone;
    }
    
    public String toSaveFormat() {
        // Default: T | 1 | description
        return String.format("T | %d | %s", isDone ? 1 : 0, description);
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