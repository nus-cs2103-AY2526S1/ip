package snowy.command;

import snowy.task.Task;
import snowy.TaskList;
import snowy.Storage;

public class MarkCommand extends Command {
    private final int taskNum;
    private final boolean isMarkDone;

    public MarkCommand(int taskNum, boolean isMarkDone) {
        this.taskNum = taskNum;
        this.isMarkDone = isMarkDone;
    }

    /**
     * Marks the task corresponding to the task number in the task list as done or not done.
     * @param tasks
     * @param storage
     * @return A string that shows what task is marked as done or not done
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        Task task = tasks.getTask(taskNum);
        if (isMarkDone) {
            task.mark();
            storage.save(tasks.getTaskList());
            return "Sure! I've marked this task as done: " + task;
        } else {
            task.unmark();
            storage.save(tasks.getTaskList());
            return "Okie, I've marked this task as not done yet: " + task;
        }
    }
}
