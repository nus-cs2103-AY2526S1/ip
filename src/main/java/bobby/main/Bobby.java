package bobby.main;

import bobby.bobbyexception.BobbyException;
import bobby.command.Command;
import bobby.parser.Parser;
import bobby.storage.Storage;
import bobby.task.Task;
import bobby.task.ToDo;
import bobby.task.TaskList;
import bobby.ui.Ui;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Main class for the Bobby task manager application.
 * Handles initialization, command loop, and program entry point.
 */
public class Bobby {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructs a Bobby instance, loading tasks from the specified file path.
     * If loading fails, initializes with an empty task list.
     *
     * @param filePath Path to the file storing tasks.
     */
    public Bobby(String filePath) {
        assert filePath != null : "File path should not be null";
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (Exception e) {
            ui.showLoadingError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main command loop, reading and executing user commands until exit.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (BobbyException e) {
                ui.showError(e.getMessage());
            } catch (Exception e) {
                ui.showError("An unexpected error occurred: " + e.getMessage());
            }
        }
    }

    /**
     * Entry point for the Bobby application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Bobby("data/tasks.txt").run();
    }

    public String getResponse(String input) {
        assert input != null : "Input to getResponse should not be null";
        try {
            Command command = Parser.parse(input);
            return command.execute(tasks, ui, storage);
        } catch (BobbyException e) {
            return e.getMessage();
        } catch (Exception e) {
            return "An unexpected error occurred: " + e.getMessage();
        }
    }

    /**
     * Returns a formatted string of today's schedule (Deadlines and Events).
     */
    public String getTodaySchedule() {
        ArrayList<Task> todayTasks = tasks.getTasksForDate(LocalDate.now());
        if (todayTasks.isEmpty()) {
            return "You have no events or deadlines scheduled for today.";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Here is your schedule for today (" + LocalDate.now() + "):\n");
        int idx = 1;
        for (Task t : todayTasks) {
            sb.append(idx++).append(". ").append(t).append("\n");
        }
        return sb.toString().trim();
    }

    /**
     * Returns a formatted string of all ToDo tasks.
     */
    public String getTodoList() {
        ArrayList<Task> allTasks = tasks.getAll();
        ArrayList<ToDo> todos = new ArrayList<>();
        for (Task t : allTasks) {
            if (t instanceof ToDo) {
                todos.add((ToDo) t);
            }
        }
        if (todos.isEmpty()) {
            return "You have no todos in your list.";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Here is your todo list:\n");
        int idx = 1;
        for (ToDo todo : todos) {
            sb.append(idx++).append(". ").append(todo).append("\n");
        }
        return sb.toString().trim();
    }
}