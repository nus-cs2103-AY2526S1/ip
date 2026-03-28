package bugsbunny.commands;

import java.time.LocalDateTime;
import java.util.ArrayList;

import bugsbunny.app.Ui;
import bugsbunny.exception.BugsBunnyException;
import bugsbunny.parsers.DateTimeParser;
import bugsbunny.storage.Storage;
import bugsbunny.tasks.Task;
import bugsbunny.tasks.TaskList;

/**
 * Gets the list of tasks that are due by a specified date and time.
 */
public class DueCommand extends Command {
    private static final String DUE_USAGE = "Usage: due <yyyy-mm-dd hhmm>";

    public DueCommand(String args) {
        super(args);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BugsBunnyException {
        super.ensureValidArgs(super.args, DueCommand.DUE_USAGE);
        LocalDateTime dueDate =
                DateTimeParser.parseStringToDateTime(super.args.trim(), DateTimeParser.INPUT_TO_DATE_TIME);

        ArrayList<Task> dueTasks = tasks.getTasksDueBy(dueDate);
        String output;
        if (dueTasks.isEmpty()) {
            output = "You have no tasks that are due by "
                    + dueDate.format(DateTimeParser.DATE_TIME_STRING_FORMATTER);
        } else {
            output = "Here are the tasks that are due by "
                    + dueDate.format(DateTimeParser.DATE_TIME_STRING_FORMATTER);
            for (int i = 0; i < dueTasks.size(); i++) {
                output += String.format("\n %d. %s", i + 1, dueTasks.get(i));
            }
        }
        return output;
    }
}
