package BobbyWasabi.UI;

import BobbyWasabi.Tasks.Task;
import BobbyWasabi.Tasks.TaskList;

import java.util.Scanner;

public class UI {
    private Scanner scanner;
    private final static String decoLine = "____________________________________________________________";

    public UI () {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads and returns the next line of user input from the console.
     *
     * @return The user's input as a string.
     */
    public String getNextInput() {
        return this.scanner.nextLine();
    }

    /**
     * Prints a greeting message to the user when the bot starts.
     */
    public void greetUser() {
        String botGreet = """
                ____________________________________________________________
                 Hello! I'm Bobby Wasabi
                 What can I do for you?
                ____________________________________________________________
                
                """;
        System.out.println(botGreet);
    }


    /**
     * Displays the current list of tasks to the user.
     *
     * @param tasks The BobbyWasabi.BobbyWasabi.TaskList containing all current tasks.
     */
    public void listMessage(TaskList tasks) {
        String listOutput = UI.decoLine + "\n" + "Here are the tasks in your list:\n" + tasks + UI.decoLine;
        System.out.println(listOutput);
    }

    /**
     * Displays a confirmation message after marking a task as completed.
     *
     * @param indx       The task number shown to the user (1-based index).
     * @param targetTask The task that has been marked as done.
     */
    public void markTaskMessage(int indx, Task targetTask) {
        String curTask = String.format(
                "%d. %s\n",
                indx,
                targetTask);

        String output = String.format("""
                                Nice! I've marked this task as done:
                                   %s""",
                curTask);

        System.out.println(decoLine + "\n" + output + decoLine);
    }

    /**
     * Displays a confirmation message after marking a task as not done.
     *
     * @param indx       The task number shown to the user (1-based index).
     * @param targetTask The task that has been unmarked.
     */
    public void unmarkTaskMessage(int indx, Task targetTask) {
        String curTask = String.format(
                "%d. %s\n",
                indx,
                targetTask);

        String output = String.format("""
                                Nice! I've marked this task as not done yet:
                                   %s""",
                curTask);

        System.out.println(decoLine + "\n" + output + decoLine);
    }


    /**
     * Displays a confirmation message after a task is added to the task list.
     *
     * @param task The task that was added.
     * @param num  The new total number of tasks in the list.
     */
    public void addTaskMessage(Task task, int num) {

        String s = String.format("""
                ____________________________________________________________
                Got it. I've added this task:
                    %s
                Now you have %d tasks in the list.
                ____________________________________________________________
                """,
                task, num);

        System.out.println(s);
    }

    /**
     * Displays a confirmation message after a task is removed from the task list.
     *
     * @param targetTask The task that was deleted.
     * @param taskSize   The new total number of tasks in the list.
     */
    public void deleteMessage(Task targetTask, int taskSize) {
        String output = String.format("""
                        ____________________________________________________________
                        Noted. I've removed this task:
                            %s
                        Now you have %d tasks in the list
                        ____________________________________________________________
                        """,
                targetTask, taskSize);

        System.out.println(output);
    }

    /**
     * Displays a generic message prompting the user to enter a valid command.
     */
    public void invalidMessage() {
        this.generateErrorMsg("Please provide a valid command!");
    }

    /**
     * Prints a farewell message and exits the program by closing the input scanner.
     */
    public void farewellUser() {
        System.out.println("""
                    ____________________________________________________________
                    Bye. Hope to see you again soon!
                    ____________________________________________________________
               
                    """);
        this.exit();
    }

    /**
     * Closes the input scanner and performs cleanup before exit.
     */
    public void exit() {
        this.scanner.close();
    }

    /**
     * Displays an error message wrapped in a decorative format.
     *
     * @param e The error message to display.
     */
    public void generateErrorMsg(String e) {

        String s = String.format("""
                ____________________________________________________________
                OOPS!!! %s
                ____________________________________________________________
                """,
                e);

        System.out.println(s);
    }

}
