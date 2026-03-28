package choicebot.command;

import java.util.ArrayList;

import choicebot.ChoiceBotException;
import choicebot.storage.Storage;
import choicebot.task.Task;
import choicebot.task.TaskList;
import choicebot.ui.UI;

/**
 * Represents a command that sorts the task list alphabetically or chronologically.
 */
public class SortCommand extends Command {
    protected String taskType;

    /**
     * Constructs a SortCommand.
     *
     * @param taskType Type of tasks we are sorting the task list by.
     */
    public SortCommand(String taskType) {
        this.taskType = taskType;
    }

    /**
     * Executes the Sort command, displaying the list of tasks in alphabetical
     * or chronological order depending on task type.
     *
     * @param tasks Task list in current instance.
     * @param ui User interface in current instance.
     * @param storage Storage used in current instance.
     * @throws ChoiceBotException If sortType is blank.
     */
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws ChoiceBotException {
        ArrayList<Task> sortedTasks = tasks.getTaskList();
        switch (taskType) {
        case "todo":
            sortedTasks = tasks.sortTodoTasks();
            break;
        case "event":
            sortedTasks = tasks.sortEventTasks();
            break;
        case "deadline":
            sortedTasks = tasks.sortDeadlineTasks();
            break;
        default:
            throw new ChoiceBotException("Invalid task type to sort by. You can only sort todo, event, and deadline.");
        }
        return ui.displayList(sortedTasks, false);
    }
}
