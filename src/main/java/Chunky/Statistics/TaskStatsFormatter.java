package Chunky.Statistics;

/**
 * Formats task statistics for display in user interfaces.
 *
 * This class handles the presentation layer for task statistics, converting
 * statistical data objects into human-readable strings. It separates formatting
 * logic from calculation logic, following the Single Responsibility Principle.
 *
 * Provides different formatting styles for different display contexts:
 * - Compact format for continuous display (task counter)
 * - Detailed format for on-demand reporting
 *
 * Uses private helper methods to maintain SLAP compliance and improve
 * code organization.
 */
public class TaskStatsFormatter {

    /**
     * Formats basic task statistics into a compact display string.
     *
     * Creates a single-line summary suitable for status bars or task counters.
     * The format follows the pattern: "Tasks: X total | Y completed | Z remaining"
     *
     * @param stats the basic statistics to format
     * @return formatted string suitable for compact display
     */
    public String formatBasicStats(TaskStats stats) {
        return String.format("Tasks: %d total | %d completed | %d remaining",
                stats.getTotalTasks(),
                stats.getCompletedTasks(),
                stats.getRemainingTasks());
    }

    /**
     * Formats detailed task statistics into a comprehensive report.
     *
     * Creates a multi-line report including basic statistics, completion
     * percentage, and task type breakdown. Handles the special case of
     * empty task lists with an encouraging message.
     *
     * @param detailedStats the comprehensive statistics to format
     * @return formatted multi-line string suitable for detailed reporting
     */
    public String formatDetailedStats(DetailedTaskStats detailedStats) {
        if (detailedStats.getBasicStats().getTotalTasks() == 0) {
            return formatEmptyTasksMessage();
        }

        return formatNonEmptyTasksMessage(detailedStats);
    }

    /**
     * Generates an encouraging message for users with no tasks.
     *
     * Provides guidance to users who haven't created any tasks yet,
     * suggesting the available task types they can create.
     *
     * @return message for empty task lists
     */
    private String formatEmptyTasksMessage() {
        return "No tasks yet! Start by adding a todo, deadline, or event.";
    }

    /**
     * Formats detailed statistics for non-empty task lists.
     *
     * Creates a structured report showing total counts, completion metrics,
     * and breakdown by task type. Uses consistent formatting and includes
     * percentage calculations with one decimal place precision.
     *
     * @param stats the detailed statistics to format
     * @return formatted comprehensive statistics report
     */
    private String formatNonEmptyTasksMessage(DetailedTaskStats stats) {
        return String.format("Task Statistics:\n" +
                        "Total Tasks: %d\n" +
                        "Completed: %d (%.1f%%)\n" +
                        "Remaining: %d\n\n" +
                        "Task Types:\n" +
                        "Todos: %d\n" +
                        "Deadlines: %d\n" +
                        "Events: %d",
                stats.getBasicStats().getTotalTasks(),
                stats.getBasicStats().getCompletedTasks(),
                stats.getCompletionRate(),
                stats.getBasicStats().getRemainingTasks(),
                stats.getBreakdown().getTodoCount(),
                stats.getBreakdown().getDeadlineCount(),
                stats.getBreakdown().getEventCount());
    }
}