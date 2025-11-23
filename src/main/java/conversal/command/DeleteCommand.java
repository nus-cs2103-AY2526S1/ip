package conversal.command;

import conversal.exception.ConversalException;
import conversal.storage.Storage;
import conversal.task.Task;
import conversal.task.TaskList;
import conversal.ui.Ui;

/**
 * Represents a command that deletes a task from the task list.
 */
public class DeleteCommand implements Command {

    private final String input;

    /**
     * Constructs a {@code DeleteCommand} with the given user input.
     *
     * @param input full command string containing the task index to delete
     */
    public DeleteCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the delete command: removes the task at the given index,
     * saves the task list, and shows a confirmation via the UI.
     *
     * @param tasks   task list to modify
     * @param storage storage to persist changes
     * @param ui      UI component to display messages
     * @throws ConversalException if the input format is invalid
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws ConversalException {
        if (input.length() <= 7) {
            throw new ConversalException("To delete a task, enter: delete (task number)");
        }
        try {
            int index = Integer.parseInt(input.substring(7)) - 1;
            Task removedTask = tasks.deleteTask(index);
            storage.save(tasks.getList());
            ui.deleteMessage(tasks.size(), removedTask.toString());
        } catch (NumberFormatException e) {
            throw new ConversalException("Ah, I got it! " + ui.getInstructionDelete());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
