
package rafayel.command;

import rafayel.RafayelException;
import rafayel.storage.Storage;
import rafayel.task.TaskList;
import rafayel.task.Todo;

/**
 * Handles the creation and addition of a new Todo task.
 */
public class TodoCommand extends Command {
    private final String description;

    /**
     * Constructs a new Todo command.
     *
     * @param description of the Todo task.
     * @throws RafayelException if the description is invalid.
     */
    public TodoCommand(String description) throws RafayelException {
        super(Parser.CommandType.TODO);

        this.description = description.trim();
        if (description.isEmpty()) {
            throw new RafayelException("Please add in the description of the Todo task.");
        }
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws RafayelException {
        Todo newTask = new Todo(description);
        tasks.add(newTask);
        storage.save(tasks.getAll());

        return getNewTaskString(newTask, tasks.getSize());
    }
}
