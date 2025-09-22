package moon.commands;

import moon.commands.enums.Command;
import moon.models.Task;
import moon.parser.exceptions.InvalidIndexException;
import moon.parser.util.ParseChecker;
import moon.ui.UiMessages;

/**
 * Command to mark a {@link Task} in the task list as <b>not done</b>.
 */
public class UnmarkCommand extends BaseCommand {
    /** Associated command keyword and status code. */
    public static final Command COMMAND = Command.UNMARK;

    /** Index of the task to be marked as <b>not done</b>. */
    public final int unmarkedIndex;

    /**
     * Creates a new UnmarkCommand.
     *
     * @param index Index of the task to be marked <b>not done</b>.
     */
    public UnmarkCommand(int index) {
        this.unmarkedIndex = index;
    }

    /**
     * Marks the specified task in the task list as <b>not done</b>.
     * <ul>
     *   <li>If the index is invalid, an {@link InvalidIndexException} is thrown.</li>
     *   <li>If the task is already unmarked, an informational message is shown.</li>
     *   <li>If the task is successfully unmarked, a confirmation message is shown.</li>
     * </ul>
     *
     * @return message of the unmarked task to be displayed to the user
     * @throws InvalidIndexException If the provided index is out of range
     */
    @Override
    public String execute() throws InvalidIndexException {
        ParseChecker.isIndexOutOfRange(unmarkedIndex, getList());
        Task taskToUnmark = getList().get(unmarkedIndex);

        if (!taskToUnmark.isDone()) {
            return UiMessages.showAlreadyUnmarkedMessage(taskToUnmark);
        } else {
            taskToUnmark.setDone(false);
            return UiMessages.showUnmarkedSuccessfulMessage(taskToUnmark);
        }
    }
}
