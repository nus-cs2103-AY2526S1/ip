package command;

import java.time.LocalDateTime;

import model.Event;
import ui.DateTimeService;
import ui.TaskService;
import ui.ui;

/**
 * Add a command to support user to add a Event Task
 */
public class AddEventCommand implements Command {
    private TaskService taskService;
    private ui ui;
    private String arguments;
    private DateTimeService dateTimeService;
    private Event event;

    public AddEventCommand(TaskService taskService, ui ui, String arguments) {
        this.taskService = taskService;
        this.ui = ui;
        this.arguments = arguments;
        this.dateTimeService = new DateTimeService();
        this.event = null;
    }

    @Override
    public void execute() {
        if (!arguments.contains("/from") || !arguments.contains("/to")) {
            ui.showError("Event requires '/from' and '/to' parameter");
            return;
        }

        String[] parts = arguments.split(" /from | /to");

        if (parts.length != 3) {
            ui.showError("Invalid event format!");
            return;
        }

        String description = parts[0];
        String from = parts[1];
        String to = parts[2];

        if (description.isEmpty()) {
            ui.showError("Description cannot be empty!");
        }

        event = new Event(description, from, to);
        taskService.addTask(event);
        ui.showTaskAdded(event, taskService.getTaskCount());
    }

    @Override
    public boolean isExit(){
        return false;
    }

    @Override
    public String toString() {
        if (!arguments.contains("/from") || !arguments.contains("/to")) {
            return "Event requires '/from' and '/to' parameter";
        }

        String[] parts = arguments.split("/from|/to");

        if (parts.length != 3) {
            return "Invalid event format!\n" + "requires description, starting time and ending time";
        }

        int taskCount = taskService.getTaskCount();

        return "Got it. I've added this task: \n" + event.toString() + "\n"
                + "Now you have " + taskCount + " tasks in the list";
    }
}
