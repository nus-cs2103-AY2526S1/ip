package HawkerUncle.command;

import HawkerUncle.storage.Storage;
import HawkerUncle.task.Deadline;
import HawkerUncle.task.Event;
import HawkerUncle.task.Task;
import HawkerUncle.task.TaskList;
import HawkerUncle.ui.Ui;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class RemindCommand implements Command{


    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList reminders = getUpcomingTask(tasks);
        return Ui.showReminders(reminders);
    }

    private TaskList getUpcomingTask(TaskList tasks) {
        TaskList upcoming = new TaskList();

        for (int i = 0; i < tasks.size(); ++i) {
            Task task = tasks.get(i);
            if (!task.getDone() && isUpcoming(task)) {
                upcoming.add(task);
            }
        }

        return upcoming;
    }

    private boolean isUpcoming(Task task) {
        LocalDateTime now = LocalDateTime.now();
        if (task instanceof Deadline deadline) {
            return deadline.getBy().isAfter(now);
        } else if (task instanceof Event event) {
            return event.getFrom().isAfter(now) || event.getTo().isAfter(now);
        } else {
            return false;
        }
    }
}
