package jimbot.ui;

import java.util.List;
import java.util.Scanner;

import jimbot.tasktypes.Task;
import jimbot.tasktypes.TaskList;

/**
  * Class that handles the UI of programs' responses to user.
  *
  * @author limjimin-nus
  * */
public class UI {
    private final Scanner scanner;

    public UI() {
        scanner = new Scanner(System.in);
    }

    public String markRes(TaskList userList, int index) {
        String header = "  Nice♪ I've marked this task as done: \n    ";
        return header + userList.getTask(index) + "\n  ♪ ｖ(＾＿＾ｖ)♪ ~~ ♪(ｖ＾＿＾)ｖ";
    }

    public String unmarkRes(TaskList userList, int index) {
        String header = "  OK, I've marked this task as not done yet: \n    ";
        return header + userList.getTask(index) + "\n  (｀_´)ゞ";
    }

    public String addTask(Task task, int taskCount) {
        String header = "  Got it. I've added this task: \n    ";
        String closer = "\n  Now you have " + taskCount + " tasks in the list!\n  (￣^￣)ゞ";

        return header + task + closer;
    }

    public String deleteTask(Task task, int taskCount) {
        String header = "  Noted. I've removed this task:\n    ";
        String closer = "\n  Now you have " + taskCount + " tasks in the list! \n  (─.─)ゞ";

        return header + task + closer;
    }

    public String printList(List<Task> list) {
        int taskCount = list.size();

        String listContent = "\n                ノ( ゜-゜ノ)";
        for (int i = 0; i < taskCount; i++) {
            Task task = list.get(i);
            listContent += "\n    " + (i + 1) + ". " + task;
        }
        String header = "  Here are the tasks in your list: ";
        return header + listContent;
    }

    /**
     * Prints a the lsit of tasks at a specific date.
     *
     * @param list List of tasks with the same dates.
     * @param isToday If specific date matches LocalDate.now(), string response changes to mention that.
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
            header = "  Here are your tasks for today: \n";
        } else {
            header = "  Here are your tasks for that date:";
        }

        return header + listContent + "    (・ω・)ノ";
    }

    public static String hello(String name) {
        return "  Hello! I'm " + name + "!"
                        + "  What can I do for you?\n"
                        + " (^з^)-☆";
    }

    public String goodBye() {
        return """
                    \\(^O^) Bye! Hope to see you again soon!
                    """;
    }

    /**
     * Response for unrecognized commands.
     */
    public String respond(String userInput) {
        return "Sorry I don't recognize this command:\n"
                + "\"" + userInput + "\"\n"
                + "Type \"help\" for the list possible commands.";
    }

    /**
     * Prints a list of all possible commands.
     */
    public String commandList() {
        return """
                            _(•̀ω•́ 」∠)_
                  Here are the commands you can use:
                    (input date in dd/mm/yyyy)
                    bye/goodbye
                    deadline
                    delete
                    event
                    find
                    list
                    mark
                    today
                    todo
                    unmark
                """;
    }
}
