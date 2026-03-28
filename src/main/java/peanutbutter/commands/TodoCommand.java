package peanutbutter.commands;

import peanutbutter.exceptions.JuinException;
import peanutbutter.tasks.Task;
import peanutbutter.tasks.TaskList;
import peanutbutter.tasks.Todo;
import peanutbutter.ui.Ui;

/**
 * Represents a command to create and add a Todo task.
 */
public class TodoCommand extends Command {
    private final String args;

    /**
     * Creates a new TodoCommand with the given arguments.
     *
     * @param args the arguments containing the description of the todo
     */
    public TodoCommand(String args) {
        this.args = args;
    }

    /**
     * Executes the TodoCommand.
     *
     * @param taskList the list of tasks
     * @param ui the user interface for displaying messages
     * @throws JuinException if the arguments are invalid
     */
    @Override
    public boolean run(TaskList taskList, Ui ui) throws JuinException {
        if (args.isEmpty()) {
            throw new JuinException("The description of todo cannot be empty!");
        }

        Task todo = new Todo(args);
        taskList.addTask(todo);
        ui.addTaskMessage(taskList, todo);

        return false;
    }
}
