package peanutbutter.commands;

import peanutbutter.exceptions.JuinException;
import peanutbutter.tasks.Task;
import peanutbutter.tasks.TaskList;
import peanutbutter.ui.Ui;

/**
 * Represents a command to delete a task from the list.
 */
public class DeleteCommand extends Command {
    private final String args;

    /**
     * Creates a new DeleteCommand with the given arguments.
     *
     * @param args the arguments containing the index of the task to delete
     */
    public DeleteCommand(String args) {
        this.args = args;
    }

    /**
     * Executes the DeleteCommand.
     *
     * @param taskList the list of tasks
     * @param ui the user interface for displaying messages
     * @throws JuinException if the index is invalid or out of bounds
     */
    @Override
    public boolean run(TaskList taskList, Ui ui) throws JuinException {
        if (args.isEmpty()) {
            throw new JuinException("State an index to delete.");
        }

        try {
            int index = Integer.parseInt(args) - 1;
            Task removedTask = taskList.deleteTask(index);
            ui.deleteTaskMessage(taskList, removedTask);
        } catch (NumberFormatException e) {
            throw new JuinException("Invalid task number!");
        }
        return false;
    }
}
