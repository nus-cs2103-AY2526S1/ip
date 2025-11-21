package jamal.command;

import jamal.task.Deadline;
import jamal.task.Task;
import jamal.task.TaskList;
import jamal.util.Storage;

/**
 * Deadline Command SubClass for deadline task creation
 */

public class DeadlineTaskCommand extends Command {

    protected Deadline task;

    public DeadlineTaskCommand(Task task) {
        this.task = (Deadline) task;
    }

    /**
     * Executes Command on TaskList
     * Writes an unmarked Deadline data into Storage
     *
     * @param taskList Tasklist that contains an Arraylist of tasks
     * @param storage Stores data and allow read write operations on it
     * @return String of actionable
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        String result = taskList.addTask(task);
        storage.addLine("D`UM`0`" + task.getDescription() + "`" + task.getBy());
        return result;
    }
}
