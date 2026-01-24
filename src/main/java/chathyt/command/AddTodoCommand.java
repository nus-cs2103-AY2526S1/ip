package chathyt.command;

/**
 *  Represents a command to add a todo task.
 */

import java.util.ArrayList;
import chathyt.task.Task;
import chathyt.storage.Storage;
import chathyt.ui.Ui;
import chathyt.task.Todo;
import chathyt.exception.EmptyDescriptionException;

public class AddTodoCommand implements Command {
    private final String description;

    public AddTodoCommand(String description) {
        this.description = description;
    }

    /**
     *
     * @param tasks The list of existing tasks.
     * @param storage The storage handler for saving tasks.
     * @param ui The UI handler for displaying messages.
     * @return A string message confirming the added todo,
     *         or an error message if the input is invalid.
     */
    @Override
    public String execute(ArrayList<Task> tasks, Storage storage, Ui ui) {
        try {
            if (description.isEmpty()) {
                throw new EmptyDescriptionException("Haha! You don't want to do anything right?");
            }
            Task task = new Todo(description);
            tasks.add(task);
            storage.save(tasks);
            return Ui.showAddedTask(task, tasks.size());
        } catch (EmptyDescriptionException e) {
            return Ui.showError(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}