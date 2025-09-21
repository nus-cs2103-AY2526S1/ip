package thopz;


/**
 * Represents the command to list all tasks in the task list
 * <p>
 * This command retrieves all tasks from the {@link TaskList}
 * and formats them using the {@link Ui}.
 * It does not modify the {@link TaskList} or {@link Storage}
 */

public class ListCommand extends Command {

    @Override
    public String perform(TaskList ls, Storage storage, Ui ui) {
       return ui.showList(ls.getAllTasks());
    }

}
