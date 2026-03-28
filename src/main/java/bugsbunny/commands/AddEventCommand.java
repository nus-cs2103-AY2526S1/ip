package bugsbunny.commands;

import java.time.LocalDateTime;

import bugsbunny.app.Ui;
import bugsbunny.exception.BugsBunnyException;
import bugsbunny.parsers.DateTimeParser;
import bugsbunny.storage.Storage;
import bugsbunny.tasks.Event;
import bugsbunny.tasks.Task;
import bugsbunny.tasks.TaskList;


/**
 * Adds a {@link bugsbunny.tasks.Event} task to the list and saves the updated state.
 */
public class AddEventCommand extends Command {
    private static final String EVENT_FROM_SEPARATOR = "/from";
    private static final String EVENT_TO_SEPARATOR = "/to";
    private static final String EVENT_USAGE = "Usage: event <description> /from <start> /to <end>";

    public AddEventCommand(String args) {
        super(args);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BugsBunnyException {
        ensureValidArgs(super.args, EVENT_USAGE);
        String[] parsedEventArgs = parseEventArgs(super.args);
        String taskName = parsedEventArgs[0];
        String from = parsedEventArgs[1];
        String to = parsedEventArgs[2];
        LocalDateTime fromDateTime = DateTimeParser.parseStringToDateTime(from, DateTimeParser.INPUT_TO_DATE_TIME);
        LocalDateTime toDateTime = DateTimeParser.parseStringToDateTime(to, DateTimeParser.INPUT_TO_DATE_TIME);

        Task t = new Event(taskName, fromDateTime, toDateTime);
        String output = tasks.addTask(t);
        output += super.saveOrAppendError(tasks, ui, storage);
        return output;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void ensureValidArgs(String args, String usageError) throws BugsBunnyException {
        if (args == null
                || args.isBlank()
                || !args.contains(AddEventCommand.EVENT_FROM_SEPARATOR)
                || !args.contains(AddEventCommand.EVENT_TO_SEPARATOR)) {
            throw new BugsBunnyException(usageError);
        }
    }

    /**
     * Parse the input string to a String array { Task description, Start Date , End Date }.
     *
     * @param args Input string by the user.
     * @return String array { Task description, Start Date , End Date }.
     * @throws BugsBunnyException If args is invalid.
     */
    private String[] parseEventArgs(String args) throws BugsBunnyException {
        String[] fromSplit = super.args.split(AddEventCommand.EVENT_FROM_SEPARATOR, 2);
        String taskName = fromSplit[0].trim();

        // no taskName included
        if (fromSplit.length < 2 || taskName.isEmpty()) {
            throw new BugsBunnyException(AddEventCommand.EVENT_USAGE);
        }

        String[] toSplit = fromSplit[1].trim().split(AddEventCommand.EVENT_TO_SEPARATOR, 2);
        String from = toSplit[0].trim();

        // nothing after /from
        if (toSplit.length < 2 || from.isEmpty()) {
            throw new BugsBunnyException(AddEventCommand.EVENT_USAGE);
        }

        String to = toSplit[1].trim();

        // nothing after /to
        if (to.isEmpty()) {
            throw new BugsBunnyException(AddEventCommand.EVENT_USAGE);
        }

        return new String[] {taskName, from, to};
    }
}
