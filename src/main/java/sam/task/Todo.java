package sam.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }
    
    public Todo(String description, boolean isDone) {
        super(description);
        if (isDone) {
            this.markDone();
        }
    }
    
    @Override
    protected String kind() {
        return "[T]";
    }
    
    public boolean isDone() {
        return super.isDone();
    }
    
    public String toSaveFormat() {
        return String.format("T | %d | %s", isDone() ? 1 : 0, description);
    }
}
