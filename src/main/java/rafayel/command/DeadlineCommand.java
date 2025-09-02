
package rafayel.command;

import java.time.LocalDateTime;

import rafayel.RafayelException;
import rafayel.storage.Storage;
import rafayel.task.Deadline;
import rafayel.task.TaskList;

/**
 * Handles the creation and addition of a new Deadline task.
 */
public class DeadlineCommand extends Command {
    private final String description;

    /**
     * Constructs a deadline command with description and date that it is due.
     *
     * @param description of the deadline command including date.
     */
    public DeadlineCommand(String description) {
        super(Parser.CommandType.DEADLINE);

        this.description = description.trim();
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws RafayelException {
        if (description.isEmpty()) {
            throw new RafayelException("Please add in the description of the Deadline task.");
        }
        if (!description.contains("/by")) {
            throw new RafayelException("Deadline format is wrong. Example: deadline [desc] /by [time]");
        }

        String[] taskInfo = description.split("/by ");
        LocalDateTime by = handleReadDate(taskInfo[1].trim());

        Deadline newTask = new Deadline(description, by);
        tasks.add(newTask);
        storage.save(tasks.getAll());

        return getNewTaskString(newTask, tasks.getSize());
    }
}
