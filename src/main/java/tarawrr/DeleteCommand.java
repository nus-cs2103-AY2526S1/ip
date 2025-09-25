package tarawrr;

/**
 * DeleteCommand Class - Represents a command for adding a deleting task.
 */
public class DeleteCommand extends Command {
    private int index;

    //Constructor initiates an instance of DeleteCommand with index to be deleted
    public DeleteCommand(int i) {
        this.index = i;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.getTasks().get(this.index - 1);
        tasks.removeFromTaskList(this.index);
        try {
            storage.save(tasks);
        } catch (TarawrrException e) {
            throw new RuntimeException(e);
        }
        return ui.showDeletedTask(task, tasks.numberOfTasks());
    }
}
