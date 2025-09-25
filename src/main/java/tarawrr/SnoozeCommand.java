package tarawrr;

public class SnoozeCommand extends Command {
    private final int taskIndex;
    private final String newDate;
    private final String endDate; // For events

    public SnoozeCommand(int taskIndex, String newDate, String endDate) {
        this.taskIndex = taskIndex;
        assert taskIndex >= 0 : "task index should be non-negative";

        this.newDate = newDate;
        assert newDate != null : "new date should not be null";

        this.endDate = endDate; // may be empty for deadlines
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws TarawrrException {
        // Get task to be snoozed
        Task task = tasks.getTasks().get(this.taskIndex - 1);

        if (task instanceof Deadline) {
            Deadline existingTask = (Deadline) task;
            Deadline newTask = existingTask.changeDeadline(newDate);
            tasks.getTasks().set(this.taskIndex - 1, newTask);
        } else if (task instanceof Event) {
            Event existingTask = (Event) task;

            if (this.endDate == "") {
                ui.showError("Please provide a new end date for the event.");
            }

            Event newTask = existingTask.changeDate(newDate, endDate);
            tasks.getTasks().set(this.taskIndex - 1, newTask);
        } else {
            ui.showError("Snooze command can only be applied to Deadline or Event tasks.");
        }

        storage.save(tasks);

        Task newTask = tasks.getTasks().get(this.taskIndex - 1);
        return ui.showSnoozeMessage(newTask);
    }

}
