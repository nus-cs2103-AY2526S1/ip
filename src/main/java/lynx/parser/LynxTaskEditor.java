package lynx.parser;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

import lynx.storage.LynxTaskList;
import objectclasses.command.DeleteCommand;
import objectclasses.command.ListCommand;
import objectclasses.command.LynxCommand;
import objectclasses.command.MarkCommand;
import objectclasses.command.UnmarkCommand;
import objectclasses.exception.LynxException;
import objectclasses.exception.MissingArgumentException;
import objectclasses.task.Task;

/**
 * Contains methods for executing commands that act on tasks within a task list.
 */
public class LynxTaskEditor {

    private final LynxTaskList taskList;

    public LynxTaskEditor(LynxTaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Marks tasks in the task list as specified by the input command.
     *
     * @param input Command staring with "mark".
     * @return String representing tasks marked.
     * @throws LynxException If command is invalid.
     */
    public String markTasks(String input) throws LynxException {
        if (input.length() <= 4 || !input.startsWith("mark")) {
            throw new MissingArgumentException("mark");
        }

        Consumer<Task> mark = Task::setComplete;
        MarkCommand command = new MarkCommand(input.substring(5).trim());
        return executeCommand(command, mark);
    }

    /**
     * Unmarks tasks in the task list as specified by the input command.
     *
     * @param input Command staring with "unmark".
     * @return String representing tasks unmarked.
     * @throws LynxException If command is invalid.
     */
    public String unmarkTasks(String input) throws LynxException {
        if (input.length() <= 6 || !input.startsWith("unmark")) {
            throw new MissingArgumentException("unmark");
        }

        Consumer<Task> unmark = Task::setIncomplete;
        UnmarkCommand command = new UnmarkCommand(input.substring(7).trim());
        return executeCommand(command, unmark);
    }

    /**
     * Removes tasks from the task list as specified by the input command.
     *
     * @param input Command staring with "delete".
     * @return String representing tasks deleted.
     * @throws LynxException If command is invalid.
     */
    public String deleteTasks(String input) throws LynxException {
        if (input.length() <= 6 || !input.startsWith("delete")) {
            throw new MissingArgumentException("delete");
        }

        Consumer<Task> delete = taskList::removeTask;
        DeleteCommand command = new DeleteCommand(input.substring(7).trim());
        String taskCount = String.format("You currently have %d task(s) in your list.%n", taskList.getCount());
        return String.format("%s%s", executeCommand(command, delete), taskCount);
    }

    /**
     * Prints tasks in the task list as specified by the input command.
     *
     * @param input Command staring with "list".
     * @return String representing tasks to list.
     * @throws LynxException If command is invalid.
     */
    public String listTasks(String input) throws LynxException {
        if (input.length() <= 4 || !input.startsWith("list")) {
            throw new MissingArgumentException("list");
        }

        Consumer<Task> list = task -> {};
        ListCommand command = new ListCommand(input.substring(5).trim());
        return executeCommand(command, list);
    }

    /**
     * Returns all urgent (incomplete) tasks today, if any. Uses the system clock.
     *
     * @return String representation of notice.
     */
    public String tasksToday() {
        LocalDateTime today = LocalDateTime.now();
        Stream<Task> tasks = LynxSorter.filterTasksByDate(taskList.getAllTasks(), today);
        List<Task> tasks1 = LynxSorter.filterTasksByStatus(tasks, Task.Status.INCOMPLETE).toList();

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
    public String tasksFromToday() {
        List<Task> tasks = LynxSorter.filterTasksByStatus(taskList.getAllTasks(), Task.Status.INCOMPLETE).toList();

        if (tasks.isEmpty()) {
            return "You have no outstanding tasks. Good job!";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Reminder for your outstanding tasks:");
        stringBuilder.append(executeOnTasks(task -> {}, tasks, ""));
        return stringBuilder.toString();
    }

    /**
     * Executes a command and returns its execution details.
     *
     * @param command <code>LynxCommand</code> containing the details of the user command.
     * @param action <code>Consumer</code> representing action to perform on tasks.
     * @return String detailing the command execution.
     * @throws LynxException If command is invalid.
     */
    private String executeCommand(LynxCommand command, Consumer<Task> action) throws LynxException {
        LynxSearcher.findTasks(command, taskList.getAllTasks());

        String actionDialogue = command.actionDialogue();
        String resultDialogue = executeOnTasks(action, command.getSearchResult(), command.emptyDialogue());
        return String.format("%s%s", actionDialogue, resultDialogue);
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

}
