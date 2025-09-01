package lynx.parser;

import static lynx.parser.LynxSearcher.findTasks;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

import lynx.storage.LynxTaskList;
import objectclasses.command.LynxCommand;
import objectclasses.exception.LynxException;
import objectclasses.exception.MissingArgumentException;
import objectclasses.task.Task;

/**
 * Contains methods to execute commands that act on the tasks within the task list.
 */
public class LynxTaskEditor {

    /**
     * Marks tasks in the task list as specified by the input command.
     *
     * @param input Command staring with "mark".
     * @return String representing tasks marked.
     * @throws LynxException If command is invalid.
     */
    public static String markTasks(String input) throws LynxException {
        Consumer<Task> mark = Task::setComplete;
        String empty = "     (No tasks found or marked)";

        if (input.length() <= 4 || !input.startsWith("mark")) {
            throw new MissingArgumentException("mark");
        }
        LynxCommand command = new LynxCommand(input.substring(5).trim());
        findTasks(command, LynxTaskList.getAllTasks());

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Marked %s", command.getSearchString()));
        stringBuilder.append(executeOnTasks(mark, command.getSearchResult(), empty));
        return stringBuilder.toString();
    }

    /**
     * Unmarks tasks in the task list as specified by the input command.
     *
     * @param input Command staring with "unmark".
     * @return String representing tasks unmarked.
     * @throws LynxException If command is invalid.
     */
    public static String unmarkTasks(String input) throws LynxException {
        Consumer<Task> unmark = Task::setIncomplete;
        String empty = "     (No tasks found or unmarked)";

        if (input.length() <= 6 || !input.startsWith("unmark")) {
            throw new MissingArgumentException("unmark");
        }
        LynxCommand command = new LynxCommand(input.substring(7).trim());
        findTasks(command, LynxTaskList.getAllTasks());

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Unmarked %s", command.getSearchString()));
        stringBuilder.append(executeOnTasks(unmark, command.getSearchResult(), empty));
        return stringBuilder.toString();
    }

    /**
     * Removes tasks from the task list as specified by the input command.
     *
     * @param input Command staring with "delete".
     * @return String representing tasks deleted.
     * @throws LynxException If command is invalid.
     */
    public static String deleteTasks(String input) throws LynxException {
        Consumer<Task> delete = task -> LynxTaskList.removeTask(task, false);
        String empty = "     (No tasks found or deleted)";

        if (input.length() <= 6 || !input.startsWith("delete")) {
            throw new MissingArgumentException("delete");
        }
        LynxCommand command = new LynxCommand(input.substring(7).trim());
        findTasks(command, LynxTaskList.getAllTasks());

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Removed %s", command.getSearchString()));
        stringBuilder.append(executeOnTasks(delete, command.getSearchResult(), empty));
        stringBuilder.append(String.format("You currently have %d task(s) in your list.%n", LynxTaskList.getCount()));
        return stringBuilder.toString();
    }

    /**
     * Prints tasks in the task list as specified by the input command.
     *
     * @param input Command staring with "list".
     * @return String representing tasks to list.
     * @throws LynxException If command is invalid.
     */
    public static String listTasks(String input) throws LynxException {
        Consumer<Task> list = task -> {};
        String empty = "     (No tasks yet)";

        if (input.length() <= 4 || !input.startsWith("list")) {
            throw new MissingArgumentException("list");
        }
        LynxCommand command = new LynxCommand(input.substring(5).trim());
        findTasks(command, LynxTaskList.getAllTasks());

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Here are %s", command.getSearchString()));
        stringBuilder.append(executeOnTasks(list, command.getSearchResult(), empty));
        return stringBuilder.toString();
    }

    /**
     * Maps a <code>Consumer</code> to a list of tasks and prints each task.
     *
     * @param consumer <code>Consumer</code> containing the action to execute on each task.
     * @param tasks List of tasks to perform action on.
     * @param empty String to be printed instead if list is empty.
     * @return String representing tasks acted on.
     */
    private static String executeOnTasks(Consumer<Task> consumer, List<Task> tasks, String empty) {
        int count = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (Task task : tasks) {
            count++;
            consumer.accept(task);
            stringBuilder.append(String.format("%n     %d.%s", count, task));
        }
        if (count == 0) {
            stringBuilder.append(String.format("%n%s", empty));
        }
        return stringBuilder.toString();
    }

    /**
     * Returns all urgent (incomplete) tasks today, if any. Uses the system clock.
     *
     * @return String representation of notice.
     */
    public static String tasksToday() {
        LocalDateTime today = LocalDateTime.now();
        Stream<Task> tasks = LynxTaskList.filterTasksByDate(LynxTaskList.getAllTasks(), today);
        List<Task> tasks1 = LynxTaskList.filterTasksByStatus(tasks, Task.Status.INCOMPLETE).toList();

        if (tasks1.isEmpty()) {
            return "You have cleared all tasks today. Good job!";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Here are the tasks requiring completion today:");
        stringBuilder.append(executeOnTasks(task -> {}, tasks1, ""));
        return stringBuilder.toString();
    }

    /**
     * Returns all incomplete tasks from today onwards. Uses the system clock.
     *
     * @return String representation of notice.
     */
    public static String tasksFromToday() {
        List<Task> tasks = LynxTaskList.filterTasksByStatus(
                LynxTaskList.getAllTasks(), Task.Status.INCOMPLETE).toList();

        if (tasks.isEmpty()) {
            return "You have no outstanding tasks. Good job!";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Reminder for your outstanding tasks:");
        stringBuilder.append(executeOnTasks(task -> {}, tasks, ""));
        return stringBuilder.toString();
    }

}
