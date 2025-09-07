package dobby;

import dobby.exceptions.*;
import dobby.task.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Parses user input and executes corresponding commands.
 */
public class Parser {

    private final TaskList taskList;

    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Interprets a raw user command and performs the associated action.
     *
     * @param input Raw user input.
     * @param ui UI for user interaction.
     * @param storage Storage for saving tasks.
     * @return True if the command is "bye" and program should exit.
     * @throws DobbyException If the input is invalid.
     */
    public boolean handleCommand(String input, Ui ui, Storage storage) throws DobbyException {
        if (input.equalsIgnoreCase("bye")) {
            return true;
        } else if (input.equalsIgnoreCase("list")) {
            handleList(ui);
        } else if (input.startsWith("mark")) {
            handleMark(input, ui, storage);
        } else if (input.startsWith("unmark")) {
            handleUnmark(input, ui, storage);
        } else if (input.startsWith("delete")) {
            handleDelete(input, ui, storage);
        } else if (input.startsWith("todo")) {
            handleTodo(input, ui, storage);
        } else if (input.startsWith("deadline")) {
            handleDeadline(input, ui, storage);
        } else if (input.startsWith("event")) {
            handleEvent(input, ui, storage);
        } else if (input.startsWith("find")) {
            handleFind(input);
        } else {
            throw new InvalidCommandException("Unknown command: " + input);
        }
        return false;
    }

    // ==================== Handlers ====================

    private void handleList(Ui ui) {
        ui.showTasks(taskList.getTasks());
    }

    private void handleMark(String input, Ui ui, Storage storage) throws DobbyException {
        try {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            Task task = taskList.getTasks().get(index);
            task.markAsDone();
            ui.showTaskMarkedDone(task);
            storage.saveTasks(taskList.getTasks());
        } catch (Exception e) {
            throw new InvalidCommandException("Invalid task number for mark command.");
        }
    }

    private void handleUnmark(String input, Ui ui, Storage storage) throws DobbyException {
        try {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            Task task = taskList.getTasks().get(index);
            task.markUndone();
            ui.showTaskMarkedUndone(task);
            storage.saveTasks(taskList.getTasks());
        } catch (Exception e) {
            throw new InvalidCommandException("Invalid task number for unmark command.");
        }
    }

    private void handleDelete(String input, Ui ui, Storage storage) throws DobbyException {
        try {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            Task removed = taskList.deleteTask(index);
            ui.showTaskDeleted(removed);
            storage.saveTasks(taskList.getTasks());
        } catch (Exception e) {
            throw new InvalidCommandException("Invalid task number for delete command.");
        }
    }

    private void handleTodo(String input, Ui ui, Storage storage) throws DobbyException {
        String description = input.substring(5).trim();
        if (description.isEmpty()) throw new InvalidTaskException("Task description cannot be empty!");
        Task t = new ToDo(description);
        taskList.addTask(t);
        ui.showTaskAdded(t, taskList.getTasks().size());
        storage.saveTasks(taskList.getTasks());
    }

    private void handleDeadline(String input, Ui ui, Storage storage) throws DobbyException {
        try {
            String[] parts = input.substring(9).split("/by");
            if (parts.length != 2) throw new InvalidCommandException("Invalid deadline format.");
            LocalDateTime by = LocalDateTime.parse(parts[1].trim(),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            Task t = new Deadline(parts[0].trim(), by);
            taskList.addTask(t);
            ui.showTaskAdded(t, taskList.getTasks().size());
            storage.saveTasks(taskList.getTasks());
        } catch (Exception e) {
            throw new InvalidCommandException("Invalid deadline command: " + e.getMessage());
        }
    }

    private void handleEvent(String input, Ui ui, Storage storage) throws DobbyException {
        try {
            String[] parts = input.substring(5).split("/from|/to");
            if (parts.length != 3) throw new InvalidCommandException("Invalid event format.");
            LocalDateTime from = LocalDateTime.parse(parts[1].trim(),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            LocalDateTime to = LocalDateTime.parse(parts[2].trim(),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            Task t = new Event(parts[0].trim(), from, to);
            taskList.addTask(t);
            ui.showTaskAdded(t, taskList.getTasks().size());
            storage.saveTasks(taskList.getTasks());
        } catch (Exception e) {
            throw new InvalidCommandException("Invalid event command: " + e.getMessage());
        }
    }

    private void handleFind(String input) {
        String keyword = input.substring(5).trim();
        if (keyword.isEmpty()) {
            System.out.println("Please provide a keyword to search.");
            return;
        }
        ArrayList<Task> matches = taskList.findTasks(keyword);
        if (matches.isEmpty()) {
            System.out.println("No matching tasks found.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < matches.size(); i++) {
                System.out.println((i + 1) + "." + matches.get(i));
            }
        }
    }
}
