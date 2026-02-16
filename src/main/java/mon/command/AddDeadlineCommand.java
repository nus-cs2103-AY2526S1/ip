package mon.command;

import mon.storage.Storage;
import mon.task.Deadline;
import mon.task.TaskList;

/**
 * Command to add a deadline task.
 */
public class AddDeadlineCommand extends Command {
    private static final String INDENT = "    ";
    private static final String MESSAGE_TASK_ADDED = "Got it. I've added this task:";
    private static final String MESSAGE_TASK_COUNT_PREFIX = "Now you have ";
    private static final String MESSAGE_TASK_COUNT_SUFFIX = " tasks in the list.";
    
    private final String description;
    private final String by;
    
    /**
     * Creates a new AddDeadlineCommand with the specified description and deadline.
     * 
     * @param description the description of the deadline task
     * @param by the deadline date
     */
    public AddDeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }
    
    @Override
    public String execute(TaskList taskList, Storage storage) throws Exception {
        boolean isDescriptionNull = description == null;
        boolean isDescriptionEmpty = description != null && description.trim().isEmpty();
        boolean isDescriptionInvalid = isDescriptionNull || isDescriptionEmpty;
        
        if (isDescriptionInvalid) {
            throw new Exception("Deadline description cannot be empty!");
        }
        
        boolean isByNull = by == null;
        boolean isByEmpty = by != null && by.trim().isEmpty();
        boolean isByInvalid = isByNull || isByEmpty;
        
        if (isByInvalid) {
            throw new Exception("Deadline date cannot be empty!");
        }
        
        try {
            // Add the task
            taskList.addTask(new Deadline(description, by));
            
            return INDENT + MESSAGE_TASK_ADDED + "\n" + 
                   INDENT + taskList.getTask(taskList.size() - 1).toString() + "\n" +
                   INDENT + MESSAGE_TASK_COUNT_PREFIX + taskList.size() + MESSAGE_TASK_COUNT_SUFFIX;
        } catch (Exception e) {
            throw new Exception("Invalid deadline format!");
        }
    }
}
