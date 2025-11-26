package aries.command;

import aries.AriesException;
import aries.task.Task;
import aries.task.TaskList;
import aries.task.Todo;
import aries.ui.Ui;

/**
 * Adds a ToDo task to the task list.
 */
public class ToDoCommand implements Command {
    private String description;

    /**
     * Constructor for ToDoCommand.
     *
     * @param description The description of the ToDo task.
     */
    public ToDoCommand(String description) {
        this.description = description;
    }

    @Override
    public CommandResult execute(TaskList tasks, Ui ui) throws AriesException {
        if (description == null || description.isEmpty()) {
            throw new AriesException("OOPS!!! The description of a todo cannot be empty.");
        }

        Task t = new Todo(description);
        tasks.addTask(t);
        return new CommandResult(ui.showAddedString(tasks), true, false);
    }
}
