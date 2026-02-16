package mon.command;

import mon.storage.Storage;
import mon.task.TaskList;
import mon.task.Todo;

/**
 * Command to add a todo task.
 */
public class AddTodoCommand extends Command {
    private static final String INDENT = "    ";
    private static final String MESSAGE_TASK_ADDED = "Got it. I've added this task:";
    private static final String MESSAGE_TASK_COUNT_PREFIX = "Now you have ";
    private static final String MESSAGE_TASK_COUNT_SUFFIX = " tasks in the list.";
    
    private final String description;
    
    /**
     * Creates a new AddTodoCommand with the specified description.
     * 
     * @param description the description of the simple task
     */
    public AddTodoCommand(String description) {
        this.description = description;
    }
    
    @Override
    public String execute(TaskList taskList, Storage storage) throws Exception {
        boolean isDescriptionNull = description == null;
        boolean isDescriptionEmpty = description != null && description.trim().isEmpty();
        boolean isDescriptionInvalid = isDescriptionNull || isDescriptionEmpty;
        
        if (isDescriptionInvalid) {
            throw new Exception("Todo description cannot be empty!");
        }
        
        // Add the task
        taskList.addTask(new Todo(description));
        
        String result = INDENT + MESSAGE_TASK_ADDED + "\n" + 
                       INDENT + taskList.getTask(taskList.size() - 1).toString() + "\n" +
                       INDENT + MESSAGE_TASK_COUNT_PREFIX + taskList.size() + MESSAGE_TASK_COUNT_SUFFIX;
        
        return result;
    }
}
