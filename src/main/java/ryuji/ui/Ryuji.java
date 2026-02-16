package ryuji.ui;

import ryuji.command.Command;
import ryuji.storage.Storage;
import ryuji.task.TaskList;

/**
 * The {@code Ryuji} class serves as the main entry point for the Ryuji chatbot application.
 * <p>This class is responsible for initializing the user interface, task list, storage, and parser.
 * It manages the core logic of the chatbot by reading user input in a loop, parsing commands,
 * and executing them accordingly.
 * It interacts with the task list to modify tasks, and it communicates with the user through the user interface.</p>
 */
public class Ryuji {
    private Parser parser = new Parser();
    private Storage storage;
    private TaskList tasks;
    private Ui ui = new Ui();
    private String response;

    /**
     * Constructs a {@code Ryuji} instance with the specified file path for storage.
     * <p>This constructor initializes the storage using the provided file path.
     * It attempts to read and load existing tasks from the file.
     * If loading the tasks fails (e.g., due to an invalid file format or missing file),
     * it initializes an empty task list and notifies the user of the failure.</p>
     *
     * @param filePath the file path to the storage file where tasks are saved
     */
    public Ryuji(String filePath) {
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(this.storage.readFile());
        } catch (Exception e) {
            this.ui.showFailuretoLoadTaskListError();
            this.tasks = new TaskList();
        }
    }

    /**
     * Constructs a {@code Ryuji} instance with a default storage file ("tasks.csv").
     * <p>If no file path is provided, this constructor will attempt to load tasks from the default storage file.
     * If loading fails, it initializes an empty task list and notifies the user.</p>
     */
    public Ryuji() {
        this.storage = new Storage("tasks.csv");
        try {
            this.tasks = new TaskList(this.storage.readFile());
        } catch (Exception e) {
            this.ui.showFailuretoLoadTaskListError();
            this.tasks = new TaskList();
        }
    }

    /**
     * Starts the chatbot application.
     * <p>This method displays a welcome message, then enters a loop to continuously read, parse,
     * and execute user commands until the exit command is received.
     * It handles error messages, displays feedback to the user,
     * and ensures that the user can interact with the chatbot until they choose to exit.</p>
     */
    public void run() {
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = parser.parse(fullCommand);
                response = c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Generates a response for the user's chat message.
     * <p>This method is invoked when the user sends an input to the chatbot in the GUI.
     * It parses the input, executes the corresponding command, and returns the chatbot's response.
     * It also handles any exceptions that occur during the parsing or command execution.</p>
     *
     * @param input the user input string
     * @return the chatbot's response as a string
     */
    public String getResponse(String input) {
        try {
            Command c = parser.parse(input);
            return response = c.execute(tasks, ui, storage);
        } catch (Exception e) {
            return response = ui.showError(e.getMessage());
        }
    }

    public String getFilePathForCurrentStorage() {
        return storage.getFilePath();
    }

    /**
     * Main method that launches the Ryuji chatbot application.
     * <p>This method is the entry point when running the application.
     * It initializes the {@code Ryuji} instance with the default storage
     * file "tasks.csv" and starts the chatbot by calling the {@code run} method.</p>
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        new Ryuji("tasks.csv").run();
    }
}
