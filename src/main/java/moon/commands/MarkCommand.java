package moon.commands;

import moon.commands.enums.Command;
import moon.models.Task;
import moon.parser.exceptions.InvalidIndexException;
import moon.parser.util.ParseChecker;
import moon.ui.UiMessages;

/**
 * Command to mark a {@link Task} in the task list as <b>done</b>.
 */
public class MarkCommand extends BaseCommand {
    /** Associated command keyword and status code. */
    public static final Command COMMAND = Command.MARK;

    /** Index of the task to be marked as <b>done</b>. */
    public final int markedIndex;

    /**
     * Creates a new MarkCommand.
     *
     * @param index Index of the task to be marked <b>done</b>
     */
    public MarkCommand(int index) {
        this.markedIndex = index;
    }

    /**
     * Marks the specified task in the task list as <b>done</b>.
     * <ul>
     *   <li>If the index is invalid, an {@link InvalidIndexException} is thrown.</li>
     *   <li>If the task is already marked, an informational message is shown.</li>
     *   <li>If the task is successfully marked, a confirmation message is shown.</li>
     * </ul>
     *
     * @return message of the marked task to be displayed to the user
     * @throws InvalidIndexException If the provided index is out of range
     */
    @Override
    public String execute() throws InvalidIndexException {
        ParseChecker.isIndexOutOfRange(markedIndex, getList());
        Task taskToMark = getList().get(markedIndex);

        if (taskToMark.isDone()) {
            return UiMessages.showAlreadyMarkedMessage(taskToMark);
        } else {
            taskToMark.setDone(true);
            return UiMessages.showMarkedSuccessfulMessage(taskToMark);
        }
    }
}
