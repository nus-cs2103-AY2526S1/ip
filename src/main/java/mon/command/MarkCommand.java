package mon.command;

import mon.storage.Storage;
import mon.task.Task;
import mon.task.TaskList;

/**
 * Command to mark a task as done.
 */
public class MarkCommand extends Command {
    private static final String INDENT = "    ";
    private static final String MESSAGE_MARKED_DONE = "Nice! I've marked this task as done:";
    
    private final int taskNumber;
    
    /**
     * Creates a new MarkCommand to mark the specified task as done.
     * 
     * @param taskNumber the number of the task to mark (1-indexed)
     */
    public MarkCommand(int taskNumber) {
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
        
        // Mark the task as done
        Task task = taskList.getTask(taskNumber - 1);
        assert task != null : "Retrieved task cannot be null";
        task.setStatus(true);
        
        return INDENT + MESSAGE_MARKED_DONE + "\n" + 
               INDENT + task.toString();
    }
}
