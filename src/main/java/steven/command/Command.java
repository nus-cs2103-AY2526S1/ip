package steven.command;

import steven.storage.Storage;
import steven.task.TaskList;

/**
 * Represents the different commands that can be given by the user.
 * Each command corresponds to an action supported by the chatbot.
 */
public interface Command {

    /**
     * Executes the command using the provided storage and task list.
     *
     * @param storage The local storage system used to save changes.
     * @param tasks The task list on which the command operates.
     * @return A message indicating the result of executing the command.
     */
    String execute(Storage storage, TaskList tasks);
}
