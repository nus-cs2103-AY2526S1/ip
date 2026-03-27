package jaiden.ui;

import jaiden.command.Command;
import jaiden.command.CommandType;
import jaiden.exception.JaidenException;
import jaiden.storage.Storage;
import jaiden.task.TaskList;

/**
 * Main class for Jaiden.
 */
public class Jaiden {
    private final Storage storage;
    private TaskList tasks;
    private CommandType commandType;
    private boolean hasLoadError = false;

    /**
     * Constructor for Jaiden.
     *
     * @param filePath File path to save data in txt format.
     */
    public Jaiden(String filePath) {
        assert filePath != null;
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(this.storage.load());
        } catch (JaidenException e) {
            this.hasLoadError = true;
            this.tasks = new TaskList();
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        assert input != null;
        try {
            Command c = Parser.parse(input.split(" "));
            c.execute(this.tasks, this.storage);
            this.commandType = c.getCommandType();
            assert this.commandType != null;
            return c.getString();
        } catch (JaidenException e) {
            this.commandType = CommandType.ERRORCOMMAND;
            return e.getMessage();
        }
    }

    /**
     * Gets command type.
     *
     * @return Command type.
     */
    public CommandType getCommandType() {
        return this.commandType;
    }

    /**
     * Checks if Jaiden has load error.
     *
     * @return true if Jaiden has load error, false otherwise.
     */
    public boolean hasLoadError() {
        return this.hasLoadError;
    }
}
