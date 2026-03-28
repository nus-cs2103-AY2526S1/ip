package command;

import exceptions.MarkExceptions;
import storage.Storage;
import task.TaskList;


/**
 * Represents an abstract user command that can be executed.
 * A <code>Command</code> stores any arguments provided by the user
 * and has access to the <code>TaskList</code> it operates on.
 */
public abstract class Command {
    protected String arg;
    protected TaskList taskList;

    /**
     * Creates a new <code>Command</code> with the given arguments and task list.
     *
     * @param arg      The user input argument for the command
     * @param tasklist The <code>TaskList</code> that this command will operate on
     */
    public Command(String arg, TaskList tasklist) {
        this.arg = arg;
        this.taskList = tasklist;
    }

    /**
     * Executes the command and then saves the updated <code>TaskList</code>
     * to the given <code>Storage</code>.
     *
     * @param storage The <code>Storage</code> used to persist the task list
     * @throws MarkExceptions if the command cannot be executed
     */
    public void executeAndSave(Storage storage) throws MarkExceptions {
        this.execute();
        storage.save(taskList);
    }

    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command
     */
    public abstract void execute() throws MarkExceptions;

    @Override
    public String toString() {
        return arg;
    }
}
