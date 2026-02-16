package ineffa;

import commands.Command;
import commands.Parser;
import ineffaexceptions.IneffaException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Simulates a Personal Assistant Chatbot.
 */
public class Ineffa {
    private Storage storage;
    private TaskList tasks;
    private final Ui ui;
    private boolean isExit = false;

    /**
     * Reads file and updates tasks with file contents.
     *
     * @param filePath file containing task strings.
     */
    public Ineffa(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.loadFileContents());
        } catch (IneffaException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Runs the program.
     */
    private void run() {
        Ui.showWelcome();

        // continuously loops to handle user commands
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.getExit();
            } catch (IneffaException e) {
                ui.showError("Error: " + e.getMessage());
            } catch (NumberFormatException e) {
                ui.showError("Error: Please enter a proper number");
            } catch (Exception e) {
                ui.showError("Unexpected error: " + e.getMessage());
            }
        }
        ui.exit();
    }

    public static void main(String[] args) {
        new ineffa.Ineffa("data/ineffa.txt").run();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        String errorMessage = "";
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (IneffaException e) {
            errorMessage = "Error: " + e.getMessage();
        } catch (NumberFormatException e) {
            errorMessage = "Error: Please enter a proper number";
        } catch (Exception e) {
            errorMessage = "Unexpected error: " + e.getMessage();
        }
        ui.showError(errorMessage);
        return errorMessage;
    }
}
