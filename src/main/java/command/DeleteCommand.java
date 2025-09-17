package command;

import javafx.util.Pair;
import misc.PepeException;
import state.Storage;
import state.TaskList;
import state.Ui;
import tasks.Task;

/**
 * Command to delete a task from the TaskList.
 */
public class DeleteCommand implements Command {
    private final int deleteIdx;

    public DeleteCommand(int deleteIdx) {
        this.deleteIdx = deleteIdx;
    }

    /**
     * Factory method to construct a DeleteCommand from user input
     * @param arguments A list of user-input strings
     * @return An instance of the DeleteCommand
     * @throws PepeException if an exception occurred while parsing user input or constructing DeleteCommand class
     */
    public static DeleteCommand fromInput(String[] arguments) throws PepeException {
        int deleteIdx;
        if (arguments.length != 1) {
            throw new PepeException("Invalid number of arguments");
        }
        try {
            deleteIdx = Integer.parseInt(arguments[0]) - 1;
        } catch (NumberFormatException e) {
            throw new PepeException("Invalid delete index " + arguments[0]);
        }
        return new DeleteCommand(deleteIdx);
    }

    @Override
    public Pair<String, Boolean> execute(Ui ui, Storage storage, TaskList tasks) throws PepeException {
        if (tasks.size() <= deleteIdx || deleteIdx < 0) {
            throw new PepeException("Submitted task idx is out of bounds");
        }
        Task task = tasks.get(deleteIdx);
        tasks.remove(deleteIdx);
        storage.saveTasks(tasks);
        String message = "Noted. I've removed this task:\n  %s\nNow you have %d tasks in the list.\n".formatted(task,
                tasks.size());
        return new Pair<>(ui.formatMessage(message), true);
    }
}
