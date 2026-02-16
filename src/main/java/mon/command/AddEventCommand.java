package mon.command;

import mon.storage.Storage;
import mon.task.Event;
import mon.task.TaskList;

/**
 * Command to add an event task.
 */
public class AddEventCommand extends Command {
    private static final String INDENT = "    ";
    private static final String MESSAGE_TASK_ADDED = "Got it. I've added this task:";
    private static final String MESSAGE_TASK_COUNT_PREFIX = "Now you have ";
    private static final String MESSAGE_TASK_COUNT_SUFFIX = " tasks in the list.";
    
    private final String description;
    private final String from;
    private final String to;
    
    /**
     * Creates a new AddEventCommand with the specified description, start time, and end time.
     * 
     * @param description the description of the event task
     * @param from the start time of the event
     * @param to the end time of the event
     */
    public AddEventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }
    
    @Override
    public String execute(TaskList taskList, Storage storage) throws Exception {
        boolean isDescriptionNull = description == null;
        boolean isDescriptionEmpty = description != null && description.trim().isEmpty();
        boolean isDescriptionInvalid = isDescriptionNull || isDescriptionEmpty;
        
        if (isDescriptionInvalid) {
            throw new Exception("Event description cannot be empty!");
        }
        
        boolean isFromNull = from == null;
        boolean isFromEmpty = from != null && from.trim().isEmpty();
        boolean isFromInvalid = isFromNull || isFromEmpty;
        
        if (isFromInvalid) {
            throw new Exception("Event start time cannot be empty!");
        }
        
        boolean isToNull = to == null;
        boolean isToEmpty = to != null && to.trim().isEmpty();
        boolean isToInvalid = isToNull || isToEmpty;
        
        if (isToInvalid) {
            throw new Exception("Event end time cannot be empty!");
        }
        
        try {
            // Add the task
            taskList.addTask(new Event(description, from, to));
            
            return INDENT + MESSAGE_TASK_ADDED + "\n" + 
                   INDENT + taskList.getTask(taskList.size() - 1).toString() + "\n" +
                   INDENT + MESSAGE_TASK_COUNT_PREFIX + taskList.size() + MESSAGE_TASK_COUNT_SUFFIX;
        } catch (Exception e) {
            throw new Exception("Invalid event format!");
        }
    }
}
