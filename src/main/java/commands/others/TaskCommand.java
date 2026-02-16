package commands.others;

import commands.Command;
import commands.CommandsEnum;
import ineffaexceptions.IneffaException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * Creates a Task based on Task type.
 */
public class TaskCommand extends Command {
    private CommandsEnum command;
    private String taskInfo;

    /**
     * Creates a task based on command
     *
     * @param command enum type of task to create.
     * @param taskInfo Information of task.
     */
    public TaskCommand(CommandsEnum command, String taskInfo) {
        super(false, command);
        this.command = command;
        this.taskInfo = taskInfo;
    }

    /**
     * Adds task to TaskList.
     *
     * @return Message from executing task.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IneffaException {
        Task task = Task.parseTask(command, taskInfo);
        tasks.add(task);

        String message = "Got it. I've added this task:\n" + task
                + "\n" + "Now you have " + tasks.taskSize() + " tasks in the list.";
        Ui.displayMessage(message);
        return message;
    }
}
