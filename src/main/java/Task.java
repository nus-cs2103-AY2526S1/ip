public abstract class Task {
    protected static int currId = 0;
    protected final int id;
    protected String name;
    protected boolean completed = false;

    public Task(String name) {
        currId += 1;
        this.id = currId;
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public boolean isCompleted() { return completed; }
    public void setCompleted() { this.completed = true; }
    public void resetCompleted() { this.completed = false; }

    // Markers for subclasses to override
    public abstract String getTypeSymbol();

    // Each subclass has its own toString
    @Override
    public abstract String toString();
}