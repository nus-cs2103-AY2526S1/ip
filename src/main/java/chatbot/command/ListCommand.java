package chatbot.command;

import chatbot.storage.Storage;
import chatbot.tasklist.TaskList;
import chatbot.ui.Ui;

/**
 * Represents a command to list all tasks in the TaskList.
 * When executed, it displays all tasks with their indices in the Ui.
 */
public class ListCommand extends Command {

    /**
     * Executes the ListCommand:
     * - Iterates through the TaskList and builds a formatted string of tasks
     * - Displays the tasks using the Ui
     * - If the task list is empty, displays an appropriate message
     *
     * @param tasks   the TaskList to display
     * @param ui      the Ui instance to display messages
     * @param storage the Storage instance for persistence (not used)
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "TaskList cannot be null";
        assert ui != null : "Ui cannot be null";
        assert storage != null : "Storage cannot be null";
        if (tasks.getTasks().isEmpty()) {
            return "Task list is empty! \nBEEP B00P";
        }

        String taskListStr =
                java.util.stream.IntStream.range(0, tasks.getTasks().size())
                        .mapToObj(i -> (i + 1) + ". " + tasks.getTasks().get(i))
                        .collect(java.util.stream.Collectors.joining("\n"));

        return "Here are your tasks:\n" + taskListStr;
    }

    /**
     * Indicates whether this command exits the application.
     *
     * @return false because ListCommand does not terminate the chatbot
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
