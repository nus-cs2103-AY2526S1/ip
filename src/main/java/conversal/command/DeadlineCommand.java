package conversal.command;

import java.time.LocalDate;

import conversal.exception.ConversalException;
import conversal.storage.Storage;
import conversal.task.Deadline;
import conversal.task.Task;
import conversal.task.TaskList;
import conversal.ui.Ui;

/**
 * Represents a command to create and add a Deadline task
 * to the task list.
 *
 * The DeadlineCommand is created when the user input starts with "deadline ".
 *
 * It extracts the description and due date from the input,
 * creates a new Deadline task,
 * adds it to the TaskList,
 * saves the updated list to Storage,
 * and updates the user through Ui.
 */
public class DeadlineCommand implements Command {

    // Fields
    private String input;

    /**
     * Creates a DeadlineCommand using the user input.
     *
     * @param input the user input containing the description and due date of the deadline
     */
    public DeadlineCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the command to create and add a new Deadline task.
     *
     * If the description or due date are missing, or if the due date
     * cannot be parsed into a valid LocalDate, a ConversalException is thrown.
     * Else, the new task is added to the TaskList,
     * the list is saved via Storage,
     * and a confirmation message is displayed through Ui.
     *
     * @param tasks   the task list to add the new deadline into
     * @param storage the storage of tasks
     * @param ui      the UI to show the confirmation message
     * @throws ConversalException if the deadline description or date is missing or invalid
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws ConversalException {
        if (input.length() <= 9) {
            throw new ConversalException("Ah, I got it! " + ui.getInstructionDeadline());
        }
        if (!input.contains(" /by ")) {
            throw new ConversalException("Ah, I got it! " + ui.getInstructionDeadline());
        }
        String[] info = input.substring(9).split(" /by ");
        if (info.length < 2 || info[0].trim().isEmpty() || info[1].trim().isEmpty()) {
            throw new ConversalException("Ah, I got it! " + ui.getInstructionDeadline());
        }
        try {
            LocalDate dueDate = LocalDate.parse(info[1].trim());
            Task t = new Deadline(info[0], dueDate);
            tasks.addTask(t);
            storage.save(tasks.getList());
            ui.addMessage(t, tasks.size());
        } catch (Exception e) {
            throw new ConversalException("Ah, I got it! " + ui.getInstructionDeadline());
        }
    }

    /**
     * Returns whether this command exits the application.
     *
     * @return false because DeadlineCommand never exits the application
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
