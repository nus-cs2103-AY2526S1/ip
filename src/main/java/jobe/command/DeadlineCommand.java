package jobe.command;

import jobe.exception.JobeException;
import jobe.storage.Storage;
import jobe.task.Task;
import jobe.task.TaskList;
import jobe.ui.Ui;

/**
 * Represents a command to instruct Jobe to exit when user inputs "deadline".
 */
public class DeadlineCommand extends Command {
    private String taskDescription;
    private String deadline;
    
    /**
     * Creates a DeadlineCommand object.
     *
     * @param taskDescription Description of task.
     * @param deadline Deadline of task.
     */
    public DeadlineCommand(String taskDescription, String deadline) {
        this.taskDescription = taskDescription;
        this.deadline = deadline;
    }
    
    /**
     * Creates a DeadlineCommand Object
     *
     * @param args User's input without the command word.
     * @return A DeadlineCommand object with the corresponding arguments.
     * @throws JobeException If user passes in incorrect inputs.
     */
    public static DeadlineCommand createDeadlineCommand(String args) throws JobeException {
        String[] taskDescription = parseAndValidateDeadlineArgs(args);
        
        return new DeadlineCommand(taskDescription[0], taskDescription[1].trim());
    }
    
    private static String[] parseAndValidateDeadlineArgs(String args) throws JobeException {
        String[] taskDescription = args.split(" /");
        
        if (taskDescription[0].isBlank()) {
            throw new JobeException("OOPS!!!! The description of a deadline task cannot be empty!");
        }
        
        if (taskDescription.length < 2) {
            throw new JobeException("OOPS!!!! You forgot to specify the deadline!");
        }
        
        if (taskDescription.length > 2) {
            throw new JobeException("OOPS!!!! You have entered additional parameters!\n"
                    + "Please ensure you only enter the task description and deadline!");
        }
        
        return taskDescription;
    }
    
    /**
     * Creates new deadline task, adds this created task to user's task list,
     * saves tasks to user's local file and prints confirmation messages to the user.
     *
     * @param taskList Current user's task list for new tasks to be added.
     * @param ui Ui object used to display messages to the user.
     * @param storage Storage object to save tasks to user's local file.
     * @throws JobeException If DeadlineTask class throws an exception when new task is created.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws JobeException {
        Task task = taskList.createDeadlineTask(this.taskDescription, this.deadline);
        storage.saveTasks(taskList);
        ui.showDeadlineResponse(task, taskList);
    }
    
    /**
     * Sets isExit to false.
     *
     * @return boolean false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
