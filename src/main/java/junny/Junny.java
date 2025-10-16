package junny;

import java.util.ArrayList;

import junny.Ui.Ui;
import junny.command.Command;
import junny.tasks.Task;

/**
 * The main entry point of the Duke program.
 * Initializes necessary components and runs the application.
 */
public class Junny {
    private static Ui ui = new Ui();
    // store in D:\work\CS2103T\ip\data
    private static Storage storage = new Storage("./data/Junny.txt");
    private static ArrayList<Task> tasks = storage.loadAllTasks();
    private static Parser parser = new Parser(ui);
    private boolean lastResponseFalse = false;

    /**
     * Returns the success state of the last processed command.
     *
     * @return true if the last command was valid and executed successfully,
     *         false if the last command caused an error.
     */
    public boolean getLastResponseState() {
        return !lastResponseFalse;  // true if no error
    }

    /**
     * Processes a single user input and returns Junny's response.
     * <p>
     * Used by the GUI layer to display feedback.
     *
     * @param input The raw user command string.
     * @return A response message from Junny after executing the command,
     *         or an error message if the command was invalid.
     */
    public String getResponse(String input) {
        Command command;
        try {
            command = parser.parse(input); // parsing may throw IllegalArgumentException
            lastResponseFalse = false;
        } catch (IllegalArgumentException e) {
            lastResponseFalse = true;
            return e.getMessage(); // return a friendly message to GUI
        }
        assert command != null : "Sorry but I don't understand that command :(";
        if (command == null) { // input was invalid
            lastResponseFalse = true;
            return "OOPS!!! I'm sorry, but I don't know what that means :(";
        }

        try {
            command.run(tasks, ui, storage); // run the command
            return ui.currectMsg(); // return the latest message for GUI
        } catch (IllegalArgumentException e) {
            lastResponseFalse = true;
            return e.getMessage();
        }

    }

    /**
     * Returns the {@link Ui} instance used by Junny.
     *
     * @return The {@code Ui} object for displaying messages and reading commands.
     */
    public Ui getUi() {
        return ui;
    }

    /**
     * Runs the CLI version of Junny.
     * <p>
     * Continuously reads commands from the user, parses them,
     * executes them, and prints the result until an exit command is issued.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        ui.printHi();
        Parser parser = new Parser(ui);

        while (true) {
            // process: get
            String userInput = ui.readCommands();
            Command command = parser.parse(userInput);

            if (command == null) {
                continue; // invalid input handled in parser
            }

            command.run(tasks, ui, storage);

            if (command.isExit()) {
                break;
            }
        }
    }
}
