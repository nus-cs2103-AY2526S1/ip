package whchenyicn.ui;

import whchenyicn.task.Task;
import whchenyicn.task.TaskList;

import java.util.Scanner;

/**
 * Ui class to print messsages for usre interaction.
 */
public class Ui {
    private static final String hline = "__________________________________________________";
    private Scanner sc;

    /**
     * Creates UI with a scanner for user input.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Reads next command by user.
     *
     * @return User input as string.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints welcome message.
     */
    public String printWelcome() {
        System.out.println(hline);
        System.out.println("Hello! I'm whchenyicn");
        System.out.println("What can I do for you?");
        System.out.println(hline);
        return(hline + "\nHello! I'm whchenyicn\n" + "What can I do for you?\n" + hline);
    }

    /**
     * Prints loaded tasks and message.
     *
     * @param tlist List of tasks loaded and to be printed.
     */
    public String printLoad(TaskList tlist) {
        System.out.println(hline);
        System.out.println("Loaded " + tlist.size() + " tasks from list");
        System.out.println(hline);
        return(hline + "\nLoaded " + tlist.size() + " tasks from list\n" + hline);
    }

    /**
     * Prints error msg.
     *
     * @param msg Error msg to be printed.
     */
    public String printError(String msg) {
        System.out.println(hline);
        System.out.println("[ERROR] " + msg);
        System.out.println(hline);
        return (hline + "\n[ERROR] " + msg + "\n" + hline);
    }

    /**
     * Prints marked task.
     *
     * @param tlist List of tasks.
     * @param i Index of task to be marked.
     */
    public String printMarked(TaskList tlist, int i) {
        assert i >= 1 && i <= tlist.size() : "i must be within 1..size";

        System.out.println(hline);
        System.out.println("Nice! I've marked this whchenyicn.task as done:");
        System.out.println(tlist.get(i - 1).toString());
        System.out.println(hline);
        return(hline + "\nNice! I've marked this whchenyicn.task as done:\n" +
                tlist.get(i - 1).toString() + "\n" + hline);
    }

    /**
     * Prints unmarked task.
     *
     * @param tlist List of tasks.
     * @param i Index of task to be unmarked.
     */
    public String printUnmarked(TaskList tlist, int i) {
        assert i >= 1 && i <= tlist.size() : "i must be within 1..size";

        System.out.println(hline);
        System.out.println("OK, I've marked this whchenyicn.task as not done yet:");
        System.out.println(tlist.get(i - 1).toString());
        System.out.println(hline);
        return(hline + "\nOK, I've marked this whchenyicn.task as not done yet: \n" +
                tlist.get(i - 1).toString() + "\n" + hline);
    }

    /**
     * Prints the list of tasks.
     *
     * @param tlist List of tasks.
     */
    public String printList(TaskList tlist) {
        String str = hline + "\n";
        System.out.println(hline);
        if (tlist.size() <=0 ) {
            System.out.println("List is empty");
            str += "List is empty\n";
        }

        String tasks = tlist.asList().stream()
                .map(task -> task.toString())
                .reduce("", (a, b) -> a + "\n" + b);

        str += tasks + "\n";
        str += hline;

        System.out.println(tasks);
        System.out.println(hline);
        return str;
    }

    /**
     * Prints the todo task added to the list.
     *
     * @param tlist List of tasks.
     */
    public String printTodoTask(TaskList tlist) {
        String str = hline + "\nGot it. I've added this task:\n";
        System.out.println(hline);
        System.out.println("Got it. I've added this task:");
        str += "  " + tlist.get(tlist.size() - 1).toString() + "\n";
        System.out.println("  " + tlist.get(tlist.size() - 1).toString());
        str += "Now you have " + tlist.size() + " tasks in the list.\n";
        System.out.println("Now you have " + tlist.size() + " tasks in the list.");
        str += hline;
        System.out.println(hline);

        return str;
    }

    /**
     * Prints the deadline task added to the list.
     *
     * @param tlist List of Tasks.
     */
    public String printDeadlineTask(TaskList tlist) {
        String str = hline + "\nGot it. I've added this task:\n";
        System.out.println(hline);
        System.out.println("Got it. I've added this task:");
        str += "  " + tlist.get(tlist.size() - 1).toString() + "\n";
        System.out.println("  " + tlist.get(tlist.size() - 1).toString());
        str += "Now you have " + tlist.size() + " tasks in the list.\n";
        System.out.println("Now you have " + tlist.size() + " tasks in the list.");
        str += hline;
        System.out.println(hline);

        return str;
    }

    /**
     * Prints the Event task added to the list.
     *
     * @param tlist List of Tasks.
     */
    public String printEventTask(TaskList tlist) {
        String str = hline + "\nGot it. I've added this task:\n";
        System.out.println(hline);
        System.out.println("Got it. I've added this task:");
        str += "  " + tlist.get(tlist.size() - 1).toString() + "\n";
        System.out.println("  " + tlist.get(tlist.size() - 1).toString());
        str += "Now you have " + tlist.size() + " tasks in the list.\n";
        System.out.println("Now you have " + tlist.size() + " tasks in the list.");
        str += hline;
        System.out.println(hline);

        return str;
    }

    /**
     * Prints the task removed from the list.
     *
     * @param tlist List of Tasks.
     */
    public String printDelete(TaskList tlist, Task remove) {
        String str = hline + "\n" + "This task has been removed \n";
        System.out.println(hline);
        System.out.println("This task has been removed");
        str += "  " + remove.toString() + "\n";
        System.out.println("  " + remove.toString());
        str += tlist.size() + " tasks left in the list.\n" + hline;
        System.out.println(tlist.size() + " tasks left in the list.");
        System.out.println(hline);

        return str;
    }

    /**
     * Prints list of tasks that matches find keyword.
     *
     * @param matchList List of tasks containing keyword.
     */
    public String printFind(TaskList matchList) {
        String str = hline + "\n";
        System.out.println(hline);
        if (matchList.isEmpty()) {
            System.out.println("No matching tasks found.");
            str += "No matching tasks found.\n";
        }
        else {
            str += "Here are the matching tasks in your list:\n";
            System.out.println("Here are the matching tasks in your list:");
            String tasks = matchList.asList().stream()
                    .map(Task::toString)
                    .reduce((a, b) -> a + "\n" + b)
                    .orElse("");

            str += tasks + "\n";
        }
        System.out.println(hline);
        str += hline;
        return str;
    }

    /**
     * Prints exit message.
     */
    public String printBye() {
        System.out.println("Bye! Hope to see you again soon!");
        System.out.println(hline);
        return hline + "\nBye! Hope to see you again soon!\n" + hline;
    }

    public String printReminders(TaskList reminders, int days) {

        if (reminders.isEmpty()) {
            return hline + "\nNo upcoming tasks within " + days + " days.\n" + hline;
        }

        String tasks = reminders.asList().stream()
                .map(Task::toString)
                .reduce((a, b) -> a + "\n" + b)
                .orElse("");
        return hline + "\nHere are tasks due within " + days + " days:\n"
                + tasks + "\n" + hline;
    }
}
