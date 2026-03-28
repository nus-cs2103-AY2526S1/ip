package karl;

import javafx.application.Platform;
import karl.command.Command;
import karl.exception.KarlException;
import karl.parser.Parser;
import karl.storage.Storage;
import karl.task.TaskList;
import karl.ui.Ui;

/**
 * Main chatbot class for backend logic, suitable for GUI use.
 */
public class Karl {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isExit;
    private String commandType;

    public Karl(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (KarlException e) {
            tasks = new TaskList();
        }
        isExit = false;
    }

    /**
     * Gets chatbot's response to a string input, for GUI use.
     * @param input user input string
     * @return chatbot's reply as text
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            command.execute(tasks, ui, storage); // may update state
            commandType = command.getClass().getSimpleName();
            if (command.isExit()) {
                Platform.exit();
            }
            return ui.getLastMessage(); // You need to support text response with Ui
        } catch (KarlException e) {
            commandType = "";
            return "Oh no! " + e.getMessage();
        }
    }

    public Ui getUi() {
        return ui;
    }

    /**
     * Returns the type of the last command executed.
     * @return command type name
     */
    public String getCommandType() {
        return commandType;
    }
}
