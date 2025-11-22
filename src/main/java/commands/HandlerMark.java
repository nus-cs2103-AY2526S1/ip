package commands;

import java.util.HashMap;

import exceptions.ApunableException;
import models.ContactBook;
import models.Task;
import models.TaskList;
import utils.Ui;

/**
 * Handles the {@code mark} command from user.
 */
public class HandlerMark implements CommandHandler {
    @Override
    public void handle(TaskList taskList, ContactBook contactList, Ui ui,
                       String firstParam, HashMap<String, String> params) throws ApunableException {
        
        assert !firstParam.isEmpty() : "Oops! I didn't catch that, please give me a valid task number.";

        try {
            int index = Integer.parseInt(firstParam) - 1;
            Task task = taskList.get(index);

            if (task.getStatusIcon().equals("X")) {
                ui.echo("This task has been marked as done");
            } else {
                task.markAsDone();

                ui.echo("Nice! I've marked this task as done:");
                ui.echo("  " + task.toString());
            }
        } catch (NumberFormatException e) {
            throw new ApunableException("Hey, that doesn't look like a number. Please enter a valid index.");
        } catch (IndexOutOfBoundsException e) {
            if (taskList.size() == 0) {
                throw new ApunableException("Your taskList is empty! Nothing to mark as done");
            }
            throw new ApunableException(String.format(
                    "That number's out of range! Please choose a task between 1 and %d.", taskList.size()));
        }
    }
}
