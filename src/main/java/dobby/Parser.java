package dobby;

import dobby.exceptions.*;
import dobby.task.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Parses user input and executes corresponding commands.
 */
public class Parser {

    /**
     * Interprets a raw user command and performs the associated action.
     *
     * @param input Raw user input.
     * @param tasks Task list to modify.
     * @param ui UI for user interaction.
     * @param storage Storage for saving tasks.
     * @return True if the command is "bye" and program should exit.
     * @throws DobbyException If the input is invalid.
     */
    public boolean handleCommand(String input, TaskList tasks, Ui ui, Storage storage) throws DobbyException {
        if (input.equalsIgnoreCase("bye")) {
            return true;
        } else if (input.equalsIgnoreCase("list")) {
            ui.showTasks(tasks.getAll());
        } else if (input.startsWith("mark")) {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            Task task = tasks.get(index);
            task.markAsDone();
            ui.showTaskMarkedDone(task);
            storage.saveTasks(tasks.getAll());
        } else if (input.startsWith("unmark")) {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            Task task = tasks.get(index);
            task.markUndone();
            ui.showTaskMarkedUndone(task);
            storage.saveTasks(tasks.getAll());
        } else if (input.startsWith("delete")) {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            Task removed = tasks.delete(index);
            ui.showTaskDeleted(removed);
            storage.saveTasks(tasks.getAll());
        } else if (input.startsWith("todo")) {
            Task t = new ToDo(input.substring(5).trim());
            if (t.getDescription().isEmpty()) throw new InvalidTaskException("Task description cannot be empty!");
            tasks.add(t);
            ui.showTaskAdded(t, tasks.size());
            storage.saveTasks(tasks.getAll());
        } else if (input.startsWith("deadline")) {
            String[] parts = input.substring(9).split("/by");
            if (parts.length != 2) throw new InvalidCommandException("Invalid deadline format.");
            LocalDateTime by = LocalDateTime.parse(parts[1].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            Task t = new Deadline(parts[0].trim(), by);
            tasks.add(t);
            ui.showTaskAdded(t, tasks.size());
            storage.saveTasks(tasks.getAll());
        } else if (input.startsWith("event")) {
            String[] parts = input.substring(5).split("/from|/to");
            if (parts.length != 3) throw new InvalidCommandException("Invalid event format.");
            LocalDateTime from = LocalDateTime.parse(parts[1].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            LocalDateTime to = LocalDateTime.parse(parts[2].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            Task t = new Event(parts[0].trim(), from, to);
            tasks.add(t);
            ui.showTaskAdded(t, tasks.size());
            storage.saveTasks(tasks.getAll());
        } else {
            throw new InvalidCommandException("Unknown command: " + input);
        }
        return false;
    }
}
