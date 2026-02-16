package commands.task;

import commands.Command;
import commands.CommandsEnum;
import ineffaexceptions.IneffaException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Task when user wants to exit program
 */
public class ExitCommand extends Command {
    /**
     * Instantiates command by telling super class that this is exit command.
     */
    public ExitCommand() {
        super(true, CommandsEnum.BYE);
    }

    /**
     * Writes tasks to text file.
     *
     * @return Message from executing task
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IneffaException {
        storage.writeToFile(tasks); // update file with updated tasks
        return Ui.getExitMessage();
    }
}
