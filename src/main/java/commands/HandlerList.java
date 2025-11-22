package commands;

import java.util.HashMap;
import java.util.stream.Stream;

import exceptions.ApunableException;
import models.ContactBook;
import models.TaskList;
import utils.Ui;

/**
 * Handles the {@code list} command from user and list out all the added tasks.
 */
public class HandlerList implements CommandHandler {
    @Override
    public void handle(TaskList taskList, ContactBook contactList, Ui ui,
                       String firstParam, HashMap<String, String> params) throws ApunableException {
        if (taskList.size() == 0) {
            ui.echo("There is currently no task in your list");
        } else {
            ui.echo("Here are the tasks in your list:");
        
            Stream.iterate(0, i -> i < taskList.size(), i -> i + 1).forEach(i -> {
                    ui.echo(String.format("%d.%s", i + 1, taskList.get(i)));
            });
        }
    }
}
