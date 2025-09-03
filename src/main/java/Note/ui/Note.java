package Note.ui;

import java.io.IOException;
import java.util.List;

/**
 * The Note class represents a simple command-line task manager chatbot.
 * Delegates responsibilities to Ui, TaskList, Parser, and Storage.
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

    public void run() {
        ui.showWelcome();

        while (true) {
            String input = ui.readCommand();
            String command = Parser.getCommand(input);
            String arguments = Parser.getArguments(input);

            try {
                switch (command) {
                    case "bye":
                        ui.showGoodbye();
                        return;

                    case "list":
                        List<Task> allTasks = tasks.getAllTasks();
                        if (allTasks.isEmpty()) {
                            ui.showMessage("No tasks yet.");
                        } else {
                            StringBuilder sb = new StringBuilder("Here are the tasks in your list:");
                            for (int i = 0; i < allTasks.size(); i++) {
                                sb.append("\n ").append(i + 1).append(".").append(allTasks.get(i));
                            }
                            ui.showMessage(sb.toString());
                        }
                        break;

                    case "mark":
                        int markIndex = Integer.parseInt(arguments) - 1;
                        Task markTask = tasks.getTask(markIndex);
                        markTask.markAsDone();
                        saveTasks();
                        ui.showMessage("Nice! I've marked this task as done:\n  " + markTask);
                        break;

                    case "unmark":
                        int unmarkIndex = Integer.parseInt(arguments) - 1;
                        Task unmarkTask = tasks.getTask(unmarkIndex);
                        unmarkTask.markAsNotDone();
                        saveTasks();
                        ui.showMessage("OK, I've marked this task as not done yet:\n  " + unmarkTask);
                        break;

                    case "delete":
                        int delIndex = Integer.parseInt(arguments) - 1;
                        Task removed = tasks.deleteTask(delIndex);
                        saveTasks();
                        ui.showMessage("Noted. I've removed this task:\n  " + removed +
                                "\nNow you have " + tasks.size() + " tasks in the list.");
                        break;

                    case "todo":
                        if (arguments.isEmpty()) throw new NoteException("The description of a todo cannot be empty.");
                        Task todo = new Todo(arguments);
                        tasks.addTask(todo);
                        saveTasks();
                        ui.showMessage("Got it. I've added this task:\n  " + todo +
                                "\nNow you have " + tasks.size() + " tasks in the list.");
                        break;

                    case "deadline":
                        String[] deadlineParts = arguments.split(" /by ", 2);
                        if (deadlineParts.length < 2) throw new NoteException("Deadline must have a description and a /by date/time.");
                        Task deadline = new Deadline(deadlineParts[0], deadlineParts[1]);
                        tasks.addTask(deadline);
                        saveTasks();
                        ui.showMessage("Got it. I've added this task:\n  " + deadline +
                                "\nNow you have " + tasks.size() + " tasks in the list.");
                        break;

                    case "event":
                        String[] eventParts = arguments.split(" /from | /to ", 3);
                        if (eventParts.length < 3) throw new NoteException("Event must have a description, /from and /to fields.");
                        Task event = new Event(eventParts[0], eventParts[1], eventParts[2]);
                        tasks.addTask(event);
                        saveTasks();
                        ui.showMessage("Got it. I've added this task:\n  " + event +
                                "\nNow you have " + tasks.size() + " tasks in the list.");
                        break;

                    case "find":
                        if (arguments.isEmpty()) throw new NoteException("Please provide a keyword to search for.");
                        List<Task> matching = tasks.getAllTasks().stream()
                                .filter(t -> t.getDescription().contains(arguments))
                                .toList();
                        if (matching.isEmpty()) {
                            ui.showMessage("No matching tasks found.");
                        } else {
                            StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:");
                            for (int i = 0; i < matching.size(); i++) {
                                sb.append("\n ").append(i + 1).append(".").append(matching.get(i));
                            }
                            ui.showMessage(sb.toString());
                        }
                        break;

                    default:
                        throw new NoteException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (NoteException e) {
                ui.showMessage("OOPS!!! " + e.getMessage());
            } catch (NumberFormatException e) {
                ui.showMessage("OOPS!!! Please provide a valid number.");
            }
        }
    }

    private void saveTasks() {
        try {
            storage.save(tasks.getAllTasks());
        } catch (IOException e) {
            ui.showMessage("Error saving tasks: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Note().run();
    }
}
