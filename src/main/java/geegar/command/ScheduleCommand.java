package geegar.command;

import java.time.LocalDate;
import java.util.ArrayList;

import geegar.exception.GeegarException;
import geegar.gui.Gui;
import geegar.storage.Storage;
import geegar.task.Task;
import geegar.task.TaskList;

/**
 * A Command that shows the schedule of task based on input
 */
public class ScheduleCommand extends Command {
    private LocalDate date;

    public ScheduleCommand(LocalDate date) {
        assert date != null : "Date in ScheduleCommand must not be null";
        this.date = date;
    }

    @Override
    public void execute(TaskList tasks, Gui gui, Storage storage) throws GeegarException {

        assert tasks != null : "TaskList must not be null";
        assert gui != null : "Gui must not be null";
        assert storage != null : "Storage must not be null";

        gui.printSchedule();
        ArrayList<Task> tasksOnDate = tasks.showTasksOnDate(date);
        for (Task task : tasksOnDate) {
            gui.printTask(task);
        }
        if (tasksOnDate.size() == 0) {
            gui.printEmpty();
        }
    }
}
