package paul;

import paul.exception.PaulException;
import paul.parser.Parser;
import paul.task.TaskList;
import paul.storage.Storage;
import paul.ui.Ui;

/**
 * The main class of PAUL.
 */
public class Paul {
    private final Ui ui;
    private TaskList tasks;
    private final Storage storage;
    private final Parser parser;

    /**
     * Constructor for Paul with a file path for task storage.
     * Loads existing tasks from storage, if loading fails, initializes an empty TaskList.
     *
     * @param filePath The path to the file used for saving and loading tasks.
     */
    public Paul(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            tasks = storage.loadTasks();
        } catch (PaulException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs Paul. It reads user input, parses commands, and executes them until the user exits.
     */
    public void run() {
        ui.greetUser();
        boolean isExit = false;

        while (!isExit) {
            try {
                String command = ui.readCommand();
                parser.parse(command, tasks, storage, ui);

                if (command.equalsIgnoreCase("bye")) {
                    isExit = true;
                }
            } catch (PaulException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Paul("data/paul.txt").run();
    }
}