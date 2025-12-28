package meeseeks.command;

import meeseeks.storage.Storage;
import meeseeks.task.RecurringTask;
import meeseeks.task.Task;
import meeseeks.task.TaskList;
import meeseeks.ui.Ui;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Command to list all overdue recurring tasks.
 * Searches through all tasks and filters for recurring tasks that are past their due date.
 */
public class ListOverdueCommand extends Command {
    private static final String NO_OVERDUE_MESSAGE = "Great! No overdue recurring tasks found.";
    private static final String OVERDUE_HEADER = "Here are your overdue recurring tasks:\n";

    /**
     * Executes the command and displays the results through the UI.
     * 
     * @param tasks the task list to search for overdue tasks
     * @param ui the UI component for displaying results
     * @param storage the storage component (not used in this command)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String response = executeAndGetResponse(tasks, storage);
        ui.showMessage(response);
    }

    /**
     * Executes the command and returns the response as a string.
     * 
     * @param tasks the task list to search for overdue tasks
     * @param storage the storage component (not used in this command)
     * @return the formatted response string
     */
    @Override
    public String executeAndGetResponse(TaskList tasks, Storage storage) {
        List<RecurringTask> overdueTasks = findOverdueTasks(tasks);

        if (overdueTasks.isEmpty()) {
            return NO_OVERDUE_MESSAGE;
        } else {
            return formatOverdueTasksList(overdueTasks);
        }
    }

    /**
     * Finds all overdue recurring tasks in the task list.
     * 
     * @param tasks the task list to search
     * @return a list of overdue recurring tasks
     */
    private List<RecurringTask> findOverdueTasks(TaskList tasks) {
        return tasks.getList().stream()
                .filter(task -> task instanceof RecurringTask)
                .map(task -> (RecurringTask) task)
                .filter(RecurringTask::isOverdue)
                .collect(Collectors.toList());
    }

    /**
     * Formats the list of overdue tasks into a numbered string.
     * 
     * @param overdueTasks the list of overdue tasks to format
     * @return the formatted string with numbered task list
     */
    private String formatOverdueTasksList(List<RecurringTask> overdueTasks) {
        StringBuilder message = new StringBuilder(OVERDUE_HEADER);
        for (int i = 0; i < overdueTasks.size(); i++) {
            message.append((i + 1)).append(".").append(overdueTasks.get(i)).append("\n");
        }
        return message.toString().trim();
    }
}
