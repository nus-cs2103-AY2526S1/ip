package burgerburglar;


/**
 * The main class of the BurgerBurglar application.
 * <p>
 * Responsible for initializing the UI, loading tasks from storage,
 * and running the main program loop.
 */
public class BurgerBurglar {
    /** Default file path for saving and loading tasks. */
    private static final String DEFAULT_FILE_PATH = "data/burgerburglar.txt";
    private final Ui ui;
    private final TaskList tasks;
    private final Storage storage;

    /**
     * Constructs a new BurgerBurglar instance.
     *
     * @param filePath The path of the file to load/save tasks.
     */
    public BurgerBurglar(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = initializeTasks();
    }

    public BurgerBurglar() {
        this(DEFAULT_FILE_PATH);
    }

    /**
     * Initializes the task list by attempting to load from storage.
     */
    private TaskList initializeTasks() {
        try {
            return storage.load();
        } catch (BurgerException e) {
            ui.showError("Could not load saved tasks. Starting with empty list.");
            return new TaskList();
        }
    }

    /**
     * Starts the main program loop.
     */
    public void run() {
        showGreeting();

        boolean isExit = false;
        while (!isExit) {
            isExit = processNextCommand();
        }
    }

    /**
     * Reads and executes the next user command.
     *
     * @return true if the command signals exit, false otherwise
     */
    private boolean processNextCommand() {
        String input = ui.readCommand();
        try {
            Command command = Parser.parse(input);
            command.execute(tasks, ui, storage);
            return command.isExit();
        } catch (BurgerException e) {
            ui.showError(e.getMessage());
            return false;
        }
    }


    /**
     * Entry point of the application.
     *
     * @param args Command line arguments (unused)
     */
    public static void main(String[] args) {
        new BurgerBurglar().run();
    }

    /**
     * Provides a response for GUI integration.
     *
     * @param input user command
     * @return program response as a string
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(tasks, ui, storage);
        } catch (BurgerException e) {
            return e.getMessage();
        }
    }

    public String showGreeting() {
        return "GOODDAY, GOODBURGER.\nIF YOU NEED HELP, SAY HELP.";
    }
}
