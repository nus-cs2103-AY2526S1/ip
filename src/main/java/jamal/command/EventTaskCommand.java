package jamal.command;

import jamal.task.Event;
import jamal.task.Task;
import jamal.task.TaskList;
import jamal.util.Storage;

/**
 * Event Command SubClass for event task creation
 */
public class EventTaskCommand extends Command {

    protected Event task;

    public EventTaskCommand(Task task) {
        this.task = (Event) task;
    }

    /**
     * Executes Command on TaskList
     * Writes an unmarked Event data into Storage
     *
     * @param taskList Tasklist that contains an Arraylist of tasks
     * @param storage Stores data and allow read write operations on it
     * @return String of actionable
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        String result = taskList.addTask(task);
        storage.addLine("E`UM`0`" + task.getDescription() + "`" + task.getStart() + "`" + task.getEnd());
        return result;
    }
}
