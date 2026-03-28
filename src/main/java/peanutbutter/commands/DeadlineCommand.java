package peanutbutter.commands;

import peanutbutter.exceptions.JuinException;
import peanutbutter.tasks.Deadline;
import peanutbutter.tasks.Task;
import peanutbutter.tasks.TaskList;
import peanutbutter.ui.Ui;

/**
 * Represents a command to create and add a Deadline task.
 */
public class DeadlineCommand extends Command {
    private final String args;

    /**
     * Creates a new DeadlineCommand with the given arguments.
     *
     * @param args the arguments containing description and due date
     */
    public DeadlineCommand(String args) {
        this.args = args;
    }

    /**
     * Executes the DeadlineCommand.
     *
     * @param taskList the list of tasks
     * @param ui       the user interface for displaying messages
     * @throws JuinException if the arguments are invalid or the deadline cannot be created
     */
    @Override
    public boolean run(TaskList taskList, Ui ui) throws JuinException {
        if (args.isBlank()) {
            throw new JuinException("The description of a deadline cannot be empty!");
        }

        String[] parts = args.split(" /by", 2);
        if (parts[0].isBlank() || parts.length < 2 || parts[1].isBlank()) {
            throw new JuinException("Deadline requires both a description and a /by date!");
        }

        try {
            Task deadline = new Deadline(parts[0].trim(), parts[1].trim());
            taskList.addTask(deadline);
            ui.addTaskMessage(taskList, deadline);
        } catch (java.time.format.DateTimeParseException e) {
            throw new JuinException("Invalid date/time format! Use yyyy-MM-dd or yyyy-MM-dd HHmm");
        } catch (IllegalArgumentException e) {
            throw new JuinException("Invalid date/time format. Use yyyy-MM-dd or yyyy-MM-dd HHmm");
        }
        return false;
    }
}