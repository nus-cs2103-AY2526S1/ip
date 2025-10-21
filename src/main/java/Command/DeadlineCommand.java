package Command;

import JohnException.JohnException;

import Parser.DateParser;

import Task.TaskItem;
import Task.TaskList;
import Task.Deadline;

import UI.Ui;

import java.time.LocalDate;

/**
 * Command to add a deadline task to the task list.
 */
public class DeadlineCommand extends Command {
    private String description;
    private String deadline;

    /**
     * Creates a deadline command while checking that deadline is present in input.
     *
     * @param content Contents of deadline task.
     */
    public DeadlineCommand(String content) throws JohnException {
        if (!content.contains("/by")) {
            throw new JohnException("☹ OOPS!!! The description of a deadline must have '/by'.");
        }
        String[] parts = content.split("/by", 2);

        this.description = parts[0].trim();
        this.deadline = parts[1].trim();
    }

    /**
     * Executes the command by creating and adding a deadline task to task list.
     * Validates that the description is non-empty, parses the deadline string
     * into a LocalDate.
     *
     * @param tasks Task list to mutate.
     * @param ui UI used to present feedback to the user.
     * @throws JohnException If the description is empty or the date cannot be parsed.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws JohnException {
        assert tasks != null : "Command: tasks must not be null";
        assert ui != null : "Command: ui must not be null";
        if (description.isEmpty()) {
            throw new JohnException("The deadline description cannot be empty.");
        }
        LocalDate deadlineDate = DateParser.parseUser(deadline);

        TaskItem t = new Deadline(description, false, deadlineDate);
        tasks.add(t);
        ui.showAdded(t, tasks.getSize());
    }
}
