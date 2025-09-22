package command;

import exception.MarkException;
import task.TaskList;
import ui.Ui;
import storage.Storage;

import java.io.IOException;

/**
 * Represents the command to mark a task in the task list as undone.
 */
public class UnmarkCommand extends Command {
    /**
     * Constructs a {@link UnmarkCommand} with the relevant user input.
     *
     * @param input the raw user input that triggered the command
     */
    public UnmarkCommand(String input) {
        super(input);
    }

    /**
     * Executes the unmark command: marks the indicated task in the task list as undone,
     * displays a success/failure message and updates the save file accordingly.
     *
     * @param tasklist the {@link TaskList} where tasks are stored
     * @param ui       the {@link Ui} that the user interacts with
     * @param storage  the {@link Storage} that retrieves and updates the save file
     * @throws MarkException if there is an invalid input
     * @throws IOException if there is an error with updating the save file
     */
    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws MarkException, IOException {
        try {
            String index = input.substring(7);
            int item = Integer.parseInt(index) - 1;

            tasklist.get(item).markAsUndone();
            ui.chatbotPrint("Ok, I've marked this task as not done yet:\n    " + tasklist.get(item));
        } catch (Exception e) {
            throw new MarkException("I'm not sure which item you're trying to unmark. Try again?");
        }

        storage.saveToFile(tasklist);
    }
}
