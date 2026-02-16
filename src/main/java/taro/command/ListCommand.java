package taro.command;

import java.util.stream.IntStream;

import taro.corecomp.Storage;
import taro.corecomp.TaskList;
import taro.ui.Ui;

/**
 * Represents the {@code list} command which displays all tasks
 * currently stored in the task list.
 * The command input must be exactly:
 *     list
 * displays all tasks along with their status and details.
 */
public class ListCommand implements Command {

    /**
     * Executes the {@code list} command.
     * Displays all tasks in the {@link TaskList} through the {@link Ui}.
     * No modification is made to the {@link TaskList} or {@link Storage}.
     *
     * @param tasks   the current list of tasks
     * @param ui      the user interface used to display messages
     * @param storage the storage handler; not used in this command
     * @return {@code false} as the program should not terminate after execution
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "TaskList must not be null when executing";
        assert ui != null : "UI must not be null when executing";
        assert storage != null : "Storage must not be null when executing";

        ui.showLine();
        if (tasks.size() == 0) {
            ui.show(" No tasks in your list.");
        } else {
            ui.show(" Here are the tasks in your list:");
            IntStream.range(0, tasks.size())
                    .mapToObj(i -> (i + 1) + ". " + tasks.get(i))
                    .forEach(ui::show);
        }
        ui.showLine();
        return false;
    }
}
