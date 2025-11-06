package bestbot.command;

import bestbot.Ui;
import bestbot.Storage;
import bestbot.task.Task;
import bestbot.task.Deadline;
import bestbot.task.TaskList;
import bestbot.exception.BestbotException;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Command to sort tasks in the task list.
 *
 * Minimal behavior:
 *  - Deadline tasks come first, ordered by their 'by' date.
 *  - All other tasks follow, ordered by display text (case-insensitive).
 */
public class SortCommand extends Command {

    /**
     * Sorts the tasks and persists the new order.
     *
     * @param tasks   the TaskList to sort
     * @param ui      the Ui to show results
     * @param storage the Storage to persist the new order
     * @throws BestbotException not thrown in the current implementation but kept for
     *                          API consistency with other Commands.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BestbotException {
        List<Task> list = tasks.getTasks();

        Comparator<Task> comparator = (a, b) -> {
            if (a instanceof Deadline && b instanceof Deadline) {
                LocalDate da = ((Deadline) a).getBy();
                LocalDate db = ((Deadline) b).getBy();
                return da.compareTo(db);
            }
            if (a instanceof Deadline) {
                return -1;
            }
            if (b instanceof Deadline) {
                return 1;
            }
            return a.toString().compareToIgnoreCase(b.toString());
        };

        Collections.sort(list, comparator);
        storage.save(tasks.getTasks());
        ui.showMessage("Here is your sorted task list:");
        ui.showTasks(tasks.getTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
