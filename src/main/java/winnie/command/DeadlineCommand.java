package winnie.command;

import winnie.storage.Storage;
import winnie.task.Deadline;
import winnie.tasklist.TaskList;
import winnie.ui.Ui;

public class DeadlineCommand extends Command {
    private String description;
    private String deadline;

    public DeadlineCommand(String description, String deadline) {
        this.description = description;
        this.deadline = deadline;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Deadline deadline = new Deadline(description, this.deadline);
            tasks.addTask(deadline);
            ui.showTaskAdded(deadline, tasks.getTaskCount());
            storage.saveTasksToFile(tasks);
        } catch (Exception e) {
            ui.showError(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
