package cheryl.command;

import cheryl.task.Task;
import cheryl.task.Todo;
import cheryl.util.DukeException;
import cheryl.util.Storage;
import cheryl.util.TaskList;
import cheryl.util.Ui;

/**
 * Represents a command to add a Todo task.
 */
public class AddTodoCommand implements Command {
    private String title;

    /**
     * Creates a new AddTodoCommand with the given arguments.
     *
     * @param arguments The title of the Todo task
     * @throws DukeException If the title is empty
     */
    public AddTodoCommand(String arguments) throws DukeException {
        if (arguments.trim().isEmpty()) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
        this.title = arguments.trim();
    }

    /**
     * Executes the command: adds a new Todo to the TaskList.
     *
     * @param tasks   The TaskList to add the Todo to
     * @param ui      The Ui to display messages
     * @param storage The Storage to save the updated list
     * @throws DukeException If an error occurs during saving
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = new Todo(title);
        tasks.addTask(task);
        ui.showMessage("Got it. I've added this task:");
        ui.showMessage(task.toString());
        ui.showMessage("Now you have " + tasks.getSize() + " tasks in the list.");
        try {
            storage.save(tasks.getTasks());
        } catch (Exception e) {
            throw new DukeException("Error saving tasks: " + e.getMessage());
        }
    }

    public boolean isExit() {
        return false;
    }
}