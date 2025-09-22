package moon.commands;

import moon.commands.enums.Command;
import moon.models.Task;
import moon.parser.exceptions.InvalidIndexException;
import moon.parser.util.ParseChecker;
import moon.ui.UiMessages;

/**
 * Command to delete a {@link Task} task from the task list.
 */
public class DeleteCommand extends BaseCommand {
    /** Associated command keyword and status code. */
    public static final Command COMMAND = Command.DELETE;

    /** Index of the task to be deleted */
    public final int deletedIndex;

    /**
     * Creates a new DeleteCommand.
     *
     * @param index Index of the task to be deleted
     */
    public DeleteCommand(int index) {
        this.deletedIndex = index;
    }

    /**
     * Executes the delete command.
     * <p>
     * Removes the specified task from the task list and shows a confirmation message.
     *
     * @return confirmation message of the deleted task to be displayed to the user
     * @throws InvalidIndexException If the provided index is out of range
     */
    @Override
    public String execute() throws InvalidIndexException {
        ParseChecker.isIndexOutOfRange(deletedIndex, getList());
        Task deletedTask = this.getList().delete(deletedIndex);
        return UiMessages.showDeleteTaskMessage(deletedTask);
    }
}
