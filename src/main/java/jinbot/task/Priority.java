package jinbot.task;

/**
 * Represents priority of each task either LOW, MEDIUM or HIGH.
 */
public enum Priority {
    HIGH(3),
    MEDIUM(2),
    LOW(1);

    private final int priority;

    /**
     * Constructs priority.
     *
     * @param priority The int value of priority.
     */
    Priority(int priority) {
        this.priority = priority;
    }

    /**
     * Returns priority of the task.
     * @return Returns int value of priority.
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Compares the priorities between two tasks.
     *
     * @param priority Priority of the task for comparing.
     * @return Returns boolean value.
     */
    public boolean isHigherThan(Priority priority) {
        return this.priority > priority.priority;
    }


}
