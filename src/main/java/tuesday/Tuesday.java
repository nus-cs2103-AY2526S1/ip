package tuesday;

import java.io.File;
import java.io.FileNotFoundException;

import tuesday.command.Command;
import tuesday.exception.TuesdayException;
import tuesday.parser.Parser;
import tuesday.storage.Storage;
import tuesday.task.TaskList;
import tuesday.ui.Ui;


/**
 * Main entry point for Tuesday chatbot
 * Initializes UI, storage, and task list, and runs the program loop.
 */

public class Tuesday {

    private static final String STORAGE_URL = "/data/tuesday.txt";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isExit;

    /**
     * Construct a Tuesday application using the given filepath
     * Load the tasks from the filepath storage file available
     * @param filePath
     */
    public Tuesday(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        isExit = false;
        try {
            tasks = new TaskList(storage.loadData());
        } catch (FileNotFoundException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        } catch (Exception e) {
            ui.showError("Failed to load data: " + e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Entry point of application
     * Create the required data directory if required
     * @param args
     */
    public static void main(String[] args) {
        // Load data from data/tuesday.txt to list
        String projectPath = System.getProperty("user.dir");
        String filePath = projectPath + STORAGE_URL;

        File file = new File(filePath);
        File parent = file.getParentFile();
        if (!parent.exists() && !parent.mkdirs()) {
            System.out.println("Failed to create directory: " + parent.getAbsolutePath());
        }

        System.out.println("Loading from: " + filePath);
        new Tuesday(filePath).run();
    }

    /**
     * Run the chatbot with welcome message
     * Parse user command until exit command
     * Print on terminal only
     */
    public void run() {
        ui.showWelcomeMessage();
        while (!this.isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                this.isExit = c.isExit();
                System.out.println(this.isExit);
            } catch (TuesdayException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Return the response as a String
     * @param input
     * @return String as response
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            String response = c.getResponse(tasks, ui, storage);
            this.isExit = c.isExit();
            return response;
        } catch (TuesdayException e) {
            ui.showError(e.getMessage());
            return e.getMessage();
        }
    }

    /**
     *
     * @return isExit
     */
    public boolean isExit() {
        return isExit;
    }

}
