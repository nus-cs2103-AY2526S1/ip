package khat.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import khat.Parser;
import khat.exception.KhatException;
import khat.storage.Storage;
import khat.task.TaskList;
import khat.ui.Ui;

/** Represents a command to filter deadline tasks on a specified date. */
public class DateCommand extends Command {

    private String dateString;

    /**
     * Constructs a date command with given date string.
     *
     * @param date Date to filter deadline tasks by.
     */
    public DateCommand(String date) {
        this.dateString = date;
    }

    /**
     * {@inheritDoc}
     *
     * Parses date string and filters tasks with deadline on that date.
     * @throws KhatException If date format is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KhatException {
        try {
            LocalDate date = Parser.parseDate(dateString);
            TaskList filteredTasks = tasks.getTasksOnDate(date);
            ui.showTasksOnDate(filteredTasks, date);
        } catch (DateTimeParseException e) {
            throw new KhatException("Invalid command! Please use dates in the format dd-MM-yyyy!");
        }
    }
}
