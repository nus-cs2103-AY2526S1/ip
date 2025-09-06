package paul;

import paul.exception.PaulException;
import paul.parser.Parser;
import paul.storage.Storage;
import paul.task.TaskList;
import paul.ui.Ui;

/**
 * The main class of PAUL.
 */
public class Paul {
    private final Ui ui;
    private TaskList tasks;
    private final Storage storage;
    private final Parser parser;
    private String commandType;

    /**
     * Constructor for Paul with a file path for task storage.
     * Loads existing tasks from storage, if loading fails, initializes an empty TaskList.
     *
     * @param filePath The path to the file used for saving and loading tasks.
     */
    public Paul(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            tasks = storage.loadTasks();
        } catch (PaulException e) {
            System.out.println(ui.showLoadingError());
            tasks = new TaskList();
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            commandType = Parser.getCommandType(input).toString();
            return parser.parseAndExecute(input, tasks, storage, ui);
        } catch (PaulException e) {
            return "Error: " + e.getMessage();
        }
    }

    public String getCommandType() {
        return commandType;
    }

    public Ui getUi() {
        return ui;
    }
}
