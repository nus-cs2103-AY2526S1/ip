package eureka;

import eureka.command.Command;
import eureka.task.TaskList;
import eureka.ui.Ui;

/**
 * The main entry point of the Eureka chatbot application.
 * <p>
 * The {@code Eureka} class initializes the core components:
 * {@link TaskList} for managing tasks, {@link Storage} for file persistence,
 * and {@link Ui} for user interaction. It handles the program flow by reading
 * user commands, parsing them into {@link Command} objects, executing them,
 * and determining when to exit.
 */
public class Eureka {

    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    /**
     * Constructs a new {@code Eureka} instance with the given file path.
     * <p>
     * Loads existing tasks from storage if available, otherwise starts
     * with an empty task list.
     *
     * @param filePath the path to the file used for task storage
     */
    public Eureka(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main program loop.
     * <p>
     * Displays a welcome message, repeatedly reads user input,
     * parses it into a {@link Command}, executes it, and stops
     * when an exit command is issued.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Returns a placeholder response for GUI integration.
     *
     * @param input the user input
     * @return a simple echo-style response
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }

    /**
     * Gets the current {@link TaskList}.
     *
     * @return the task list
     */
    public TaskList getTasks() {
        return tasks;
    }

    /**
     * Gets the {@link Storage} instance.
     *
     * @return the storage manager
     */
    public Storage getStorage() {
        return storage;
    }

    /**
     * Gets the {@link Ui} instance.
     *
     * @return the user interface manager
     */
    public Ui getUi() {
        return ui;
    }

    /**
     * The main entry point of the program.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        new Eureka("data/record.txt").run();
    }
}
