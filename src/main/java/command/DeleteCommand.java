package command;

import exception.DeleteException;
import task.Task;
import task.TaskList;
import ui.Ui;
import storage.Storage;

import java.io.IOException;

/**
 * Represents the command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    /**
     * Constructs a {@link DeleteCommand} with the relevant user input.
     *
     * @param input the raw user input that triggered the command
     */
    public DeleteCommand(String input) {
        super(input);
    }

    /**
     * Executes the delete command: deletes a task from the task list,
     * displays a success/failure message and updates the save file accordingly.
     *
     * @param tasklist the {@link TaskList} where tasks are stored
     * @param ui       the {@link Ui} that the user interacts with
     * @param storage  the {@link Storage} that retrieves and updates the save file
     * @throws DeleteException if there is an invalid input
     * @throws IOException if there is an error with updating the save file
     */
    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws DeleteException, IOException {
        try {
            String desc = input.substring(7);
            int item = Integer.parseInt(desc);

            Task taskToDelete = tasklist.get(item - 1);
            tasklist.remove(item - 1);

            ui.chatbotPrint("I've deleted the task:\n      " + taskToDelete);
            ui.chatbotPrint("Now you have " + tasklist.size() + " tasks in the list.");
        } catch (Exception e) {
            throw new DeleteException("I'm not sure which item you're trying to delete. Try again?");
        }

        storage.saveToFile(tasklist);
    }

    /**
     * Returns the help message associated with the delete command
     *
     * @return the corresponding help message
     */
    @Override
    public String getHelpMessage() {
        return """
                delete:
                deletes the specified task from the task list.
                format: delete [index of task]""";
    }
}
