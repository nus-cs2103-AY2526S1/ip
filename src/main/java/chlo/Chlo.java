package chlo;

import chlo.command.Command;
import chlo.exception.ChloException;
import chlo.storage.Storage;
import chlo.task.TaskList;
import chlo.ui.Parser;
import chlo.ui.Ui;


/**
 * Chlo class that calls command executions
 */
public class Chlo {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private String commandType;

    /**
     * Chlo constructor
     * @param filePath
     */
    public Chlo(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (ChloException e) {
            tasks = new TaskList();
        }
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parseInput(input);
            c.execute(tasks, ui, storage);
            this.commandType = c.getClass().getSimpleName();
            return c.getString();
        } catch (ChloException e) {
            return "Error: " + e.getMessage();
        }
    }

    public String getCommandType() {
        return this.commandType;
    }
}



