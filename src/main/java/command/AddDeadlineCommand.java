package command;

import java.time.LocalDate;

import task.DeadlineTask;
import task.TaskList;
import ui.Ui;

/**
 * Command implementation for adding deadline tasks.
 *
 * Note: GitHub Copilot assisted in implementing date handling logic
 * and suggesting parameter validation patterns.
 */
public class AddDeadlineCommand extends Command {
    private final String description;
    private final LocalDate by;

    public AddDeadlineCommand(String description, LocalDate by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public String execute(TaskList tasks, Ui ui) {
        DeadlineTask deadlineTask = new DeadlineTask(description, by);
        tasks.add(deadlineTask);
        return "This task has been successfully added:\n" + deadlineTask.toString();
    }
}