package bestbot.command;

import bestbot.exception.BestbotException;
import bestbot.Ui;
import bestbot.Storage;
import bestbot.task.Task;
import bestbot.task.TaskList;
import bestbot.task.Todo;

/**
 * Adds a new {@link Todo} task to the task list.
 */
public class AddTodoCommand extends Command {
    private final String description;

    /**
     * Creates an {@code AddTodoCommand} with the given description.
     *
     * @param description Description of the todo task.
     */
    public AddTodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the command by creating a {@link Todo} task and adding it to the task list.
     * Updates the UI and saves the task list to storage.
     *
     * @param tasks   The task list to add the new todo into.
     * @param ui      The UI used to display feedback.
     * @param storage The storage used to save tasks persistently.
     * @throws BestbotException If the description is blank.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BestbotException {
        if (description.isBlank()) {
            throw new BestbotException("The description of a todo cannot be empty.");
        }

        Task todo = new Todo(description);
        tasks.add(todo);

        ui.showTaskAdded(todo, tasks.size());
        storage.save(tasks.getTasks());
    }

    /**
     * Returns whether this command causes the program to exit.
     *
     * @return Always {@code false}, since adding a todo does not exit the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
