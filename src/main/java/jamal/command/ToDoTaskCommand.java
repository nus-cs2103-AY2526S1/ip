package jamal.command;

import jamal.task.Task;
import jamal.task.TaskList;
import jamal.util.Storage;

/**
 * Deadline Command SubClass for todo task creation
 */
public class ToDoTaskCommand extends Command {

    protected Task task;

    public ToDoTaskCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes Command on TaskList
     * Writes an unmarked Todo data into Storage
     *
     * @param taskList Tasklist that contains an Arraylist of tasks
     * @param storage Stores data and allow read write operations on it
     * @return String of actionable
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        String result = taskList.addTask(task);
        storage.addLine("T`UM`0`" + task.getDescription());
        return result;
    }
}
