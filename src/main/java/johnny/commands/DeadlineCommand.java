package johnny.commands;

import java.time.LocalDate;

import johnny.storage.Storage;
import johnny.tasklist.TaskList;
import johnny.tasks.DeadlineTask;
import johnny.tasks.Task;
import johnny.ui.Ui;

/**
 * A command that adds a deadline task to the TaskList.
 */
public class DeadlineCommand extends Command {
    protected String name;
    protected LocalDate deadline;

    /**
     * Creates a new DeadlineCommand instance with the task name and date of
     * deadline
     * 
     * @param name     Name of task
     * @param deadline Date of deadline
     */
    public DeadlineCommand(String name, LocalDate deadline) {
        this.name = name;
        this.deadline = deadline;
    }

    public String getName() {
        return this.name;
    }

    public LocalDate getDeadline() {
        return this.deadline;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task newTask = new DeadlineTask(this.name, this.deadline);
        tasks.addTask(newTask);
        return ui.printAddTaskMessage(tasks, newTask);
    }

    @Override
    public boolean isBye() {
        return false;
    }
}
