package Note.ui;

import java.io.IOException;
import java.util.List;

/**
 * The Note class represents a simple command-line task manager chatbot.
 * Delegates responsibilities to Ui, TaskList, Parser, and Storage.
 * Supports both CLI (run()) and GUI (processInput()).
 */
public class Note {
    private static final String FILE_PATH = "data/duke.txt";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Note() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the CLI version of Note.
     */
    public void run() {
        ui.showWelcome();

        while (true) {
            String input = ui.readCommand();
            String response = processInput(input);
            ui.showMessage(response);

            if (Parser.getCommand(input).equals("bye")) {
                return;
            }
        }
    }

    /**
     * Processes a single command and returns the response as a String.
     * Useful for GUI integration.
     */
    public String processInput(String input) {
        String command = Parser.getCommand(input);
        String arguments = Parser.getArguments(input);

        try {
            switch (command) {
                case "bye":
                    return "Bye! Hope to see you again soon!";

                case "list":
                    List<Task> allTasks = tasks.getAllTasks();
                    if (allTasks.isEmpty()) return "No tasks yet.";
                    StringBuilder sbList = new StringBuilder("Here are the tasks in your list:");
                    for (int i = 0; i < allTasks.size(); i++) {
                        sbList.append("\n ").append(i + 1).append(".").append(allTasks.get(i));
                    }
                    return sbList.toString();

                case "mark":
                    int markIndex = Integer.parseInt(arguments) - 1;
                    Task markTask = tasks.getTask(markIndex);
                    markTask.markAsDone();
                    saveTasks();
                    return "Nice! I've marked this task as done:\n  " + markTask;

                case "unmark":
                    int unmarkIndex = Integer.parseInt(arguments) - 1;
                    Task unmarkTask = tasks.getTask(unmarkIndex);
                    unmarkTask.markAsNotDone();
                    saveTasks();
                    return "OK, I've marked this task as not done yet:\n  " + unmarkTask;

                case "delete":
                    int delIndex = Integer.parseInt(arguments) - 1;
                    Task removed = tasks.deleteTask(delIndex);
                    saveTasks();
                    return "Noted. I've removed this task:\n  " + removed +
                            "\nNow you have " + tasks.size() + " tasks in the list.";

                case "todo":
                    if (arguments.isEmpty()) throw new NoteException("The description of a todo cannot be empty.");
                    Task todo = new Todo(arguments);
                    tasks.addTask(todo);
                    saveTasks();
                    return "Got it. I've added this task:\n  " + todo +
                            "\nNow you have " + tasks.size() + " tasks in the list.";

                case "deadline":
                    String[] deadlineParts = arguments.split(" /by ", 2);
                    if (deadlineParts.length < 2)
                        throw new NoteException("Deadline must have a description and a /by date/time.");
                    Task deadline = new Deadline(deadlineParts[0], deadlineParts[1]);
                    tasks.addTask(deadline);
                    saveTasks();
                    return "Got it. I've added this task:\n  " + deadline +
                            "\nNow you have " + tasks.size() + " tasks in the list.";

                case "event":
                    String[] eventParts = arguments.split(" /from | /to ", 3);
                    if (eventParts.length < 3)
                        throw new NoteException("Event must have a description, /from and /to fields.");
                    Task event = new Event(eventParts[0], eventParts[1], eventParts[2]);
                    tasks.addTask(event);
                    saveTasks();
                    return "Got it. I've added this task:\n  " + event +
                            "\nNow you have " + tasks.size() + " tasks in the list.";

                case "find":
                    if (arguments.isEmpty()) throw new NoteException("Please provide a keyword to search for.");
                    String keyword = arguments.toLowerCase(); // convert the search keyword to lowercase
                    List<Task> matching = tasks.getAllTasks().stream()
                            .filter(t -> t.getDescription().toLowerCase().contains(keyword)) // compare in lowercase
                            .toList();
                    if (matching.isEmpty()) return "No matching tasks found.";
                    StringBuilder sbMatch = new StringBuilder("Here are the matching tasks in your list:");
                    for (int i = 0; i < matching.size(); i++) {
                        sbMatch.append("\n ").append(i + 1).append(".").append(matching.get(i));
                    }
                    return sbMatch.toString();

                case "help":
                    return HelpCommand.getHelpMessage();

                default:
                    return "OOPS!!! I'm sorry, but I don't know what that means :-(";
            }
        } catch (NoteException e) {
            return "OOPS!!! " + e.getMessage();
        } catch (NumberFormatException e) {
            return "OOPS!!! Please provide a valid number.";
        }
    }

    /**
     * Saves all tasks to the storage file.
     */
    private void saveTasks() {
        try {
            storage.save(tasks.getAllTasks());
        } catch (IOException e) {
            ui.showMessage("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Entry point for CLI.
     */
    public static void main(String[] args) {
        new Note().run();
    }
}
