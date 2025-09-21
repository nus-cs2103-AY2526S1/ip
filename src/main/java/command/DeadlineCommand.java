package command;

import task.Deadline;
import task.TaskList;
import ui.Ui;
import storage.Storage;
import exception.DeadlineException;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Represents the command to add a {@link Deadline} task to the task list.
 */
public class DeadlineCommand extends Command {
    /**
     * Constructs a {@link DeadlineCommand} with the relevant user input.
     *
     * @param input the raw user input that triggered the command
     */
    public DeadlineCommand(String input) {
        super(input);
    }

    /**
     * Executes the deadline command: adds a {@link Deadline} to the task list,
     * displays a success/failure message and updates the save file accordingly.
     *
     * @param tasklist the {@link TaskList} where tasks are stored
     * @param ui       the {@link Ui} that the user interacts with
     * @param storage  the {@link Storage} that retrieves and updates the save file
     * @throws DeadlineException if there is an invalid input
     * @throws IOException if there is an error with updating the save file
     */
    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws DeadlineException, IOException {
        try {
            Deadline td = new Deadline(input.substring(9, input.indexOf("/by") - 1), input.substring(input.indexOf("/by") + 4));
            tasklist.add(td);
            ui.chatbotPrint("Got it. I've added this task:\n      " + td);
            ui.chatbotPrint("Now you have " + tasklist.size() + " tasks in the list.");
        } catch (Exception e) {
            throw new DeadlineException("To create a new deadline item, the command is: deadline /by [due date (yyyy-mm-dd)]");
        }

        storage.saveToFile(tasklist);
    }
}
