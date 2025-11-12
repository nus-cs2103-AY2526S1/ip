package seedu.haru;

import java.util.ArrayList;
import java.time.format.DateTimeParseException;

/**
 * The main class of the Haru Chatbot application.
 * Handles the main program loop, user input, and command execution.
 */
public class Haru {
    private static final String LOGO = """
               ___ ___
             /   |   \\_____ _______ __ __
            /    ~    \\__  \\\\_  __ \\  |  \\
            \\    Y    // __ \\|  | \\/  |  /
             \\___|_  /(____  /__|  |____/
                   \\/      \\/
            """;

    private final Ui ui;
    private final Storage storage;
    private final TaskList taskList;

    /**
     * Constructs a new Haru chatbot instance.
     * <p>
     * Initializes the user interface, storage, and task list by loading
     * existing tasks from the storage file.
     */
    public Haru() {
        this.ui = new Ui();
        this.storage = new Storage("./data/haru.txt");
        this.taskList = new TaskList(new ArrayList<>(storage.loadTasks()));
    }

    /**
     * The entry point of the Haru application.
     * <p>
     * Creates a new Haru instance and starts its interactive command loop.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String... args) {
        Haru haru = new Haru();
        haru.run();
    }

    /**
     * Runs the main interactive loop of the Haru chatbot.
     * <p>
     * Continuously reads user input, processes commands, and displays responses
     * until an exit command is received. Errors are caught and displayed to the user.
     */
    private void run() {
        ui.showWelcome(LOGO);

        while (true) {
            try {
                String input = ui.readCommand();
                if (shouldExit(input)) {
                    break;
                }
                processCommand(input);
            } catch (HaruException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.close();
    }

    /**
     * Checks if the input command is "bye" to exit the program.
     *
     * @param input the user input
     * @return true if the user wants to exit, false otherwise
     */
    private boolean shouldExit(String input) {
        if (input.equalsIgnoreCase("bye")) {
            ui.showGoodbye();
            return true;
        }
        return false;
    }

    /**
     * Normalizes a command by mapping its shortcut (first letter) to the full command.
     *
     * @param command the user input command
     * @return the normalized command
     */
    private String normalizeCommand(String command) {
        command = command.toLowerCase().trim();

        return switch (command) {
            case "t" -> "todo";
            case "d" -> "deadline";
            case "e" -> "event";
            case "l" -> "list";
            case "m" -> "mark";
            case "u" -> "unmark";
            case "del" -> "delete";
            case "f" -> "find";
            default -> command;
        };
    }


    /**
     * Processes a user command by parsing it and dispatching it
     * to the appropriate command handler.
     *
     * @param input the raw user input
     * @throws HaruException if the command is invalid
     */
    private void processCommand(String input) throws HaruException {
        String command = normalizeCommand(Parser.getCommand(input));
        String arg = Parser.getArguments(input);

        switch (command) {
        case "list":
            handleListCommand();
            break;
        case "todo":
            handleTodoCommand(arg);
            break;
        case "deadline":
            handleDeadlineCommand(arg);
            break;
        case "event":
            handleEventCommand(arg);
            break;
        case "mark":
            handleMarkCommand(arg);
            break;
        case "unmark":
            handleUnmarkCommand(arg);
            break;
        case "delete":
            handleDeleteCommand(arg);
            break;
        case "find":
            handleFindCommand(arg);
            break;
        default:
            throw new HaruException("Please specify the type of your task");
        }
    }

    /**
     * Displays all tasks in the task list to the user.
     * <p>
     * If the task list is empty, a message indicating no tasks are found is shown.
     * Otherwise, a numbered list of tasks is displayed.
     */
    private void handleListCommand() {
        if (taskList.size() == 0) {
            ui.showMessage("    No task found :(");
        } else {
            String taskListString = buildTaskListString();
            ui.showMessage(taskListString);
        }
    }

    /**
     * Builds a formatted string representing all tasks in the task list.
     * <p>
     * Each task is numbered starting from 1, and each line is indented for display.
     *
     * @return a string containing all tasks in a readable format
     */
    private String buildTaskListString() {
        StringBuilder sb = new StringBuilder("    Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            sb.append("    ")
                    .append(i + 1)
                    .append(". ")
                    .append(taskList.getTasks().get(i))
                    .append("\n");
        }
        return sb.toString();
    }

    /**
     * Handles the "todo" command by creating a ToDo task and adding it to the list.
     *
     * @param arg the description of the todo task
     * @throws HaruException if the description is empty
     */
    private void handleTodoCommand(String arg) throws HaruException {
        validateNonEmptyArgument(arg, "Please specify the name of the task");

        Task todo = new ToDo(arg, Type.TODO);
        addTaskAndSave(todo);
        showTaskAddedMessage(todo);
    }

    /**
     * Handles the "deadline" command by creating a Deadline task and adding it to the list.
     *
     * @param arg the description and deadline of the task
     * @throws HaruException if the date format is invalid
     */
    private void handleDeadlineCommand(String arg) throws HaruException {
        String[] deadlineParts = Parser.parseDeadline(arg);
        Task deadlineTask = createDeadlineTask(deadlineParts);
        addTaskAndSave(deadlineTask);
        showTaskAddedMessage(deadlineTask);
    }

    /**
     * Creates a Deadline task from parsed parts.
     *
     * @param deadlineParts the array containing description and date
     * @return the created Deadline task
     * @throws HaruException if the date format is invalid
     */
    private Task createDeadlineTask(String[] deadlineParts) throws HaruException {
        try {
            return new Deadline(deadlineParts[0], deadlineParts[1], Type.DEADLINE);
        } catch (DateTimeParseException e) {
            throw new HaruException("    Invalid date format! Use yyyy-mm-dd, e.g., 2019-12-02");
        }
    }

    /**
     * Handles the "event" command by creating an Event task and adding it to the list.
     *
     * @param arg the description, start, and end times of the event
     */
    private void handleEventCommand(String arg) throws HaruException {
        String[] eventParts = Parser.parseEvent(arg);
        Task eventTask = new Event(eventParts[0], eventParts[2], eventParts[1], Type.EVENT);
        addTaskAndSave(eventTask);
        showTaskAddedMessage(eventTask);
    }

    /**
     * Handles the "mark" command by marking a task as done.
     *
     * @param arg the index of the task to mark
     * @throws HaruException if the task index is invalid
     */
    private void handleMarkCommand(String arg) throws HaruException {
        int index = parseTaskIndex(arg);
        taskList.mark(index);
        storage.saveTasks(taskList.getTasks());
        ui.showMessage("    Nice! I've marked this task as done:\n        "
                + taskList.getTasks().get(index));
    }

    /**
     * Handles the "unmark" command by marking a task as not done.
     *
     * @param arg the index of the task to unmark
     * @throws HaruException if the task index is invalid
     */
    private void handleUnmarkCommand(String arg) throws HaruException {
        int index = parseTaskIndex(arg);
        taskList.unmark(index);
        storage.saveTasks(taskList.getTasks());
        ui.showMessage("    OK, I've unmarked this task as not done yet:\n        "
                + taskList.getTasks().get(index));
    }

    /**
     * Handles the "delete" command by removing a task from the list.
     *
     * @param arg the index of the task to delete
     * @throws HaruException if the task index is invalid
     */
    private void handleDeleteCommand(String arg) throws HaruException {
        int index = parseTaskIndex(arg);
        Task removed = taskList.remove(index);
        storage.saveTasks(taskList.getTasks());
        ui.showMessage("    Noted. I've removed this task:\n        " + removed);
    }

    /**
     * Handles the "find" command by searching tasks containing a keyword.
     *
     * @param arg the keyword to search for
     * @throws HaruException if the keyword is empty
     */
    private void handleFindCommand(String arg) throws HaruException {
        validateNonEmptyArgument(arg, "Please specify what you are trying to find");

        TaskList result = taskList.find(arg);
        if (result.size() == 0) {
            ui.showMessage("    No task found :(");
        } else {
            String foundTasksString = buildFoundTasksString(result);
            ui.showMessage(foundTasksString);
        }
    }

    /**
     * Builds a formatted string representing the tasks found in a search result.
     * <p>
     * Each task is numbered starting from 1, and each line is indented for display.
     *
     * @param result the TaskList containing the matching tasks
     * @return a string containing the matching tasks in a readable, numbered format
     */
    private String buildFoundTasksString(TaskList result) {
        StringBuilder sb = new StringBuilder("    Here are the matching tasks in your list:\n");
        for (int i = 0; i < result.size(); i++) {
            sb.append("    ")
                    .append(i + 1)
                    .append(". ")
                    .append(result.getTasks().get(i))
                    .append("\n");
        }
        return sb.toString();
    }

    /**
     * Adds a task to the task list and saves the updated list to storage.
     *
     * @param task the task to add
     */
    private void addTaskAndSave(Task task) {
        taskList.add(task);
        storage.saveTasks(taskList.getTasks());
    }

    /**
     * Shows a message to the user confirming that a task has been added.
     *
     * @param task the task that was added
     */
    private void showTaskAddedMessage(Task task) {
        ui.showMessage("    Got it. I've added this task:\n        " + task
                + "\n    Now you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * Parses a task index from a string argument.
     *
     * @param arg the string containing the task index
     * @return the zero-based task index
     * @throws HaruException if the input is not a valid integer
     */
    private int parseTaskIndex(String arg) throws HaruException {
        try {
            return Integer.parseInt(arg) - 1;
        } catch (NumberFormatException e) {
            throw new HaruException("Please provide a valid task number");
        }
    }

    /**
     * Validates that a command argument is not empty.
     *
     * @param arg the argument to validate
     * @param errorMessage the error message to throw if empty
     * @throws HaruException if the argument is empty
     */
    private void validateNonEmptyArgument(String arg, String errorMessage) throws HaruException {
        if (arg.isEmpty()) {
            throw new HaruException(errorMessage);
        }
    }

    /**
     * Generates a response for the user's chat message.
     *
     * @param input the input string provided by the user
     * @return the processed result as a string
     */
    public String getResponse(String input) {
        Storage responseStorage = new Storage("./data/haru.txt");
        TaskList responseTaskList = new TaskList(new ArrayList<>(responseStorage.loadTasks()));

        try {
            if (input.equalsIgnoreCase("bye")) {
                return "Goodbye! Hope to see you again soon.";
            }

            String command = Parser.getCommand(input);
            String arg = Parser.getArguments(input);

            return processResponseCommand(command, arg, responseTaskList, responseStorage);

        } catch (HaruException e) {
            return e.getMessage();
        }
    }

    /**
     * Processes a user command in the context of response generation.
     *
     * @param command the command type
     * @param arg the command arguments
     * @param taskList the task list to operate on
     * @param storage the storage instance to persist changes
     * @return the response string
     * @throws HaruException if the command is invalid
     */
    private String processResponseCommand(String command, String arg,
                                          TaskList taskList, Storage storage) throws HaruException {
        String instruction = normalizeCommand(command);
        return switch (instruction) {
            case "list" -> handleListResponse(taskList);
            case "todo" -> handleTodoResponse(arg, taskList, storage);
            case "deadline" -> handleDeadlineResponse(arg, taskList, storage);
            case "event" -> handleEventResponse(arg, taskList, storage);
            case "mark" -> handleMarkResponse(arg, taskList, storage);
            case "unmark" -> handleUnmarkResponse(arg, taskList, storage);
            case "delete" -> handleDeleteResponse(arg, taskList, storage);
            case "find" -> handleFindResponse(arg, taskList);
            default -> throw new HaruException("Please specify the type of your task");
        };
    }

    /**
     * Handles the "list" command for generating a response string.
     *
     * @param taskList the task list
     * @return formatted string of all tasks
     */
    private String handleListResponse(TaskList taskList) {
        if (taskList.size() == 0) {
            return "No task found :(";
        }

        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            sb.append(i + 1).append(". ").append(taskList.getTasks().get(i)).append("\n");
        }
        return sb.toString();
    }

    /**
     * Handles the "todo" command for generating a response string.
     *
     * @param arg task description
     * @param taskList the task list
     * @param storage storage instance
     * @return response string
     * @throws HaruException if argument is empty
     */
    private String handleTodoResponse(String arg, TaskList taskList, Storage storage) throws HaruException {
        validateNonEmptyArgument(arg, "Please specify the name of the task");

        Task todo = new ToDo(arg, Type.TODO);
        taskList.add(todo);
        storage.saveTasks(taskList.getTasks());
        return "Got it. I've added this task:\n  " + todo
                + "\nNow you have " + taskList.size() + " tasks in the list.";
    }

    /**
     * Handles the "deadline" command for generating a response string.
     *
     * @param arg task description and deadline
     * @param taskList the task list
     * @param storage storage instance
     * @return response string
     * @throws HaruException if date is invalid
     */
    private String handleDeadlineResponse(String arg, TaskList taskList, Storage storage) throws HaruException {
        String[] deadlineParts = Parser.parseDeadline(arg);
        Task deadlineTask = createDeadlineTaskForResponse(deadlineParts);
        taskList.add(deadlineTask);
        storage.saveTasks(taskList.getTasks());
        return "Got it. I've added this task:\n  " + deadlineTask
                + "\nNow you have " + taskList.size() + " tasks in the list.";
    }

    /**
     * Creates a Deadline task from description and date strings.
     *
     * @param deadlineParts array with description at index 0 and date at index 1
     * @return the created Deadline task
     * @throws HaruException if the date format is invalid
     */
    private Task createDeadlineTaskForResponse(String[] deadlineParts) throws HaruException {
        try {
            return new Deadline(deadlineParts[0], deadlineParts[1], Type.DEADLINE);
        } catch (DateTimeParseException e) {
            throw new HaruException("Invalid date format! Use yyyy-mm-dd, e.g., 2019-12-02");
        }
    }

    /**
     * Handles the "event" command for generating a response string.
     *
     * @param arg task description, start, and end times
     * @param taskList the task list
     * @param storage storage instance
     * @return response string
     */
    private String handleEventResponse(String arg, TaskList taskList, Storage storage) throws HaruException {
        String[] eventParts = Parser.parseEvent(arg);
        Task eventTask = new Event(eventParts[0], eventParts[2], eventParts[1], Type.EVENT);
        taskList.add(eventTask);
        storage.saveTasks(taskList.getTasks());
        return "Got it. I've added this task:\n  " + eventTask
                + "\nNow you have " + taskList.size() + " tasks in the list.";
    }

    /**
     * Handles the "mark" command for generating a response string.
     *
     * @param arg task index
     * @param taskList the task list
     * @param storage storage instance
     * @return response string
     * @throws HaruException if index is invalid
     */
    private String handleMarkResponse(String arg, TaskList taskList, Storage storage) throws HaruException {
        int index = parseTaskIndex(arg);
        taskList.mark(index);
        storage.saveTasks(taskList.getTasks());
        return "Nice! I've marked this task as done:\n  " + taskList.getTasks().get(index);
    }

    /**
     * Handles the "unmark" command for generating a response string.
     *
     * @param arg task index
     * @param taskList the task list
     * @param storage storage instance
     * @return response string
     * @throws HaruException if index is invalid
     */
    private String handleUnmarkResponse(String arg, TaskList taskList, Storage storage) throws HaruException {
        int index = parseTaskIndex(arg);
        taskList.unmark(index);
        storage.saveTasks(taskList.getTasks());
        return "OK, I've unmarked this task as not done yet:\n  " + taskList.getTasks().get(index);
    }

    /**
     * Handles the "delete" command for generating a response string.
     *
     * @param arg task index
     * @param taskList the task list
     * @param storage storage instance
     * @return response string
     * @throws HaruException if index is invalid
     */
    private String handleDeleteResponse(String arg, TaskList taskList, Storage storage) throws HaruException {
        int index = parseTaskIndex(arg);
        Task removed = taskList.remove(index);
        storage.saveTasks(taskList.getTasks());
        return "Noted. I've removed this task:\n  " + removed;
    }

    /**
     * Handles the "find" command for generating a response string.
     *
     * @param arg keyword to search for
     * @param taskList the task list
     * @return formatted string of matching tasks
     * @throws HaruException if keyword is empty
     */
    private String handleFindResponse(String arg, TaskList taskList) throws HaruException {
        validateNonEmptyArgument(arg, "Please specify what you are trying to find");

        TaskList result = taskList.find(arg);
        if (result.size() == 0) {
            return "No task found :(";
        }

        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < result.size(); i++) {
            sb.append(i + 1).append(". ").append(result.getTasks().get(i)).append("\n");
        }
        return sb.toString();
    }
}