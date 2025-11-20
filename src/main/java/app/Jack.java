package app;

import commands.Command;
import exceptions.JackException;
import parser.Parser;
import storage.Storage;
import tasklists.TaskList;
import ui.Ui;

/**
 * The Jack class is the main entry point for the application.
 * It manages the UI, task list, and storage, and runs the main program loop.
 */
public class Jack {

    private final Ui ui;
    private final TaskList taskList;
    private final Storage storage;

    /**
     * Constructs a Jack instance with the given file path for storage.
     *
     * @param filePath The file path to store tasks.
     */
    public Jack(String filePath) {
        ui = new Ui(); // Initialize the UI class
        taskList = new TaskList(); // Initialize the TaskList.TaskList class
        storage = new Storage(filePath); // Initialize the Storage.Storage class

        // Load tasks from storage into the TaskList.TaskList
        storage.loadTasks(taskList);
    }

    /**
     * Runs the main program loop, reading and executing user commands.
     */
    public void run() {
        ui.showWelcome(); // Show welcome message

        while (true) {
            try {
                String fullCommand = ui.readCommand(); // Read the user command
                // Parse the command using Parser.Parser and execute it
                Command c = Parser.parse(fullCommand);
                String response = c.execute(taskList, ui, storage);

                // Print the response for CLI users
                if (response != null && !response.isEmpty()) {
                    System.out.println(response);
                }

                // If this is an exit command, persist tasks and stop the loop
                if (c.isExit()) {
                    storage.saveTasks(taskList);
                    break;
                }
            } catch (JackException e) {
                System.out.println(ui.showError(e.getMessage())); // Show user-friendly error message
            } catch (Exception e) {
                System.out.println(ui.showError(e.getMessage())); // Show error message
            }
        }
    }

    /**
     * Processes user input and returns the response as a String for gui.
     *
     * @param input The user input command.
     * @return The response string.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            String response = c.execute(taskList, ui, storage);

            // Persist tasks if GUI commanded exit
            if (c.isExit()) {
                storage.saveTasks(taskList);
            }

            assert response != null : "Command execution should not return null";
            return response;
        } catch (JackException e) {
            return e.getMessage();
        }
    }

    /**
     * Returns the welcome message from the UI as a String for gui display.
     *
     * @return welcome message string
     */
    public String getWelcomeMessage() {
        return ui.getWelcomeMessage();
    }

    /**
     * The main method to start the application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        // Initialize app.Jack with the file path to store tasks
        new Jack("data/Jack.txt").run(); // Start the application
    }
}
