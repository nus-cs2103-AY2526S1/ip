package leo;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }


    /**
     * Adds the task to the ArrayList wrapped within the TaskList
     * Saves the contents of the TaskList to the file tied to storage
     * Calls methods of the Ui to print relevant messages
     * @param tasks The TaskList initialised at Leo.java
     * @param ui The Ui object of Leo.java
     * @param storage The Storage object of Leo.java, used to write the tasks into a file
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.addTask(task);
            int size = tasks.size();
            storage.save(tasks);
            return ui.taskAdded(tasks.elem(size), size);
        } catch (Exception e) {
            return ui.showError(e);
        }

    }
}
