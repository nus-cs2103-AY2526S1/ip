package friday.commands;

import friday.storage.FridayStorage;
import friday.tasklist.FridayTaskList;
import friday.exceptions.UnknownCommandFridayException;
import friday.ui.FridayUi;

/**
 * Represents the commands to be executed
 */
public abstract class FridayCommand {
    public void process() {
        return;
    }
    public String execute(FridayTaskList taskList, FridayUi ui, FridayStorage storage)
            throws UnknownCommandFridayException {
        return "";
    }
}
