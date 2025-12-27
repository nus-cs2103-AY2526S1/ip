package smartstelle;

import java.io.IOException;
import java.util.Objects;

/**
 * The SmartStelle class is the main entry point for the SmartStelle task
 * management application.
 * It handles initialization of the Storage, TaskList, and Ui components,
 * and manages the main program loop for reading and executing user commands.
 */
public class SmartStelle {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a SmartStelle application instance with a specified file path for
     * storage.
     * <p>
     * Initializes the UI, Storage, and TaskList. If the storage file cannot be
     * read,
     * starts with an empty TaskList.
     *
     * @param filePath The file path for storing task data.
     */
    public SmartStelle(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (StelleException | IOException e) {
            System.out.println("Starting fresh (couldn't read save file).");
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main loop of the SmartStelle application.
     * <p>
     * Continuously reads user commands via the UI, parses and executes them using
     * the Parser,
     * until the user inputs "bye" to exit.
     * Errors encountered during command execution are displayed to the user.
     */
    public void run() {
        ui.showWelcome();

        while (true) {
            String input = ui.readCommand();
            try {
                if (Objects.equals(Parser.parse(input, tasks, storage, ui), "bye")) {
                    break;
                }
            } catch (IOException | StelleException e) {
                ui.showError(e.getMessage());
            }
        }

        ui.showGoodbye();
    }

    public String getResponse(String input) {
        try {
            return Parser.parse(input, tasks, storage, ui);
        } catch (IOException | StelleException e) {
            return e.getMessage();
        }
    }
}
