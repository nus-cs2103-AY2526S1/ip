public abstract class Task {

    public enum TaskType {
        TODO("[T]"),
        DEADLINE("[D]"),
        EVENT("[E]");
        private final String symbol;
        TaskType(String symbol) { this.symbol = symbol; }
        public String getSymbol() { return symbol; }
    }

    public enum Status {
        COMPLETE("[X]"),
        INCOMPLETE("[ ]");
        private final String symbol;
        Status(String symbol) { this.symbol = symbol; }
        public String getSymbol() { return symbol; }
    }

    protected static int currId = 0;
    protected final int id;
    protected String name;
    protected Status status = Status.INCOMPLETE;
    protected TaskType type;

    public Task(String name, TaskType type) {
        currId += 1;
        this.id = currId;
        this.name = name;
        this.type = type;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public TaskType getType() {return type; }
    public Status getStatus() {return status; }
    public void setCompleted() { this.status = Status.COMPLETE; }
    public void resetCompleted() { this.status = Status.INCOMPLETE; }

    // Each subclass has its own toString
    @Override
    public abstract String toString();

}