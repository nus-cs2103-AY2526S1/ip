package lux.commands;

import lux.data.AliasList;
import lux.data.TaskList;
import lux.data.TodoTask;
import lux.exception.LuxException;
import lux.storage.Storage;
import lux.ui.Ui;

/**
 * Command that adds a simple todo task (no date/time).
 */
public class TodoCommand extends Command {
    private String argument;

    public TodoCommand(String argument) {
        this.argument = argument;
    }

    /**
     * Create a {@link lux.data.TodoTask}, append it to the task list and
     * return the confirmation message.
     *
     * @throws LuxException when the todo description is missing or empty
     */
    public String execute(TaskList tasks, Ui ui, Storage storage, AliasList aliases) throws LuxException {
        if (argument == null || argument.trim().isEmpty()) {
            throw new LuxException("The description of a todo cannot be empty.");
        }

        TodoTask task = new TodoTask(argument.trim());
        tasks.addTasks(task);
        return ui.addTodo(task);
    }

    public boolean isExit() {
        return false;
    }
}
