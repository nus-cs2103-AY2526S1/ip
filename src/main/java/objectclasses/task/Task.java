package objectclasses.task;

import java.time.LocalDateTime;

import objectclasses.exception.LynxException;

/**
 * Represents a task with a <code>TaskType</code>, <code>Status</code>, name and id for tracking.
 * <p>
 * <code>Status</code> is <code>INCOMPLETE</code> by default, and id is assigned by the constructor.
 */
public abstract class Task {

    /**
     * Represents the three type of tasks.
     */
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

        /**
         * Checks if the given symbol matches one of the task types alphabetically.
         *
         * @param symbol Symbol to be checked as a string.
         * @return <code>TaskType</code> matching the symbol.
         * @throws LynxException If symbol does not match any <code>TaskType</code>.
         */
        public static TaskType matchSymbol(String symbol) throws LynxException {
            switch (symbol.toLowerCase()) {
            case "todo" -> {
                return TODO;
            }
            case "deadline" -> {
                return DEADLINE;
            }
            case "event" -> {
                return EVENT;
            }
            default -> {
                throw new LynxException("Invalid type.");
            }
            }
        }

    }

    /**
     * Represents the three type of statuses.
     */
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

        /**
         * Checks if the given symbol matches one of the statuses alphabetically.
         *
         * @param symbol Symbol to be checked as a string.
         * @return <code>Status</code> matching the symbol.
         * @throws LynxException If symbol does not match any <code>Status</code>.
         */
        public static Status matchSymbol(String symbol) throws LynxException {
            switch (symbol.toLowerCase()) {
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

    /**
     * Creates a task with a name and type.
     * @param name Name of the task.
     * @param type Type of task.
     */
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
