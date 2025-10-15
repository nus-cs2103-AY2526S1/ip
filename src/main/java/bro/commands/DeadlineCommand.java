package bro.commands;

import bro.tasks.Deadline;
import bro.tasks.Tasks;
import bro.utils.FileIo;

/**
 * Represents a command to add a deadline task.
 */
public class DeadlineCommand extends Command {
    private String description;
    private String byDate;

    /**
     * Creates a new DeadlineCommand with the given description and due date.
     *
     * @param description The description of the deadline task.
     * @param byDate      The due date of the deadline task.
     */
    public DeadlineCommand(String description, String byDate) {
        this.description = description;
        this.byDate = byDate;
    }

    /**
     * Executes the command and returns the result as a string.
     *
     * @return The result of executing the command.
     */
    @Override
    public String execute(Tasks tasks) {
        Deadline deadline = new Deadline(description, byDate);
        String output = tasks.addTask(deadline);
        FileIo.addEntry(deadline.toDataString() + "\n");
        return output;
    }
}
