package commands;

import java.util.HashMap;

import exceptions.ApunableException;
import models.ContactBook;
import models.Task;
import models.TaskList;
import utils.Ui;

/**
 * Handles the {@code delete <index>} command from user and delete a task based on provided index. 
 */
public class HandlerDelete implements CommandHandler {
    @Override
    public void handle(TaskList taskList, ContactBook contactList, Ui ui,
                       String firstParam, HashMap<String, String> params) throws ApunableException {
        
        assert !firstParam.isEmpty() : "Did you forgot to tell me which task to delete? ";
        
        try {
            int index = Integer.parseInt(firstParam) - 1;
    
            Task taskToRemove = taskList.get(index);
            taskList.remove(index);

            ui.echo("Noted. I've deleted this task:");
            ui.echo("  " + taskToRemove.toString());
            ui.echo(String.format("Now you have %d tests in the list.", TaskList.tasks.size()));
        } catch (NumberFormatException e) {
            throw new ApunableException("Hey, that doesn't look like a number. Please enter a valid index.");
        } catch (IndexOutOfBoundsException e) {
            if (taskList.size() == 0) {
                throw new ApunableException("You know what? The task list has been emptied!");
            }
            throw new ApunableException(String.format("Your current task indices are between 1 and %d", taskList.size()));
        }
    }
}
