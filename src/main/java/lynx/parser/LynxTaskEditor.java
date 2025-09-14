package lynx.parser;

import java.util.List;
import java.util.function.Consumer;

import lynx.storage.LynxTaskList;
import objectclasses.command.DeleteCommand;
import objectclasses.command.ListCommand;
import objectclasses.command.LynxCommand;
import objectclasses.command.MarkCommand;
import objectclasses.command.ReminderCommand;
import objectclasses.command.UnmarkCommand;
import objectclasses.exception.LynxException;
import objectclasses.exception.MissingArgumentException;
import objectclasses.task.Task;

/**
 * Contains methods for executing commands that act on tasks in a task list.
 * These include mark, unmark, delete and list commands.
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
        if (!input.startsWith("mark ")) {
            throw new MissingArgumentException("mark");
        }

        MarkCommand command = new MarkCommand(input.substring(5).trim());
        Consumer<Task> mark = Task::setComplete;
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
        if (!input.startsWith("unmark ")) {
            throw new MissingArgumentException("unmark");
        }

        UnmarkCommand command = new UnmarkCommand(input.substring(7).trim());
        Consumer<Task> unmark = Task::setIncomplete;
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
        if (!input.startsWith("delete ")) {
            throw new MissingArgumentException("delete");
        }

        DeleteCommand command = new DeleteCommand(input.substring(7).trim());
        Consumer<Task> delete = taskList::removeTask;
        String taskCount = String.format("\nYou currently have %d task(s) in your list.", taskList.getCount());
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
        if (!input.startsWith("list ")) {
            throw new MissingArgumentException("list");
        }

        ListCommand command = new ListCommand(input.substring(5).trim());
        Consumer<Task> list = task -> {};
        return executeCommand(command, list);
    }

    /**
     * Returns all urgent (incomplete) tasks today, if any. Uses the system clock.
     *
     * @return String representation of notice.
     */
    public String tasksToday() {
        try {
            ReminderCommand command = ReminderCommand.urgent();
            Consumer<Task> remind = task -> {};
            return executeCommand(command, remind);
        } catch (LynxException e) {
            return "";
        }
    }

    /**
     * Returns all incomplete tasks from today onwards. Uses the system clock.
     *
     * @return String representation of notice.
     */
    public String tasksFromToday() {
        try {
            ReminderCommand command = ReminderCommand.incomplete();
            Consumer<Task> remind = task -> {};
            return executeCommand(command, remind);
        } catch (LynxException e) {
            return "";
        }
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
        String resultDialogue = performOnTasks(action, command.getSearchResult(), command.emptyDialogue());
        assert(!actionDialogue.isEmpty());
        assert(!resultDialogue.isEmpty());

        return String.format("%s%s", actionDialogue, resultDialogue);
    }

    /**
     * Maps a <code>Consumer</code> to a list of tasks and prints each task.
     *
     * @param consumer <code>Consumer</code> containing the action to perform on each task.
     * @param tasks List of tasks to perform action on.
     * @param empty String to be printed instead if list is empty.
     * @return String representing tasks acted on.
     */
    private static String performOnTasks(Consumer<Task> consumer, List<Task> tasks, String empty) {
        int count = 0;
        StringBuilder stringBuilder = new StringBuilder();

        for (Task task : tasks) {
            count++;
            consumer.accept(task);
            stringBuilder.append(String.format("%n     %s", task));
        }
        if (count == 0) {
            stringBuilder.append(String.format("%n%s", empty));
        }

        assert (!stringBuilder.isEmpty());
        return stringBuilder.toString();
    }

}
