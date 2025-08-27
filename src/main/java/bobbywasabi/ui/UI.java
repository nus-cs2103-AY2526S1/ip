package bobbywasabi.ui;

import java.util.Scanner;

import bobbywasabi.tasks.Task;
import bobbywasabi.tasks.TaskList;

/**
 * Handles user interaction through the console.
 * Provides methods to read user input and display messages.
 */
public class UI {

    /**
     * Constructs a new UI instance and initializes the input scanner.
     */
    public UI() {
    }

    /**
     * Prints a greeting message to the user when the bot starts.
     */
    public String greetUser() {
        String botGreet = """
                 Hello! I'm Bobby Wasabi
                 What can I do for you?
                """;
        return botGreet;
    }

    /**
     * Displays the current list of tasks to the user.
     *
     * @param tasks The BobbyWasabi.BobbyWasabi.TaskList containing all current tasks.
     */
    public String listMessage(TaskList tasks) {
        String listOutput = "Here are the tasks in your list:\n" + tasks;
        return listOutput;
    }

    /**
     * Displays the search results for tasks matching a query.
     *
     * @param tasks A formatted string representation of matching tasks.
     */
    public String findMessage(String tasks) {
        String output = tasks.isEmpty()
                ? "Sorry! We could not find any matching tasks :/\n"
                : "Here are the matching tasks in your list:\n";
        String botResponse = output + tasks;
        return botResponse;
    }

    /**
     * Displays a confirmation message after marking a task as completed.
     *
     * @param indx       The task number shown to the user (1-based index).
     * @param targetTask The task that has been marked as done.
     */
    public String markTaskMessage(int indx, Task targetTask) {
        String curTask = String.format(
                "%d. %s\n",
                indx,
                targetTask);

        String output = String.format("""
                                Nice! I've marked this task as done:
                                %s
                                """,
                curTask);

        return output;
    }

    /**
     * Displays a confirmation message after marking a task as not done.
     *
     * @param indx       The task number shown to the user (1-based index).
     * @param targetTask The task that has been unmarked.
     */
    public String unmarkTaskMessage(int indx, Task targetTask) {
        String curTask = String.format(
                "%d. %s\n",
                indx,
                targetTask);

        String output = String.format("""
                                Nice! I've marked this task as not done yet:
                                %s
                                """,
                curTask);

        return output;
    }


    /**
     * Displays a confirmation message after a task is added to the task list.
     *
     * @param task The task that was added.
     * @param num  The new total number of tasks in the list.
     */
    public String addTaskMessage(Task task, int num) {

        String s = String.format("""
                Got it. I've added this task:
                %s
                Now you have %d tasks in the list.
                """,
                task, num);

        return s;
    }

    /**
     * Displays a confirmation message after a task is removed from the task list.
     *
     * @param targetTask The task that was deleted.
     * @param taskSize   The new total number of tasks in the list.
     */
    public String deleteMessage(Task targetTask, int taskSize) {
        String output = String.format("""
                        Noted. I've removed this task:
                        %s
                        Now you have %d tasks in the list
                        """,
                targetTask, taskSize);

       return output;
    }

    /**
     * Displays a generic message prompting the user to enter a valid command.
     */
    public String invalidMessage() {

        return this.generateErrorMsg("Please provide a valid command!");
    }

    /**
     * Prints a farewell message and exits the program by closing the input scanner.
     */
    public String farewellUser() {
        return """
               Bye. Hope to see you again soon!
               """;
    }


    /**
     * Displays an error message wrapped in a decorative format.
     *
     * @param e The error message to display.
     */
    public String generateErrorMsg(String e) {

        String s = String.format("""
                OOPS!!! %s
                """,
                e);

        return s;
    }

}
