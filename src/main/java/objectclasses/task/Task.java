package objectclasses.task;

import java.time.LocalDateTime;

import objectclasses.exception.CommandFormatException;
import objectclasses.exception.InvalidTaskException;
import objectclasses.exception.LynxEnumException;
import objectclasses.exception.LynxException;

/**
 * Represents a task with a <code>TaskType</code>, <code>Status</code>, name, priority and id for tracking.
 * <p>
 * <code>Status</code> is <code>INCOMPLETE</code> by default, and id is assigned by the constructor.
 */
public abstract class Task implements Comparable<Task> {

    /**
     * Represents the three types of tasks.
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
                throw new LynxEnumException("Invalid type.");
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
                throw new LynxEnumException("Invalid status.");
            }
            }
        }

    }

    private static int currId = 0;
    private final int id;
    private String name;
    private int priority = 0;
    private Status status = Status.INCOMPLETE;
    private TaskType type;

    /**
     * Creates a task with a name and type.
     * @param name Name of the task.
     * @param type Type of task.
     */
    public Task(String name, int priority, TaskType type) {
        currId += 1;
        id = currId;
        this.name = name;
        this.priority = priority;
        this.type = type;
    }

    /**
     * Checks that a task name is not blank, does not contain the "/" character, and does not exceed 150 characters.
     *
     * @param name Name of the task to be checked.
     * @throws LynxException If task name is invalid.
     */
    public static void checkName(String name) throws LynxException {
        if (name.isBlank()) {
            throw InvalidTaskException.blankName();
        }
        if (name.contains("/")) {
            throw InvalidTaskException.invalidName();
        }
        if (name.length() > 150) {
            throw InvalidTaskException.longName();
        }
    }

    /**
     * Parses a priority represented as a string into a non-negative integer.
     *
     * @param priority Priority specified as a string.
     * @throws LynxException If priority is not a valid non-negative integer.
     */
    public static int parsePriority(String priority) throws LynxException {
        int intPriority;
        try {
            intPriority = Integer.parseInt(priority);
        } catch (NumberFormatException e) {
            throw CommandFormatException.invalidPriority();
        }

        if (intPriority < 0) {
            throw CommandFormatException.invalidPriority();
        }
        return intPriority;
    }

    public int getId() {
        assert(id >= 0);
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
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
     * Compares two tasks based on their priority.
     *
     * @return -1 if the current task has a greater priority.
     * 0 if both tasks are of equal priority.
     * 1 if the current task has a lower priority.
     */
    @Override
    public int compareTo(Task task) {
        return -Integer.compare(this.priority, task.priority);
    }

    /**
     * Returns a string representation of the task used for storing it in a text file.
     *
     * @return String representation.
     */
    public String storageRepresentation() {
        StringBuilder taskString = new StringBuilder();
        taskString.append(type.name());
        taskString.append("|").append(status.name());
        taskString.append("|").append(id);
        taskString.append("|").append(name);
        taskString.append("|").append(priority);
        return taskString.toString();
    }

    /**
     * Returns a string representation of the task without its id.
     *
     * @return String representation.
     */
    public String testRepresentation() {
        return String.format("[%s]%s%s %s", priority, type.getSymbol(), status.getSymbol(), name);
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
