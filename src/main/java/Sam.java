import java.util.Scanner;

import sam.parser.Parser;
import sam.task.Deadline;
import sam.task.Event;
import sam.task.Priority;
import sam.task.Task;
import sam.task.TaskList;
import sam.task.Todo;
import sam.ui.Ui;

/**
 * Represents the main application class for Sam, a task management application.
 * This class handles the main program loop, user input processing, and
 * coordinates between different components like UI, Parser, TaskList, and Storage.
 */
public class Sam {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    /**
     * Constructor for Sam class.
     */
    public Sam() {
        this.ui = new Ui();
        this.storage = new Storage("./data/sam.txt");
        this.tasks = new TaskList(storage.load());
    }

    /**
     * Gets the welcome message for GUI.
     * @return The welcome message string
     */
    public String getWelcomeMessage() {
        return "Hello! I'm Sam\nWhat can I do for you?";
    }

    /**
     * Processes user input and returns a response for GUI.
     * @param input The user input string
     * @return The response string
     */
    public String getResponse(String input) {
        String[] parsed = Parser.parse(input.trim());
        String verb = parsed[0];
        String rest = parsed[1];

        try {
            switch (Command.of(verb)) {
                case BYE:
                    return "Bye. Hope to see you again soon!";

                case LIST:
                    if (tasks.size() == 0) {
                        return "Your task list is empty!";
                    }
                    StringBuilder listOutput = new StringBuilder("Here are the tasks in your list:\n");
                    for (int i = 0; i < tasks.size(); i++) {
                        listOutput.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
                    }
                    return listOutput.toString().trim();

                case MARK: {
                    int idx = parseIndex(rest, tasks.size());
                    tasks.get(idx).markDone();
                    storage.save(tasks.getTasks());
                    return "Nice! I've marked this task as done:\n" + tasks.get(idx);
                }

                case UNMARK: {
                    int idx = parseIndex(rest, tasks.size());
                    tasks.get(idx).unmark();
                    storage.save(tasks.getTasks());
                    return "OK, I've marked this task as not done yet:\n" + tasks.get(idx);
                }

                case DELETE: {
                    int idx = parseIndex(rest, tasks.size());
                    Task removed = tasks.remove(idx);
                    storage.save(tasks.getTasks());
                    return "Noted. I've removed this task:\n" + removed + 
                           "\nNow you have " + tasks.size() + " tasks in the list.";
                }

                case TODO: {
                    if (rest.isEmpty()) {
                        throw new EmptyDescriptionException("todo");
                    }
                    tasks.add(new Todo(rest));
                    storage.save(tasks.getTasks());
                    return "Got it. I've added this task:\n" + tasks.get(tasks.size() - 1) + 
                           "\nNow you have " + tasks.size() + " tasks in the list.";
                }

                case DEADLINE: {
                    if (rest.isEmpty() || !rest.contains("/by")) {
                        throw new SamException("OOPS!!! Use: deadline <description> /by <time>");
                    }
                    String[] a = rest.split("/by", 2);
                    String descr = a[0].trim(), by = a[1].trim();
                    if (descr.isEmpty() || by.isEmpty()) {
                        throw new SamException("OOPS!!! Use: deadline <description> /by <time>");
                    }
                    tasks.add(new Deadline(descr, by));
                    storage.save(tasks.getTasks());
                    return "Got it. I've added this task:\n" + tasks.get(tasks.size() - 1) + 
                           "\nNow you have " + tasks.size() + " tasks in the list.";
                }

                case EVENT: {
                    if (rest.isEmpty() || !rest.contains("/from") || !rest.contains("/to")) {
                        throw new SamException("OOPS!!! Use: event <description> /from <start> /to <end>");
                    }
                    String[] p1 = rest.split("/from", 2);
                    String[] p2 = p1[1].split("/to", 2);
                    String descr = p1[0].trim(), from = p2[0].trim(), to = p2[1].trim();
                    if (descr.isEmpty() || from.isEmpty() || to.isEmpty()) {
                        throw new SamException("OOPS!!! Use: event <description> /from <start> /to <end>");
                    }
                    tasks.add(new Event(descr, from, to));
                    storage.save(tasks.getTasks());
                    return "Got it. I've added this task:\n" + tasks.get(tasks.size() - 1) + 
                           "\nNow you have " + tasks.size() + " tasks in the list.";
                }

                case FIND: {
                    if (rest.isEmpty()) {
                        throw new SamException("OOPS!!! Please provide a keyword to search for.");
                    }
                    StringBuilder findOutput = new StringBuilder("Here are the matching tasks in your list:\n");
                    int matchCount = 0;
                    for (int i = 0; i < tasks.size(); i++) {
                        Task task = tasks.get(i);
                        if (task.toString().toLowerCase().contains(rest.toLowerCase())) {
                            matchCount++;
                            findOutput.append(matchCount).append(".").append(task).append("\n");
                        }
                    }
                    if (matchCount == 0) {
                        return "No tasks found matching '" + rest + "'.";
                    }
                    return findOutput.toString().trim();
                }

                case PRIORITY: {
                    return handlePriorityCommand(rest);
                }

                case UNKNOWN:
                    throw new UnknownCommandException();
            }
        } catch (SamException e) {
            return e.getMessage();
        } catch (NumberFormatException e) {
            return "OOPS!!! Task number must be an integer.";
        }
        return "";
    }

