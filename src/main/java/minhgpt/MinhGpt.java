package minhgpt;

import minhgpt.command.Command;
import minhgpt.storage.Storage;
import minhgpt.task.Task;
import minhgpt.task.TaskList;
import minhgpt.ui.Ui;

/**
 * Main logic for the program.
 */
public class MinhGpt {
    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    /**
     * Initialises an instance of MinhGpt.
     *
     * @param isFresh If true, the program will skip reading from memory.
     */
    public MinhGpt(boolean isFresh) {
        Task.initialise();
        Command.initialise();
        this.storage = new Storage();
        this.taskList = isFresh ? new TaskList() : storage.loadTasks();
        this.ui = new Ui();
    }

    /**
     * Returns a response given an user input 'input'.
     */
    public String getResponse(String input) {
        input = input.trim();
        Command cmd = Command.parseCommand(input);
        return cmd.execute(input, taskList, ui, storage);
    }
}
