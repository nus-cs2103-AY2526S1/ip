package commands;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import exceptions.ApunableException;
import models.ContactBook;
import models.Task;
import models.TaskList;
import utils.DateTimeUtil;
import utils.Ui;

/**
 * Prints a list of tasks that occur at given date(i.e. {@code task.isOccuringAt()} return true).
 */
public class HandlerCheckOccur implements CommandHandler {
    @Override
    public void handle(TaskList taskList, ContactBook contactList, Ui ui,
                       String firstParam, HashMap<String, String> params) throws ApunableException {

        assert !firstParam.isEmpty() : "Umm, I think you forgot to provide a date time :)";

        LocalDateTime inputDate = DateTimeUtil.tryParse(firstParam);

        assert inputDate != null
                : "I used up all my knowledge and still can't recognize this date, consider using dd-mm-yyyy HH:mm";

        List<Task> occuringTasks = Stream.iterate(0, i -> i < taskList.size(), i -> i + 1)
                .map(i -> taskList.get(i)).filter(task -> {
                    Boolean isOcurring = task.isOcurringAt(inputDate);
                    return isOcurring != null && isOcurring == true;
                }).toList();

        if (occuringTasks.isEmpty()) {
            ui.echo("(no tasks occuring at given date time)");
        } else {
            ui.echo("Here are the tasks occuring on this date:");

            Stream.iterate(0, i -> i < occuringTasks.size(), i -> i + 1).forEach(i -> {
                ui.echo(String.format("%d.%s", i + 1, occuringTasks.get(i)));
            });
        }
    }
}
