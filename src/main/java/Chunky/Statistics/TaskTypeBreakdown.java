package Chunky.Statistics;

/**
 * Immutable data structure representing task distribution by type.
 *
 * This class tracks the count of each task type (Todo, Deadline, Event) in
 * the system, providing insights into how users organize their work. The
 * immutable design ensures data consistency and thread safety.
 *
 * Used in detailed statistics reporting to show task composition patterns.
 */
public class TaskTypeBreakdown {
    private final int todoCount;
    private final int deadlineCount;
    private final int eventCount;

    /**
     * Constructs a TaskTypeBreakdown with counts for each task type.
     *
     * @param todoCount number of Todo tasks
     * @param deadlineCount number of Deadline tasks
     * @param eventCount number of Event tasks
     */
    public TaskTypeBreakdown(int todoCount, int deadlineCount, int eventCount) {
        this.todoCount = todoCount;
        this.deadlineCount = deadlineCount;
        this.eventCount = eventCount;
    }

    /**
     * Returns the number of Todo tasks.
     *
     * @return Todo task count
     */
    public int getTodoCount() { return todoCount; }

    /**
     * Returns the number of Deadline tasks.
     *
     * @return Deadline task count
     */
    public int getDeadlineCount() { return deadlineCount; }

    /**
     * Returns the number of Event tasks.
     *
     * @return Event task count
     */
    public int getEventCount() { return eventCount; }
}