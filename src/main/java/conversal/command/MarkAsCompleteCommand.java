package conversal.command;

import conversal.exception.ConversalException;
import conversal.storage.Storage;
import conversal.task.TaskList;
import conversal.ui.Ui;

/**
 * Marks a task as completed in the task list.
 */
public class MarkAsCompleteCommand implements Command {

    private final String input;

    /**
     * Constructs a MarkAsCompleteCommand with the given user input.
     *
     * @param input full command string containing the task index to mark
     */
    public MarkAsCompleteCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the mark-as-complete command: updates the task,
     * saves the list, and shows an acknowledgement.
     *
     * @param tasks   the task list to update
     * @param storage the storage to persist changes
     * @param ui      the UI to display feedback
     * @throws ConversalException if input is invalid or index parsing fails
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws ConversalException {
        if (input.length() <= 5) {
            throw new ConversalException("Ah, I got it! " + ui.getInstructionMark());
        }
        try {
            int index = Integer.parseInt(input.substring(5)) - 1;
            tasks.markTaskComplete(index);
            storage.save(tasks.getList());
            ui.acknowledge(tasks.get(index), true);
        } catch (NumberFormatException e) {
            throw new ConversalException("Ah, I got it! " + ui.getInstructionMark());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
