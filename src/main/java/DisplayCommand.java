import java.util.List;

/// This class executes the listing task command
///
/// @author Ravichandran Gokul
public class DisplayCommand extends Command {
    // Declare fields
    private List<Task> listOfTasks;
    private Ui ui;

    /**
     * Constructs a new {@code DisplayCommand} object with the task list and UI object.
     * This constructor initializes the object's internal state based on the provided parameters.
     *
     * @param listOfTasks The list of tasks.
     * @param ui The UI object.
     */
    public DisplayCommand(List<Task> listOfTasks, Ui ui) {
        this.listOfTasks = listOfTasks;
        this.ui = ui;
    }

    @Override
    public void execute() {
        this.ui.displayList(this.listOfTasks);
    }
}
