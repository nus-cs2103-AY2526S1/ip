import java.util.List;

/// This class executes the mark task command
///
/// @author Ravichandran Gokul
public class MarkCommand extends Command {
    // Declare fields
    private int index;
    private List<Task> listOfTasks;
    private Ui ui;

    /**
     * Constructs a new {@code MarkCommand} object with the task number, task list and the UI object.
     * This constructor initializes the object's internal state based on the provided parameters.
     *
     * @param index The id number of the task.
     * @param listOfTasks The list of tasks.
     * @param ui THe UI object.
     */
    public MarkCommand(int index, List<Task> listOfTasks, Ui ui) {
        this.index = index;
        this.listOfTasks = listOfTasks;
        this.ui = ui;
    }

    @Override
    public void execute() {
        Task task = listOfTasks.get(index - 1);
        task.markAsDone();
        ui.markTaskMessage(task);
    }
}
