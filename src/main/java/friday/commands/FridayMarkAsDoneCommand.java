package friday.commands;

import friday.tasklist.FridayTaskList;
import friday.exceptions.UnknownCommandFridayException;
import friday.storage.FridayStorage;
import friday.ui.FridayUi;

/**
 * Represent the command that mark the task as isDone.
 */
public class FridayMarkAsDoneCommand extends FridayCommand {
    public String argument;

    public FridayMarkAsDoneCommand(String argument) {
        this.argument = argument;
    }

    /**
     * Processes the argument String to check if it is valid.
     * @param arg is the command after the keyword.
     * @return a processed command.
     * @throws UnknownCommandFridayException if the argument does not fit the command description.
     */
    public int process(String arg) throws UnknownCommandFridayException {
        if(arg.trim().isEmpty() || arg.trim().matches(".*\\D.*")) {
            throw new UnknownCommandFridayException("You must specify the task number to mark.");
        }

        return Integer.parseInt(arg);
    }
    /**
     * This marks the requested task as isDone.
     * @param taskList the task list.
     * @param storage the Storage app.Friday is using.
     */
    public String execute(FridayTaskList taskList, FridayUi ui, FridayStorage storage)
            throws UnknownCommandFridayException {
            int taskNo = process(this.argument);
            taskList.markTaskAsDone(taskNo);
            storage.writeListToFile(taskList.getList());
            return ui.showTaskHasBeenMarked(taskList.getTask(taskNo));
    }
}
