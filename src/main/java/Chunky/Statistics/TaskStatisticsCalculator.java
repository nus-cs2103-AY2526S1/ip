package Chunky.Statistics;

import Chunky.Task.Task;
import Chunky.Task.TaskList;

/**
 * Calculates task statistics for the Chunky task manager application.
 * This class follows the Single Responsibility Principle by handling only
 * the calculation logic for task metrics, separating computation from
 * presentation and data storage.
 */
public class TaskStatisticsCalculator {
    private TaskList tasks;

    /**
     * Constructs a TaskStatisticsCalculator with the given task list.
     *
     * @param tasks the TaskList to calculate statistics from
     */
    public TaskStatisticsCalculator(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Calculates basic task statistics including total, completed, and remaining counts.
     *
     * This method provides the core metrics needed for the task counter display.
     * It delegates the completion counting to a separate method to maintain
     * single responsibility and improve readability.
     *
     * @return TaskStats object containing total, completed, and remaining task counts
     */
    public TaskStats calculateBasicStats() {
        int totalTasks = tasks.size();
        int completedTasks = countCompletedTasks();
        int remainingTasks = totalTasks - completedTasks;

        return new TaskStats(totalTasks, completedTasks, remainingTasks);
    }

    /**
     * Calculates comprehensive task statistics including basic stats, type breakdown,
     * and completion rate.
     *
     * This method aggregates multiple statistical calculations to provide a complete
     * view of task management performance. It follows the composition pattern by
     * combining results from other calculation methods.
     *
     * @return DetailedTaskStats object containing all available statistics
     */
    public DetailedTaskStats calculateDetailedStats() {
        TaskStats basicStats = calculateBasicStats();
        TaskTypeBreakdown breakdown = calculateTaskTypeBreakdown();
        double completionRate = calculateCompletionRate(basicStats);

        return new DetailedTaskStats(basicStats, breakdown, completionRate);
    }

    /**
     * Counts the number of completed tasks in the task list.
     *
     * Iterates through all tasks and checks their completion status using
     * the getDone() method. This is a private utility method that encapsulates
     * the completion counting logic.
     *
     * @return the number of tasks marked as completed
     */
    private int countCompletedTasks() {
        int completed = 0;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDone()) {
                completed++;
            }
        }
        return completed;
    }

    /**
     * Calculates the breakdown of tasks by type (Todo, Deadline, Event).
     *
     * Determines task types by examining the string representation of each task.
     * This approach relies on the consistent formatting of task toString() methods
     * where each type starts with a specific prefix ([T], [D], [E]).
     *
     * Note: This method depends on the toString() format staying consistent.
     * Consider using instanceof checks for more robust type detection.
     *
     * @return TaskTypeBreakdown object containing counts for each task type
     */
    private TaskTypeBreakdown calculateTaskTypeBreakdown() {
        int todoCount = 0;
        int deadlineCount = 0;
        int eventCount = 0;

        for (int i = 0; i < tasks.size(); i++) {
            String taskString = tasks.get(i).toString();
            if (taskString.startsWith("[T]")) todoCount++;
            else if (taskString.startsWith("[D]")) deadlineCount++;
            else if (taskString.startsWith("[E]")) eventCount++;
        }

        return new TaskTypeBreakdown(todoCount, deadlineCount, eventCount);
    }

    /**
     * Calculates the completion rate as a percentage.
     *
     * Handles the edge case of zero total tasks by returning 0.0 to avoid
     * division by zero. The result is expressed as a percentage (0-100).
     *
     * @param stats the basic statistics containing task counts
     * @return completion rate as a percentage (0.0 to 100.0)
     */
    private double calculateCompletionRate(TaskStats stats) {
        if (stats.getTotalTasks() == 0) return 0.0;
        return (double) stats.getCompletedTasks() / stats.getTotalTasks() * 100;
    }
}