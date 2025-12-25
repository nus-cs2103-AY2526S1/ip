package leo;

public class ListCommand extends Command {

    /**
     * Fetches the list of tasks from the Storage
     * TaskList iterates through itself
     * Ui prints the string of each task in the TaskList
     * @param tasks TaskList object of Leo.java
     * @param ui Ui object of Leo.java
     * @param storage Storage object of Leo.java
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            TaskList lst = new TaskList(storage.load());
            return ui.iterate(lst.iterate());
        } catch (Exception e) {
            return ui.showError(e);
        }
    }
}
