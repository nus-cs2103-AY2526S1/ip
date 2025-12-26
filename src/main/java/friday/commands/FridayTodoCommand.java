package friday.commands;

import friday.exceptions.UnknownCommandFridayException;
import friday.storage.FridayStorage;
import friday.tasklist.FridayTaskList;
import friday.ui.FridayUi;

public class FridayTodoCommand extends FridayCommand {
    public String argument;

    public FridayTodoCommand(String argument) {
        this.argument = argument;
    }

    /**
     * Processes the argument String to check if it is valid.
     * @param arg is the command after the keyword.
     * @return a processed command.
     * @throws UnknownCommandFridayException if the argument does not fit the command description.
     */
    public String process(String arg) throws UnknownCommandFridayException {
        if(arg.trim().isEmpty()) {
            throw new UnknownCommandFridayException("Description of a Todo task cannot be empty!");
        }
        return arg;
    }

    public String execute(FridayTaskList taskList, FridayUi ui, FridayStorage storage)
            throws UnknownCommandFridayException {
        String processArgument = process(this.argument);

        String[] parts = processArgument.split(" #", 2);

        String argument = parts[0];
        String tag ="";

        if(parts.length < 2 || parts[1].trim().isEmpty()) {
            tag = "";
        } else {
            tag = " #" + parts[1];
        }



        taskList.addTodoTask(argument, tag);
        FridayStorage.writeListToFile(taskList.getList());
        return ui.showTaskHasBeenAdded(taskList.getTask(taskList.getNumberOfTasks()),taskList);
    }
}
