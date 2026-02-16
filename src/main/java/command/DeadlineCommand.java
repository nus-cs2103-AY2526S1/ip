package command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import task.Deadline;
import task.Task;
import ui.Lmbd;

/**
 * Represents a command to create and add a new {@link Deadline} task to the
 * Lmbd application's task list. This command requires a task description and a
 * due date specified with "/by".
 */
public class DeadlineCommand extends Command {
    public DeadlineCommand() {
        super("deadline", 1, "Creates a new DEADLINE task");
    }

    @Override
    String run(Lmbd lmbd, String[] args) {
        StringBuilder name = new StringBuilder();
        int curr = 0;
        while (curr < args.length && !args[curr].equals("/by")) {
            name.append(args[curr] + " ");
            curr += 1;
        }

        if (name.length() == 0) {
            return "The deadline task name cannot be empty.";
        }

        name.deleteCharAt(name.length() - 1);

        if (curr == args.length) {
            return "Expected /by, reached end of line instead";
        }

        curr += 1;
        StringBuilder by = new StringBuilder();
        while (curr < args.length) {
            by.append(args[curr] + " ");
            curr += 1;
        }

        if (by.length() == 0) {
            return "The '/by' date cannot be empty.";
        }

        by.deleteCharAt(by.length() - 1);

        LocalDate byDate;
        try {
            byDate = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            return "Expected a date in YYYY-MM-DD format";
        }

        Task t = new Deadline(name.toString(), byDate);
        lmbd.tasks.addTask(t);
        return String.format("Added the DEADLINE task %s, you now have %d tasks.", t.getTaskTitle(),
            lmbd.tasks.getTaskSize());
    }
}
