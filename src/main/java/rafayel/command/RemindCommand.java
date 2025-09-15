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

public class RemindCommand extends Command {

    /**
     * Constructs a Remind Command
     */
    public RemindCommand() {
        super(CommandHandle.CommandType.REMIND);
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws RafayelException {
        if (tasks.getSize() <= 0) {
            return "You have no tasks in the list!";
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

        String upcomingReminders = reminders.isEmpty() ? ""
                : "Upcoming Deadlines:\n" + IntStream.range(0, reminders.size())
                        .mapToObj(i -> (i + 1) + ". " + reminders.get(i).toString()).collect(Collectors.joining("\n"));
        String overdueReminders = overdue.isEmpty() ? ""
                : "OVERDUE TASKS:\n" + IntStream.range(0, overdue.size())
                        .mapToObj(i -> (i + 1) + ". " + overdue.get(i).toString()).collect(Collectors.joining("\n"));
        return overdueReminders + upcomingReminders;
    }
}
