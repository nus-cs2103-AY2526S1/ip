package command;

import exceptions.EmptyDescriptionException;
import exceptions.MarkExceptions;
import task.TaskList;

public class TodoCommand extends Command {

    /**
     * Creates a new TodoCommand.
     *
     * @param arg the user input (description)
     * @param tasklist the task list to which the todo task will be added
     */
    public TodoCommand(String arg, TaskList tasklist) {
        super(arg, tasklist);
    }

    /**
     * Adds a new todo task to the <code>TaskList</code>.
     *
     * @throws EmptyDescriptionException if the todo description is empty
     */
    public void execute() throws MarkExceptions {
        if (arg.isEmpty()) {
            throw new EmptyDescriptionException("todo");
        }
        taskList.addTodo(arg);
    }
}
