package yuan;

import javafx.application.Platform;
import yuan.parser.Parser;
import yuan.storage.Storage;
import yuan.tasklist.TaskList;
import yuan.ui.UI;

/**
 * The main entry point of the chatbot application.
 * Yuan is a task manager that allows users to add, list, mark,
 * unmark, remove tasks in the task manager.
 */
public class Yuan {
    private static final Storage storage = new Storage("./data/yuan.txt");
    private static final TaskList taskList = storage.load();
    private static final UI ui = new UI();

    /**
     * Generates a response for the user's chat message for GUI.
     *
     * @param input user input string
     * @return response string for GUI
     */
    public String getResponse(String input) {
        return executeCommand(input);
    }

    /**
     * Executes a command entered by the user (GUI).
     *
     * @param input user input string
     * @return response string
     */
    public String executeCommand(String input) {
        return Parser.parseAndExecute(input, taskList, storage, ui);
    }

    /**
     * Entry point for CLI mode.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        ui.showWelcome();

        String input;
        while (true) {
            input = ui.readCommand();
            String response = Parser.parseAndExecute(input, taskList, storage, ui);

            if (input.trim().equals("bye")) {
                ui.showBye();
                Platform.exit(); // for CLI consistency with GUI
                break;
            }

            ui.showMessage(response);
        }
    }
}
