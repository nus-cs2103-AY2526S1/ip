package friday.commands;

import friday.tasklist.FridayTaskList;
import friday.exceptions.UnknownCommandFridayException;
import friday.storage.FridayStorage;
import app.Friday;
import friday.ui.FridayUi;

public class FridayExitCommand extends FridayCommand {
    /**
     * Executes the Exit command for the bot
     * @param taskList is the current tasklist the bot is using.
     * @param ui is the current ui the bot is using.
     * @param storage is the current storage the bot is using.
     * @throws UnknownCommandFridayException If the command is not one of the known command.
     */
    public String execute(FridayTaskList taskList, FridayUi ui, FridayStorage storage)
            throws UnknownCommandFridayException {
        return ui.showGoodbye();
    }
}
