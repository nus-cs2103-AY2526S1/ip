package bugsbunny.commands;

import java.time.LocalDateTime;

import bugsbunny.app.Ui;
import bugsbunny.exception.BugsBunnyException;
import bugsbunny.parsers.DateTimeParser;
import bugsbunny.storage.Storage;
import bugsbunny.tasks.Deadline;
import bugsbunny.tasks.Task;
import bugsbunny.tasks.TaskList;

/**
 * Adds a {@link bugsbunny.tasks.Deadline} task to the list and saves the updated state.
 */
public class AddDeadlineCommand extends Command {
    private static final String DEADLINE_SEPARATOR = "/by";
    private static final String DEADLINE_USAGE = "Usage: deadline <description> /by <date>";

    public AddDeadlineCommand(String args) {
        super(args);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BugsBunnyException {
        ensureValidArgs(super.args, AddDeadlineCommand.DEADLINE_USAGE);
        String[] bySplit = parseDeadlineArgs(super.args);
        String taskName = bySplit[0].trim();
        String by = bySplit[1].trim();
        LocalDateTime byDateTime = DateTimeParser.parseStringToDateTime(by, DateTimeParser.INPUT_TO_DATE_TIME);

        Task t = new Deadline(taskName, byDateTime);
        String output = tasks.addTask(t);
        output += super.saveOrAppendError(tasks, ui, storage);
        return output;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void ensureValidArgs(String args, String usageError) throws BugsBunnyException {
        if (args == null || args.isBlank() || !args.contains(AddDeadlineCommand.DEADLINE_SEPARATOR)) {
            throw new BugsBunnyException(usageError);
        }
    }

    /**
     * Parse the input string to a String array { Task description, Date }.
     *
     * @param args Input string by the user.
     * @return String array { Task description, Date }.
     * @throws BugsBunnyException If args is invalid.
     */
    private String[] parseDeadlineArgs(String args) throws BugsBunnyException {
        String[] bySplit = args.trim().split(AddDeadlineCommand.DEADLINE_SEPARATOR, 2);
        if (bySplit.length < 2 || bySplit[0].isBlank() || bySplit[1].isBlank()) {
            throw new BugsBunnyException(AddDeadlineCommand.DEADLINE_USAGE);
        }
        return bySplit;
    }
}
