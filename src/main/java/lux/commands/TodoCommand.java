package lux.commands;

import lux.data.AliasList;
import lux.data.TaskList;
import lux.data.TodoTask;
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
     */
    public String execute(TaskList tasks, Ui ui, Storage storage, AliasList aliases) {
        TodoTask task = new TodoTask(argument);
        tasks.addTasks(task);
        return ui.addTodo(task);
    }

    public boolean isExit() {
        return false;
    }
}
