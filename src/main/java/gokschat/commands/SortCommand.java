package gokschat.commands;

import java.util.List;

import gokschat.Ui;
import gokschat.tasks.Task;

/// This class executes the sort tasks command
///
/// @author Ravichandran Gokul
public class SortCommand extends Command{
    // Declare fields
    private String keyword;
    private List<Task> listOfTasks;
    private Ui ui;

    /**
     * Constructs a new {@code gokschat.commands.SortCommand} object with the keyword, task list and UI object.
     * This constructor initializes the object's internal state based on the provided parameters.
     *
     * @param keyword
     * @param listOfTasks
     * @param ui
     */
    public SortCommand(String keyword, List<Task> listOfTasks, Ui ui) {
        this.keyword = keyword;
        this.listOfTasks = listOfTasks;
        this.ui = ui;
    }

    @Override
    public String execute() {
        List<Task> sortedTasks = listOfTasks.stream()
                .sorted()
                .toList();

        return ui.displayList(sortedTasks);
    }
}
