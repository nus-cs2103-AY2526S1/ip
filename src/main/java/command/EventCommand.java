package command;

import task.Event;
import task.TaskList;
import ui.Ui;
import storage.Storage;

import java.io.IOException;
import java.time.LocalDate;
import exception.EventException;

/**
 * Represents the command to add a {@link Event} task to the task list.
 */
public class EventCommand extends Command {
    /**
     * Constructs a {@link EventCommand} with the relevant user input.
     *
     * @param input the raw user input that triggered the command
     */
    public EventCommand(String input) {
        super(input);
    }

    /**
     * Executes the event command: adds a {@link Event} to the task list,
     * displays a success/failure message and updates the save file accordingly.
     *
     * @param tasklist the {@link TaskList} where tasks are stored
     * @param ui       the {@link Ui} that the user interacts with
     * @param storage  the {@link Storage} that retrieves and updates the save file
     * @throws EventException if there is an invalid input
     * @throws IOException if there is an error with updating the save file
     */
    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws EventException, IOException {
        try {
            Event td = new Event(input.substring(6, input.indexOf("/from") - 1),
                    input.substring(input.indexOf("/from") + 6, input.indexOf("/to") - 1),
                    input.substring(input.indexOf("/to") + 4));
            tasklist.add(td);
            ui.chatbotPrint("Got it. I've added this task:\n      " + td);
            ui.chatbotPrint("Now you have " + tasklist.size() + " tasks in the list.");
        } catch (Exception e) {
            throw new EventException("To create a new event item, the command is: event /from [start (yyyy-mm-dd)] /to [end (yyyy-mm-dd)]");
        }

        storage.saveToFile(tasklist);
    }
}
