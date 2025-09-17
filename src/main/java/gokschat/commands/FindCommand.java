package gokschat.commands;

import java.util.List;

import gokschat.Ui;
import gokschat.tasks.Task;

/// This class executes the find task command
///
/// @author Ravichandran Gokul
public class FindCommand extends Command {
    // Declare fields
    private String keyword;
    private List<Task> listOfTasks;
    private Ui ui;

    /**
     * Constructs a new {@code gokschat.commands.FindCommand} object with the keyword, task list and UI object.
     * This constructor initializes the object's internal state based on the provided parameters.
     *
     * @param keyword
     * @param listOfTasks
     * @param ui
     */
    public FindCommand(String keyword, List<Task> listOfTasks, Ui ui) {
        this.keyword = keyword;
        this.listOfTasks = listOfTasks;
        this.ui = ui;
    }

    @Override
    public String execute() {
        List<Task> filteredTasks = listOfTasks.stream()
                .filter(task -> task.getNameOfTask().toLowerCase().contains(keyword.toLowerCase()))
                .toList();

        return ui.displayList(filteredTasks);
    }
}
