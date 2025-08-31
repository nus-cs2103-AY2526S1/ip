package lynx.parser;

import static lynx.parser.LynxSearcher.findTasks;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

import lynx.storage.LynxTaskList;
import lynx.ui.LynxUI;
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
     * @throws LynxException If command is invalid.
     */
    public static void markTasks(String input) throws LynxException {
        Consumer<Task> mark = Task::setComplete;
        String empty = "     (No tasks found or marked)";

        if (input.length() <= 4 || !input.startsWith("mark")) {
            throw new MissingArgumentException("mark");
        }
        LynxCommand command = new LynxCommand(input.substring(5).trim());
        findTasks(command, LynxTaskList.getAllTasks());
        LynxUI.line();
        System.out.println(String.format("Marked %s", command.getSearchString()));
        executeOnTasks(mark, command.getSearchResult(), empty);
        LynxUI.line();
    }

    /**
     * Unmarks tasks in the task list as specified by the input command.
     *
     * @param input Command staring with "unmark".
     * @throws LynxException If command is invalid.
     */
    public static void unmarkTasks(String input) throws LynxException {
        Consumer<Task> unmark = Task::setIncomplete;
        String empty = "     (No tasks found or unmarked)";

        if (input.length() <= 6 || !input.startsWith("unmark")) {
            throw new MissingArgumentException("unmark");
        }
        LynxCommand command = new LynxCommand(input.substring(7).trim());
        findTasks(command, LynxTaskList.getAllTasks());
        LynxUI.line();
        System.out.println(String.format("Unmarked %s", command.getSearchString()));
        executeOnTasks(unmark, command.getSearchResult(), empty);
        LynxUI.line();
    }

    /**
     * Removes tasks from the task list as specified by the input command.
     *
     * @param input Command staring with "delete".
     * @throws LynxException If command is invalid.
     */
    public static void deleteTasks(String input) throws LynxException {
        Consumer<Task> delete = task -> LynxTaskList.removeTask(task, false);
        String empty = "     (No tasks found or deleted)";

        if (input.length() <= 6 || !input.startsWith("delete")) {
            throw new MissingArgumentException("delete");
        }
        LynxCommand command = new LynxCommand(input.substring(7).trim());
        findTasks(command, LynxTaskList.getAllTasks());
        LynxUI.line();
        System.out.println(String.format("Removed %s", command.getSearchString()));
        executeOnTasks(delete, command.getSearchResult(), empty);
        System.out.println("You currently have " + LynxTaskList.getCount() + " task(s) in your list.");
        LynxUI.line();
    }

    /**
     * Prints tasks in the task list as specified by the input command.
     *
     * @param input Command staring with "list".
     * @throws LynxException If command is invalid.
     */
    public static void listTasks(String input) throws LynxException {
        Consumer<Task> list = task -> {};
        String empty = "     (No tasks yet)";

        if (input.length() <= 4 || !input.startsWith("list")) {
            throw new MissingArgumentException("list");
        }
        LynxCommand command = new LynxCommand(input.substring(5).trim());
        findTasks(command, LynxTaskList.getAllTasks());
        LynxUI.line();
        System.out.println(String.format("Here are %s", command.getSearchString()));
        executeOnTasks(list, command.getSearchResult(), empty);
        LynxUI.line();
    }

    /**
     * Maps a <code>Consumer</code> to a list of tasks and prints each task.
     *
     * @param consumer <code>Consumer</code> containing the action to execute on each task.
     * @param tasks List of tasks to perform action on.
     * @param empty String to be printed instead if list is empty.
     */
    private static void executeOnTasks(Consumer<Task> consumer, List<Task> tasks, String empty) {
        int count = 0;
        for (Task task : tasks) {
            count++;
            consumer.accept(task);
            System.out.println("     " + count + "." + task);
        }
        if (count == 0) {
            System.out.println(empty);
        }
    }

    /**
     * Prints all urgent (incomplete) tasks today, if any. Uses the system clock.
     */
    public static void tasksToday() {
        LocalDateTime today = LocalDateTime.now();
        Stream<Task> tasks = LynxTaskList.filterTasksByDate(LynxTaskList.getAllTasks(), today);
        List<Task> tasks1 = LynxTaskList.filterTasksByStatus(tasks, Task.Status.INCOMPLETE).toList();

        if (tasks1.isEmpty()) {
            System.out.println("You have cleared all tasks today. Good job!");
            LynxUI.line();
            return;
        }
        System.out.println("Here are the tasks requiring completion today:");
        executeOnTasks(task -> {}, tasks1, "");
        LynxUI.line();
    }

    /**
     * Prints all incomplete tasks from today onwards. Uses the system clock.
     */
    public static void tasksFromToday() {
        List<Task> tasks = LynxTaskList.filterTasksByStatus(
                LynxTaskList.getAllTasks(), Task.Status.INCOMPLETE).toList();

        if (tasks.isEmpty()) {
            System.out.println("You have no outstanding tasks. Good job!");
            LynxUI.line();
            return;
        }
        System.out.println("Reminder for your outstanding tasks:");
        executeOnTasks(task -> {}, tasks, "");
        LynxUI.line();
    }

}
