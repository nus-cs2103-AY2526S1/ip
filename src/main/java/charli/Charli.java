package charli;

import charli.command.Command;
import charli.exception.CharliException;
import charli.util.Parser;
import charli.util.Storage;
import charli.util.TaskList;
import charli.util.Ui;

/**
 * The main class for the Charli chatbot application.
 * Charli is a task management chatbot that helps users track songs, deadlines, and events.
 * It supports commands for adding, deleting, marking, and finding tasks.
 *
 * @since A-MoreOOP
 */
public class Charli {

    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    /**
     * Constructs a new Charli instance with the specified file path for data storage.
     * Initializes the UI, storage, and loads existing tasks from file.
     */
    public Charli() {
        ui = new Ui();
        storage = new Storage("./data/charli.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }

    }

    /**
     * Runs the main chatbot loop, processing user commands until exit.
     * Handles command parsing, execution, and error handling.
     */
    public void run() {

        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (CharliException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Charli().run();
    }

    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(tasks, ui, storage);
        } catch (CharliException e) {
            return e.getMessage();
        }
    }
}


