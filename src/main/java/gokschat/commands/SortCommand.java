package gokschat.commands;

import java.util.Collections;
import java.util.List;

import gokschat.Ui;
import gokschat.tasks.Task;

/// This class executes the sort tasks command
///
/// @author Ravichandran Gokul
public class SortCommand extends Command{
    // Declare fields
    private List<Task> listOfTasks;
    private Ui ui;

    /**
     * Constructs a new {@code gokschat.commands.SortCommand} object with the task list and UI object.
     * This constructor initializes the object's internal state based on the provided parameters.
     *
     * @param listOfTasks
     * @param ui
     */
    public SortCommand(List<Task> listOfTasks, Ui ui) {
        this.listOfTasks = listOfTasks;
        this.ui = ui;
    }

    @Override
    public String execute() {
        Collections.sort(listOfTasks);

        return ui.displayList(listOfTasks);
    }
}
