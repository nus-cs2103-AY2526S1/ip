package itachi.command;

import java.io.IOException;

import itachi.ItachiException;
import itachi.Storage;
import itachi.Ui;
import itachi.task.Deadline;
import itachi.task.TaskList;

/**
 * Represents a command to add a {@link Deadline} task to the task list.
 * <p>
 * When executed, this command creates a {@link Deadline} object with
 * the inputted description and deadline and adds it to the {@link TaskList}.
 * It then saves the updated list to storage and via {@link Ui}, shows
 * messages that confirms the command is executed successfully
 */
public class DeadlineCommand extends Command {
    private String description;
    private String by;

    /**
     * Constructs a {@code DeadlineCommand}
     * with the given description and deadline.
     *
     * @param description the description of the deadline task
     * @param by the deadline date in string format
     */
    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Executes this command: adds a {@link Deadline} to the task list,
     * saves the tasks, and displays confirmation messages.
     *
     * @param tasks the {@link TaskList} where the new task will be added
     * @param ui the {@link Ui} used to display messages to the user
     * @param storage the {@link Storage} used to save the updated task list
     * @throws ItachiException if the command fails due to invalid input or state
     * @throws IOException if saving the task list fails
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ItachiException, IOException {
        assert tasks != null : "Task list should never be null";
        Deadline deadline = new Deadline(this.description, this.by);
        tasks.add(deadline);
        storage.save(tasks.getTasks());
        ui.showLine();
        ui.showMessage("Got it. I've added this task:");
        ui.showMessage(deadline.toString());
        ui.showMessage("Now you have " + tasks.size() + " tasks in the list!");
        ui.showLine();
        if (this.isUndoable()) {
            CommandHistory.getInstance().push(this);
        }
    }

    @Override
    public void undo(TaskList tasks, Ui ui, Storage storage) throws ItachiException, IOException {
        tasks.remove(tasks.size() - 1);
        storage.save(tasks.getTasks());
        ui.showMessage("Undo Deadline: " + description);
    }
}
