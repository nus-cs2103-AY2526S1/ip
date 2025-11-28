package nailongbot;

import nailongbot.command.Command;
import nailongbot.utils.Parser;
import nailongbot.utils.Storage;
import nailongbot.utils.TaskList;
import nailongbot.utils.Ui;

/**
 * The main chatbot application class that coordinates all components.
 * Supports both console and GUI modes.
 */
public class NaiLong {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    /**
     * Constructor for the chatbot.
     */
    public NaiLong() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.tasks = new TaskList(storage.loadTasks());
        this.parser = new Parser();
    }
    /**
     * Parses the input from the user and results in its execution
     *
     * @param input the command that the user has typed and entered
     * @return a formatted string message confirming that the deadline was added
     * @throws Exception if there is an error while executing command "input"
     */
    public String executeCommand(String input) {
        assert input != null && !input.isBlank(); // if input is null or blank, result in error

        try {
            Command command = Parser.parse(input);
            return command.execute(tasks, ui, storage);
        } catch (Exception e) {
            String result = Ui.LINE + "Something went wrong!\n" + e.getMessage() + "\n" + Ui.LINE;
            return result;
        }
    }

    public TaskList getTasks() {
        return tasks;
    }

    public Ui getUi() {
        return ui;
    }

    public Storage getStorage() {
        return storage;
    }

    public String getResponse(String input) {
        return executeCommand(input);
    }
}
