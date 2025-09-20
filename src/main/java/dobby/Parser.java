package dobby;

import dobby.exceptions.DobbyException;
import dobby.exceptions.InvalidCommandException;
import dobby.exceptions.InvalidTaskException;
import dobby.exceptions.StorageException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import dobby.task.Task;
import dobby.task.ToDo;
import dobby.task.Deadline;
import dobby.task.Event;
import dobby.task.TaskType;

/**
 * Parses user input and executes corresponding commands.
 * All methods return Strings suitable for GUI display.
 */
public class Parser {

    private final TaskList taskList;
    private final Storage storage;
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public Parser(TaskList taskList, Storage storage) {
        this.taskList = taskList;
        this.storage = storage;
    }

    /**
     * Interprets a raw user command and returns the output string.
     */
    public String handleCommand(String input) throws DobbyException {
        assert input != null : "Input cannot be null";
        input = input.trim();

        if (input.equalsIgnoreCase("bye")) {
            return "Bye! Hope to see you again soon!";
        } else if (input.equalsIgnoreCase("list")) {
            return handleList();
        } else if (input.equalsIgnoreCase("help")) {   // <-- new help command
            return handleHelp();
        } else if (input.startsWith("mark")) {
            return handleMark(input);
        } else if (input.startsWith("unmark")) {
            return handleUnmark(input);
        } else if (input.startsWith("delete")) {
            return handleDelete(input);
        } else if (input.startsWith("todo")) {
            return handleTodo(input);
        } else if (input.startsWith("deadline")) {
            return handleDeadline(input);
        } else if (input.startsWith("event")) {
            return handleEvent(input);
        } else if (input.startsWith("find")) {
            return handleFind(input);
        }
        else {
            throw new InvalidCommandException("Unknown command: " + input);
        }
    }

    private String handleList() {
        if (taskList.getTasks().isEmpty()) return "You have no tasks in your list.";
        StringBuilder sb = new StringBuilder("Here are your tasks:\n");
        for (int i = 0; i < taskList.getTasks().size(); i++) {
            sb.append(i + 1).append(". ").append(taskList.getTasks().get(i)).append("\n");
        }
        return sb.toString();
    }

    private String handleMark(String input) throws DobbyException {
        try {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            Task t = taskList.getTasks().get(index);
            t.markDone();
            storage.saveTasks(taskList.getTasks());
            return "Nice! I've marked this task as done:\n  " + t;
        } catch (Exception e) {
            throw new InvalidCommandException("Invalid task number for mark command.");
        }
    }

    private String handleUnmark(String input) throws DobbyException {
        try {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            Task t = taskList.getTasks().get(index);
            t.markNotDone();
            storage.saveTasks(taskList.getTasks());
            return "Nice! I've marked this task as not done:\n  " + t;
        } catch (Exception e) {
            throw new InvalidCommandException("Invalid task number for unmark command.");
        }
    }

    private String handleDelete(String input) throws DobbyException {
        try {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            Task t = taskList.deleteTask(index);
            storage.saveTasks(taskList.getTasks());
            return "Noted. I've removed this task:\n  " + t;
        } catch (Exception e) {
            throw new InvalidCommandException("Invalid task number for delete command.");
        }
    }

    private String handleTodo(String input) throws DobbyException {
        if (input.length() <= 4) {
            throw new InvalidTaskException("Task description cannot be empty!");
        }
        String desc = input.substring(5).trim();
        if (desc.isEmpty()) {
            throw new InvalidTaskException("Task description cannot be empty!");
        }
        Task t = new ToDo(desc);
        taskList.addTask(t);
        storage.saveTasks(taskList.getTasks());
        return "Got it. I've added this task:\n  " + t +
                "\nNow you have " + taskList.getTasks().size() + " tasks in the list.";
    }


    private String handleDeadline(String input) throws DobbyException {
        try {
            String[] parts = input.substring(9).split("/by");
            if (parts.length != 2) throw new InvalidCommandException("Invalid deadline format.");
            LocalDateTime by = LocalDateTime.parse(parts[1].trim(), DATE_FORMAT);
            Task t = new Deadline(parts[0].trim(), by);
            taskList.addTask(t);
            storage.saveTasks(taskList.getTasks());
            return "Got it. I've added this deadline:\n  " + t +
                    "\nNow you have " + taskList.getTasks().size() + " tasks in the list.";
        } catch (Exception e) {
            throw new InvalidCommandException("Invalid deadline command: " + e.getMessage());
        }
    }

    private String handleEvent(String input) throws DobbyException {
        try {
            String[] parts = input.substring(5).split("/from|/to");
            if (parts.length != 3) throw new InvalidCommandException("Invalid event format.");
            LocalDateTime from = LocalDateTime.parse(parts[1].trim(), DATE_FORMAT);
            LocalDateTime to = LocalDateTime.parse(parts[2].trim(), DATE_FORMAT);
            Task t = new Event(parts[0].trim(), from, to);
            taskList.addTask(t);
            storage.saveTasks(taskList.getTasks());
            return "Got it. I've added this event:\n  " + t +
                    "\nNow you have " + taskList.getTasks().size() + " tasks in the list.";
        } catch (Exception e) {
            throw new InvalidCommandException("Invalid event command: " + e.getMessage());
        }
    }

    private String handleFind(String input) throws DobbyException {
        String keyword = input.substring(4).trim(); // extract text after "find"
        if (keyword.isEmpty()) {
            throw new InvalidCommandException("Please provide a keyword to search for.");
        }

        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (int i = 0; i < taskList.getTasks().size(); i++) {
            Task t = taskList.getTasks().get(i);
            if (t.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                if (count == 0) sb.append("Here are the matching tasks in your list:\n");
                sb.append(count + 1).append(".").append(t).append("\n");
                count++;
            }
        }

        if (count == 0) {
            return "No matching tasks found for keyword: " + keyword;
        }
        return sb.toString();
    }


    private String handleHelp() {
        return "Here are the available commands:\n"
                + "1. list - Display all tasks\n"
                + "2. todo <description> - Add a ToDo task\n"
                + "3. deadline <description> /by <yyyy-MM-dd HHmm> - Add a Deadline task\n"
                + "4. event <description> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm> - Add an Event\n"
                + "5. mark <task number> - Mark a task as done\n"
                + "6. unmark <task number> - Mark a task as not done\n"
                + "7. delete <task number> - Delete a task\n"
                + "8. find <task name> - Search for a task\n"
                + "9. help - Show this help message\n"
                + "10. bye - Exit the application";
    }

}
