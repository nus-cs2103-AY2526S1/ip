import java.io.File;
import java.io.IOException;

/**
 * SOFI is a task management application that allows users to manage their tasks.
 * It supports three types of tasks: Todo, Deadline, and Event.
 * The application provides a command-line interface for task management.
 */
public class SOFI {
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
                
                if (command.equals("bye")) {
                    ui.showGoodbye();
                    break;
                }

                if (command.equals("list")) {
                    ui.showTaskList(tasks.getTasks());
                }

                else if (command.equals("todo")) {
                    String taskDescription = Parser.parseTodoDescription(userInput);
                    if (taskDescription.isEmpty()) {
                        throw new SofiException("A todo needs a description. Try: todo read book");
                    }
                    tasks.addTask(new Todo(taskDescription));
                    try { storage.save(tasks.getTasks()); } catch (IOException ignore) {} // Save after each change
                    ui.showTaskAdded(tasks.getTask(tasks.size() - 1), tasks.size());
                }

                else if (command.equals("deadline")) {
                    if (!userInput.contains(" /by ")) {
                        throw new SofiException("Deadlines must include /by. Example: deadline return book /by Sunday");
                    }
                    String[] parts = Parser.parseDeadline(userInput);
                    String taskDescription = parts[0];
                    String by = parts[1];
                    if (taskDescription.isEmpty()) {
                        throw new SofiException("The deadline description cannot be empty.");
                    }
                    if (by.isEmpty()) {
                        throw new SofiException("The /by time cannot be empty.");
                    }
                    tasks.addTask(new Deadline(taskDescription, by));
                    try { storage.save(tasks.getTasks()); } catch (IOException ignore) {}
                    ui.showTaskAdded(tasks.getTask(tasks.size() - 1), tasks.size());
                }

                else if (command.equals("event")) {
                    if (!userInput.contains(" /from ") || !userInput.contains(" /to ")) {
                        throw new SofiException("Events need /from and /to. Example: event team meeting /from Mon 2pm /to Mon 3pm");
                    }
                    String[] parts = Parser.parseEvent(userInput);
                    String taskDescription = parts[0];
                    String from = parts[1];
                    String to = parts[2];
                    if (taskDescription.isEmpty()) {
                        throw new SofiException("The event description cannot be empty.");
                    }
                    if (from.isEmpty() || to.isEmpty()) {
                        throw new SofiException("Both /from and /to times must be provided.");
                    }
                    tasks.addTask(new Event(taskDescription, from, to));
                    try { storage.save(tasks.getTasks()); } catch (IOException ignore) {}
                    ui.showTaskAdded(tasks.getTask(tasks.size() - 1), tasks.size());
                }

                else if (command.equals("mark")) {
                    String[] tokens = userInput.split(" ", 2);
                    if (tokens.length < 2 || tokens[1].trim().isEmpty()) {
                        throw new SofiException("Please provide a task number to mark. Example: mark 2");
                    }
                    int taskNumber;
                    try {
                        taskNumber = Parser.parseTaskNumber(userInput);
                    } catch (NumberFormatException e) {
                        throw new SofiException("That doesn't look like a number. Try: mark 2");
                    }
                    if (!tasks.isValidIndex(taskNumber)) {
                        throw new SofiException("Task number out of range. You have " + tasks.size() + " task(s).");
                    }
                    tasks.markTask(taskNumber, true);
                    try { storage.save(tasks.getTasks()); } catch (IOException ignore) {}
                    ui.showTaskMarked(tasks.getTask(taskNumber), true);
                }

                else if (command.equals("unmark")) {
                    String[] tokens = userInput.split(" ", 2);
                    if (tokens.length < 2 || tokens[1].trim().isEmpty()) {
                        throw new SofiException("Please provide a task number to unmark. Example: unmark 2");
                    }
                    int taskNumber;
                    try {
                        taskNumber = Parser.parseTaskNumber(userInput);
                    } catch (NumberFormatException e) {
                        throw new SofiException("That doesn't look like a number. Try: unmark 2");
                    }
                    if (!tasks.isValidIndex(taskNumber)) {
                        throw new SofiException("Task number out of range. You have " + tasks.size() + " task(s).");
                    }
                    tasks.markTask(taskNumber, false);
                    try { storage.save(tasks.getTasks()); } catch (IOException ignore) {}
                    ui.showTaskMarked(tasks.getTask(taskNumber), false);
                }

                else if (command.equals("delete")) {
                    String[] tokens = userInput.split(" ", 2);
                    if (tokens.length < 2 || tokens[1].trim().isEmpty()) {
                        throw new SofiException("Please provide the task number to delete. Example: delete 3");
                    }
                    int taskNumber;
                    try {
                        taskNumber = Parser.parseTaskNumber(userInput);
                    } catch (NumberFormatException e) {
                        throw new SofiException("That doesn't look like a number. Try: delete 3");
                    }
                    if (!tasks.isValidIndex(taskNumber)) {
                        throw new SofiException("Task number out of range. You have " + tasks.size() + " task(s).");
                    }
                    Task removed = tasks.removeTask(taskNumber);
                    try { storage.save(tasks.getTasks()); } catch (IOException ignore) {}
                    ui.showTaskRemoved(removed, tasks.size());
                }

                else {
                    throw new SofiException("I don't recognize that command. Try: list, todo, deadline, event, mark, unmark, bye");
                }
            } catch (SofiException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.close();
    }

    /**
     * Main entry point for the SOFI application.
     * 
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        new SOFI("." + java.io.File.separator + "data" + java.io.File.separator + "duke.txt").run();
    }
}

