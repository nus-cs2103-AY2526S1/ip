package haru.model;

/**
 * Represents the type of a task.
 */
public enum TaskType {
    /**
     * A to-do task.
     */
    TODO('T'),

    /**
     * A deadline task.
     */
    DEADLINE('D'),

    /**
     * An event task.
     */
    EVENT('E');

    private final char code;

    /**
     * Constructs a TaskType with code.
     *
     * @param code the task type code
     */
    TaskType(char code) {
        this.code = code;
    }

    /**
     * Returns the task type code.
     *
     * @return the task type code
     */
    public char getCode() {
        return this.code;
    }
}
