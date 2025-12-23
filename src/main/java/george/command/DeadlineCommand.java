package george.command;

import george.exceptions.GeorgeException;
import george.task.TaskManager;

/**
 * Represents a command to add a deadline task to the task manager.
 * A deadline task consists of a description and a specific due date.
 */
public class DeadlineCommand extends Command {
    private final String description;
    private final String date;

    /**
     * Constructs a DeadlineCommand with the specified description and due date.
     *
     * @param description The description of the deadline task
     * @param date The due date of the deadline task
     */
    public DeadlineCommand(String description, String date) {
        this.description = description;
        this.date = date;
    }

    @Override
    public String execute(TaskManager manager) throws GeorgeException {
        return manager.addDeadlineTask(description, date);
    }

    @Override
    public String getCommandWord() {
        return "deadline";
    }
}
