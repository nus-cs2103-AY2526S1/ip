package ip.commands;

import java.io.FileNotFoundException;

import ip.exceptions.FileCorruptedException;
import ip.exceptions.UnknownInputException;
import ip.storage.Storage;
import ip.tasks.Task;
import ip.tasks.TaskList;
import ip.ui.Ui;

/**
 * Command to delete task from TaskList based on index
 */
public class DeleteCommand implements Command {
    private static final String PREFIX = "delete ";
    private static final int PREFIX_LENGTH = PREFIX.length();

    /**
     * Deletes task from TaskList based on index displayed on UI
     * Rewrites data file with updated TaskList and calls UI
     *
     * @throws UnknownInputException if task does not exist or no index is given
     * @inheritDoc
     */
    @Override
    public String execute(String input, Ui ui, Storage storage, TaskList tasks) throws
            UnknownInputException, FileCorruptedException, FileNotFoundException {

        if (input.length() <= PREFIX_LENGTH) {
            throw new UnknownInputException("'delete' requires a number after");
        }

        String numberString = input.substring(PREFIX_LENGTH).trim();

        boolean isValidNumber = NumberValidator.isValidNumber(numberString);

        if (!isValidNumber) {
            throw new UnknownInputException("'delete' requires a number after");
        }

        int index = Integer.parseInt(numberString);

        if (index < 1 || index > tasks.size()) {
            throw new UnknownInputException("you can't delete a task that doesn't exist");
        }

        int originalLength = tasks.size();
        Task curr = tasks.get(index - 1);
        tasks.remove(index - 1);
        assert tasks.size() == originalLength - 1 : "Task not deleted!";

        storage.rewriteStorage(tasks);

        return ui.showDeleteCommand(curr, tasks.size());

    }
}
