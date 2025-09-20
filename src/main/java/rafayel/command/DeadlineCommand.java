
package rafayel.command;

import java.time.LocalDateTime;

import rafayel.RafayelException;
import rafayel.storage.Storage;
import rafayel.task.Deadline;
import rafayel.task.TaskList;

/**
 * Represents a command that creates and adds a new Deadline task.
 * A deadline requires a description and a due date (/by).
 */
public class DeadlineCommand extends Command {

    /** Error message when deadline format is invalid. */
    public static final String DEADLINE_FORMAT_ERROR = "A deadline must be set with 'deadline [your task] /by [time]'"
            + ". \nThis isn't abstract art — precision is key!";

    /** Error message when the description is empty */
    public static final String EMPTY_DESC_ERROR = "A blank canvas? How am I supposed to paint with no description? "
            + "Tell me what this deadline is for. ";

    /** Stores the description and date of the Deadline task. */
    private final String descriptionDate;


    /**
     * Constructs a deadline command with description and date that it is due.
     *
     * @param descriptionDate of the deadline command including date.
     */
    public DeadlineCommand(String descriptionDate) {
        super(CommandHandle.CommandType.DEADLINE);

        this.descriptionDate = descriptionDate.trim();
    }

    /**
     * Executes the deadline command by creating a {@link Deadline} task.
     *
     * @param tasks the current task list.
     * @param storage the storage handler.
     * @return confirmation message after adding the task.
     * @throws RafayelException if input format is invalid or parsing fails.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws RafayelException {
        if (descriptionDate.isEmpty()) {
            throw new RafayelException(EMPTY_DESC_ERROR + DEADLINE_FORMAT_ERROR);
        }
        if (!descriptionDate.contains("/by ")) {
            throw new RafayelException(DEADLINE_FORMAT_ERROR);
        }

        String[] taskInfo = descriptionDate.split("/by", 2);

        if (taskInfo.length < 2 || taskInfo[1].trim().isEmpty()) {
            throw new RafayelException(
                    "A deadline without a due date? How very... abstract. "
                            + "Please specify when this masterpiece is due. "
                            + DEADLINE_FORMAT_ERROR);
        }

        String description = taskInfo[0].trim();
        if (description.isEmpty()) {
            throw new RafayelException(EMPTY_DESC_ERROR + DEADLINE_FORMAT_ERROR);
        }
        LocalDateTime by = handleReadDate(taskInfo[1].trim());

        Deadline newTask = new Deadline(description, by);
        tasks.add(newTask);
        storage.save(tasks.getAll());

        return getNewTaskString(newTask, tasks.getSize());
    }
}
