package poopiemeow;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import poopiemeow.exception.EmptyDescriptionException;
import poopiemeow.parser.Parser;
import poopiemeow.storage.Storage;
import poopiemeow.task.Task;
import poopiemeow.ui.Ui;

/**
 * Main application class for PoopieMeow, a task management system.
 * This class orchestrates the user interface, task storage, and command
 * processing.
 * It provides an interactive command-line interface for managing todos,
 * deadlines, and events.
 *
 * @author tch1001
 * @version 1.0
 */
public class PoopieMeow {
    /** Storage component for persisting tasks to file */
    private Storage storage;
    /** Task list manager for organizing and manipulating tasks */
    private TaskList tasks;
    /** User interface component for displaying messages and reading input */
    private Ui ui;
    /** Scanner for reading user input from console */
    private Scanner scanner;

    /**
     * Constructs a new PoopieMeow application instance.
     * Initializes the UI, storage, and loads existing tasks from the specified
     * file.
     * If the file cannot be loaded, creates an empty task list.
     *
     * @param filePath the path to the file where tasks will be stored and loaded
     *                 from
     */
    public PoopieMeow(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        scanner = new Scanner(System.in);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main application loop.
     * Displays welcome message and continuously processes user commands until "bye"
     * is entered.
     * Handles all command types and provides appropriate error messages for invalid
     * inputs.
     */
    public void run() {
        ui.showWelcome();

        while (true) {
            String input = ui.readCommand(scanner);

            try {
                if (input.equals("bye")) {
                    ui.showGoodbye();
                    break;
                } else if (input.equals("list")) {
                    ui.showTaskList(tasks.getTasks());
                } else if (input.startsWith("mark ")) {
                    handleMarkCommand(input);
                } else if (input.startsWith("unmark ")) {
                    handleUnmarkCommand(input);
                } else if (input.startsWith("delete ")) {
                    handleDeleteCommand(input);
                } else if (input.startsWith("todo ") || input.startsWith("deadline ") || input.startsWith("event ")) {
                    handleAddTaskCommand(input);
                } else if (input.equals("todo") || input.equals("deadline") || input.equals("event")) {
                    throw new EmptyDescriptionException("The description cannot be empty!");
                } else if (input.startsWith("show ")) {
                    handleShowCommand(input);
                } else if (input.startsWith("find ")) {
                    String keyword = input.substring(5).trim();
                    if (keyword.isEmpty()) {
                        ui.showError("Please provide a keyword to search for.");
                    } else {
                        ArrayList<Task> matchingTasks = tasks.findTasks(keyword);
                        ui.showMatchingTasks(matchingTasks);
                    }
                } else if (input.trim().isEmpty()) {
                    throw new EmptyDescriptionException("Please enter a command!");
                } else {
                    ui.showError("I don't understand '" + input + "'. Please try a valid command!");
                }
            } catch (EmptyDescriptionException e) {
                ui.showError(e.getMessage());
            } catch (DateTimeParseException e) {
                ui.showError(
                        "Invalid format, use the format yyyy-mm-dd hhmm for dates and times!\nFor example: 2023-10-15 1430");
            } catch (IOException e) {
                ui.showError("Error saving to file: " + e.getMessage());
            }
        }
    }

    /**
     * Handles the mark command to mark a task as done.
     * Parses the task number from the input and marks the corresponding task as
     * completed.
     *
     * @param input the user input string starting with "mark "
     * @throws IOException if there's an error saving to file
     */
    private void handleMarkCommand(String input) throws IOException {
        try {
            int index = Integer.parseInt(input.substring(5)) - 1;
            if (index < 0 || index >= tasks.size()) {
                ui.showError("Task number " + (index + 1) + " does not exist. Please enter a valid task number (1 to "
                        + tasks.size() + ").");
            } else {
                Task task = tasks.getTask(index);
                task.markAsDone();
                storage.save(tasks.getTasks());
                ui.showTaskMarked(task);
            }
        } catch (NumberFormatException e) {
            ui.showError("Please enter a valid task number after 'mark'.");
        }
    }

    /**
     * Handles the unmark command to mark a task as not done.
     * Parses the task number from the input and marks the corresponding task as
     * incomplete.
     *
     * @param input the user input string starting with "unmark "
     * @throws IOException if there's an error saving to file
     */
    private void handleUnmarkCommand(String input) throws IOException {
        try {
            int index = Integer.parseInt(input.substring(7)) - 1;
            if (index < 0 || index >= tasks.size()) {
                ui.showError("Task number " + (index + 1) + " does not exist. Please enter a valid task number (1 to "
                        + tasks.size() + ").");
            } else {
                Task task = tasks.getTask(index);
                task.markAsUndone();
                storage.save(tasks.getTasks());
                ui.showTaskUnmarked(task);
            }
        } catch (NumberFormatException e) {
            ui.showError("Please enter a valid task number after 'unmark'.");
        }
    }

    /**
     * Handles the delete command to remove a task from the list.
     * Parses the task number from the input and removes the corresponding task.
     *
     * @param input the user input string starting with "delete "
     * @throws IOException if there's an error saving to file
     */
    private void handleDeleteCommand(String input) throws IOException {
        try {
            int index = Integer.parseInt(input.substring(7)) - 1;
            if (index < 0 || index >= tasks.size()) {
                ui.showError("Task number " + (index + 1) + " does not exist. Please enter a valid task number (1 to "
                        + tasks.size() + ").");
            } else {
                Task removedTask = tasks.deleteTask(index);
                storage.save(tasks.getTasks());
                ui.showTaskDeleted(removedTask, tasks.size());
            }
        } catch (NumberFormatException e) {
            ui.showError("Please enter a valid task number after 'delete'.");
        }
    }

    /**
     * Handles commands to add new tasks (todo, deadline, event).
     * Parses the input and creates the appropriate task type, then adds it to the
     * task list.
     *
     * @param input the user input string starting with "todo ", "deadline ", or
     *              "event "
     * @throws EmptyDescriptionException if the task description is empty
     * @throws DateTimeParseException    if the date/time format is invalid
     * @throws IOException               if there's an error saving to file
     */
    private void handleAddTaskCommand(String input)
            throws EmptyDescriptionException, DateTimeParseException, IOException {
        Task newTask = Parser.parseTask(input);
        tasks.addTask(newTask);
        storage.save(tasks.getTasks());
        ui.showTaskAdded(newTask, tasks.size());
    }

    /**
     * Handles the show command to display tasks on a specific date.
     * Parses the date string and shows all tasks that occur on that date.
     *
     * @param input the user input string starting with "show "
     */
    private void handleShowCommand(String input) {
        String dateStr = input.substring(5);
        try {
            LocalDateTime date = LocalDateTime.parse(dateStr + " 0000", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            ui.showTasksOnDate(tasks.getTasks(), date);
        } catch (DateTimeParseException e) {
            ui.showError("Please provide date in the format: yyyy-mm-dd");
        }
    }

    public String getResponse(String input) {
        try {
            if (input.equals("bye")) {
                return "Goodbye! Hope to see you again soon!";
            } else if (input.equals("list")) {
                return ui.getTaskListString(tasks.getTasks());
            } else if (input.startsWith("mark ")) {
                return handleMarkCommandGUI(input);
            } else if (input.startsWith("unmark ")) {
                return handleUnmarkCommandGUI(input);
            } else if (input.startsWith("delete ")) {
                return handleDeleteCommandGUI(input);
            } else if (input.startsWith("todo ") || input.startsWith("deadline ") || input.startsWith("event ")) {
                return handleAddTaskCommandGUI(input);
            } else if (input.equals("todo") || input.equals("deadline") || input.equals("event")) {
                throw new EmptyDescriptionException("The description cannot be empty!");
            } else if (input.startsWith("show ")) {
                return handleShowCommandGUI(input);
            } else if (input.startsWith("find ")) {
                String keyword = input.substring(5).trim();
                if (keyword.isEmpty()) {
                    return "Please provide a keyword to search for.";
                } else {
                    ArrayList<Task> matchingTasks = tasks.findTasks(keyword);
                    return ui.getMatchingTasksString(matchingTasks);
                }
            } else if (input.trim().isEmpty()) {
                throw new EmptyDescriptionException("Please enter a command!");
            } else {
                return "I don't understand '" + input + "'. Please try a valid command!";
            }
        } catch (EmptyDescriptionException e) {
            return e.getMessage();
        } catch (DateTimeParseException e) {
            return "Invalid format, use the format yyyy-mm-dd hhmm for dates and times!\nFor example: 2023-10-15 1430";
        } catch (IOException e) {
            return "Error saving to file: " + e.getMessage();
        }
    }

    /**
     * Handles the mark command for GUI mode.
     */
    private String handleMarkCommandGUI(String input) throws IOException {
        try {
            int index = Integer.parseInt(input.substring(5)) - 1;
            if (index < 0 || index >= tasks.size()) {
                return "Task number " + (index + 1) + " does not exist. Please enter a valid task number (1 to "
                        + tasks.size() + ").";
            } else {
                Task task = tasks.getTask(index);
                task.markAsDone();
                storage.save(tasks.getTasks());
                return ui.getTaskMarkedString(task);
            }
        } catch (NumberFormatException e) {
            return "Please enter a valid task number after 'mark'.";
        }
    }

    /**
     * Handles the unmark command for GUI mode.
     */
    private String handleUnmarkCommandGUI(String input) throws IOException {
        try {
            int index = Integer.parseInt(input.substring(7)) - 1;
            if (index < 0 || index >= tasks.size()) {
                return "Task number " + (index + 1) + " does not exist. Please enter a valid task number (1 to "
                        + tasks.size() + ").";
            } else {
                Task task = tasks.getTask(index);
                task.markAsUndone();
                storage.save(tasks.getTasks());
                return ui.getTaskUnmarkedString(task);
            }
        } catch (NumberFormatException e) {
            return "Please enter a valid task number after 'unmark'.";
        }
    }

    /**
     * Handles the delete command for GUI mode.
     */
    private String handleDeleteCommandGUI(String input) throws IOException {
        try {
            int index = Integer.parseInt(input.substring(7)) - 1;
            if (index < 0 || index >= tasks.size()) {
                return "Task number " + (index + 1) + " does not exist. Please enter a valid task number (1 to "
                        + tasks.size() + ").";
            } else {
                Task removedTask = tasks.deleteTask(index);
                storage.save(tasks.getTasks());
                return ui.getTaskDeletedString(removedTask, tasks.size());
            }
        } catch (NumberFormatException e) {
            return "Please enter a valid task number after 'delete'.";
        }
    }

    /**
     * Handles commands to add new tasks for GUI mode.
     */
    private String handleAddTaskCommandGUI(String input)
            throws EmptyDescriptionException, DateTimeParseException, IOException {
        Task newTask = Parser.parseTask(input);
        tasks.addTask(newTask);
        storage.save(tasks.getTasks());
        return ui.getTaskAddedString(newTask, tasks.size());
    }

    /**
     * Handles the show command for GUI mode.
     */
    private String handleShowCommandGUI(String input) {
        String dateStr = input.substring(5);
        try {
            LocalDateTime date = LocalDateTime.parse(dateStr + " 0000", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            return ui.getTasksOnDateString(tasks.getTasks(), date);
        } catch (DateTimeParseException e) {
            return "Please provide date in the format: yyyy-mm-dd";
        }
    }

    /**
     * Main entry point for the PoopieMeow application.
     * Creates a new application instance and runs it with the default data file
     * path.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        new PoopieMeow("data/tasks.txt").run();
    }
}
