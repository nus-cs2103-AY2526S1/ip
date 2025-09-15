
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
    private final String descriptionDate;
    private static final String DEADLINE_FORMAT_ERROR = "Deadline format is wrong. Example: deadline [desc] /by [time]";

    /**
     * Constructs a deadline command with description and date that it is due.
     *
     * @param descriptionDate of the deadline command including date.
     */
    public DeadlineCommand(String descriptionDate) {
        super(CommandHandle.CommandType.DEADLINE);

        this.descriptionDate = descriptionDate.trim();
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws RafayelException {
        if (descriptionDate.isEmpty()) {
            throw new RafayelException(DEADLINE_FORMAT_ERROR + "Please add in the description of the Deadline task.");
        }
        if (!descriptionDate.contains("/by")) {
            throw new RafayelException(DEADLINE_FORMAT_ERROR);
        }

        String[] taskInfo = descriptionDate.split("/by ");
        String description = taskInfo[0].trim();
        LocalDateTime by = handleReadDate(taskInfo[1].trim());

        Deadline newTask = new Deadline(description, by);
        tasks.add(newTask);
        storage.save(tasks.getAll());

        return getNewTaskString(newTask, tasks.getSize());
    }
}
