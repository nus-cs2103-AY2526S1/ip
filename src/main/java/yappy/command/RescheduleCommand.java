package yappy.command;

import java.time.LocalDateTime;

import yappy.backend.Storage;
import yappy.backend.TaskList;
import yappy.exception.YappyException;
import yappy.task.Event;
import yappy.ui.Ui;

/**
 * Represents a command to reschedule a <code>Event</code> task.
 * When executed, it changes the 'to' and 'from' date for an existing event
 * any other type of task will return an error.
 */
public class RescheduleCommand extends Command {
    private final int index;
    private final LocalDateTime toDate;
    private final LocalDateTime fromDate;

    /**
     * Constructor to create new RescheduleCommand object
     * and set the index of the task to be rescheduled and
     * the new to and from dates.
     * @param index The index of the task in the task list.
     * @param fromDate The new start date.
     * @param toDate The new end date.
     */
    public RescheduleCommand(int index, LocalDateTime fromDate, LocalDateTime toDate) {
        this.index = index;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }


    /**
     * Reschedule an <code>Event</code> task to a new to and from date.
     * @param taskList The tasks which have been added to Yappy.
     * @param ui The ui object that controls interactions with a user.
     * @param storage The storage object that handles storing to and reading from disk.
     * @return Message to be printed to user, either success or error.
     * @throws YappyException If the input index is out of bounds.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws YappyException {
        if (!(taskList.getTasks().get(this.index) instanceof Event)) {
            throw new YappyException("You can only reschedule an event task!");
        }
        try {
            Event task = (Event) taskList.getTasks().get(this.index);
            return task.reschedule(this.fromDate, this.toDate);
        } catch (IndexOutOfBoundsException e) {
            throw new YappyException("Index out of bounds! Try again with a valid index");
        }
    }
}
