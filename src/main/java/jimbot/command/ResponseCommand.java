package jimbot.command;

import jimbot.storage.Storage;
import jimbot.tasktype.TaskList;
import jimbot.ui.UI;

/**
 * Represents a command to handle unrecognized or generic user input.
 * This command delegates the response to the UI component.
 *
 * @author limjimin-nus
 */
public class ResponseCommand implements Command {
    private final String userInput;

    /**
     * Constructs a ResponseCommand with the given user input.
     *
     * @param userInput Raw user input to be processed.
     */
    public ResponseCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Executes the command by generating a response for unrecognized input.
     *
     * @param userList The current list of tasks.
     * @param userStorage The storage handler for persisting task changes.
     * @param user The UI component used to generate a response.
     * @return A string containing the response to the user input.
     */
    @Override
    public String execute(TaskList userList, Storage userStorage, UI user) {
        return user.respond(userInput);
    }
}
