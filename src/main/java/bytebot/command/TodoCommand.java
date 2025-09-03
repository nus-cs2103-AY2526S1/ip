package bytebot.command;

import bytebot.ByteException;
import bytebot.storage.Storage;
import bytebot.task.Task;
import bytebot.task.Todo;
import bytebot.ui.Ui;

/**
 * Adds a Todo task without time information.
 */
public class TodoCommand extends Command {
    private final String description;

    /**
     * Constructs a todo command.
     *
     * @param description Description of the to-do task
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Validates input, creates the to-do, and display feedback.
     */
    @Override
    public String execute(Ui ui, Storage storage) throws ByteException {
        if (description == null || description.trim().isEmpty()) {
            throw new ByteException("Note that the description of a todo cannot be empty");
        }
        Task task = new Todo(description);
        storage.addTask(task);
        return ui.showAddedTask(task, storage.getSize());
    }
}


