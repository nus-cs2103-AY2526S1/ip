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
    // Essential constants for configuration and error messages
    private static final String DATA_FILE_PATH = "./data/sam.txt";
    private static final String DEADLINE_FORMAT_ERROR = "🌅 Gentle reminder: deadlines need clarity. Try: deadline <description> /by <time>";
    private static final String EVENT_FORMAT_ERROR = "🌸 Events flow from start to end. Use: event <description> /from <start> /to <end>";
    private static final String FIND_KEYWORD_ERROR = "🔍 To seek, one must name what they search for. Please provide a keyword.";
    private static final String TASK_NUMBER_ERROR = "🧮 Numbers bring order to chaos. Please provide a valid task number.";
    
    private final TaskList tasks;
    private final Storage storage;
    private final Ui ui;

    /**
     * Constructor for Sam class.
     */
    public Sam() {
        this.ui = new Ui();
        this.storage = new Storage(DATA_FILE_PATH);
        this.tasks = new TaskList(storage.load());
    }

    /**
     * Gets the welcome message for GUI.
     * @return The welcome message string
     */
    public String getWelcomeMessage() {
        return "🧘‍♂️ Greetings, I am Sam the Zen Master.\nIn stillness, all tasks find their place. How may I guide your productivity journey today?";
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
            return switch (Command.of(verb)) {
                case BYE -> "🙏 May peace accompany you on your journey. Until we meet again in mindful productivity!";
                case LIST -> handleListCommand();
                case MARK -> handleMarkCommand(rest);
                case UNMARK -> handleUnmarkCommand(rest);
                case DELETE -> handleDeleteCommand(rest);
                case TODO -> handleTodoCommand(rest);
                case DEADLINE -> handleDeadlineCommand(rest);
                case EVENT -> handleEventCommand(rest);
                case FIND -> handleFindCommand(rest);
                case PRIORITY -> handlePriorityCommand(rest);
                case UNKNOWN -> throw new UnknownCommandException();
            };
        } catch (SamException e) {
            return e.getMessage();
        } catch (NumberFormatException e) {
            return TASK_NUMBER_ERROR;
        }
    }

    /**
     * Handles the list command to display all tasks.
     * Returns a formatted string containing all tasks with their indices,
     * or a message indicating the list is empty.
     * @return The formatted list of tasks or empty list message
     */
    private String handleListCommand() {
        // Guard clause: handle empty list case first
        if (tasks.size() == 0) {
            return "🍃 Your mind is clear, your task list is empty. In emptiness, there is infinite potential.";
        }
        
        // Happy path: build and return task list
        StringBuilder listOutput = new StringBuilder("🧘‍♂️ Behold your current path of productivity:\n");
        for (int i = 0; i < tasks.size(); i++) {
            listOutput.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
        }
        return listOutput.toString().trim();
    }

    /**
     * Handles the mark command to mark a task as done.
     * @param rest The task index argument
     * @return The response message after marking the task
     * @throws SamException If the task index is invalid
     */
    private String handleMarkCommand(String rest) throws SamException {
        int idx = parseIndex(rest, tasks.size());
        tasks.get(idx).markDone();
        saveTasks();
        return "✨ Excellent! Another step forward on your journey. Task completed with mindful intention:\n" + tasks.get(idx);
    }

    /**
     * Handles the unmark command to mark a task as not done.
     * @param rest The task index argument
     * @return The response message after unmarking the task
     * @throws SamException If the task index is invalid
     */
    private String handleUnmarkCommand(String rest) throws SamException {
        int idx = parseIndex(rest, tasks.size());
        tasks.get(idx).unmark();
        saveTasks();
        return "🔄 No judgment here. Sometimes we must revisit our path. Task unmarked with compassion:\n" + tasks.get(idx);
    }

    /**
     * Handles the delete command to remove a task.
     * @param rest The task index argument
     * @return The response message after deleting the task
     * @throws SamException If the task index is invalid
     */
    private String handleDeleteCommand(String rest) throws SamException {
        int idx = parseIndex(rest, tasks.size());
        Task removed = tasks.remove(idx);
        saveTasks();
        return "🌸 Released into the void. Sometimes letting go creates space for new growth:\n" + removed + 
               "\nYour path now holds " + tasks.size() + " mindful tasks.";
    }

    /**
     * Handles the todo command to add a new todo task.
     * @param rest The task description
     * @return The response message after adding the task
     * @throws EmptyDescriptionException If the description is empty
     */
    private String handleTodoCommand(String rest) throws EmptyDescriptionException {
        if (rest.isEmpty()) {
            throw new EmptyDescriptionException("todo");
        }
        return addTask(new Todo(rest));
    }

    /**
     * Handles the deadline command to add a new deadline task.
     * @param rest The task description with /by time
     * @return The response message after adding the task
     * @throws SamException If the format is invalid
     */
    private String handleDeadlineCommand(String rest) throws SamException {
        if (rest.isEmpty() || !rest.contains("/by")) {
            throw new SamException(DEADLINE_FORMAT_ERROR);
        }
        
        String[] parts = rest.split("/by", 2);
        String description = parts[0].trim();
        String by = parts[1].trim();
        
        if (description.isEmpty() || by.isEmpty()) {
            throw new SamException(DEADLINE_FORMAT_ERROR);
        }
        
        return addTask(new Deadline(description, by));
    }

    /**
     * Handles the event command to add a new event task.
     * @param rest The task description with /from and /to times
     * @return The response message after adding the task
     * @throws SamException If the format is invalid
     */
    private String handleEventCommand(String rest) throws SamException {
        if (rest.isEmpty() || !rest.contains("/from") || !rest.contains("/to")) {
            throw new SamException(EVENT_FORMAT_ERROR);
        }
        
        String[] fromParts = rest.split("/from", 2);
        String[] toParts = fromParts[1].split("/to", 2);
        String description = fromParts[0].trim();
        String from = toParts[0].trim();
        String to = toParts[1].trim();
        
        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new SamException(EVENT_FORMAT_ERROR);
        }
        
        return addTask(new Event(description, from, to));
    }

    /**
     * Handles the find command to search for tasks.
     * Performs case-insensitive search across all task descriptions
     * and returns formatted results with match count.
     * @param rest The search keyword
     * @return The response message with matching tasks
     * @throws SamException If no keyword is provided
     */
    private String handleFindCommand(String rest) throws SamException {
        // Guard clause: validate input first
        if (rest.isEmpty()) {
            throw new SamException(FIND_KEYWORD_ERROR);
        }
        
        // Happy path: search for matching tasks
        StringBuilder findOutput = new StringBuilder("🔮 The universe reveals these aligned tasks:\n");
        int matchCount = 0;
        String keyword = rest.toLowerCase();
        
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.toString().toLowerCase().contains(keyword)) {
                matchCount++;
                findOutput.append(matchCount).append(".").append(task).append("\n");
            }
        }
        
        // Guard clause: handle no matches case
        if (matchCount == 0) {
            return "🔍 The search yields silence. Perhaps what you seek lies in a different path, or awaits creation.";
        }
        
        return findOutput.toString().trim();
    }

    /**
     * Helper method to add a task and generate the standard response.
     * @param task The task to add
     * @return The standard response message after adding a task
     */
    private String addTask(Task task) {
        tasks.add(task);
        saveTasks();
        return "🌱 A new intention has taken root in your journey:\n" + task + 
               "\nYour path of mindful productivity now contains " + tasks.size() + " tasks.";
    }

    /**
     * Handles the priority command to change task priority.
     * @param rest The task number and priority level
     * @return The response message after changing priority
     * @throws SamException If the format is invalid
     */
    private String handlePriorityCommand(String rest) throws SamException {
        if (rest.isEmpty()) {
            throw new SamException("⚖️ Priority requires both task and level. Use: priority <task_number> <priority_level>");
        }
        
        String[] parts = rest.trim().split("\\s+", 2);
        if (parts.length != 2) {
            throw new SamException("⚖️ Balance needs both elements. Use: priority <task_number> <priority_level>");
        }
        
        int idx = parseIndex(parts[0], tasks.size());
        Priority newPriority = Priority.fromString(parts[1]);
        
        Task task = tasks.get(idx);
        task.setPriority(newPriority);
        saveTasks();
        
        return "⚖️ Balance restored. The priority flows like water finding its level:\n" + task;
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
