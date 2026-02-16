package mon.command;

import mon.storage.Storage;
import mon.task.Task;
import mon.task.TaskList;

/**
 * Command to delete a task.
 */
public class DeleteCommand extends Command {
    private static final String INDENT = "    ";
    private static final String MESSAGE_TASK_DELETED = "Noted. I've removed this task:";
    private static final String MESSAGE_TASK_COUNT_PREFIX = "Now you have ";
    private static final String MESSAGE_TASK_COUNT_SUFFIX = " tasks in the list.";
    
    private final int taskNumber;
    
    /**
     * Creates a new DeleteCommand to delete the specified task.
     * 
     * @param taskNumber the number of the task to delete (1-indexed)
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }
    
    @Override
    public String execute(TaskList taskList, Storage storage) throws Exception {
        assert taskList != null : "TaskList cannot be null";
        assert storage != null : "Storage cannot be null";
        
        boolean isTaskNumberTooSmall = taskNumber < 1;
        boolean isTaskNumberTooLarge = taskNumber > taskList.size();
        boolean isTaskNumberOutOfBounds = isTaskNumberTooSmall || isTaskNumberTooLarge;
        
        if (isTaskNumberOutOfBounds) {
            throw new Exception("Task number is out of bounds!");
        }
        
        // Remove the task
        Task removedTask = taskList.getTask(taskNumber - 1);
        assert removedTask != null : "Retrieved task cannot be null";
        taskList.removeTask(taskNumber - 1);
        
        return INDENT + MESSAGE_TASK_DELETED + "\n" + 
               INDENT + removedTask.toString() + "\n" +
               INDENT + MESSAGE_TASK_COUNT_PREFIX + taskList.size() + MESSAGE_TASK_COUNT_SUFFIX;
    }
}
