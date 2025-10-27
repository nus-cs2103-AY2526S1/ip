package command;

import java.time.LocalDate;

import model.Deadline;
import ui.DateTimeService;
import ui.TaskService;
import ui.ui;

/**
 * Add command to support user add a task with specific deadline
 */
public class AddDeadlineCommand implements Command {
    private TaskService taskService;
    private ui ui;
    private String arguments;
    private DateTimeService dateTimeService;
    private Deadline deadline;

    public AddDeadlineCommand(TaskService taskService, ui ui, String arguments) {
        this.taskService = taskService;
        this.ui = ui;
        this.arguments = arguments;
        this.dateTimeService = new DateTimeService();
        this.deadline = null;
    }

    @Override
    public void execute() {
        if (!arguments.contains("/by")) {
            ui.showError("Deadline requires '/by' parameter");
            return;
        }

        String[] parts = arguments.split("/by", 2);
        String description = parts[0].trim();
        String date = parts[1].trim();

        if (description.isEmpty()) {
            ui.showError("Deadline description is empty");
            return;
        }

        String datetime = dateTimeService.outputDateTime(date);
        deadline = new Deadline(description, datetime);
        taskService.addTask(deadline);
        ui.showTaskAdded(deadline, taskService.getTaskCount());
    }

    @Override
    public boolean isExit(){
        return false;
    }

    @Override
    public String toString() {
        if (!arguments.contains("/by")) {
            return "Deadline requires '/by' parameter";
        }
        String[] parts = arguments.split("/by", 2);
        String description = parts[0].trim();
        String date = parts[1].trim();

        if (description.isEmpty()) {
            return "Deadline description is empty";
        }
        int taskCount = taskService.getTaskCount();
        return "Got it. I've added this task: \n" + deadline.toString() + "\n"
                + "Now you have " + taskCount + " tasks in the list";
    }
}
