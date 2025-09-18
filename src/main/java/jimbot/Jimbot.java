package jimbot;

import jimbot.command.Command;
import jimbot.command.Commands;
import jimbot.exception.JimbotException;
import jimbot.storage.Storage;
import jimbot.tasktype.TaskList;
import jimbot.ui.UI;

/**
 * Main class for Jimbot application.
 * Initializes UI, storage and tasklist, and includes method for retrieving a response to user inputs.
 *
 * @author limjimin-nus
 */
public class Jimbot {
    private final Storage userStorage;
    private final TaskList userList;
    private final UI user;
    private Command commandType;

    /**
     * Initializes a new Jimbot instance.
     * Loads user data from the specified file.
     *
     * @param filePath Path to the file used for storing and loading user data.
     */
    public Jimbot(String filePath) {
        user = new UI();
        userStorage = new Storage(filePath);
        userList = userStorage.load();
    }

    /**
     * Returns a response according to the raw user input string.
     *
     * @param userInput Raw user input string
     * @return Appropriate response from UI.
     */
    public String getResponse(String userInput) {
        try {
            commandType = Commands.fromString(userInput);

            return commandType.execute(userList, userStorage, user);
        } catch (JimbotException e) {
            return e.getMessage();
        }
    }

    public Command getCommandType() {
        return commandType;
    }
}
