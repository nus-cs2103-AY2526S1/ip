package tarawrr;

/**
 * EventCommand Class - Represents a command for adding a Event task.
 */
public class EventCommand extends Command {
    private String description;
    private String start;
    private String end;

    //Constructor initiates an instance of EventCommand with description, start date and end date
    public EventCommand(String description, String start, String end) {
        this.description = description;
        this.start = start;
        this.end = end;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws TarawrrException {
        Event event = new Event(description, start, end);
        tasks.addToTaskList(event);
        storage.save(tasks);
        return ui.showTaskAddedMessage(event, tasks.numberOfTasks());
    }
}
