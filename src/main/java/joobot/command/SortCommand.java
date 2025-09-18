package joobot.command;

import java.util.Comparator;

import joobot.main.Storage;
import joobot.task.Task;
import joobot.task.TaskList;

/**
 * Represents a command that sorts all tasks alphabetically by description.
 */
public class SortCommand extends Command {

    @Override
    public String execute(TaskList tasks, Storage storage) {
        tasks.sort(Comparator.comparing(Task::getDescription, String.CASE_INSENSITIVE_ORDER));
        storage.save(tasks.getAllTasks()); // persist the sorted list
        return "Tasks have been sorted alphabetically!";
    }
}
