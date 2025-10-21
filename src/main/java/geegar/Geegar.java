
package geegar;

import geegar.command.Command;
import geegar.exception.GeegarException;
import geegar.gui.Gui;
import geegar.parser.Parser;
import geegar.storage.Storage;
import geegar.task.TaskList;
import geegar.ui.Ui;
import javafx.application.Platform;

/**
 * This is the main class
 */
public class Geegar {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates an instance of a geegar object based on the filepath
     * @param filePath
     */
    public Geegar(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (GeegarException e) {
            ui.printLoadingError();
            tasks = new TaskList();
        }
    }


    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        if (input.trim().isEmpty()) {
            return "Please enter a command!";
        }

        try {
            Command command = Parser.parse(input);
            Gui gui = new Gui();

            command.execute(tasks, gui, storage);

            if (command.isExit()) {
                gui.printGoodbye();
                String farewell = gui.getResponse();

                Platform.runLater(() -> Platform.exit());
                return farewell;
            }

            return gui.getResponse();

        } catch (GeegarException e) {
            Gui gui = new Gui();
            gui.printError(e.getMessage());
            return gui.getResponse();
            // return "Error!!: " + e.getMessage();
        }
    }

    public String getWelcomeMessage() {
        Gui gui = new Gui();
        gui.printIntroduction();
        return gui.getResponse();
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }


}