package rafayel.command;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import rafayel.RafayelException;
import rafayel.storage.Storage;
import rafayel.task.Task;
import rafayel.task.TaskList;

/**
 * Represents a command that reminds the user of
 * upcoming deadlines and overdue tasks.
 * Scans all tasks with deadlines, then classifies them into:
 * 1. Upcoming deadlines (within 24 hours)
 * 2. Overdue tasks (past deadline and not done)
 */
public class RemindCommand extends Command {

    /** Stores the string when there's no tasks. */
    public static final String NO_TASK_FOR_REMINDERS = "I can't remind you when there are no tasks in the list :<";
    // for comparison purposes when reminding the user.

    /**
     * Constructs a Remind Command
     */
    public RemindCommand() {
        super(CommandHandle.CommandType.REMIND);
    }

    /**
     * Gets a formatted string for both reminders and overdue tasks.
     * Uses Java stream to easily get all tasks in the list.
     *
     * @param reminderType either reminders or overdue tasks.
     * @param tasks list of tasks that needs to be formatted.
     * @return formatted string.
     */
    private String getStringReminders(String reminderType, Task... tasks) {
        if (tasks == null || tasks.length == 0) {
            return "";
        }

        String tasksList = IntStream.range(0, tasks.length).mapToObj(i -> (i + 1) + ". " + tasks[i].toString())
                .collect(Collectors.joining("\n"));

        return reminderType + "\n" + tasksList;
    }

    // Overloaded version for ArrayList compatibility
    private String getStringReminders(String reminderType, ArrayList<Task> tasks) {
        if (tasks == null || tasks.isEmpty()) {
            return "";
        }
        return getStringReminders(reminderType, tasks.toArray(new Task[0]));
    }

    /**
     * Executes the Remind command by returning a list of tasks
     * with upcoming deadlines and overdue deadlines.
     *
     * @param tasks   the task list
     * @param storage the storage handler
     * @return a formatted string containing reminders
     * @throws RafayelException if an error occurs while processing
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws RafayelException {
        if (tasks.getSize() <= 0) {
            return NO_TASK_FOR_REMINDERS;
        }
        ArrayList<Task> reminders = new ArrayList<>();
        ArrayList<Task> overdue = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();

        for (Task task : tasks.getAll()) {
            if (task.hasDeadline() && !task.isDone()) {
                LocalDateTime deadline = task.getDeadline();
                long hoursUntilDeadline = ChronoUnit.HOURS.between(now, deadline);

                if (hoursUntilDeadline <= 24 && hoursUntilDeadline >= 0) {
                    reminders.add(task);
                } else if (hoursUntilDeadline < 0) {
                    overdue.add(task);
                }
            }
        }
        if (reminders.isEmpty() && overdue.isEmpty()) {
            return "No upcoming deadlines nor overdue tasks! :D";
        }

        String upcomingReminders = getStringReminders("Upcoming deadlines:", reminders);
        String overdueReminders = getStringReminders("OVERDUE TASKS:", overdue);

        return overdueReminders + (!upcomingReminders.isEmpty() ? "\n\n" : "") + upcomingReminders;
    }
}
