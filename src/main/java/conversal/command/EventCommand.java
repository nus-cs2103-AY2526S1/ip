package conversal.command;

import conversal.exception.ConversalException;
import conversal.storage.Storage;
import conversal.task.Event;
import conversal.task.Task;
import conversal.task.TaskList;
import conversal.ui.Ui;

/**
 * Represents a command to create and add an Event task
 * to the task list.
 *
 * The EventCommand is created when the user input starts with "event ".
 *
 * It extracts the description, start time, and end time from the input,
 * creates a new Event task,
 * adds it to the TaskList,
 * saves the updated list to Storage,
 * and updates the user through Ui.
 */
public class EventCommand implements Command {

    // Fields
    private String input;

    /**
     * Creates an EventCommand using the user input.
     *
     * @param input the user input containing the description, start, and end times of the event
     */
    public EventCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the command to create and add a new Event task.
     *
     * If the description, start time, or end time are missing or invalid,
     * a ConversalException is thrown.
     * Else, the new task is added to the TaskList,
     * the list is saved via Storage,
     * and a confirmation message is displayed through Ui.
     *
     * @param tasks   the task list to add the new event into
     * @param storage the storage of tasks
     * @param ui      the UI to show the confirmation message
     * @throws ConversalException if the event description, start, or end is missing or invalid
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws ConversalException {
        if (input.length() <= 6) {
            throw new ConversalException("Ah, I got it! " + ui.getInstructionEvent());
        }
        if (!input.contains(" /from ") || !input.contains(" /to ")) {
            throw new ConversalException("Ah, I got it! " + ui.getInstructionEvent());
        }
        String[] info = input.substring(6).split(" /from | /to ");
        if (info.length < 3 || info[0].trim().isEmpty() || info[1].trim().isEmpty()
                || info[2].trim().isEmpty()) {
            throw new ConversalException("Ah, I got it! " + ui.getInstructionEvent());
        }
        Task t = new Event(info[0], info[1], info[2]);
        tasks.addTask(t);
        storage.save(tasks.getList());
        ui.addMessage(t, tasks.size());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
