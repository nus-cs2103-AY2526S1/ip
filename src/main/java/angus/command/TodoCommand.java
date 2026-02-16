package angus.command;

import angus.exception.AngusException;
import angus.task.TaskList;

/**
 * Represents the command to create a new ToDo task given the task details
 * <p>
 * This class is responsible for calling the addTodo method implemented by the TaskList class,
 * which creates a new ToDo task and adds it to the user's list of tasks.
 */
public class TodoCommand extends Commands {
    private final TaskList tasks;
    private final String todoName;

    /**
     * Constructs a new instance of the TodoCommand class with the user's tasks and task name.
     * @param tasks The current list of tasks the user has.
     * @param todoName The name of the ToDo task to be created
     */
    public TodoCommand(TaskList tasks, String todoName) {
        this.tasks = tasks;
        this.todoName = todoName;
    }

    @Override
    public String execute() throws AngusException {
        return tasks.addTodo(todoName);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
