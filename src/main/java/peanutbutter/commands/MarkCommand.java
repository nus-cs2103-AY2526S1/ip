package peanutbutter.commands;

import peanutbutter.exceptions.JuinException;
import peanutbutter.tasks.TaskList;
import peanutbutter.ui.Ui;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    private final String args;

    /**
     * Creates a new MarkCommand with the given arguments.
     *
     * @param args the arguments containing the index of the task to mark
     */
    public MarkCommand(String args) {
        this.args = args;
    }

    /**
     * Executes the MarkCommand.
     *
     * @param taskList the list of tasks
     * @param ui the user interface for displaying messages
     * @throws JuinException if the index is invalid or out of bounds
     */
    @Override
    public boolean run(TaskList taskList, Ui ui) throws JuinException {
        if (args.isEmpty()) {
            throw new JuinException("State an index to mark.");
        }

        try {
            int index = Integer.parseInt(args) - 1;
            taskList.markTaskDone(index);
            ui.markTaskMessage(taskList.get(index));
        } catch (NumberFormatException e) {
            throw new JuinException("Invalid task number!");
        }

        return false;
    }
}
