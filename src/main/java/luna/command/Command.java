package luna.command;

import java.io.IOException;

import luna.exception.LunaException;
import luna.storage.Storage;
import luna.task.TaskList;


/**
 * Represents executable commands that the user can give to Luna.
 */
public abstract class Command {
    /**
     * Executes the command.
     * Returns Luna's response to the execution.
     */
    public abstract String execute(TaskList taskList, Storage<TaskList> storage);

    /**
     * Returns if the program should exit after executing the command.
     */
    public abstract boolean isExit();

    protected void saveTaskList(TaskList taskList, Storage<TaskList> storage) {
        try {
            storage.save(taskList);
        } catch (IOException e) {
            throw new LunaException("Saving failed.");
        }
    }
}
