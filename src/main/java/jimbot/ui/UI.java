package jimbot.ui;

import java.util.List;
import java.util.Scanner;

import jimbot.tasktype.Task;
import jimbot.tasktype.TaskList;

/**
 * Handles the user interface of the program, including
 * generating responses to user input and displaying task information.
 * Provides methods for formatting task lists, individual task actions,
 * greetings, farewells, and command responses.
 *
 * <p>All methods return formatted Strings instead of directly printing to console,
 * allowing separation of UI from application logic.</p>
 *
 * @author limjimin-nus
  * */
public class UI {
    private final Scanner scanner;

    /**
     * Constructs a UI object with a Scanner for user input.
     */
    public UI() {
        scanner = new Scanner(System.in);
    }

    /**
     * Returns a response string after marking a task as done.
     *
     * @param userList The list of tasks.
     * @param index Index of the task marked as done.
     * @return Formatted string showing the marked task.
     */
    public String markRes(TaskList userList, int index) {
        String header = "  Nice♪ I've marked this task as done: \n    ";
        return header + userList.getTask(index) + "\n  ♪ ｖ(＾＿＾ｖ)♪ ~~ ♪(ｖ＾＿＾)ｖ";
    }

    /**
     * Returns a response string after unmarking a task.
     *
     * @param userList The list of tasks.
     * @param index Index of the task marked as not done.
     * @return Formatted string showing the unmarked task.
     */
    public String unmarkRes(TaskList userList, int index) {
        String header = "  Ogayy, I've marked this task as not done yet: \n    ";
        return header + userList.getTask(index) + "\n  (｀_´)ゞ";
    }

    /**
     * Returns a response string after adding a new task to the list.
     *
     * @param task Task added.
     * @param taskCount Number of tasks after addition.
     * @return Formatted string showing the added task and total count.
     */
    public String addTask(Task task, int taskCount) {
        String header = "  Cool beans~ I've added this task: \n    ";
        String closer = "\n  You now have " + taskCount + " tasks in the list!\n  (￣^￣)ゞ";

        return header + task + closer;
    }

    /**
     * Returns a response string after deleting a task from the list.
     *
     * @param task Task removed.
     * @param taskCount Number of tasks remaining.
     * @return Formatted string showing the deleted task and remaining count.
     */
    public String deleteTask(Task task, int taskCount) {
        String header = "  Aite! I've removed this task:\n    ";
        String closer = "\n  You now have " + taskCount + " tasks in the list! \n  (─.─)ゞ";
        return header + task + closer;
    }

    /**
     * Prints a formatted string listing all tasks in a list.
     *
     * @param list List of tasks.
     * @return Formatted string showing all tasks with index numbers.
     */
    public String printList(List<Task> list) {
        int taskCount = list.size();

        String listContent = "";
        for (int i = 0; i < taskCount; i++) {
            Task task = list.get(i);
            listContent += "\n    " + (i + 1) + ". " + task;
        }

        if (listContent.isEmpty()) {
            return "  ( ㄏ-᷅_-᷄)ㄏ You've nothing to do...";
        }

        String header = "  Here are the tasks in your list: ";
        return header + listContent + "    ノ( ゜-゜ノ)";
    }

    /**
     * Prints a formatted string listing tasks at a specified date.
     *
     * @param list List of tasks with the same dates.
     * @param isToday True if the specified date is today, affecting header wording.
     * @return Formatted string showing tasks at the specified date.
     */
    public String printListAtDate(List<Task> list, boolean isToday) {
        int taskCount = list.size();

        String listContent = "";
        for (int i = 0; i < taskCount; i++) {
            Task task = list.get(i);
            listContent += "\n    " + (i + 1) + ". " + task;
        }

        String header;
        if (isToday) {
            header = "  Your tasks for today: ";
        } else {
            header = "  Your tasks for that date:";
        }

        return header + listContent + "    (・ω・)ノ";
    }

    /**
     * Prints a response after clearing user task list.
     *
     * @return Formatted string stating task list has been cleared.
     */
    public String clear() {
        return """
                   (＾∀＾)ゞ I've cleared your tasks! Go play~
                """;
    }

    /**
     * Returns a greeting string with the bot's name.
     *
     * @param name Name of the bot.
     * @return Formatted greeting string.
     */
    public String greet(String name) {
        return "  Hello♪ I'm " + name + "!"
                        + "  What can I do for you?\n"
                        + " (^з^)-☆";
    }

    /**
     * Returns a farewell string when the user exits.
     *
     * @return Formatted farewell string.
     */
    public String goodBye() {
        return """
                    \\(^O^) Baiii~ Hope to see you again soon!
                    """;
    }

    /**
     * Prints a list of all possible commands.
     */
    public String commandList() {
        return """
                  Here are the commands you can use:
                    (input date in dd/MM/yyyy)
                    bye/goodbye
                    deadline
                    delete
                    event
                    find
                    hi
                    list
                    mark
                    today
                    todo
                   unmark         _(•̀ω•́ 」∠)_
                """;
    }
}
