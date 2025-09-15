package bytebot.command;

import bytebot.ByteException;
import bytebot.storage.Storage;
import bytebot.task.TaskList;
import bytebot.ui.Ui;

/**
 * Sorts tasks by a criteria.
 * Supports sorting deadlines and events.
 */
public class SortCommand extends Command {
    /**
     * Supported sort types for the sort command.
     */
    public enum SortType {
        DEADLINE,
        ALL
    }

    private final SortType sortType;

    public SortCommand(SortType sortType) {
        this.sortType = sortType;
    }

    /**
     * Executes the sort command.
     * Retrieves tasks from storage, applies the sorting, and displays
     * the sorted output on the UI. Shows deadlines sorted by due date.
     *
     * @param ui       UI used to render output
     * @param storage  Storage providing access to tasks
     * @return Rendered output string from the UI
     * @throws ByteException If the sort type is unsupported or any error occurs
     */
    @Override
    public String execute(Ui ui, Storage storage) throws ByteException {
        switch (sortType) {
        case DEADLINE: {
            TaskList deadlines = storage.getDeadlinesSorted();
            if (deadlines.size() == 0) {
                return "No deadlines found.";
            }
            return ui.showTasks(deadlines);
        }
        case ALL: {
            TaskList combined = storage.getAllTasksSortedByType();
            if (combined.size() == 0) {
                return "No tasks found.";
            }
            return ui.showTasks(combined);
        }
        default:
            throw new ByteException("Invalid sort type");
        }
    }
}


