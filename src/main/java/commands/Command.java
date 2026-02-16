package commands;

import core.TaskList;
import util.Storage;
import util.TronaldDumpException;

/**
 * Represents a command that can be executed by the TronaldDump chatbot.
 */
public abstract class Command {
    protected TaskList taskList;
    protected Storage storage;

    /**
     * Constructor for Command class.
     * @param taskList The task list to operate on
     * @param storage The storage utility for persistence
     */
    public Command(TaskList taskList, Storage storage) {
        this.taskList = taskList;
        this.storage = storage;
    }

    public abstract void execute(String input, String[] parts) throws TronaldDumpException;

    public abstract boolean isExit();
}
