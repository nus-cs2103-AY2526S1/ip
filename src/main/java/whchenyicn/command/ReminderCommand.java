package whchenyicn.command;

import whchenyicn.exceptions.WhchenyicnException;
import whchenyicn.task.Task;
import whchenyicn.task.TaskList;
import whchenyicn.task.Deadline;
import whchenyicn.ui.Storage;
import whchenyicn.ui.Ui;

import java.time.LocalDate;

/**
 * A command to get reminders about tasks
 */
public class ReminderCommand extends Command {
    private String days;

    /**
     * Creates a command to remind users about upcoming tasks
     *
     * @param days Number of days to be reminded when task is due.
     */
    public ReminderCommand(String days) {
        this.days = days;
    }

    @Override
    public String execute(TaskList tlist, Ui ui, Storage storage) throws WhchenyicnException {
        int days = 31;
        if (this.days != null && !this.days.isEmpty()) {
            try {
                days = Integer.parseInt(this.days.trim());
            } catch (NumberFormatException e) {
                throw new WhchenyicnException("Invalid number of days.");
            }
        }

        LocalDate today = LocalDate.now();
        LocalDate cutoff = today.plusDays(days);

        TaskList reminders = new TaskList();

        for (Task t : tlist.asList()) {
            if (t instanceof Deadline) {
                LocalDate date = ((Deadline) t).getDate();
                if (!date.isBefore(today) && !date.isAfter(cutoff)) {
                    reminders.add(t);
                }
            }
        }

        return ui.printReminders(reminders, days);
    }
}