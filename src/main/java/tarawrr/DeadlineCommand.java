package tarawrr;

/**
 * DeadlineCommand Class - Represents a command for adding a deadline task.
 */
public class DeadlineCommand extends Command{
    private String description;
    private String date;

    //Constructor initiates an instance of DeadlineCommand with description and date
    public DeadlineCommand(String description, String date) {
        this.description = description;
        this.date = date;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws TarawrrException {
        Deadline deadline = new Deadline(description, date);
        tasks.addToTaskList(deadline);
        try {
            storage.save(tasks);
        } catch (TarawrrException e) {
            throw new RuntimeException(e);
        }
       return ui.showTaskAddedMessage(deadline, tasks.numberOfTasks());
    }
}
