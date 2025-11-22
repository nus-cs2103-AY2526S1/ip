package commands;

import java.util.HashMap;

import exceptions.ApunableException;
import models.ContactBook;
import models.Task;
import models.TaskList;
import utils.Ui;

/**
 * A specialized type of CommandHandler that handle tasks involving creating new task.
 */
public abstract class CreateTaskHandler implements CommandHandler {
    @Override
    /**
     * A specific chunk of code that will be reused by all the CreateTaskHandler. 
     */
    public void handle(TaskList taskList, ContactBook contactList, Ui ui,
                       String firstParam, HashMap<String, String> params) throws ApunableException {
        Task task = createTask(firstParam, params);
        taskList.add(task);

        ui.echo("Noted. I've added this task:");
        ui.echo("  " + task.toString());
        ui.echo(String.format("Now you have %d tasks in the list.", taskList.size()));
    }

    /**
     * Creates a new task based on the given parameters and return it. the parameters are same as the one in {@code handle} method. 
     * 
     * @return created task. 
     */
    public abstract Task createTask(String firstParam, HashMap<String, String> params) throws ApunableException;
}
