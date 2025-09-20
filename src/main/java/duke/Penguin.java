package duke;
/**
 * Penguin is a task management chatbot designed to help users organize and track their tasks efficiently.
 * This chatbot supports various types of tasks, including todos, deadlines, and events. It provides
 * functionality to add, delete, mark/unmark, list, find, and sort tasks. The application follows
 * object-oriented design principles with separation of concerns using dedicated classes for user
 * interface (Ui), data storage (Storage), command parsing (Parser), and task management (TaskList).
 * Key Features:
 * - Todo tasks: Simple tasks without specific timing
 * - Deadline tasks: Tasks with a specific due date
 * - Event tasks: Tasks with start and end times
 * - Task marking/unmarking as done
 * - Task deletion by index
 * - Task searching by keyword
 * - Task sorting by deadline
 * - Persistent storage to file
 * The Penguin chatbot can operate in both command-line interface (CLI) mode and graphical user
 * interface (GUI) mode through JavaFX. It ensures a smooth user experience by handling errors
 * gracefully and providing informative feedback to the user.
 */

public class Penguin {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a new instance of the Penguin chatbot with the specified file path for data storage.
     * This constructor initializes the core components of the Penguin chatbot, including:
     * - User Interface (Ui): Manages interactions with the user, displaying messages and reading input.
     * - Storage: Handles the loading and saving of tasks to a specified file, ensuring data persistence.
     * - TaskList: Maintains the list of tasks, providing operations to add, remove, and modify tasks.
     * The constructor attempts to load existing tasks from the specified file path. If the file does not
     * exist or an error occurs during loading, it initializes an empty task list and displays an error
     * message to the user. This ensures that the chatbot can start even if previous data cannot be loaded.
     * @param filePath The path to the data file where tasks are stored and retrieved. This path is used
     *                 for both reading existing tasks and saving new tasks. If the file does not exist,
     *                 it will be created when the first task is saved.
     */
    public Penguin(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (PenguinException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main loop of the chatbot.
     */
    public void run() {
        ui.showWelcome();

        while (ui.hasNextCommand()) {
            try {
                String input = ui.readCommand();
                Parser.Command command = Parser.parse(input);
                executeCommand(command);
            } catch (PenguinException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    private void executeCommand(Parser.Command command) throws PenguinException {
        switch (command.getType()) {
        case BYE:
            handleByeCommand();
            break;

        case LIST:
            handleListCommand();
            break;

        case MARK:
            handleMarkCommand(command.getTaskNumber());
            break;

        case UNMARK:
            handleUnmarkCommand(command.getTaskNumber());
            break;

        case DELETE:
            handleDeleteCommand(command.getTaskNumber());
            break;

        case TODO:
            handleTodoCommand(command.getDescription());
            break;

        case DEADLINE:
            handleDeadlineCommand(command.getDescription(), command.getDeadline());
            break;

        case EVENT:
            handleEventCommand(command.getDescription(), command.getFrom(), command.getTo());
            break;

        case FIND:
            handleFindCommand(command.getDescription());
            break;

        case SORT:
            handleSortCommand();
            break;

        case INVALID:
        default:
            handleInvalidCommand();
            break;
        }
    }

    private void handleByeCommand() {
        ui.showGoodbye();
        ui.close();
    }

    private void handleListCommand() {
        ui.showTaskList(tasks);
    }

    private void handleInvalidCommand() {
        ui.showInvalidTask();
    }

    /**
     * Handles the sort command.
     * @throws PenguinException if there's an error
     */
    private void handleSortCommand() throws PenguinException {
        tasks.sortByDeadline();
        storage.save(tasks.getTasks());
        ui.showTasksSorted();
    }

    /**
     * Handles the mark command.
     * @param num The task number as string
     * @throws PenguinException if there's an error
     */
    private void handleMarkCommand(String num) throws PenguinException {
        try {
            int idx = Integer.parseInt(num) - 1;
            if (tasks.isValidIndex(idx)) {
                Task task = tasks.markTask(idx);
                storage.save(tasks.getTasks());
                ui.showTaskMarked(task);
            } else {
                ui.showInvalidTask();
            }
        } catch (NumberFormatException e) {
            ui.showInvalidTask();
        }
    }

    /**
     * Handles the unmark command.
     * @param num The task number as string
     * @throws PenguinException if there's an error
     */
    private void handleUnmarkCommand(String num) throws PenguinException {
        try {
            int idx = Integer.parseInt(num) - 1;
            if (tasks.isValidIndex(idx)) {
                Task task = tasks.unmarkTask(idx);
                storage.save(tasks.getTasks());
                ui.showTaskUnmarked(task);
            } else {
                ui.showInvalidTask();
            }
        } catch (NumberFormatException e) {
            ui.showInvalidTask();
        }
    }

    /**
     * Handles the delete command.
     * @param num The task number as string
     * @throws PenguinException if there's an error
     */
    private void handleDeleteCommand(String num) throws PenguinException {
        try {
            int idx = Integer.parseInt(num) - 1;
            if (tasks.isValidIndex(idx)) {
                Task task = tasks.remove(idx);
                storage.save(tasks.getTasks());
                ui.showTaskDeleted(task, tasks.size());
            } else {
                ui.showInvalidTask();
            }
        } catch (NumberFormatException e) {
            ui.showInvalidTask();
        }
    }

    /**
     * Handles the todo command.
     * @param desc The task description
     * @throws PenguinException if there's an error
     */
    private void handleTodoCommand(String desc) throws PenguinException {
        if (desc.isEmpty()) {
            throw new PenguinException("The description of a todo cannot be empty.");
        }
        Task task = new Todo(desc);
        tasks.add(task);
        storage.save(tasks.getTasks());
        ui.showTaskAdded(task, tasks.size());
    }

    /**
     * Handles the deadline command.
     * @param description The task description
     * @param deadline The deadline string
     * @throws PenguinException if there's an error
     */
    private void handleDeadlineCommand(String description, String deadline) throws PenguinException {
        if (description.isEmpty()) {
            throw new PenguinException("The description of a deadline cannot be empty.");
        }
        try {
            Task task = new Deadline(description, deadline);
            tasks.add(task);
            storage.save(tasks.getTasks());
            ui.showTaskAdded(task, tasks.size());
        } catch (Exception e) {
            throw new PenguinException("Please use the date format yyyy-mm-dd (e.g., 2019-12-02)");
        }
    }

    /**
     * Handles the event command.
     * @param description The task description
     * @param from The start time
     * @param to The end time
     * @throws PenguinException if there's an error
     */
    private void handleEventCommand(String description, String from, String to) throws PenguinException {
        if (description.isEmpty()) {
            throw new PenguinException("The description of an event cannot be empty.");
        }
        Task task = new Event(description, from, to);
        tasks.add(task);
        storage.save(tasks.getTasks());
        ui.showTaskAdded(task, tasks.size());
    }

    /**
     * Handles the find command.
     * @param keyword The keyword to search for
     * @throws PenguinException if there's an error
     */
    private void handleFindCommand(String keyword) throws PenguinException {
        if (keyword.isEmpty()) {
            throw new PenguinException("The keyword for find cannot be empty.");
        }
        TaskList matchingTasks = tasks.findTasks(keyword);
        ui.showMatchingTasks(matchingTasks);
    }

    /**
     * Generates a response for the user's chat message.
     * @param input The user input
     * @return The response from Penguin
     */
    public String getResponse(String input) {
        assert input != null : "User input should not be null";
        try {
            Parser.Command command = Parser.parse(input);

            switch (command.getType()) {
            case BYE:
                return "Bye. Hope to see you again soon!";
            case LIST:
                return getTaskListString();
            case MARK:
                return handleMarkCommandGui(command.getTaskNumber());
            case UNMARK:
                return handleUnmarkCommandGui(command.getTaskNumber());
            case DELETE:
                return handleDeleteCommandGui(command.getTaskNumber());
            case TODO:
                return handleTodoCommandGui(command.getDescription());
            case DEADLINE:
                return handleDeadlineCommandGui(command.getDescription(), command.getDeadline());
            case EVENT:
                return handleEventCommandGui(command.getDescription(), command.getFrom(), command.getTo());
            case FIND:
                return handleFindCommandGui(command.getDescription());
            case SORT:
                return handleSortCommandGui();
            case INVALID:
                return "OOPS!!! I'm sorry, but I don't know what that means :-(";
            default:
                return "OOPS!!! I'm sorry, but I don't know what that means :-(";
            }
        } catch (Exception e) {
            return "OOPS!!! " + e.getMessage();
        }
    }

    private String getTaskListString() {
        if (tasks.size() == 0) {
            return "No tasks in your list.";
        }
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1) + ". " + tasks.get(i) + "\n");
        }
        return sb.toString();
    }

    private String handleMarkCommandGui(String taskNumber) {
        try {
            int idx = Integer.parseInt(taskNumber) - 1;
            if (tasks.isValidIndex(idx)) {
                Task task = tasks.markTask(idx);
                storage.save(tasks.getTasks());
                return "Nice! I've marked this task as done:\n" + task;
            } else {
                return "OOPS!!! Invalid task number.";
            }
        } catch (NumberFormatException e) {
            return "OOPS!!! Please provide a valid task number.";
        } catch (PenguinException e) {
            return "OOPS!!! " + e.getMessage();
        }
    }

    private String handleUnmarkCommandGui(String taskNumber) {
        try {
            int idx = Integer.parseInt(taskNumber) - 1;
            if (tasks.isValidIndex(idx)) {
                Task task = tasks.unmarkTask(idx);
                storage.save(tasks.getTasks());
                return "Nice! I've marked this task as not done yet:\n" + task;
            } else {
                return "OOPS!!! Invalid task number.";
            }
        } catch (NumberFormatException e) {
            return "OOPS!!! Please provide a valid task number.";
        } catch (PenguinException e) {
            return "OOPS!!! " + e.getMessage();
        }
    }

    private String handleDeleteCommandGui(String taskNumber) {
        try {
            int idx = Integer.parseInt(taskNumber) - 1;
            if (tasks.isValidIndex(idx)) {
                Task task = tasks.remove(idx);
                storage.save(tasks.getTasks());
                return "Noted. I've removed this task:\n  " + task
                    + "\nNow you have " + tasks.size() + " tasks in the list.";
            } else {
                return "OOPS!!! Invalid task number.";
            }
        } catch (NumberFormatException e) {
            return "OOPS!!! Please provide a valid task number.";
        } catch (PenguinException e) {
            return "OOPS!!! " + e.getMessage();
        }
    }

    private String handleTodoCommandGui(String description) {
        try {
            if (description.isEmpty()) {
                throw new PenguinException("The description of a todo cannot be empty.");
            }
            Task task = new Todo(description);
            tasks.add(task);
            storage.save(tasks.getTasks());
            return "Got it. I've added this task:\n  " + task
                + "\nNow you have " + tasks.size() + " tasks in the list.";
        } catch (PenguinException e) {
            return "OOPS!!! " + e.getMessage();
        }
    }

    private String handleDeadlineCommandGui(String description, String deadline) {
        try {
            if (description.isEmpty()) {
                throw new PenguinException("The description of a deadline cannot be empty.");
            }
            Task task = new Deadline(description, deadline);
            tasks.add(task);
            storage.save(tasks.getTasks());
            return "Got it. I've added this task:\n  " + task
                + "\nNow you have " + tasks.size() + " tasks in the list.";
        } catch (Exception e) {
            return "OOPS!!! Please use the date format yyyy-mm-dd (e.g., 2019-12-02)";
        }
    }

    private String handleEventCommandGui(String description, String from, String to) {
        try {
            if (description.isEmpty()) {
                throw new PenguinException("The description of an event cannot be empty.");
            }
            Task task = new Event(description, from, to);
            tasks.add(task);
            storage.save(tasks.getTasks());
            return "Got it. I've added this task:\n  " + task
                + "\nNow you have " + tasks.size() + " tasks in the list.";
        } catch (PenguinException e) {
            return "OOPS!!! " + e.getMessage();
        }
    }

    private String handleFindCommandGui(String keyword) {
        try {
            if (keyword.isEmpty()) {
                throw new PenguinException("The keyword for find cannot be empty.");
            }
            TaskList matchingTasks = tasks.findTasks(keyword);
            if (matchingTasks.size() == 0) {
                return "No matching tasks found.";
            } else {
                StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
                for (int i = 0; i < matchingTasks.size(); i++) {
                    sb.append((i + 1) + "." + matchingTasks.get(i) + "\n");
                }
                return sb.toString();
            }
        } catch (PenguinException e) {
            return "OOPS!!! " + e.getMessage();
        }
    }

    private String handleSortCommandGui() {
        try {
            tasks.sortByDeadline();
            storage.save(tasks.getTasks());
            return "Tasks have been sorted! Deadlines are now arranged chronologically (latest to earliest).";
        } catch (PenguinException e) {
            return "OOPS!!! " + e.getMessage();
        }
    }

    /**
     * Entry point of the Penguin chatbot application.
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        new Penguin("data/tasks.txt").run();
    }
}
