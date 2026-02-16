package betty;

import betty.command.Command;
import betty.exception.BettyException;
import betty.parser.Parser;
import betty.storage.Storage;
import betty.task.TaskList;
import betty.ui.Ui;

/**
 * Represents the Betty object which is the task manager bot application
 * Betty manages the task list by parsing user commands, executing commands, and
 * saving updates to persistent storage
 */
public class Betty {

    private final Ui ui;
    private TaskList taskList;
    private final Storage storage;

    /**
     * Constructs a new Betty chatbot using basic filePath "./data/betty.txt"
     */
    public Betty() {
        this.ui = new Ui();
        this.storage = new Storage("./data/Betty.txt");
        try {
            // Loads storage data file to taskList
            this.taskList = new TaskList(storage.load());
        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
            this.taskList = new TaskList();
        }
    }
    /**
     * Constructs a new Betty chatbot
     * @param filePath filePath for persistent storage of tasks using for storing/loading of task list
     */
    public Betty(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            // Loads storage data file to taskList
            this.taskList = new TaskList(storage.load());
        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
            this.taskList = new TaskList();
        }
    }
    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        String response = "";
        try {
            Command c = Parser.parseCommand(input);
            response = c.execute(this.taskList, this.ui, this.storage);
            return response;
        } catch (BettyException e) {
            return this.ui.printError(e.getMessage());
        }
    }
    public String getGreeting() {
        return this.ui.greeting();
    }
}
