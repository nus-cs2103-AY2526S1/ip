package commands;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import exceptions.ApunableException;
import models.ContactBook;
import models.Task;
import models.TaskList;
import utils.Ui;

/**
 * Handles the {@code find} command from user and list out all the tasks which descriptions contains the provided keyword.
 */
public class HandlerFind implements CommandHandler {
    @Override
    public void handle(TaskList taskList, ContactBook contactList, Ui ui,
                       String firstParam, HashMap<String, String> params) throws ApunableException {
        assert !firstParam.isEmpty() : "Hmm... I can't search for nothing! Try giving me a keyword to look for.";

        String lowerFirstParam = firstParam.toLowerCase();

        List<Task> matchTasks = Stream.iterate(0, i -> i < taskList.size(), i -> i + 1)
                .map(i -> taskList.get(i)).filter(task -> {
                    String lowerDesc = task.getDescription().toLowerCase();
                    return lowerDesc.contains(lowerFirstParam);
                }).toList();

        if (matchTasks.isEmpty()) {
            ui.echo("(no matching task)");
        } else {
            ui.echo("Here are the matching tasks in your list:");

            Stream.iterate(0, i -> i < matchTasks.size(), i -> i + 1).forEach(i -> {
                ui.echo(String.format("%d.%s", i + 1, matchTasks.get(i)));
            });
        }
    }
}