package fatty.command;

import java.time.LocalDateTime;

import fatty.FattyException;
import fatty.Storage;
import fatty.TaskList;
import fatty.Ui;
import fatty.task.DeadlineTask;
import fatty.task.ToDoTask;

/**
 * Command to create deadline task
 */
public class DeadlineCommand extends Command {
    private final String description;
    private final LocalDateTime by;

    /**
     * Create deadline task in tasklist
     * @param description task description
     * @param by deadline date
     */
    public DeadlineCommand(String description, LocalDateTime by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws FattyException {
        DeadlineTask deadline = new DeadlineTask(description, by);

        assert deadline instanceof DeadlineTask : "task should be DeadlineTask";
        taskList.addTask(deadline);
        storage.saveTasks(taskList);
        return ui.showTaskAdded(deadline, taskList);
    }
}
