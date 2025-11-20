package commands;

import exceptions.JackException;
import storage.Storage;
import tasklists.TaskList;
import ui.Ui;

/**
 * The Command class is an abstract base class for all command classes.
 * It defines the structure of commands that can be executed on a task list,
 * such as adding a task, removing a task, or other operations.
 * Each concrete command (e.g., AddTodoCommand, AddDeadlineCommand) will implement
 * the execute method to perform specific actions.
 */
public abstract class Command {
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws JackException;
    public abstract boolean isExit();
}