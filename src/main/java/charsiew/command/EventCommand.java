package charsiew.command;

import java.io.IOException;
import java.time.LocalDate;

import charsiew.storage.Storage;
import charsiew.task.Event;
import charsiew.task.Task;
import charsiew.task.TaskList;
import charsiew.ui.Ui;

/**
 * Represents a command to add an Event task to the TaskList.
 */
public class EventCommand extends Command {
    private final String description;
    private final LocalDate from;
    private final LocalDate to;

    /**
     * Constructs an EventCommand with the specified description and dates.
     *
     * @param description Description of the event.
     * @param from        Start date of the event.
     * @param to          End date of the event.
     */
    public EventCommand(String description, LocalDate from, LocalDate to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Creates an EventCommand from a user input string.
     * Expected format: "description /from yyyy-MM-dd /to yyyy-MM-dd"
     *
     * @param input the user input string
     * @return an EventCommand with parsed description and dates
     */
    public static EventCommand fromInput(String input) {
        String[] parts = input.split("/from", 2);
        String desc = parts[0].trim();

        String[] timeParts = parts[1].split("/to", 2);
        LocalDate from = LocalDate.parse(timeParts[0].trim());
        LocalDate to = LocalDate.parse(timeParts[1].trim());

        return new EventCommand(desc, from, to);
    }

    /**
     * Executes the command by creating a new Event, adding it to the TaskList,
     * saving the updated list, and showing a confirmation message.
     *
     * @param tasks   The TaskList to add the event to.
     * @param ui      The UI object to show messages.
     * @param storage The Storage object to save changes.
     * @throws IOException if saving the TaskList fails.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task t = new Event(description, from, to);
        tasks.add(t);
        storage.save(tasks);
        return ui.showAddedTask(t, tasks.size());
    }

    /**
     * Undo adding an Event
     *
     * @param tasks   The {@link TaskList} containing all current tasks.
     * @param ui      The {@link Ui} instance responsible for user interaction.
     * @param storage The {@link Storage} instance used to save or load tasks.
     * @throws Exception If an error occurs during command execution.
     */
    @Override
    public void undo(TaskList tasks, Ui ui, Storage storage) throws Exception {
        tasks.remove(tasks.size() - 1);
    }

    /**
     * @return true, an Event Command can be undone.
     **/
    public boolean canUndo() {
        return true;
    }
}
