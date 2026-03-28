package karl.command;

import karl.exception.KarlException;
import karl.storage.Storage;
import karl.task.Task;
import karl.task.TaskList;
import karl.task.Todo;
import karl.ui.Ui;

/**
 * Adds a todo task to the task list.
 */
public class AddTodoCommand implements Command {
    private final String description;

    /**
     * Constructs an AddTodoCommand from user input.
     *
     * @param input the raw input string starting with "todo"
     * @throws KarlException if the description is missing
     */
    public AddTodoCommand(String input) throws KarlException {
        assert input != null : "Input should not be null";
        this.description = input.substring(4).trim();
        if (this.description.isEmpty())
            throw new KarlException("The description of a todo cannot be empty.");
    }

    /**
     * Executes addition of a todo task, saves the task list and displays confirmation.
     *
     * @param tasks the TaskList to add to
     * @param ui the Ui to interact with the user
     * @param storage the Storage to persist data
     * @throws KarlException if storage fails
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KarlException {
        assert tasks != null;
        assert ui != null;
        assert storage != null;
        Task todo = new Todo(description);
        tasks.add(todo);
        storage.saveTasks(tasks.getTasks());
        ui.showAddedTask(todo, tasks.size());
    }

    /**
     * Indicates this command does not terminate the program.
     *
     * @return false as this command does not exit
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
