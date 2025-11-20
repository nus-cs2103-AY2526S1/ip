package commands;

import exceptions.JackException;
import storage.Storage;
import tasklists.TaskList;
import ui.Ui;

/**
 * Command to sort all Deadline tasks in the task list chronologically, replacing their positions in the list.
 */
public class SortDeadlinesCommand extends Command {
    /**
     * Executes the sort deadlines command: sorts all Deadline tasks by due date in place.
     *
     * @param taskList The task list to sort.
     * @param ui The UI for user feedback.
     * @param storage The storage to save the sorted list.
     * @return A message indicating the result of the sort.
     * @throws JackException Never thrown in this command.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws JackException {
        taskList.sortDeadlinesChronologicallyInPlace();
        storage.saveTasks(taskList);
        return ui.showSortedDeadlines(taskList.getTasks());
    }

    /**
     * This command does not cause the program to exit.
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
