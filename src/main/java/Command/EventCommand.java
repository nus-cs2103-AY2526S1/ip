package Command;

import JohnException.JohnException;

import Parser.DateParser;

import Task.TaskItem;
import Task.TaskList;
import Task.Event;

import UI.Ui;

import java.time.LocalDate;

/**
 * Command to add an event task to the task list.
 */
public class EventCommand extends Command {
    private String description;
    private String from;
    private String to;

    /**
     * Creates an event command while checking that from and to dates are present in input.
     *
     * @param content Contents of deadline task.
     */
    public EventCommand(String content) throws JohnException {
        if (!content.contains("/from") || !content.contains("/to") || content.indexOf("/from") > content.indexOf("/to")) {
            throw new JohnException("☹ OOPS!!! The description of an event must have '/from' then '/to'.");
        }
        String[] part1 = content.split("/from", 2);
        this.description = part1[0].trim();
        String[] part2 = part1[1].split("/to", 2);
        this.from = part2[0].trim();
        this.to = part2[1].trim();
    }

    /**
     * Executes the command by creating and adding an event task to task list.
     * Validates that the description is non-empty, parses the from and to strings
     * into LocalDate.
     *
     * @param tasks Task list to mutate.
     * @param ui UI used to present feedback to the user.
     * @throws JohnException If the description is empty or the dates cannot be parsed.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws JohnException {
        assert tasks != null : "Command: tasks must not be null";
        assert ui != null : "Command: ui must not be null";
        if (description.isEmpty()) {
            throw new JohnException("The event description cannot be empty.");
        }
        LocalDate fromDate = DateParser.parseUser(from);
        LocalDate toDate = DateParser.parseUser(to);

        TaskItem t = new Event(description, false, fromDate, toDate);
        tasks.add(t);
        ui.showAdded(t, tasks.getSize());
    }
}
