import java.util.List;

/// This class executes the adding task command
///
/// @author Ravichandran Gokul
public class AddCommand extends Command {
    private List<Task> listOfTasks;
    private Ui ui;
    private String taskName;
    private String taskType;

    /**
     * Constructs a new {@code DeleteCommand} object with the task list, the UI object, task name, and task
     * type.
     * This constructor initializes the object's internal state based on the provided parameters.
     *
     * @param listOfTasks The list of tasks.
     * @param ui          The UI object.
     * @param taskName    The name of task.
     * @param taskType    The type of the task (deadline, event etc.,).
     */
    public AddCommand(List<Task> listOfTasks, Ui ui, String taskName, String taskType) {
        this.listOfTasks = listOfTasks;
        this.ui = ui;
        this.taskName = taskName;
        this.taskType = taskType;
    }

    @Override
    public void execute() {
        Task newTask;
        if (taskType.equals("todo")) {
            newTask = new Task(taskName);
        } else if (taskType.equals("deadline")) {
            int firstSlashIndex = taskName.indexOf("/");
            String actualName = taskName.substring(0, firstSlashIndex - 1);
            String deadline = taskName.substring(firstSlashIndex + 4);
            newTask = new DeadlineTask(actualName, deadline);
        } else {
            int firstSlashIndex = taskName.indexOf("/");
            String actualName = taskName.substring(0, firstSlashIndex - 1);
            String fromPlusTo = taskName.substring(firstSlashIndex + 1);
            firstSlashIndex = fromPlusTo.indexOf("/");
            String from = fromPlusTo.substring(5, firstSlashIndex - 1);
            String to = fromPlusTo.substring(firstSlashIndex + 4);
            newTask = new EventTask(actualName, from, to);
        }

        listOfTasks.add(newTask);
        ui.addTaskMessage(newTask, listOfTasks);
    }
}