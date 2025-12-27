package fatty;

import fatty.command.Command;

/**
 * Main logic for Fatty
 */
public class Fatty {
    /** File path to load and save taskList */
    private static final String TASK_FILE = "./data/fattyTasks.txt";
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;
    private String startupMessage;
    private boolean isExit = false;

    public Fatty() {
        this(TASK_FILE);
    }

    /**
     * Creates instance of Fatty to be fed into GUI
     * @param filePath default filepath for storing TaskFile
     */
    public Fatty(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.loadTasks());
            startupMessage = ui.showWelcome();

        } catch (FattyException e) {
            startupMessage = ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * First message from Fatty upon start up
     * @return Either Welcome message or Error message from loading Tasks
     */
    public String getStartupMessage() {
        return startupMessage;
    }

    /**
     * Handles a single user input and returns Fatty's response.
     *
     * @param input the user input
     * @return Fatty's response as a String
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            String output = c.execute(tasks, ui, storage);
            isExit = c.isExit();
            return output;

        } catch (FattyException e) {
            return e.getMessage();
        }
    }

    public boolean shouldExit() {
        return isExit;
    }

}


