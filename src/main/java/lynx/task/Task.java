package lynx.task;

public abstract class Task {

    public enum TaskType {

        TODO("[T]"),
        DEADLINE("[D]"),
        EVENT("[E]");

        private final String symbol;

        TaskType(String symbol) {
            this.symbol = symbol;
        }

        public String getSymbol() {
            return symbol;
        }

    }

    public enum Status {

        COMPLETE("[X]"),
        INCOMPLETE("[ ]");

        private final String symbol;

        Status(String symbol) {
            this.symbol = symbol;
        }

        public String getSymbol() {
            return symbol;
        }

    }

    private static int currId = 0;
    private final int id;
    private String name;
    private Status status = Status.INCOMPLETE;
    private TaskType type;

    public Task(String name, TaskType type) {
        currId += 1;
        id = currId;
        this.name = name;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public TaskType getType() {
        return type;
    }
    public Status getStatus() {
        return status;
    }

    public void setComplete() {
        this.status = Status.COMPLETE;
    }

    public void setIncomplete() {
        this.status = Status.INCOMPLETE;
    }

    // String generated for testing that compares everything except id
    public String testRepresentation() {
        return String.format("%s%s %s", type.getSymbol(), status.getSymbol(), name);
    }

    @Override
    public String toString() {
        return String.format("%s (id:%d)", testRepresentation(), id);
    }

}