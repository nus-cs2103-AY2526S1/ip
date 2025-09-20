package ming.app;

import ming.command.Command;
import ming.exception.MingException;
import ming.model.TaskList;
import ming.parser.Parser;
import ming.storage.Storage;
import ming.ui.Ui;

/**
 * The main class of the Ming application.
 * It initializes the application and starts the command loop.
 */
public class Ming {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private String commandType;

    /**
     * Constructs a Ming application with the specified file path for storage.
     *
     * @param filePath The file path where previously created tasks are stored.
     */
    public Ming(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (MingException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Starts the command loop of the application.
     * It reads user commands, executes them, and handles any exceptions.
     */
    public void run() {
        // Assert internal state consistency
        assert tasks != null;
        assert ui != null;
        assert storage != null;

        System.out.println(ui.showWelcome());
        System.out.println(ui.showLine());
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                String response = c.execute(tasks, ui, storage);
                System.out.println(response); // Print the response returned by execute
                System.out.println(ui.showLine());
                isExit = c.isExit();
            } catch (MingException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Processes user input and returns the response as a String for GUI.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            String response = c.execute(tasks, ui, storage);
            commandType = c.getType();

            assert response != null : "Command execution should not return null";
            return response;
        } catch (MingException e) {
            return e.getMessage();
        }
    }

    public static void main(String[] args) {
        new Ming("data/ming.txt").run();
    }

    public String getCommandType() {
        return commandType;
    }
}