package Chunky.Statistics;

/**
 * Comprehensive statistics container combining multiple metrics.
 *
 * This class aggregates basic task statistics, task type breakdown, and
 * calculated completion rates into a single immutable object. It uses
 * composition to combine simpler statistics objects rather than duplicating
 * their data, promoting code reuse and consistency.
 *
 * Designed for detailed reporting scenarios where complete task analysis
 * is required.
 */
public class DetailedTaskStats {
    private final TaskStats basicStats;
    private final TaskTypeBreakdown breakdown;
    private final double completionRate;

    /**
     * Constructs DetailedTaskStats with all statistical components.
     *
     * @param basicStats fundamental task counts and metrics
     * @param breakdown distribution of tasks by type
     * @param completionRate percentage of tasks completed (0.0 to 100.0)
     */
    public DetailedTaskStats(TaskStats basicStats, TaskTypeBreakdown breakdown, double completionRate) {
        this.basicStats = basicStats;
        this.breakdown = breakdown;
        this.completionRate = completionRate;
    }

    /**
     * Returns the basic task statistics.
     *
     * @return TaskStats containing fundamental counts
     */
    public TaskStats getBasicStats() { return basicStats; }

    /**
     * Returns the task type breakdown.
     *
     * @return TaskTypeBreakdown showing distribution by task type
     */
    public TaskTypeBreakdown getBreakdown() { return breakdown; }

    /**
     * Returns the completion rate as a percentage.
     *
     * @return completion rate from 0.0 to 100.0
     */
    public double getCompletionRate() { return completionRate; }
}
