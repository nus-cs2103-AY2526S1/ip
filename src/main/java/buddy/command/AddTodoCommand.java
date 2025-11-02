package buddy.command;

import buddy.exception.EmptyDescriptionException;
import buddy.model.Task;
import buddy.model.TaskList;
import buddy.model.Todo;
import buddy.storage.Storage;
import buddy.ui.Ui;

public final class AddTodoCommand implements Command {
    private final String description;

    public AddTodoCommand(String description) throws EmptyDescriptionException {
        if (description == null || description.isBlank()) {
            throw new EmptyDescriptionException("todo");
        }
        this.description = description.trim();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task t = new Todo(description);
        tasks.add(t);
        ui.showMessage("Got it. I've added this task:\n  " + t
                + "\nNow you have " + tasks.getSize() + " tasks in the list.");
    }
}
