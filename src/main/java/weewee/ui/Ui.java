package weewee.ui;

import java.util.Scanner;

import weewee.task.Task;
import weewee.task.TaskList;

/**
 * Handles user interaction for the Weewee chatbot.
 */
public class Ui {
    private final Scanner sc = new Scanner(System.in);

    /** Prints a greeting message when the chatbot starts. */
    public String showGreet() {
        return "Hello! I'm Weewee\nWhat can I do for you?\n";
    }

    /** Prints a goodbye message when the chatbot ends. */
    public String showBye() {
        return "Bye. Hope to see you again soon! smoochsmooch <3\n";
    }

    /** Prints an error message when invalid input is detected. */
    public String showError() {
        return "Command requires more input info!\n";
    }

    /** @return The raw input string typed by the user. */
    public String readNextCommand() {
        return sc.nextLine();
    }

    /**
     * Displays the list of tasks.
     *
     * @param tasks The task list to display.
     */
    public String showList(TaskList tasks) {
        if (tasks.isEmpty()) {
            return "Your list is empty UwU!\n";
        }
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(String.format("%d. %s%n", i + 1, tasks.get(i)));
        }
        sb.append('\n');
        return sb.toString();
    }

    /**
     * Displays a message confirming that a task has been marked as done.
     *
     * @param task The task that was marked.
     */
    public String showMark(Task task) {
        return "Nice! I've marked this task as done:\n" + task + "\n";
    }

    /**
     * Displays a message confirming that a task has been unmarked.
     *
     * @param task The task that was unmarked.
     */
    public String showUnmark(Task task) {
        return "OK, I've marked this task as not done yet:\n" + task + "\n";
    }

    /**
     * Displays a message confirming that a task has been deleted.
     *
     * @param task  The task that was deleted.
     * @param tasks The updated task list.
     */
    public String showDelete(Task task, TaskList tasks) {
        return String.format(
                "Noted. I've removed this task:%n%s%nNow you have %d tasks in the list.%n",
                task, tasks.size());
    }

    /**
     * Displays a message confirming that a todo task has been added.
     *
     * @param task  The task that was added.
     * @param tasks The updated task list.
     */
    public String showTodo(Task task, TaskList tasks) {
        return String.format(
                "Got it. I've added this task:%n%s%nNow you have %d tasks in the list.%n",
                task, tasks.size());
    }

    /** Displays a message confirming that a deadline task has been added. */
    public String showDeadline(Task task, TaskList tasks) {
        return String.format(
                "Got it. I've added this task:%n%s%nNow you have %d tasks in the list.%n",
                task, tasks.size());
    }

    /** Displays a message confirming that an event task has been added. */
    public String showEvent(Task task, TaskList tasks) {
        return String.format(
                "Got it. I've added this task:%n%s%nNow you have %d tasks in the list.%n",
                task, tasks.size());
    }

    /**
     * Displays the list of tasks that match the keywords
     *
     * @param tasks The task list to display.
     */
    public String showFind(TaskList tasks) {
        if (tasks.isEmpty()) {
            return "No matching tasks found baka >v<\n\n";
        }
        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(String.format("%d. %s%n", i + 1, tasks.get(i)));
        }
        sb.append('\n');
        return sb.toString();
    }

    /**
     * Returns an error message when the sort type is invalid.
     *
     * @return The error string.
     */
    public String showSortError() {
        return "Baka invalid sort type! Use sort deadline | sort event start | sort event end\n";
    }

    /**
     * Returns an error message when the find command is incorrectly formatted.
     *
     * @return The error string.
     */
    public String showFindError() {
        return "Find format is wrong baka >v<! e.g find <keyword>\n";
    }

    /**
     * Returns an error message when the event command is incorrectly formatted.
     *
     * @return The error string.
     */
    public String showEventError() {
        return "Event format is wrong baka >v<!"
                + " e.g event <activity> /from <YYYY-MM-DD HHmm> /to <YYYY-MM-DD HHmm>\n";
    }

    /**
     * Returns an error message when the deadline command is incorrectly formatted.
     *
     * @return The error string.
     */
    public String showDeadlineError() {
        return "Deadline format is wrong baka >v< !"
                + " e.g deadline <activity> /by <YYYY-MM-DD HHmm>\n";
    }

    /**
     * Returns an error message when the todo command is incorrectly formatted.
     *
     * @return The error string.
     */
    public String showTodoError() {
        return "toDo format is wrong baka >v< ! e.g todo <activity>\n";
    }

    /**
     * Returns an error message when an invalid task index is entered.
     *
     * @return The error string.
     */
    public String showIndexError() {
        return "Baka only valid task number is allowed!\n";
    }

}
