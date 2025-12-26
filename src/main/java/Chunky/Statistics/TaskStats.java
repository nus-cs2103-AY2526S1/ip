package Chunky.Statistics;

/**
 * Immutable data structure representing basic task statistics.
 *
 * This class encapsulates the fundamental metrics for task tracking in the
 * Chunky application. It follows the immutable object pattern with all fields
 * declared as final and no mutator methods provided, ensuring thread safety
 * and preventing accidental modifications.
 *
 * The class serves as a simple data container that can be safely passed
 * between components without risk of unintended state changes.
 */
public class TaskStats {
    private final int totalTasks;
    private final int completedTasks;
    private final int remainingTasks;

    /**
     * Constructs a TaskStats object with the specified counts.
     *
     * @param totalTasks the total number of tasks in the system
     * @param completedTasks the number of tasks marked as completed
     * @param remainingTasks the number of tasks not yet completed
     */
    public TaskStats(int totalTasks, int completedTasks, int remainingTasks) {
        this.totalTasks = totalTasks;
        this.completedTasks = completedTasks;
        this.remainingTasks = remainingTasks;
    }

    /**
     * Returns the total number of tasks.
     *
     * @return total task count
     */
    public int getTotalTasks() { return totalTasks; }

    /**
     * Returns the number of completed tasks.
     *
     * @return completed task count
     */
    public int getCompletedTasks() { return completedTasks; }

    /**
     * Returns the number of remaining (uncompleted) tasks.
     *
     * @return remaining task count
     */
    public int getRemainingTasks() { return remainingTasks; }
}