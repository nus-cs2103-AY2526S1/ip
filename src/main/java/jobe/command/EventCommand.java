package jobe.command;

import jobe.exception.JobeException;
import jobe.storage.Storage;
import jobe.task.Task;
import jobe.task.TaskList;
import jobe.ui.Ui;

/**
 * Represents a command to instruct Jobe to exit when user inputs "event".
 */
public class EventCommand extends Command {
    private String taskDescription;
    private String startDate;
    private String endDate;
    
    /**
     * Creates an EventCommand object.
     *
     * @param taskDescription Description of task.
     * @param startDate Start date of task.
     * @param endDate End date of task.
     */
    public EventCommand(String taskDescription, String startDate, String endDate) {
        this.taskDescription = taskDescription;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
    /**
     * Creates an EventCommand object.
     *
     * @param args User's input without the command word.
     * @return An EventCommand object with the corresponding arguments.
     * @throws JobeException If user passes in incorrect inputs.
     */
    public static EventCommand createEventCommand(String args) throws JobeException {
        String[] taskDescription = parseEventArgs(args);
        return new EventCommand(taskDescription[0], taskDescription[1], taskDescription[2]);
    }
    
    // ChatGPT was used to refactor and generate this method as part of A-AiAssisted
    private static String[] parseEventArgs(String args) throws JobeException {
        String[] taskDescription = args.split(" /", 2);
        if (taskDescription[0].isBlank()) {
            throw new JobeException("OOPS!!!! The description of an event task cannot be empty!");
        }
        
        if (taskDescription.length < 2) {
            throw new JobeException("OOPS!!!! You forgot to specify the START date/time!");
        }
        
        String[] dates = taskDescription[1].split(" /");
        if (dates.length < 2) {
            throw new JobeException("OOPS!!!! You forgot to specify the END date/time!");
        }
        
        if (dates.length > 2) {
            throw new JobeException("OOPS!!!! You have entered additional parameters!\n"
                    + "Please ensure you only enter the task description, start and end date/time!");
        }
        
        return new String[] { taskDescription[0], dates[0].trim(), dates[1].trim() };
    }
    
    /**
     * Creates new event task, adds this created task to user's task list,
     * saves tasks to user's local file and prints confirmation messages to the user.
     *
     * @param taskList Current user's task list for new tasks to be added.
     * @param ui Ui object used to display messages to the user.
     * @param storage Storage object to save tasks to user's local file.
     * @throws JobeException If EventTask class throws an exception when new task is created.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws JobeException {
        Task task = taskList.createEventTask(this.taskDescription, this.startDate, this.endDate);
        storage.saveTasks(taskList);
        ui.showEventResponse(task, taskList);
    }
    
    /**
     * Sets isExit to false.
     *
     * @return boolean false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
