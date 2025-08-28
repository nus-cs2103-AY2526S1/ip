public abstract class Task {
    private final String description;
    private boolean done;

    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    public void mark()   { this.done = true; }
    public void unmark() { this.done = false; }

    public String checkbox() { return done ? "[X]" : "[ ]"; }
    public boolean isDone() { return done; }
    public String getDescription() { return description; }

    @Override
    public String toString() {
        return checkbox() + " " + description;
    }
}
