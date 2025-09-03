import java.io.IOException;
import java.util.ArrayList;

import evansbot.Exceptions.EvansBotException;
import evansbot.command.Command;
import evansbot.task.Storage;
import evansbot.task.Task;
import evansbot.task.TaskList;
import evansbot.ui.Parser;
import evansbot.ui.Ui;

/**
 * Main class for the EvansBot application.
 * Initializes the UI, storage, and task list, and runs the main command loop.
 */
public class EvansBot {
    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;

    /**
     * Constructs an EvansBot instance with the specified storage file path.
     * Attempts to load tasks from the file; if loading fails, starts with an empty list.
     *
     * @param filePath Path to the storage file.
     */
    public EvansBot(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            ArrayList<Task> loadedTasks = storage.load();
            tasks = new TaskList(storage, loadedTasks);
        } catch (IOException e) {
            ui.showError("Could not load save file, starting with empty list.");
            tasks = new TaskList(storage);
        }
    }

    /**
     * Runs the main EvansBot command loop.
     * Reads user input, parses it into commands, executes them, and handles exceptions.
     * The loop continues until an exit command is issued.
     */
    private void run() {
        ui.greet();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand(); // read user input
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (EvansBotException e) {
                ui.showError(e.getMessage());
            }
        }

        ui.close(); // close scanner
    }

    /**
     * Main entry point of the EvansBot application.
     * Creates an EvansBot instance and runs it.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new EvansBot("./data/evansbot.txt").run();
    }

}
