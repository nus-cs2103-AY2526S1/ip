package friday.commands;

import friday.exceptions.UnknownCommandFridayException;
import friday.storage.FridayStorage;
import friday.tasklist.FridayTaskList;
import friday.ui.FridayUi;

public class FridayUnmarkAsDoneCommand extends FridayCommand {
    public String argument;

    public FridayUnmarkAsDoneCommand(String argument) {
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
            throw new UnknownCommandFridayException("You must specify the task number to unmark.");
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
        if(taskNo > taskList.getNumberOfTasks() + 1) {
            throw new UnknownCommandFridayException(
                    "Sorry, this task does not exist. Try the command \"list\" and mark an existing task on your list.");
        }
        taskList.markTaskAsUndone(taskNo);
        FridayStorage.writeListToFile(taskList.getList());
        return ui.showTaskHasBeenUnmarked(taskList.getTask(taskNo));
    }
}
