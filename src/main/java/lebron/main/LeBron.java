package lebron.main;

import lebron.command.Command;
import lebron.common.Constants;
import lebron.exception.LeBronException;
import lebron.task.TaskList;

/**
 * The main class for the LeBron application.
 * It initializes the application components and starts the command loop.
 */
public class LeBron {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    /**
     * Constructs a LeBron application with the specified file path for storage.
     * Initializes the UI, storage, and task list components.
     * If loading tasks from storage fails, initializes an empty task list.
     *
     * @param filePath The file path for storing tasks.
     */
    public LeBron(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = getTaskList();
    }
    public LeBron() {
        this(Constants.DEFAULT_FILE_PATH);
    }

    /**
     * Loads the task list from storage.
     * If loading fails, returns an empty task list and shows a loading error message.
     *
     * @return The loaded TaskList or an empty TaskList if loading fails.
     */
    private TaskList getTaskList() {
        TaskList taskList;
        try {
            taskList = new TaskList(storage.load());
        } catch (LeBronException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
        return taskList;
    }

    /**
     * The main method to start the LeBron application.
     */
    public static void main(String[] args) {
        new LeBron(Constants.DEFAULT_FILE_PATH).run();
    }

    /**
     * Starts the command loop for the LeBron application.
     * Displays a welcome message and processes user commands until exit.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            isExit = processNextCommand();
        }
    }
    /**
     * Processes the next user command.
     * Reads the command, parses it, executes it, and shows the response.
     * Catches and displays any exceptions that occur during processing.
     *
     * @return true if the command is an exit command, false otherwise.
     */
    private boolean processNextCommand() {
        try {
            String fullCommand = ui.readCommand();
            Command c = Parser.parse(fullCommand);
            String response = c.execute(tasks, ui, storage);
            ui.showMessage(response);
            return c.isExit();
        } catch (LeBronException e) {
            ui.showError(e.getMessage());
        }
        return false;
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (LeBronException e) {
            return e.getMessage();
        }
    }
}

