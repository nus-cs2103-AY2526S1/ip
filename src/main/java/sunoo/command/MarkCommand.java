package sunoo.command;

import sunoo.exception.SunooException;
import sunoo.task.TaskList;
import sunoo.ui.Ui;

/**
 * Represents an executable command that marks a task as done.
 */
public class MarkCommand extends Command {

    /** Index of the task to mark as done */
    private final int indexToMark;

    /**
     * Creates a new MarkCommand with the index of the task to mark as done.
     *
     * @param indexToMark Index of the task to mark as done.
     */
    public MarkCommand(int indexToMark) {
        this.indexToMark = indexToMark;
    }

    /**
     * {@inheritDoc}
     * <p>Marks the task corresponding to the index given.</p>
     *
     * @throws SunooException If index is invalid.
     */
    @Override
    public String execute(TaskList tasks) {
        if (indexToMark <= 0) {
            throw new SunooException("Sorry ENGENE, that's not a valid task index!");
        }
        if (indexToMark > tasks.getNumTasks()) {
            throw new SunooException("Sorry ENGENE, you don't have that many tasks!");
        }
        tasks.markTask(indexToMark);
        String response = Ui.joinLines(
                "Nice job, ENGENE! I've marked this task as done:",
                tasks.getTask(indexToMark).toString());
        assert tasks.getTask(indexToMark).getCompleteStatus();
        return Ui.wrapWithHorizontalLines(response);
    }

    /**
     * {@inheritDoc}
     *
     * @return false.
     */
    @Override
    public boolean shouldExit() {
        return false;
    }
}
