package command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import task.Event;
import task.Task;
import ui.Lmbd;

/**
 * Represents a command to create and add a new {@link Event} task to the Lmbd
 * application's task list. This command requires a task description, a start
 * date with "/from", and an end date with "/to".
 */
public class EventCommand extends Command {
    public EventCommand() {
        super("event", 1, "Creates a new EVENT task");
    }

    @Override
    String run(Lmbd lmbd, String[] args) {
        StringBuilder name = new StringBuilder();
        int curr = 0;
        while (curr < args.length && !args[curr].equals("/from")) {
            name.append(args[curr] + " ");
            curr += 1;
        }

        if (name.length() == 0) {
            return "The event task name cannot be empty.";
        }

        name.deleteCharAt(name.length() - 1);

        if (curr == args.length) {
            return "Expected /from, reached end of line instead";
        }

        curr += 1;
        StringBuilder from = new StringBuilder();
        while (curr < args.length && !args[curr].equals("/to")) {
            from.append(args[curr] + " ");
            curr += 1;
        }

        if (from.length() == 0) {
            return "The '/from' date cannot be empty.";
        }

        from.deleteCharAt(from.length() - 1);

        if (curr == args.length) {
            return "Expected /to, reached end of line instead";
        }

        curr += 1;
        StringBuilder to = new StringBuilder();
        while (curr < args.length) {
            to.append(args[curr] + " ");
            curr += 1;
        }

        if (to.length() == 0) {
            return "The '/to' date cannot be empty.";
        }

        to.deleteCharAt(to.length() - 1);

        LocalDate fromDate;
        try {
            fromDate = LocalDate.parse(from);
        } catch (DateTimeParseException e) {
            return "Expected \"from\" to be in YYYY-MM-DD format";
        }
        LocalDate toDate;
        try {
            toDate = LocalDate.parse(to);
        } catch (DateTimeParseException e) {
            return "Expected \"to\" to be in YYYY-MM-DD format";
        }

        Task t = new Event(name.toString(), fromDate, toDate);
        lmbd.tasks.addTask(t);
        return String.format("Added the EVENT task %s, you now have %d tasks.", t.getTaskTitle(),
            lmbd.tasks.getTaskSize());
    }
}
