package penguin.app;

import java.io.IOException;
import java.util.List;

import penguin.tasks.Deadline;
import penguin.tasks.Event;
import penguin.tasks.Task;
import penguin.tasks.Todo;

/**
 * Interprets and processes user commands.
 */
public class Parser {
    public enum TaskType {
        TODO, DEADLINE, EVENT;

        public static TaskType convert(String str) throws PenguinException {
            switch (str.toLowerCase()) {
                case "todo": return TODO;
                case "deadline": return DEADLINE;
                case "event": return EVENT;
                default: throw new PenguinException(str);
            }
        }
    }

    // Command constants
    private static final String BYE = "bye";
    private static final String LIST = "list";
    private static final String MARK = "mark";
    private static final String UNMARK = "unmark";
    private static final String DELETE = "delete";
    private static final String FIND = "find";
    private static final String SORT_ASC = "sort by date ascending";
    private static final String SORT_DESC = "sort by date descending";

    private static final String MSG_ALL_TASKS = "Here are the tasks in your list:";
    private static final String MSG_MATCHING_TASKS = "Here are the matching tasks in your list:";
    private static final String MSG_SORTED_ASC = "Here are the tasks in your list, sorted in ascending order:";
    private static final String MSG_SORTED_DESC = "Here are the tasks in your list, sorted in descending order:";
    private static final String MSG_UNKNOWN = "I don't understand your input.";

    /**
     * Parses a user command and executes the corresponding action.
     *
     * @param str     User input command
     * @param tasks   TaskList
     * @param ui      UI object
     * @param storage Storage object
     * @return Response string to show in GUI
     */
    public static String parse(String str, TaskList tasks, Ui ui, Storage storage) {
        try {
            if (str.equalsIgnoreCase(BYE)) {
                return handleBye(tasks, ui, storage);
            }

            switch (str.toLowerCase()) {
            case LIST:
                return ui.printList(tasks.getTasks(), MSG_ALL_TASKS);
            case SORT_ASC:
                return ui.printList(tasks.sortTasksByDate(true), MSG_SORTED_ASC);
            case SORT_DESC:
                return ui.printList(tasks.sortTasksByDate(false), MSG_SORTED_DESC);
            }

            if (str.startsWith(MARK) || str.startsWith(UNMARK)) {
                return handleMarkUnmark(str, tasks, ui);
            } else if (str.startsWith(DELETE)) {
                return handleDelete(str, tasks, ui);
            } else if (str.startsWith(FIND)) {
                return handleFind(str, tasks, ui);
            } else {
                return handleAddTask(str, tasks, ui);
            }

        } catch (PenguinException | IOException e) {
            return ui.printErrorMessage(e.getMessage());
        }
    }

    // ------------------- Helper Methods -------------------

    private static String handleBye(TaskList tasks, Ui ui, Storage storage) throws IOException {
        storage.writeAllTasks(tasks.getTasks());
        return ui.sayGoodbye();
    }

    private static String handleMarkUnmark(String str, TaskList tasks, Ui ui) throws PenguinException {
        String[] parts = str.split(" ");
        if (parts.length < 2) throw new PenguinException("Task index required for mark/unmark");

        int idx = Integer.parseInt(parts[1]) - 1;
        assert idx >= 0 : "Index cannot be negative";

        if (parts[0].equalsIgnoreCase(MARK)) {
            tasks.markAsDone(idx);
            return ui.markTask(tasks.getTask(idx));
        } else {
            tasks.markAsUndone(idx);
            return ui.unmarkTask(tasks.getTask(idx));
        }
    }

    private static String handleDelete(String str, TaskList tasks, Ui ui) throws PenguinException {
        String[] parts = str.split(" ");
        if (parts.length < 2) throw new PenguinException("Task index required for delete");

        int idx = Integer.parseInt(parts[1]) - 1;
        assert idx >= 0 : "Index cannot be negative";

        Task removed = tasks.deleteTask(idx);
        return ui.deleteTask(tasks.getNumOfTasks(), removed);
    }

    private static String handleFind(String str, TaskList tasks, Ui ui) throws PenguinException {
        String[] parts = str.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new PenguinException("Keyword required for find");
        }

        List<Task> filtered = tasks.findTasks(parts[1].trim());
        return ui.printList(filtered, MSG_MATCHING_TASKS);
    }

    private static String handleAddTask(String str, TaskList tasks, Ui ui) throws PenguinException {
        String[] parts = str.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new PenguinException(parts[0]);
        }

        String taskName = parts[1].trim();
        TaskType type = TaskType.convert(parts[0]);

        switch (type) {
        case TODO:
            tasks.addTask(new Todo(taskName));
            break;
        case DEADLINE:
            String[] deadlineParts = taskName.split("/by", 2);
            if (deadlineParts.length < 2 || deadlineParts[1].trim().isEmpty()) {
                throw new PenguinException("Deadline requires /by <date>");
            }
            tasks.addTask(new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim()));
            break;
        case EVENT:
            String[] nameAndDates = taskName.split("(?i)/from", 2); // case-insensitive
            if (nameAndDates.length < 2 || nameAndDates[1].trim().isEmpty()) {
                throw new PenguinException("Event requires /from <date> and /to <date>");
            }

            String name = nameAndDates[0].trim();
            String[] fromTo = nameAndDates[1].split("(?i)/to", 2);
            if (fromTo.length < 2 || fromTo[0].trim().isEmpty() || fromTo[1].trim().isEmpty()) {
                throw new PenguinException("Event requires both /from and /to dates");
            }

            String from = fromTo[0].trim();
            String to = fromTo[1].trim();

            tasks.addTask(new Event(name, from, to));
            break;
        }

        return ui.addTask(tasks.getNumOfTasks(), tasks.getTask(tasks.getNumOfTasks() - 1));
    }
}

