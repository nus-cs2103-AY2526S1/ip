package Command;

import JohnException.JohnException;

import Task.TaskItem;
import Task.TaskList;
import Task.Todo;

import UI.Ui;

/**
 * Command to add a to-do task to the task list.
 */
public class ToDoCommand extends Command {
    private String description;

    public ToDoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the command by creating and adding a to-do task to task list.
     * Validates that the description is non-empty.
     *
     * @param tasks Task list to mutate.
     * @param ui UI used to present feedback to the user.
     * @throws JohnException If the description is empty.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws JohnException {
        assert tasks != null : "Command: tasks must not be null";
        assert ui != null : "Command: ui must not be null";
        if (description.isEmpty()) {
            throw new JohnException("The description of a todo cannot be empty.");
        }

        TaskItem t = new Todo(description, false);
        tasks.add(t);
        ui.showAdded(t, tasks.getSize());
    }
}