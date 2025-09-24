package sofi;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * SOFI is a task management application that allows users to manage their tasks.
 * It supports three types of tasks: Todo, Deadline, and Event.
 * The application provides a command-line interface for task management.
 */
public class SOFI {
    // Command constants
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_FIND = "find";
    private static final String COMMAND_TAG = "tag";
    private static final String COMMAND_UNTAG = "untag";
    
    // Error message constants
    private static final String ERROR_TODO_DESCRIPTION = "A todo needs a description. Try: todo read book";
    private static final String ERROR_DEADLINE_BY = "Deadlines must include /by. Example: deadline return book /by Sunday";
    private static final String ERROR_DEADLINE_DESCRIPTION_EMPTY = "The deadline description cannot be empty.";
    private static final String ERROR_DEADLINE_BY_EMPTY = "The /by time cannot be empty.";
    private static final String ERROR_EVENT_FROM_TO = "Events need /from and /to. Example: event team meeting /from Mon 2pm /to Mon 3pm";
    private static final String ERROR_EVENT_DESCRIPTION_EMPTY = "The event description cannot be empty.";
    private static final String ERROR_EVENT_TIMES_EMPTY = "Both /from and /to times must be provided.";
    private static final String ERROR_TASK_NUMBER_MARK = "Please provide a task number to mark. Example: mark 2";
    private static final String ERROR_TASK_NUMBER_UNMARK = "Please provide a task number to unmark. Example: unmark 2";
    private static final String ERROR_TASK_NUMBER_DELETE = "Please provide the task number to delete. Example: delete 3";
    private static final String ERROR_NOT_A_NUMBER = "That doesn't look like a number. Try: ";
    private static final String ERROR_TASK_OUT_OF_RANGE = "Task number out of range. You have ";
    private static final String ERROR_FIND_TERM = "Please provide a search term. Example: find book";
    private static final String ERROR_TAG_TASK_NUMBER = "Please provide a task number and tag. Example: tag 1 fun";
    private static final String ERROR_UNTAG_TASK_NUMBER = "Please provide a task number and tag. Example: untag 1 fun";
    private static final String ERROR_TAG_EMPTY = "Tag cannot be empty. Example: tag 1 fun";
    private static final String ERROR_UNKNOWN_COMMAND = "I don't recognize that command. Try: list, todo, deadline, event, mark, unmark, delete, find, tag, untag, bye";
    
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a new SOFI instance with the specified file path for data storage.
     * 
     * @param filePath the path to the file where tasks will be stored
     */
    public SOFI(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            // Start fresh if can't load existing tasks
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Starts the main application loop, processing user commands until the user exits.
     * Handles all user interactions including adding, listing, marking, and deleting tasks.
     */
    public void run() {
        ui.showWelcome();
        String userInput;

        while (true) {
            userInput = ui.readCommand();

            try {
                String command = Parser.parseCommand(userInput);
                
                if (command.equals(COMMAND_BYE)) {
                    ui.showGoodbye();
                    break;
                } else if (command.equals(COMMAND_LIST)) {
                    handleListCommand();
                } else if (command.equals(COMMAND_TODO)) {
                    handleTodoCommand(userInput);
                } else if (command.equals(COMMAND_DEADLINE)) {
                    handleDeadlineCommand(userInput);
                } else if (command.equals(COMMAND_EVENT)) {
                    handleEventCommand(userInput);
                } else if (command.equals(COMMAND_MARK)) {
                    handleMarkCommand(userInput);
                } else if (command.equals(COMMAND_UNMARK)) {
                    handleUnmarkCommand(userInput);
                } else if (command.equals(COMMAND_DELETE)) {
                    handleDeleteCommand(userInput);
                } else if (command.equals(COMMAND_FIND)) {
                    handleFindCommand(userInput);
                } else if (command.equals(COMMAND_TAG)) {
                    handleTagCommand(userInput);
                } else if (command.equals(COMMAND_UNTAG)) {
                    handleUntagCommand(userInput);
                } else {
                    throw new SofiException(ERROR_UNKNOWN_COMMAND);
                }
            } catch (SofiException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.close();
    }

    /**
     * Saves the current task list to storage.
     * Silently handles save errors to prevent application interruption.
     */
    public void saveTasks() {
        try {
            storage.save(tasks.getTasks());
        } catch (IOException ignore) {
            // Ignore save errors to prevent application interruption
        }
    }
    
    /**
     * Parses and validates a task number from user input.
     * 
     * @param userInput the user input containing the task number
     * @param command the command being executed (for error messages)
     * @return the parsed task number (0-based index)
     * @throws SofiException if the task number is invalid
     */
    private int parseAndValidateTaskNumber(String userInput, String command) throws SofiException {
        String[] tokens = userInput.split(" ", 2);
        if (tokens.length < 2 || tokens[1].trim().isEmpty()) {
            String errorMessage = command.equals(COMMAND_MARK) ? ERROR_TASK_NUMBER_MARK :
                                command.equals(COMMAND_UNMARK) ? ERROR_TASK_NUMBER_UNMARK :
                                ERROR_TASK_NUMBER_DELETE;
            throw new SofiException(errorMessage);
        }
        
        int taskNumber;
        try {
            taskNumber = Parser.parseTaskNumber(userInput);
        } catch (NumberFormatException e) {
            throw new SofiException(ERROR_NOT_A_NUMBER + command + " 2");
        }
        
        if (!tasks.isValidIndex(taskNumber)) {
            throw new SofiException(ERROR_TASK_OUT_OF_RANGE + tasks.size() + " task(s).");
        }
        
        return taskNumber;
    }
    
    /**
     * Handles the list command.
     */
    private void handleListCommand() {
        ui.showTaskList(tasks.getTasks());
    }
    
    /**
     * Handles the todo command.
     * 
     * @param userInput the user input containing the todo command
     * @throws SofiException if the todo description is empty
     */
    private void handleTodoCommand(String userInput) throws SofiException {
        String taskDescription = Parser.parseTodoDescription(userInput);
        if (taskDescription.isEmpty()) {
            throw new SofiException(ERROR_TODO_DESCRIPTION);
        }
        tasks.addTask(new Todo(taskDescription));
        saveTasks();
        ui.showTaskAdded(tasks.getTask(tasks.size() - 1), tasks.size());
    }
    
    /**
     * Handles the deadline command.
     * 
     * @param userInput the user input containing the deadline command
     * @throws SofiException if the deadline format is invalid
     */
    private void handleDeadlineCommand(String userInput) throws SofiException {
        if (!userInput.contains(" /by ")) {
            throw new SofiException(ERROR_DEADLINE_BY);
        }
        String[] parts = Parser.parseDeadline(userInput);
        String taskDescription = parts[0];
        String by = parts[1];
        if (taskDescription.isEmpty()) {
            throw new SofiException(ERROR_DEADLINE_DESCRIPTION_EMPTY);
        }
        if (by.isEmpty()) {
            throw new SofiException(ERROR_DEADLINE_BY_EMPTY);
        }
        tasks.addTask(new Deadline(taskDescription, by));
        saveTasks();
        ui.showTaskAdded(tasks.getTask(tasks.size() - 1), tasks.size());
    }
    
    /**
     * Handles the event command.
     * 
     * @param userInput the user input containing the event command
     * @throws SofiException if the event format is invalid
     */
    private void handleEventCommand(String userInput) throws SofiException {
        if (!userInput.contains(" /from ") || !userInput.contains(" /to ")) {
            throw new SofiException(ERROR_EVENT_FROM_TO);
        }
        String[] parts = Parser.parseEvent(userInput);
        String taskDescription = parts[0];
        String from = parts[1];
        String to = parts[2];
        if (taskDescription.isEmpty()) {
            throw new SofiException(ERROR_EVENT_DESCRIPTION_EMPTY);
        }
        if (from.isEmpty() || to.isEmpty()) {
            throw new SofiException(ERROR_EVENT_TIMES_EMPTY);
        }
        tasks.addTask(new Event(taskDescription, from, to));
        saveTasks();
        ui.showTaskAdded(tasks.getTask(tasks.size() - 1), tasks.size());
    }
    
    /**
     * Handles the mark command.
     * 
     * @param userInput the user input containing the mark command
     * @throws SofiException if the task number is invalid
     */
    private void handleMarkCommand(String userInput) throws SofiException {
        int taskNumber = parseAndValidateTaskNumber(userInput, COMMAND_MARK);
        tasks.markTask(taskNumber, true);
        saveTasks();
        ui.showTaskMarked(tasks.getTask(taskNumber), true);
    }
    
    /**
     * Handles the unmark command.
     * 
     * @param userInput the user input containing the unmark command
     * @throws SofiException if the task number is invalid
     */
    private void handleUnmarkCommand(String userInput) throws SofiException {
        int taskNumber = parseAndValidateTaskNumber(userInput, COMMAND_UNMARK);
        tasks.markTask(taskNumber, false);
        saveTasks();
        ui.showTaskMarked(tasks.getTask(taskNumber), false);
    }
    
    /**
     * Handles the delete command.
     * 
     * @param userInput the user input containing the delete command
     * @throws SofiException if the task number is invalid
     */
    private void handleDeleteCommand(String userInput) throws SofiException {
        int taskNumber = parseAndValidateTaskNumber(userInput, COMMAND_DELETE);
        Task removed = tasks.removeTask(taskNumber);
        saveTasks();
        ui.showTaskRemoved(removed, tasks.size());
    }
    
    /**
     * Handles the find command.
     * 
     * @param userInput the user input containing the find command
     * @throws SofiException if the search term is empty
     */
    private void handleFindCommand(String userInput) throws SofiException {
        String searchTerm = Parser.parseFindKeyword(userInput);
        if (searchTerm.isEmpty()) {
            throw new SofiException(ERROR_FIND_TERM);
        }
        ArrayList<Task> matchingTasks = tasks.findTasks(searchTerm);
        ui.showFoundTasks(matchingTasks);
    }

    /**
     * Handles the tag command.
     * 
     * @param userInput the user input containing the tag command
     * @throws SofiException if the task number or tag is invalid
     */
    private void handleTagCommand(String userInput) throws SofiException {
        String[] parts = Parser.parseTagCommand(userInput);
        String taskNumberStr = parts[0];
        String tag = parts[1];
        
        if (taskNumberStr.isEmpty() || tag.isEmpty()) {
            throw new SofiException(ERROR_TAG_TASK_NUMBER);
        }
        
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(taskNumberStr) - 1;
        } catch (NumberFormatException e) {
            throw new SofiException(ERROR_NOT_A_NUMBER + "tag 1 fun");
        }
        
        if (!tasks.isValidIndex(taskNumber)) {
            throw new SofiException(ERROR_TASK_OUT_OF_RANGE + tasks.size() + " task(s).");
        }
        
        Task task = tasks.getTask(taskNumber);
        task.addTag(tag);
        saveTasks();
        ui.showTaskTagged(task, tag, true);
    }
    
    /**
     * Handles the untag command.
     * 
     * @param userInput the user input containing the untag command
     * @throws SofiException if the task number or tag is invalid
     */
    private void handleUntagCommand(String userInput) throws SofiException {
        String[] parts = Parser.parseUntagCommand(userInput);
        String taskNumberStr = parts[0];
        String tag = parts[1];
        
        if (taskNumberStr.isEmpty() || tag.isEmpty()) {
            throw new SofiException(ERROR_UNTAG_TASK_NUMBER);
        }
        
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(taskNumberStr) - 1;
        } catch (NumberFormatException e) {
            throw new SofiException(ERROR_NOT_A_NUMBER + "untag 1 fun");
        }
        
        if (!tasks.isValidIndex(taskNumber)) {
            throw new SofiException(ERROR_TASK_OUT_OF_RANGE + tasks.size() + " task(s).");
        }
        
        Task task = tasks.getTask(taskNumber);
        task.removeTag(tag);
        saveTasks();
        ui.showTaskTagged(task, tag, false);
    }

    /**
     * Returns the TaskList managed by SOFI.
     *
     * @return the TaskList instance
     */
    public TaskList getTasks() {
        return tasks;
    }

    public static void main(String[] args) {
        String filePath = "." + java.io.File.separator + "data" 
                + java.io.File.separator + "duke.txt";
        new SOFI(filePath).run();
    }
}

