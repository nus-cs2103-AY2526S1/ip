package pagrobot.ui;

import pagrobot.tasklist.TaskList;
import pagrobot.tasks.Task;

/**
 * Represents the user interface for PagroBot.
 * Replies are short and passive-aggressive.
 */
public class Ui {
    public static final String LINE = "____________________________________________________________";
    private static final String NAME = "PagroBot";

    private String out(String s) {
        System.out.println(s);
        System.out.println(LINE);
        return s;
    }

    /**
     * Prints greeting when bot starts.
     *
     * @return the greeting message.
     */
    public String greet() {
        return out("Hello. I'm " + NAME + ". Let's get this over with.");
    }

    /**
     * Prints when a task is marked.
     *
     * @param t the task.
     * @return the confirmation message.
     */
    public String mark(Task t) {
        return out("Fine. Marked.\n" + t);
    }

    /**
     * Prints when a task is unmarked.
     *
     * @param t the task.
     * @return the confirmation message.
     */
    public String unmark(Task t) {
        return out("Unmark lor. Unmarked.\n" + t);
    }

    /**
     * Prints farewell when ending session.
     *
     * @return the bye message.
     */
    public String bye() {
        return out("Bye. Try not to come back.");
    }

    /**
     * Prints all tasks in the list.
     *
     * @param tl the task list.
     * @return the string of tasks.
     */
    public String list(TaskList tl) {
        return out("Here. Your tasks:\n" + tl);
    }

    /**
     * Prints when a task is deleted.
     *
     * @param deletedTask the deleted task.
     * @param sizeLeft how many tasks left.
     * @return the confirmation message.
     */
    public String delete(Task deletedTask, int sizeLeft) {
        return out("Deleted. Happy?\n" + deletedTask + "\nLeft: " + sizeLeft);
    }

    /**
     * Prints when input command is invalid.
     *
     * @return the warning message.
     */
    public String playPunk() {
        return out("Invalid command. Don't anyhow.");
    }

    /**
     * Prints when a task is added.
     *
     * @param t the added task.
     * @param sizeLeft how many tasks left.
     * @return the confirmation message.
     */
    public String add(Task t, int sizeLeft) {
        return out("Added. You're welcome.\n" + t + "\nTotal: " + sizeLeft);
    }

    /**
     * Prints when tasks are found.
     *
     * @param taskList the found tasks.
     * @return the result string.
     */
    public String findTask(TaskList taskList) {
        return out("Found lor:\n" + taskList);
    }

    /**
     * Prints when tasks are sorted.
     *
     * @param taskList the sorted tasks.
     * @return the result string.
     */
    public String sort(TaskList taskList) {
        return out("Sorted. Finally.\n" + taskList);
    }

    /**
     * Prints the help message with all available commands.
     *
     * @return the help message.
     */
    public String help() {
        String msg = """
                     You blur? Fine, here:
                       list        - show all tasks
                       todo XXX    - add a todo
                       deadline XXX /by DATE - add a deadline
                       event XXX /from DateTime /to DateTime    - add an event
                       mark N      - mark task N done
                       unmark N    - unmark task N
                       delete N    - delete task N
                       find WORD   - find tasks with WORD
                       sort        - sort tasks
                       help        - show this help""";
        return out(msg);
    }
}
