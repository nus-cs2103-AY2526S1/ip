package conversal.command;

import conversal.exception.ConversalException;
import conversal.storage.Storage;
import conversal.task.TaskList;
import conversal.ui.Ui;

/**
 * Marks a task as incomplete in the task list.
 */
public class MarkAsIncompleteCommand implements Command {

    private final String input;

    /**
     * Constructs a MarkAsIncompleteCommand with the given user input.
     *
     * @param input full command string containing the task index to unmark
     */
    public MarkAsIncompleteCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the mark-as-incomplete command: updates the task,
     * saves the list, and shows an acknowledgement.
     *
     * @param tasks   the task list to update
     * @param storage the storage to persist changes
     * @param ui      the UI to display feedback
     * @throws ConversalException if input is invalid or index parsing fails
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws ConversalException {
        if (input.length() <= 7) {
            throw new ConversalException("Ah, I got it! " + ui.getInstructionUnmark());
        }
        try {
            int index = Integer.parseInt(input.substring(7)) - 1;
            tasks.markTaskIncomplete(index);
            storage.save(tasks.getList());
            ui.acknowledge(tasks.get(index), false);
        } catch (NumberFormatException e) {
            throw new ConversalException("Ah, I got it! " + ui.getInstructionUnmark());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
