package gokschat.commands;

import gokschat.Ui;
import gokschat.tasks.Task;

import java.util.List;

/// This class executes the unmark task command
///
/// @author Ravichandran Gokul
public class UnmarkCommand extends Command {
    // Declare fields
    private int index;
    private List<Task> listOfTasks;
    private Ui ui;

    /**
     * Constructs a new {@code gokschat.commands.UnmarkCommand} object with the task number, the task list and the UI object.
     * This constructor initializes the object's internal state based on the provided parameters.
     *
     * @param index The id number of the task.
     * @param listOfTasks The list of tasks.
     * @param ui The UI object.
     */
    public UnmarkCommand(int index, List<Task> listOfTasks, Ui ui) {
        this.index = index;
        this.listOfTasks = listOfTasks;
        this.ui = ui;
    }

    @Override
    public String execute() {
        Task task = listOfTasks.get(index - 1);
        task.unmarkTask();
        return this.ui.unmarkTaskMessage(task);
    }
}
