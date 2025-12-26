package friday.commands;

import friday.exceptions.UnknownCommandFridayException;
import friday.storage.FridayStorage;
import friday.tasklist.FridayTaskList;
import friday.ui.FridayUi;

public class FridayUnknownCommand extends FridayCommand {
    /**
     * Execute the a command when an unknown command is inserted.
     * @param taskList is the tasklist the bot is using.
     * @param ui is the ui that the bot is using.
     * @param storage is the storage the bot is using.
     * @throws UnknownCommandFridayException It will always throw this exception as an
     * unknown command is passed
     */
    public String execute(FridayTaskList taskList, FridayUi ui, FridayStorage storage)
            throws UnknownCommandFridayException {
        throw new UnknownCommandFridayException("Oops I don't know what you mean by that. " +
                "Try using the commands like \"todo\" instead!");
    }
}
