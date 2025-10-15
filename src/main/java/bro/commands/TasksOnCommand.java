package bro.commands;

import bro.tasks.Deadline;
import bro.tasks.Event;
import bro.tasks.Task;
import bro.tasks.Tasks;

/**
 * Represents a command to list tasks occurring on a specific date.
 */
public class TasksOnCommand extends Command {
    private String date;

    /**
     * Creates a new TasksOnCommand with the given date.
     *
     * @param date The date to filter tasks by.
     */
    public TasksOnCommand(String date) {
        this.date = date;
    }

    /**
     * Executes the command and returns the result as a string.
     *
     * @return The result of executing the command.
     */
    @Override
    public String execute(Tasks tasks) {
        if (date.isBlank()) {
            return "Bro... Can you do me a solid and give me a date?";
        }

        String output = "Here bro, these are the tasks on " + date + ":";

        for (int i = 0; i < tasks.getSize(); i++) {
            output += getInfoIfTaskOn(tasks, date, i);
        }

        return output;
    }

    /**
     * Helper method to get task info if it occurs on the specified date.
     *
     * @param tasks The task list to check.
     * @param date The date to check in the format "d/M/yyyy".
     * @param index The index of the task in the task list.
     * @return A string representation of the task if it occurs on the specified date, an empty string otherwise.
     */
    private String getInfoIfTaskOn(Tasks tasks, String date, int index) {
        String output = "";
        Task task = tasks.getEntry(index);
        if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            if (deadline.isOnDate(date)) {
                output = String.format("\n%d. %s", index + 1, deadline);
            }
        } else if (task instanceof Event) {
            Event event = (Event) task;
            if (event.isOnDate(date)) {
                output = String.format("\n%d. %s", index + 1, event);
            }
        }
        return output;
    }
}
