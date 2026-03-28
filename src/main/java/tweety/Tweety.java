package tweety;

import tweety.commands.Command;
import tweety.commands.Parser;

import tweety.exceptions.TweetyException;

import tweety.storage.Storage;

import tweety.tasks.TaskList;

import tweety.ui.GuiUi;
import tweety.ui.Ui;

/**
 * Main class for the Tweety application.
 * Handles user input and manages tasks including todos, deadlines, and events.
 */
public class Tweety {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    private boolean shouldExit = false;

    /**
     * Constructs a new Tweety application instance.
     * Initialises the user interface, storage, and task list with previously saved tasks.
     */
    public Tweety() {
        this.ui = new GuiUi();
        this.storage = new Storage();
        this.tasks = new TaskList(storage.loadTasks());
        this.parser = new Parser();
    }

    public String getResponse(String input) {
        try {
            Command command = parser.parseCommand(input);

            // Create a temporary UI to capture output for GUI
            GuiUi tempUi = new GuiUi();

            // Execute the command
            command.execute(tasks, tempUi, storage);

            // Check if this command should exit
            shouldExit = command.isExit();

            // Return the response from the UI
            return tempUi.getResponse();

        } catch (TweetyException e) {
            return "Error: " + e.getMessage();
        }
    }

    /**
     * Returns whether the application should exit after the last command.
     *
     * @return True if the application should exit, false otherwise.
     */
    public boolean shouldExit() {
        return shouldExit;
    }

    /**
     * Entry point for the Tweety application.
     * Creates a new Tweety instance and starts the application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new Tweety().run();
    }

    /**
     * Runs the main application loop.
     * Displays welcome message and continuously processes user commands until exit.
     */
    public void run() {

        ui.printWelcomeMessage();

        boolean isExit = false;

        while (!isExit) {
            try {
                String userInput = ui.readCommand();
                Command command = parser.parseCommand(userInput);
                isExit = executeCommand(command);
            } catch (TweetyException e) {
                ui.printErrorMessage(e);
            }
        }

        ui.closeScanner();
    }

    /**
     * Executes the given command and determines if the application should exit.
     * Delegates command execution to the command object and checks for exit condition.
     *
     * @param command The command to execute.
     * @return True if the application should exit after this command, false otherwise.
     * @throws TweetyException If command execution fails.
     */
    private boolean executeCommand(Command command) throws TweetyException {
        command.execute(tasks, ui, storage);
        return command.isExit();
    }
}
