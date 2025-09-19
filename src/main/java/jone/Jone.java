package jone;

public class Jone {

    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_FIND = "find";
    private static final String COMMAND_HELP = "help";
    private static final String COMMAND_BYE = "bye";

    private static final String ERROR_TODO_EMPTY = "Description of todo cannot be empty";
    private static final String ERROR_DEADLINE_FORMAT =
            "Deadline must be in the format: deadline <desc> /by <yyyy-MM-dd>";
    private static final String ERROR_EVENT_FORMAT =
            "Event must be in the format: event <desc> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>";
    private static final String ERROR_FIND_EMPTY = "Please provide a keyword to search for";
    private static final String ERROR_UNKNOWN_COMMAND =
            "I'm sorry, but I don't know what that means.";

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    public Jone(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        TaskList loaded;
        try {
            loaded = new TaskList(storage.load());
        } catch (JoneException e) {
            ui.showError("Failed to load tasks: " + e.getMessage());
            loaded = new TaskList();
        }
        tasks = loaded;
    }

    public String getResponse(String line) {
        try {
            String[] parsed = Parser.parse(line);
            String command = parsed[0];
            String arguments = parsed[1];

            switch (command) {
                case COMMAND_TODO:
                    return handleTodo(arguments);

                case COMMAND_DEADLINE:
                    return handleDeadline(arguments);

                case COMMAND_EVENT:
                    return handleEvent(arguments);

                case COMMAND_LIST:
                    return tasks.getTasksAsString();

                case COMMAND_MARK:
                    return handleMark(arguments);

                case COMMAND_UNMARK:
                    return handleUnmark(arguments);

                case COMMAND_DELETE:
                    return handleDelete(arguments);

                case COMMAND_FIND:
                    return handleFind(arguments);

                case COMMAND_HELP:
                    return handleHelp(arguments);

                case COMMAND_BYE:
                    return ui.showExit();

                default:
                    return ui.showError(ERROR_UNKNOWN_COMMAND);
            }
        } catch (JoneException e) {
            return ui.showError(e.getMessage());
        } catch (Exception e) {
            return ui.showError("An unexpected error occurred: " + e.getMessage());
        }
    }

    private String handleTodo(String arguments) throws JoneException {
        if (arguments.isEmpty()) {
            return ui.showError(ERROR_TODO_EMPTY);
        }
        Task todo = new Todo(arguments);
        tasks.add(todo);
        storage.save(tasks.getAllTasks());
        return ui.showTaskAdded(todo, tasks.size());
    }

    private String handleDeadline(String arguments) throws JoneException {
        String[] parts = arguments.split(" /by ", 2);
        if (arguments.isEmpty() || parts.length < 2) {
            return ui.showError(ERROR_DEADLINE_FORMAT);
        }
        Task deadline = new Deadline(parts[0], parts[1]);
        tasks.add(deadline);
        storage.save(tasks.getAllTasks());
        return ui.showTaskAdded(deadline, tasks.size());
    }

    private String handleEvent(String arguments) throws JoneException {
        String[] parts = arguments.split(" /from | /to ");
        if (arguments.isEmpty() || parts.length < 3) {
            return ui.showError(ERROR_EVENT_FORMAT);
        }
        Task event = new Event(parts[0], parts[1], parts[2]);
        tasks.add(event);
        storage.save(tasks.getAllTasks());
        return ui.showTaskAdded(event, tasks.size());
    }

    private String handleMark(String arguments) throws JoneException {
        int index = Integer.parseInt(arguments) - 1;
        tasks.get(index).mark();
        storage.save(tasks.getAllTasks());
        return ui.showTaskMarked(tasks.get(index), true);
    }

    private String handleUnmark(String arguments) throws JoneException {
        int index = Integer.parseInt(arguments) - 1;
        tasks.get(index).unmark();
        storage.save(tasks.getAllTasks());
        return ui.showTaskMarked(tasks.get(index), false);
    }

    private String handleDelete(String arguments) throws JoneException {
        int index = Integer.parseInt(arguments) - 1;
        Task removed = tasks.remove(index);
        storage.save(tasks.getAllTasks());
        return ui.showTaskRemoved(removed, tasks.size());
    }

    private String handleFind(String arguments) {
        if (arguments.isEmpty()) {
            return ui.showError(ERROR_FIND_EMPTY);
        }
        return tasks.findTasks(arguments);
    }

    private String handleHelp(String arguments) {
        return ui.showHelp();
    }
}

