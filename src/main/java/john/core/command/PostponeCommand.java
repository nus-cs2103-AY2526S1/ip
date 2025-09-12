package john.core.command;

import john.core.exception.ParseException;
import john.model.Event;
import john.model.Deadline;
import john.model.Task;
import john.model.TaskList;
import john.ports.Storage;
import john.ports.Ui;

import java.time.Duration;
import java.time.LocalDateTime;

public final class PostponeCommand implements Command {
    private final int oneBased;
    private final LocalDateTime newBy;    // for Deadline
    private final LocalDateTime newFrom;  // for Event
    private final LocalDateTime newTo;    // for Event

    public PostponeCommand(int oneBased, LocalDateTime newBy, LocalDateTime newFrom, LocalDateTime newTo) {
        this.oneBased = oneBased;
        this.newBy = newBy;
        this.newFrom = newFrom;
        this.newTo = newTo;
    }

    @Override
    public CommandResult execute(TaskList tasks, Storage storage, Ui ui) throws ParseException {
        int idx = oneBased - 1;
        if (idx < 0 || idx >= tasks.size()) {
            return CommandResult.ok("Please provide a valid task number between 1 and " + tasks.size() + ".");
        }

        Task original = tasks.get(idx);
        Task updated;

        if (original instanceof Deadline d) {
            if (newFrom != null || newTo != null) {
                throw new ParseException("Deadline supports only /by <when>.");
            }
            updated = new Deadline(d.getTitle(), newBy);

        } else if (original instanceof Event e) {
            if (newBy != null) {
                throw new ParseException("Event supports /from and /to, not /by.");
            }

            LocalDateTime start = (newFrom != null) ? newFrom : e.getFrom();
            LocalDateTime end;

            if (newFrom != null && newTo != null) {
                end = newTo;
            } else if (newFrom != null) {
                Duration dur = Duration.between(e.getFrom(), e.getTo());
                end = start.plus(dur);
            } else if (newTo != null) {
                end = newTo;
            } else {
                throw new ParseException("Usage: postpone <task-number> /from <when> [/to <when>] or /to <when>");
            }

            if (end.isBefore(start)) {
                throw new ParseException("End time must be after start time.");
            }
            updated = new Event(e.getTitle(), start, end);

        } else {
            return CommandResult.ok("Task " + oneBased + " is not postponable.");
        }

        if (original.isCompleted()) {
            updated.markAsComplete(); // preserve done-flag
        }
        tasks.set(idx, updated);
        storage.save(tasks);

        return CommandResult.ok("Noted. I've updated this task:\n" + updated);
    }
}
