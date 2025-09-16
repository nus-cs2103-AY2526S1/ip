
package rafayel.command;

import rafayel.RafayelException;
import rafayel.storage.Storage;
import rafayel.task.TaskList;
import rafayel.task.Todo;

/**
 * Represents a command that creates and adds a new Todo task.
 * A todo only requires a description.
 */
public class TodoCommand extends Command {

    /** Stores the description of the Todo task. */
    private final String description;

    /**
     * Constructs a new TodoCommand.
     *
     * @param description the description of the todo task.
     * @throws RafayelException if the description is invalid or empty.
     */
    public TodoCommand(String description) throws RafayelException {
        super(CommandHandle.CommandType.TODO);

        this.description = description.trim();
        if (description.isEmpty()) {
            throw new RafayelException(
                    "Even I can't read your mind, you know. What is this 'todo' supposed to be? Add a description for your todo task :c");
        }
    }

    /**
    * Executes the todo command by creating a Todo task.
    *
    * @param tasks the current task list.
    * @param storage the storage handler.
    * @return confirmation message after adding the task.
    * @throws RafayelException if saving fails.
    */
    @Override
    public String execute(TaskList tasks, Storage storage) throws RafayelException {
        Todo newTask = new Todo(description);
        tasks.add(newTask);
        storage.save(tasks.getAll());

        return getNewTaskString(newTask, tasks.getSize());
    }
}
