package evansbot.command;

import java.time.LocalDate;

import evansbot.Exceptions.EvansBotException;
import evansbot.task.Storage;
import evansbot.task.TaskList;
import evansbot.ui.Ui;

/**
 * Represents a command to view the schedule of the task on a specific date.
 * When executed, this command Lists the of dates in TaskList.
 */
public class ViewScheduleCommand extends Command {
    private final LocalDate date;

    public ViewScheduleCommand(LocalDate date) {
        this.date = date;
    }

    /**
     * Executes the command to list tasks in the TaskList on the date specified.
     * If either the date given is invalid, throws an EvansBotException.
     *
     * @param tasks TaskList in which the new task will be added.
     * @param ui User interface to interact with the user.
     * @param storage Storage used to save the updated task list.
     * @return String of list with task on specified date.
     * @throws EvansBotException If the date provided is invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws EvansBotException {
        return tasks.getTasksForDate(date);
    }
}
