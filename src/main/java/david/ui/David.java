package david.ui;

import java.io.IOException;

import david.command.Command;
import david.exception.DavidException;

/**
 * The chatbot is called "David" that can process commands.
 */
public class David {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * @param filePath Path name of the file to be overwritten.
     */
    public David(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            storage.init();
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Executes the input lines.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DavidException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            String response = c.executeGui(tasks, ui, storage);
            return response;
        } catch (DavidException e) {
            return "Error: " + e.getMessage();
        }
    }

    public static void main(String[] args) {
        new David("data/David.txt").run();
    }
}
