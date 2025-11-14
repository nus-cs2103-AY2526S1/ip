package monarch;

import monarch.core.Parser;
import monarch.core.Storage;
import monarch.core.TaskList;
import monarch.core.Ui;
import monarch.exceptions.MonException;

/**
 * Represents the main Chat bot.
 * It is started on running the file.
 */
public class Monarch {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for the Monarch chatbot object.
     *
     * @param filePath The path to a text file.
     */
    public Monarch(String filePath) {
        storage = new Storage();
        storage.set(filePath);
        tasks = new TaskList();
        ui = new Ui();
        try {
            tasks.set(storage.load());
        } catch (MonException e) {
            tasks.set();
        }
    }

    /**
     * Starts the main program of the Chat bot.
     *
     * @param userInput The command to execute.
     */
    public String run(String userInput) {
        try {
            Parser parser = new Parser(userInput);
            return parser.getResult();
        } catch (MonException e) {
            return e.getMessage();
        }
    }

    /**
     * Returns a greeting message.
     */
    public String greet() {
        return String.format("%s", ui.greeting());
    }

}
