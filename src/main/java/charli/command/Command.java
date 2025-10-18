package charli.command;

import charli.exception.CharliException;
import charli.util.Storage;
import charli.util.TaskList;
import charli.util.Ui;

/**
 * Represents an executable command in the Charli chatbot.
 * This interface defines the contract for all command implementations.
 */
public interface Command {

    String execute(TaskList tasks, Ui ui, Storage storage) throws CharliException;

    boolean isExit();
}
