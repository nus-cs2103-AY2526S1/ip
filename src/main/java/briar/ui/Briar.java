package briar.ui;

import briar.command.Command;
import briar.exception.BriarException;
import briar.task.TaskList;

/**
 * Represents the chatbot Briar.
 */
public class Briar {
    private Ui ui;

    private TaskList tasks;

    private Storage storage;

    /**
     * Creates a Briar chatbot.
     * @param fileDirectory File path for file containing the tasks to be placed.
     * @param fileName File name for the tasks to be stored in.
     */
    public Briar(String fileDirectory, String fileName) {
        this.storage = new Storage(fileDirectory, fileName);
        ui = new Ui();
        try {
            String taskString = storage.load();
            tasks = new TaskList(taskString);
        } catch (BriarException exception) {
            tasks = new TaskList();
        }
    }

    /**
     * Creates a Briar chatbot with default filePath.
     */
    public Briar() {
        this("./data", "Briar.txt");
    }

    /**
     * Runs the Briar chatbot.
     */
    public void run() {
        ui.showWelcome();
        listen();
        ui.showExit();
    }

    private void listen() {
        boolean isExit = false;
        while (!isExit) {
            try {
                String commandString = "";
                commandString = ui.readCommand();
                Command command = Parser.parse(commandString);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (BriarException exception) {
                ui.showError(exception.getMessage());
            }
        }
    }

    /**
     * Inputs given input string and returns the response from Briar
     * @param input User input string.
     * @return String output response from Briar.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            String response = command.execute(tasks, ui, storage);
            return response;
        } catch (BriarException exception) {
            return ui.showError(exception.getMessage());
        }
    }

    /**
     * Gets Briar to greet the user.
     */
    public String greet() {
        return ui.showWelcome();
    }
}
