package command;

import task.Task;
import task.Todo;
import tasklist.TaskList;
import ui.UI;

/**
 * Represents a command to create a new todo task.
 * Adds the todo to the task list and displays confirmation.
 */
public class TodoCommand extends Command {
    private String description;

    /**
     * Constructs a TodoCommand with the given description.
     *
     * @param description the description of the todo task
     */
    public TodoCommand(String description) {
        super(CommandType.CREATE_TODO);
        this.description = description;
    }

    /**
     * {@inheritDoc}
     * Creates a new todo task, adds it to the task list,
     * and displays confirmation messages.
     *
     * @param taskList the task list to which the todo will be added
     */
    @Override
    public String execute(TaskList taskList) {
        Task todo = new Todo(description);
        taskList.addTask(todo);
        return UI.showMessage("Got it. I've added this task:")
                + UI.showMessage(todo.toString())
                + UI.showMessage("Now you have " + taskList.size() + " tasks in the list.");
    }
}
