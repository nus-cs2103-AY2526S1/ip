package friday.commands;

import friday.tasklist.FridayTaskList;
import friday.exceptions.UnknownCommandFridayException;
import friday.storage.FridayStorage;
import friday.ui.FridayUi;

/**
 * Represents the command to show the list of tasks
 */
public class FridayGetListCommand extends FridayCommand {
    /**
     * Executes the command to get the list of task.
     * @param taskList is the current tasklist of the bot.
     * @param ui is the current ui the bot is using.
     * @param storage is the current storage that the bot is using.
     * @throws UnknownCommandFridayException If the task list is empty.
     */
    public String execute(FridayTaskList taskList, FridayUi ui, FridayStorage storage)
            throws UnknownCommandFridayException {
        if(taskList.getList().isEmpty()){
            return "There is no Tasks in the List";
        }
        return ui.showList(taskList);
    }
}
