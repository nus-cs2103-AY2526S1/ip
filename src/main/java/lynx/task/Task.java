package lynx.task;

import lynx.exception.LynxException;

import java.time.LocalDateTime;

/**
 * Represents a task with a <code>TaskType</code>, <code>Status</code>, name and id for tracking.
 * <p>
 * <code>Status</code> is <code>INCOMPLETE</code> by default, and id is assigned by the constructor.
 */
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

        COMPLETE("[C]"),
        INCOMPLETE("[I]"),
        EXPIRED("[E]");

        private final String symbol;

        Status(String symbol) {
            this.symbol = symbol;
        }

        public String getSymbol() {
            return symbol;
        }

        public static Status matchSymbol(String symbol) throws LynxException {
            switch (symbol) {
                case "complete" -> {
                    return COMPLETE;
                }
                case "incomplete" -> {
                    return INCOMPLETE;
                }
                case "expired" -> {
                    return EXPIRED;
                }
                default -> {
                    throw new LynxException("Invalid status.");
                }
            }
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
        if (status.equals(Status.COMPLETE)) {
            this.status = Status.INCOMPLETE;
        }
    }

    public void setExpired() {
        if (status.equals(Status.INCOMPLETE)) {
            this.status = Status.EXPIRED;
        }
    }

    /**
     * Checks if the task is active on the given date.
     *
     * @return True if task occurs on given date.
     */
    public abstract boolean isActive(LocalDateTime dateTime);

    /**
     * Returns a string representation of the task without its id.
     *
     * @return String representation.
     */
    public String testRepresentation() {
        return String.format("%s%s %s", type.getSymbol(), status.getSymbol(), name);
    }

    /**
     * Returns a string representation of the task with its id.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        return String.format("%s (id:%d)", testRepresentation(), id);
    }

}