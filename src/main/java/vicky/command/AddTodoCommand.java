package vicky.command;

import java.io.IOException;

import vicky.storage.Storage;
import vicky.tasklist.TaskList;
import vicky.tasklist.Todo;
import vicky.ui.Ui;

/**
 * Represents a command to add a new todo task.
 */
public class AddTodoCommand extends Command {

    private String description;

    /**
     * Constructor for AddTodoCommand class, initialises the command with a description.
     *
     * @param description The name of the todo task to be created.
     */
    public AddTodoCommand(String description) {
        this.description = description;
    }
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Todo todo = new Todo(this.description);
        tasks.addTask(todo);
        storage.save(tasks);
        return ui.showAddedTask(todo, tasks.len());
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
