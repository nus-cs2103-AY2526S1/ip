package command;

/**
 * Command implementation for sorting tasks by date.
 *
 * Note: GitHub Copilot assisted in implementing the sorting comparator
 * and suggesting the date-based sorting algorithm.
 */
public class SortCommand extends Command {
    @Override
    public String execute(task.TaskList tasks, ui.Ui ui) {
        tasks.sortTasks();
        return "Tasks have been sorted by date.";
    }
}
