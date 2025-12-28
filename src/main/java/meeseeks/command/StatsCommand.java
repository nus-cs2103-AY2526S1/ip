package meeseeks.command;

import meeseeks.storage.Storage;
import meeseeks.task.TaskList;
import meeseeks.ui.Ui;

/**
 * Command to display statistics about the task list using Java streams.
 */
public class StatsCommand extends Command {
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks == null) {
            ui.showMessage("Error: Task list is null");
            return;
        }
        
        long totalTasks = tasks.size();
        long completedTasks = tasks.getCompletedTaskCount();
        long pendingTasks = tasks.getPendingTaskCount();
        
        double completionRate = totalTasks > 0 ? (double) completedTasks / totalTasks * 100 : 0;
        
        StringBuilder stats = new StringBuilder();
        stats.append("=== Task Statistics ===\n");
        stats.append("Total tasks: ").append(totalTasks).append("\n");
        stats.append("Completed: ").append(completedTasks).append("\n");
        stats.append("Pending: ").append(pendingTasks).append("\n");
        stats.append("Completion rate: ").append(String.format("%.1f%%", completionRate));
        
        ui.showMessage(stats.toString());
    }
}
