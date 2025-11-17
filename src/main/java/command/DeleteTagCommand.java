package command;

import java.util.List;
import tasklist.TaskList;
import ui.UI;

/**
 * Represents a command to delete tags from a specific task.
 * This command removes one or more tags from a task identified by its index.
 *
 * AI-Assisted Development: This class was created with ChatGPT assistance
 * to ensure consistent patterns with existing command classes and proper
 * error handling. The AI helped structure the execute method to handle
 * IndexOutOfBoundsException gracefully and maintain consistency with
 * other command classes in the codebase.
 */
public class DeleteTagCommand extends Command {
    private final int index;
    private final List<String> tagsToRemove;

    /**
     * Constructs a DeleteTagCommand with the specified task index and tags to remove.
     *
     * @param index the zero-based index of the task from which to remove tags
     * @param tagsToRemove the list of tags to remove from the task
     */
    public DeleteTagCommand(int index, List<String> tagsToRemove) {
        super(CommandType.DTAG);
        this.index = index;
        this.tagsToRemove = tagsToRemove;
    }

    /**
     * Executes the tag deletion command by removing specified tags from the task.
     *
     * @param taskList the task list containing the task to modify
     * @return a string message indicating the result of the operation
     */
    @Override
    public String execute(TaskList taskList) {
        try {
            return taskList.deleteTag(index, tagsToRemove);
        } catch (IndexOutOfBoundsException e) {
            return UI.showMessage(e.getMessage());
        }
    }
}