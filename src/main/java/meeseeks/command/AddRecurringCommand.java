package meeseeks.command;

import meeseeks.storage.Storage;
import meeseeks.task.RecurringTask;
import meeseeks.task.RecurrenceFrequency;
import meeseeks.task.TaskList;
import meeseeks.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Command to add a new recurring task to the task list.
 */
public class AddRecurringCommand extends Command {
    private String description;
    private RecurrenceFrequency frequency;
    private LocalDateTime nextDueDate;

    /**
     * Creates a new AddRecurringCommand.
     * 
     * @param description the task description
     * @param frequency the recurrence frequency
     * @param nextDueDate the next due date
     */
    public AddRecurringCommand(String description, RecurrenceFrequency frequency, LocalDateTime nextDueDate) {
        assert description != null : "Description cannot be null";
        assert frequency != null : "Frequency cannot be null";
        assert nextDueDate != null : "Next due date cannot be null";
        
        this.description = description.trim();
        this.frequency = frequency;
        this.nextDueDate = nextDueDate;
    }

    /**
     * Executes the command.
     * 
     * @param tasks the task list
     * @param ui the UI
     * @param storage the storage
    
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        RecurringTask recurringTask = new RecurringTask(description, frequency, nextDueDate);
        tasks.add(recurringTask);
        
        String message = "Got it. I've added this recurring task:\n  " + recurringTask +
                "\nNow you have " + tasks.size() + " tasks in the list.";
        ui.showMessage(message);
    }

    /**
     * Executes the command and returns the response.
     * 
     * @param tasks the task list
     * @param storage the storage
     * @return the response
     */
    @Override
    public String executeAndGetResponse(TaskList tasks, Storage storage) throws Exception {
        RecurringTask recurringTask = new RecurringTask(description, frequency, nextDueDate);
        tasks.add(recurringTask);
        
        return "Got it. I've added this recurring task:\n  " + recurringTask +
                "\nNow you have " + tasks.size() + " tasks in the list.";
    }
}
