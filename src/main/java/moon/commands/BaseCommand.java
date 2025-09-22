package moon.commands;

import moon.models.TaskList;
import moon.parser.exceptions.ParseException;

/**
 * Abstract base class for all commands in the Moon chatbot.
 * <p>
 * Provides common metadata (task list and UI) and a shared error code.
 * Each concrete command must implement {@link #execute()}.
 */
public abstract class BaseCommand {
    private TaskList list;

    /**
     * Executes this command.
     *
     * @return confirmation message to be displayed to the user
     * @throws ParseException If the input could not be parsed properly
     */
    public abstract String execute() throws ParseException;

    /**
     * Sets metadata needed by this command before execution.
     *
     * @param list The current task list
     */
    public void setMetaData(TaskList list) {
        this.list = list;
    }

    /**
     * Returns the task list associated with this command.
     *
     * @return Task list
     */
    public TaskList getList() {
        return this.list;
    }
}
