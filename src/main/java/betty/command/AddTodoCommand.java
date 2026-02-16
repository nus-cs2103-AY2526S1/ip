package betty.command;

import betty.exception.BettyException;
import betty.storage.Storage;
import betty.task.TaskList;
import betty.task.Todo;
import betty.ui.Ui;

/**
 * Represents a command object that adds a todo task
 */
public class AddTodoCommand extends Command {

    private Todo todo;

    /**
     * Construct a new command with the given todo task provided
     * @param todo task provided to be added on command
     */
    public AddTodoCommand(Todo todo) {
        super();
        this.todo = todo;
    }

    /**
     * Executes the command to add todo task into task list and storage, and shows message on the ui
     *
     * @param taskList the list of tasks to operate on
     * @param ui       the user interface to display messages
     * @param storage  the storage manager to save changes
     * @return String representation of the result after executing the command
     * @throws BettyException if execution fails
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws BettyException {
        taskList.addTodo(this.todo);
        return ui.addTask(this.todo, taskList);
    }

    /**
     * Returns whether this command should terminate the program.
     * @return false as program does not terminate after this command
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
