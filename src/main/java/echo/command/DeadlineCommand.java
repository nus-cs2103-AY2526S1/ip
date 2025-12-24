package echo.command;

import java.time.LocalDateTime;

import echo.Echo;
import echo.task.Deadline;
import echo.task.Task;

/**
 * Represents a command to add a deadline task. A <code>DeadlineCommand</code> object
 * is a subtype of <code>Command</code> and stores a string instruction and a string due date
 */
public class DeadlineCommand extends Command {
    private final String instruction;
    private final LocalDateTime by;

    public DeadlineCommand(Echo echo, String instruction, LocalDateTime by) {
        super(echo);
        this.instruction = instruction;
        this.by = by;
    }

    @Override
    public String execute() {
        StringBuilder msg = new StringBuilder();
        Task t = new Deadline(instruction, by);
        echo.getTasklist().addTask(t);

        msg.append(echo.getUi().showAddTask(t));
        msg.append(echo.getUi().showListSize(echo.getTasklist()));

        return msg.toString();
    };
}
