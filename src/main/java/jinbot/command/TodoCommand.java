package jinbot.command;

import jinbot.task.Task;
import jinbot.task.TaskList;
import jinbot.task.Todo;
import jinbot.ui.Ui;

/**
 * Represents a command to add a todo task to the task list.
 */
public class TodoCommand implements Command {
    private final String description;

    /**
     * Constructs a TodoCommand with the given description.
     *
     * @param description The description of the todo task.
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the todo command by creating a new Todo task,
     * adding it to the task list, and displaying a confirmation message.
     *
     * @param ui       The UI instance that handles user interaction.
     * @param taskList The task list to which the todo is added.
     */
    @Override
    public String execute(Ui ui, TaskList taskList) {
        Task todo = new Todo(description);
        taskList.addTask(todo);

        String response = "Got it. I've added this task:\n  " + todo
            + "\nNow you have " + taskList.getSize()
            + " tasks in the list.";
        ui.printBox(response);
        return response;
    }
}
