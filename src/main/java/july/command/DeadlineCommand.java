package july.command;

import java.util.ArrayList;

import july.data.Storage;
import july.error.InvalidDeadlineException;
import july.error.JulyException;
import july.tasks.Deadline;
import july.tasks.Task;
import july.ui.Ui;

/**
 * Command to create and add a new deadline task to the task list.
 * This command processes user input to create a deadline task with a specific
 * due date and adds it to the current task collection.
 */
public class DeadlineCommand extends Command {
    private final String argument;

    /**
     * Constructs a DeadlineCommand with the specified argument string.
     *
     * @param argument the string containing the deadline task description and due date
     */
    public DeadlineCommand(String argument) {
        super();
        this.argument = argument;
    }

    /**
     * Executes the deadline command to create and add a new deadline task.
     * Processes the argument to create a Deadline object, adds it to the task list,
     * and displays a confirmation message to the user.
     *
     * @param tasks the ArrayList of tasks to add the new deadline to
     * @param ui the user interface component for displaying output
     * @param storage the storage component for data persistence
     * @throws JulyException if the deadline format is invalid or cannot be processed
     */
    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) throws JulyException {
       try {
           Deadline deadline = Deadline.process(argument);
           tasks.add(deadline);
           storage.save(tasks);
           addResponses(
                   "Got it. I've added this deadline:",
                   "  " + deadline.toString(),
                   String.format("Now you have %d %s in the list.",
                           tasks.size(), tasks.size() == 1 ? "task" : "tasks")
           );
       } catch (InvalidDeadlineException e) {
           throw new JulyException(e.getMessage());
       }
    }
}
