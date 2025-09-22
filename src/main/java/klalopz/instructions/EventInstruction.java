package klalopz.instructions;

import klalopz.exceptions.KlalopzException;
import klalopz.storage.DataStorage;
import klalopz.tasks.Event;
import klalopz.tasks.Task;
import klalopz.tasks.TaskList;
import klalopz.ui.TextUi;

import java.time.LocalDate;

/**
 * Represents an instruction to add an event task with a start and end date.
 */

public class EventInstruction implements Instruction {
    public String arguments;
    private final String details;
    private final LocalDate startDate;
    private final LocalDate endDate;

    /**
     * Constructs an EventInstruction from the given arguments string.
     * The expected format is "description / startDate / endDate", where dates
     * follow the {inputDateFormat}.
     *
     * @param arguments Input string containing the event details and dates.
     */
    public EventInstruction(String arguments) {
        this.arguments = arguments;
        String[] parts = arguments.split("/", 3);

        this.details = parts[0].trim();
        this.startDate = LocalDate.parse(parts[1].trim(), inputDateFormat);
        this.endDate = LocalDate.parse(parts[2].trim(), inputDateFormat);
    }
    @Override
    public void execute(TaskList storage, DataStorage dataStorage, TextUi ui) {
        Task currTask = new Event(details, Boolean.FALSE, startDate, endDate);
        storage.addTask(currTask);
        dataStorage.save(storage);

        ui.showMessage(addedTask + " \n" + currTask);
        ui.showMessage("Now you have " + storage.size() + " tasks in the list.");
        ui.showLine();

    }
    @Override
    public boolean doIExit() {
        return false;
    }
}
