package friday.commands;

import friday.exceptions.UnknownCommandFridayException;
import friday.storage.FridayStorage;
import friday.tasklist.FridayTaskList;
import friday.ui.FridayUi;

import java.util.ArrayList;

/**
 * Represents the command to find matching tasks
 */
public class FridayFindCommand extends FridayCommand {
    private String keyword;

    public FridayFindCommand(String keyword) {
        this.keyword = keyword;
    }

    public String process(String keyword) {
        return keyword.trim();
    }

    /**
     * Executes the command to find the matching task and showing it.
     * @param taskList is the tasklist that the bot is using.
     * @param ui is the ui that the bot is using.
     * @param storage is the storage that the bot is using.
     * @throws UnknownCommandFridayException If no task in the list matches with the user input keyword.
     */
    @Override
    public String execute(FridayTaskList taskList, FridayUi ui, FridayStorage storage)
            throws UnknownCommandFridayException {
        ArrayList<String> matchingResults = taskList.findTasks(process(keyword));
        if(matchingResults.isEmpty()) {
            throw new UnknownCommandFridayException("No task in your List matches with your keyword.");
        }
        return ui.showMatchingResults(matchingResults);
    }
}
