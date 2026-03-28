package chani.commands;

import chani.Storage;
import chani.TaskList;
import chani.Ui;
import chani.tasks.Task;
import chani.tasks.TaskRegistry;

/**
 * Represents a command that adds a new {@link Task} to the {@link TaskList}.
 */
public class AddCommand extends Command {

    /**
     * Creates an AddCommand with a command keyword and arguments.
     * @param command The task type (e.g., "todo", "deadline", "event").
     * @param args The task details (description, dates, etc.).
     */
    public AddCommand(String command, String... args) {
        super(command, args);
    }

    /**
     * Adds the task to the task list and returns a message.
     * @param storage The storage handler (not used here).
     * @return Message confirming the task was added.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        Task task = TaskRegistry.createTask(command, args);
        taskList.add(task);
        return ui.showAddedTask(task, taskList.size());
    }
}