    /**
     * Handles the priority command to change task priority.
     * @param rest The task number and priority level
     * @return The response message after changing priority
     * @throws SamException If the format is invalid
     */
    private String handlePriorityCommand(String rest) throws SamException {
        if (rest.isEmpty()) {
            throw new SamException("OOPS!!! Use: priority <task_number> <priority_level>");
        }
        
        String[] parts = rest.trim().split("\\s+", 2);
        if (parts.length != 2) {
            throw new SamException("OOPS!!! Use: priority <task_number> <priority_level>");
        }
        
        int idx = parseIndex(parts[0], tasks.size());
        Priority newPriority = Priority.fromString(parts[1]);
        
        Task task = tasks.get(idx);
        task.setPriority(newPriority);
        saveTasks();
        
        return "Nice! I've updated the priority of this task:\n" + task;
    }

    /**
     * Helper method to save tasks to storage.
     */
    private void saveTasks() {
        storage.save(tasks.getTasks());
    }

    /**
     * Parses a task index from user input and validates it.
     *
     * @param arg  The string argument containing the task number
     * @param size The total number of tasks in the list
     * @return The parsed and validated task index (0-based)
     * @throws SamException If the argument is invalid or the index is out of bounds
     */
    private static int parseIndex(final String arg, final int size) throws SamException {
        assert arg != null && !arg.trim().isEmpty() : "Argument cannot be null or empty";
        assert size >= 0 : "Size must be non-negative";
        int n = Integer.parseInt(arg);
        int idx = n - 1;
        if (idx < 0 || idx >= size) {
            throw new SamException("OOPS!!! Invalid task number.");
        }
        assert idx >= 0 && idx < size : "Parsed index must be valid";
        return idx;
    }

    /**
     * Main method that runs the Sam task management application.
     * Initializes the application components and runs the main program loop
     * to process user commands until the user chooses to exit.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(final String[] args) {
        Scanner sc = new Scanner(System.in);
        Sam sam = new Sam();

        sam.ui.showWelcome();

        while (true) {
            String input = sc.nextLine().trim();
            String response = sam.getResponse(input);
            
            if (response.equals("Bye. Hope to see you again soon!")) {
                sam.ui.showLine();
                System.out.println(" " + response);
                sam.ui.showLine();
                sc.close();
                return;
            }
            
            sam.ui.showLine();
            System.out.println(" " + response.replace("\n", "\n "));
            sam.ui.showLine();
        }
    }
}
