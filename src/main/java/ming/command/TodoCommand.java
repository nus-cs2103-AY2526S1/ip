package ming.command;

import java.util.List;

import ming.exception.MingException;
import ming.model.Task;
import ming.model.TaskList;
import ming.storage.Storage;
import ming.ui.Ui;

/**
 * Represents a command to add a todo task.
 */
public class TodoCommand extends Command {
    private final String description;
    private final List<String> tags;

    /**
     * Constructs a TodoCommand with the specified description.
     *
     * @param description The description of the todo task.
     */
    public TodoCommand(String description, List<String> tags) {
        this.description = description;
        this.tags = tags;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws MingException {
        Task task = tasks.addTodo(description, tags);
        storage.save(tasks.getTasks());
        return ui.showAdd(task, tasks.getSize());
    }

    @Override
    public String getType() {
        return "AddCommand";
    }
}
