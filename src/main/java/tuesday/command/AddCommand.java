package tuesday.command;

import java.time.format.DateTimeParseException;

import tuesday.task.TaskEnums.TaskType;
import tuesday.storage.Storage;
import tuesday.task.TodoTask;
import tuesday.task.EventTask;
import tuesday.task.DeadlineTask;
import tuesday.task.TaskList;
import tuesday.task.Task;
import tuesday.ui.Ui;


public class AddCommand extends Command {
    private final String DESCRIPTION;
    private final String START_TIME;
    private final String END_TIME;
    private final TaskType TASK_TYPE;

    private static final String ERROR_MESSAGE = "ERROR: ";
    private static final String SUCCESS_MESSAGE = "Got it. I've added this task:\n";
    private static final String TIME_FORMAT = "Time format should be: dd-MM-yyyy HHmm";



    /**
     * Construct AddCommend for a To-do task
     * @param description
     * @param taskType
     */
    public AddCommand(String description, TaskType taskType) {
        assert description != null && !description.isEmpty() : "Description cannot be null or empty";
        assert taskType == TaskType.TODO : "This constructor should only be used for todo tasks";
        this.DESCRIPTION = description;
        this.TASK_TYPE = taskType;
        this.END_TIME = "";
        this.START_TIME = "";
    }

    /**
     * Construct AddCommend for deadline Task
     * @param description
     * @param taskType
     * @param startTime
     */
    public AddCommand(String description, TaskType taskType, String startTime) {
        assert description != null && !description.isEmpty() : "Description cannot be null or empty";
        assert taskType == TaskType.DEADLINE: "This constructor should only be used for deadline tasks";
        assert startTime != null && !startTime.isEmpty() : "Deadline must have a start time";
        this.DESCRIPTION = description;
        this.START_TIME = startTime;
        this.END_TIME = "";
        this.TASK_TYPE = taskType;

    }

    /**
     * Construct AddCommend for event task
     * @param description
     * @param taskType
     * @param startTime
     * @param endTime
     */
    public AddCommand(String description, TaskType taskType, String startTime, String endTime) {
        assert description != null && !description.isEmpty() : "Description cannot be null or empty";
        assert taskType == TaskType.EVENT : "This constructor should only be used for event tasks";
        assert startTime != null && !startTime.isEmpty() : "Event must have a start time";
        assert endTime != null && !endTime.isEmpty() : "Event must have an end time";
        this.DESCRIPTION = description;
        this.START_TIME = startTime;
        this.END_TIME = endTime;
        this.TASK_TYPE = taskType;
    }

    /**
     * Create task based on their TaskType
     * @param tasks: TaskList
     * @param taskType: Type of Task
     * @return the created Task
     */
    private Task classifyTask(TaskList tasks, TaskType taskType) {
        Task task = null;
        switch (taskType) {
        case TODO:
            task = new TodoTask(this.DESCRIPTION);
            break;
        case DEADLINE:
            task = new DeadlineTask(this.DESCRIPTION, this.START_TIME);
            break;
        case EVENT:
            task = new EventTask(this.DESCRIPTION, this.START_TIME, this.END_TIME);
            break;
        }
        tasks.addTask(task);
        return task;
    }

    /**
     * Print the message when created a task successfully
     * @param task: created task
     * @param tasks: list of tasks
     * @return String of message
     */
    private String printSuccessMessage(Task task, TaskList tasks) {
        String response = SUCCESS_MESSAGE + task.toString() +
                "\n" + "Now you have " + tasks.size() + " tasks in the list";
        System.out.println(response);
        return response;
    }

    /**
     * Execute the add command by creating the correct type of task
     * Add it to the task list
     * Display a confirmation message to the user and the updated number of task
     * @param tasks Task list where new task is added
     * @param ui User interface for displaying message
     * @param storage Storage used to store task to hardware
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = classifyTask(tasks, TASK_TYPE);
            printSuccessMessage(task, tasks);
        } catch (DateTimeParseException e) {
            ui.showError(e.getMessage() + TIME_FORMAT);
        }

    }

    /**
     * Execute the add response
     * @param tasks
     * @param ui
     * @param storage
     * @return
     */
    @Override
    public String getResponse(TaskList tasks, Ui ui, Storage storage) {
        String response = "";
        try {
            Task task = classifyTask(tasks, TASK_TYPE);
            response = printSuccessMessage(task, tasks);
        } catch (DateTimeParseException e) {
            response = ERROR_MESSAGE + e.getMessage() + TIME_FORMAT;
            ui.showError(response);
        }

        return response;
    }

    /**
     * Indicate whether this command should exit
     * @return Always false
     */
    public boolean isExit() {
        return false;
    }
}
