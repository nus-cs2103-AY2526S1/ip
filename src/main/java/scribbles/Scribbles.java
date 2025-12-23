package scribbles;

import scribbles.command.Command;
import scribbles.exception.ScribblesException;
import scribbles.parser.Parser;
import scribbles.storage.Storage;
import scribbles.tasklist.TaskList;
import scribbles.ui.Ui;

/**
 * Provides the main entry to run and exit Scribbles
 */
public class Scribbles {

    private final Storage storage;
    private TaskList taskList;

    /**
     * Initialises the storage and task list of Scribbles.
     */
    public Scribbles() {
        this.storage = new Storage();
        try {
            this.taskList = new TaskList(storage.loadFile());
        } catch (ScribblesException e) {
            Ui.displayError(e.getMessage());
            this.taskList = new TaskList();
        }
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parseCommand(input);
            String commandOutput = c.execute(this, this.taskList, this.storage);
            assert (!commandOutput.isEmpty());
            return commandOutput;
        } catch (ScribblesException e) {
            return e.getMessage();
        }
    }
}
