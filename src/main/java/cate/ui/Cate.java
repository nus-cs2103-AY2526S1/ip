package cate.ui;

import cate.command.Command;
import cate.command.CommandManager;
import cate.exception.CateException;
import cate.task.TaskList;
import cate.util.Parser;
import cate.util.Storage;

/**
 * The main entry point for the Cate application.
 * {@code Cate} manages the user interface, command parsing,
 * task storage, and task execution loop.
 */
public class Cate {
    private Ui ui;
    private TaskList tasks;
    private Storage storage;
    private boolean isExit = false;
    private final CommandManager commandManager = new CommandManager();

    /**
     * Constructs a {@code Cate} instance with the given file path
     * for loading and saving tasks.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Cate(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) throws CateException {
        String output = "";
        Command c = Parser.parse(input, this);
        output = c.execute(storage, tasks, ui);
        isExit = c.isExit();
        commandManager.addCommand(c);
        return output;
    }

    public boolean isExit() {
        return isExit;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public static void main(String[] args) {

    }
}
