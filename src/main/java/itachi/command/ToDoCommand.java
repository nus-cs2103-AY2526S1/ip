package itachi.command;

import java.io.IOException;

import itachi.ItachiException;
import itachi.Storage;
import itachi.Ui;
import itachi.task.TaskList;
import itachi.task.Todo;

/**
 * Represents a command to add a Todo task to the task list.
 */
public class ToDoCommand extends Command {

    /** The description of the Todo task to be added. */
    private String description;

    /**
     * Creates a ToDoCommand with the given task description.
     *
     * @param description the description of the Todo task
     */
    public ToDoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the command by creating a Todo task, adding it to the TaskList,
     * saving the updated list to storage, and displaying confirmation via the UI.
     *
     * @param tasks the TaskList containing all tasks
     * @param ui the UI instance used to display output
     * @param storage the Storage instance used to persist tasks
     * @throws ItachiException if any errors related to Itachi operations occur
     * @throws IOException if there is an I/O error while accessing storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ItachiException, IOException {
        assert tasks != null : "Task list should never be null";
        Todo todo = new Todo(this.description);
        tasks.add(todo);
        storage.save(tasks.getTasks());
        ui.showLine();
        ui.showMessage("Got it. I've added this task:");
        ui.showMessage(todo.toString());
        ui.showMessage("Now you have " + tasks.size() + " tasks in the list!");
        ui.showLine();
        if (this.isUndoable()) {
            CommandHistory.getInstance().push(this);
        }
    }

    @Override
    public void undo(TaskList tasks, Ui ui, Storage storage) throws ItachiException, IOException {
        tasks.remove(tasks.size() - 1);
        storage.save(tasks.getTasks());
        ui.showMessage("Undo Todo: " + description);
    }
}
