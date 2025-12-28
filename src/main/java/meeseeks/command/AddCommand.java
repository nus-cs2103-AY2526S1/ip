package meeseeks.command;

import meeseeks.storage.Storage;
import meeseeks.task.*;
import meeseeks.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Command to add new tasks to the task list.
 * Supports three types of tasks: Todo, Deadline, and Event.
 */
public class AddCommand extends Command {
    private static final String TODO_TYPE = "todo";
    private static final String DEADLINE_TYPE = "deadline";
    private static final String EVENT_TYPE = "event";
    private static final String DATE_TIME_PATTERN = "d/M/yyyy HHmm";
    private static final String SUCCESS_MESSAGE_FORMAT = "Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.";
    
    private String description;
    private String type;
    private String by;
    private String from;
    private String to;

    /**
     * Constructor for creating a Todo task.
     * 
     * @param description the task description
     * @param type the task type (should be "todo")
     * @throws AssertionError if description or type is null
     */
    public AddCommand(String description, String type) {
        assert description != null : "Description cannot be null";
        assert type != null : "Type cannot be null";
        this.description = description.trim();
        this.type = type;
    }

    /**
     * Constructor for creating a Deadline task.
     * 
     * @param description the task description
     * @param type the task type (should be "deadline")
     * @param by the deadline date and time in format "d/M/yyyy HHmm"
     * @throws AssertionError if any parameter is null
     */
    public AddCommand(String description, String type, String by) {
        assert description != null : "Description cannot be null";
        assert type != null : "Type cannot be null";
        assert by != null : "Deadline time cannot be null";
        this.description = description.trim();
        this.type = type;
        this.by = by.trim();
    }

    /**
     * Constructor for creating an Event task.
     * 
     * @param description the task description
     * @param type the task type (should be "event")
     * @param from the start time of the event
     * @param to the end time of the event
     * @throws AssertionError if any parameter is null
     */
    public AddCommand(String description, String type, String from, String to) {
        assert description != null : "Description cannot be null";
        assert type != null : "Type cannot be null";
        assert from != null : "From time cannot be null";
        assert to != null : "To time cannot be null";
        this.description = description.trim();
        this.type = type;
        this.from = from.trim();
        this.to = to.trim();
    }

    /**
     * Executes the add command by creating the appropriate task type and adding it to the task list.
     * 
     * @param tasks the task list to add the new task to
     * @param ui the UI component for displaying messages
     * @param storage the storage component (not used in this command)
     * @throws Exception if the task type is unknown or date parsing fails
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        Task task = createTask();
        tasks.add(task);
        
        String message = String.format(SUCCESS_MESSAGE_FORMAT, task, tasks.size());
        ui.showMessage(message);
    }
    
    /**
     * Creates a task based on the command type and parameters.
     * 
     * @return the created task
     * @throws Exception if the task type is unknown or date parsing fails
     */
    private Task createTask() throws Exception {
        switch (type) {
            case TODO_TYPE:
                return new ToDo(description);
                
            case DEADLINE_TYPE:
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
                LocalDateTime deadlineDate = LocalDateTime.parse(by, formatter);
                return new Deadline(description, deadlineDate);
                
            case EVENT_TYPE:
                // Events store time as strings for flexibility
                return new Event(description, from, to);
                
            default:
                throw new Exception("Unknown task type: " + type);
        }
    }
}
