package heimerdinger.command;

import heimerdinger.Storage;
import heimerdinger.Ui;
import heimerdinger.task.TaskList;

/**
 * Lists all tasks currently in the task list.
 *
 * <p><b>Usage:</b> {@code list}</p>
 *
 * <p><b>Example:</b></p>
 * <pre>{@code
 * list
 * }</pre>
 *
 * <p>This command does not modify the task list or storage; it simply
 * instructs the {@link Ui} to display the current contents of {@link TaskList}.</p>
 */
public class ListCommand extends Command {

    /**
     * Displays all tasks in the given {@link TaskList} via the {@link Ui}.
     *
     * @param tasks   the live task list whose contents will be displayed; must not be {@code null}
     * @param ui      the UI sink used to render the task list; must not be {@code null}
     * @param storage the persistence layer (unused in this command)
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showList(tasks);
    }
}
