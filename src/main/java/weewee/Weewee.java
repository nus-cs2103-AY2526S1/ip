package weewee;

import weewee.exception.WeeweeException;
import weewee.parser.CommandParser;
import weewee.storage.Storage;
import weewee.task.TaskList;
import weewee.ui.Ui;

/**
 * The main entry point for the Weewee chatbot application.
 * This class initializes the UI, storage, and task list components.
 * It manages the main program loop: reading user commands,
 * executing them, handling errors, and saving tasks upon exit.
 */
public class Weewee {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private CommandParser.Command commandType;

    /**
     * Creates a new instance of the chatbot with a file path for storing tasks.
     *
     * @param filePath String of the saved file path.
     */
    public Weewee(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = storage.load();
    }

    /**
     * Starts the chatbot loop and terminate after bye command
     */
    public void run() {
        System.out.print(ui.showGreet());
        String input = ui.readNextCommand();

        while (!input.equals("bye")) {
            try {
                System.out.print(CommandParser.parseAndExecute(input, tasks, ui));
            } catch (WeeweeException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.print(ui.showError());
            }
            input = ui.readNextCommand();
        }
        storage.save(tasks);
        System.out.print(ui.showBye());
    }

    /**
     * Generates a response for the user's chat message.
     *
     * @param input The user's input string.
     * @return The chatbot's response as a string.
     */
    public String getResponse(String input) {
        if (input.trim().equalsIgnoreCase("bye")) {
            this.commandType = CommandParser.getCommand(input);
            storage.save(tasks);
            return ui.showBye();
        }
        try {
            this.commandType = CommandParser.getCommand(input);
            // Use parser to process command and return output instead of printing
            return CommandParser.parseAndExecute(input, tasks, ui);
        } catch (WeeweeException e) {
            return e.getMessage();
        } catch (Exception e) {
            return ui.showError();
        }
    }

    public CommandParser.Command getCommandType() {
        return commandType;
    }

    /**
     * Application entry point.
     *
     * @param args Command line arguments (unused).
     */
    public static void main(String[] args) {
        new Weewee("./data/weewee.txt").run();
    }
}
