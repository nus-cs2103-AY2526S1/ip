package echo.command;

import java.time.LocalDateTime;

import echo.Echo;
import echo.task.Event;
import echo.task.Task;

/**
 * Represents a command to add an event task. A <code>EventCommand</code> object is a
 * subtype of <code>Command</code> and stores a string instruction,
 * string from and string to.
 */
public class EventCommand extends Command {
    private final String instruction;
    private final LocalDateTime from;
    private final LocalDateTime to;

    public EventCommand(Echo echo, String instruction, LocalDateTime from, LocalDateTime to) {
        super(echo);
        this.instruction = instruction;
        this.from = from;
        this.to = to;
    }

    @Override
    public String execute() {
        StringBuilder msg = new StringBuilder();
        Task t = new Event(this.instruction, this.from, this.to);
        echo.getTasklist().addTask(t);

        msg.append(echo.getUi().showAddTask(t));
        msg.append(echo.getUi().showListSize(echo.getTasklist()));
        return msg.toString();
    }
}
