package tinkerton.command;

import tinkerton.util.Ui;
import tinkerton.util.Date;
import tinkerton.core.TinkertonException;
import tinkerton.task.TaskList;
import tinkerton.storage.Save;

/**
 * Represents a command to show tasks occurring on a specific date.
 */
public class ShowCommand extends Command {
    private static final String DATE_REGEX = "\\d{4}-\\d{2}-\\d{2}";
    private static final String DEFAULT_TIME = "0000";

    /**
     * Constructs a ShowCommand with the full command string.
     *
     * @param fullCommand The full user input command string.
     */
    public ShowCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * Executes the show command, displaying tasks that occur on the specified date.
     *
     * @param tasks The list of tasks.
     * @param ui The user interface handler.
     * @param save The save handler for persisting tasks.
     * @throws TinkertonException If the command format is invalid or no tasks are found.
     * @return The farewell message.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Save save) throws TinkertonException {
        String fullCommand = super.getFull();
        String[] parts = fullCommand.split("/on");

        if (tasks.size() == 0) {
            throw new TinkertonException(
                    "I feel like your list is empty so there is no list to show...");
        } else if (!fullCommand.contains("/on")) {
            throw new TinkertonException("Show tasks on what date..");
        }

        if (parts.length < 2) {
            throw new TinkertonException("You seem to be missing some information...");
        }

        String date = parts[1].trim();

        if (!date.matches(DATE_REGEX)) {
            throw new TinkertonException("The format of your date should be yyyy-MM-dd!");
        }

        Date check = new Date(date + " " + DEFAULT_TIME);

        TaskList filtered = tasks.filter(t -> t.onDate(check));

        if (filtered.size() == 0) {
            throw new TinkertonException("No tasks on that day yay!");
        }

        StringBuilder result = new StringBuilder("Here are the tasks on that day:\n");

        for (int i = 0; i < filtered.size(); i++) {
            result.append((i + 1)).append(". ").append(filtered.get(i)).append("\n");
        }

        return result.toString();
    }

    /**
     * Indicates whether this command should exit the application.
     *
     * @return false, as show does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
