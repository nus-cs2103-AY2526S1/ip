package minhgpt.ui;

import java.util.ArrayList;

import minhgpt.task.Task;
import minhgpt.task.TaskList;

/**
 * Responsible for all UI printing.
 */
public class Ui {
    // NOTE: PUBLIC

    /**
     * Return initial greeting message when user enter the program.
     */
    public static String getWelcomeMessage() {
        return "Hmph! What do you want? I guess I'm MinhGPT. Whatever. \n"
                + "Just spit it out already; what can I do for you?";
    }

    /**
     * Return the error response when the task with that index does not exist for
     * mark + unmark + delete operation.
     */
    public String getIndexErrorResponse() {
        return "<( ⸝⸝•̀ - •́⸝⸝)> T-there's no task with that index, you baka! "
                + "Don't you know how to count?";
    }

    /**
     * Return the response when user add a task.
     *
     * @param task Task that was just added by user.
     */
    public String getAddResponse(Task task) {
        return String.format("F-fine! I added it. It's not like I wanted to or anything...\n%s", task);
    }

    /**
     * Return the response when user mark a task.
     *
     * @param task Task that was just marked by user.
     */
    public String getMarkResponse(Task task) {
        return String.format("I-It's not like I care, but... g-good job, I guess.\n%s", task);
    }

    /**
     * Return the response when user unmark a task.
     *
     * @param task Task that was just unmarked by user.
     */
    public String getUnmarkResponse(Task task) {
        return String.format("H-huh?! Why did you unmark it?! "
                + "Are you trying to make me do more work?! \n%s", task);
    }

    /**
     * Return the response when user delete a task.
     *
     * @param task Task that was just deleted by user.
     */
    public String getDeleteResponse(Task task) {
        return String.format("Good riddance. I-I mean, it's removed now. Hmph.\n%s", task);
    }

    /**
     * Return the response when user list all tasks.
     */
    public String getListResponse(TaskList taskList) {
        StringBuilder builder = new StringBuilder(String.format(
                "Don't get the wrong idea! I'm just listing them out because... "
                        + "because I have to, okay?! You have %d tasks in total.",
                taskList.size()));
        for (int i = 0; i < taskList.size(); i++) {
            builder.append(String.format("\n%d.%s", i + 1, taskList.get(i)));
        }
        return builder.toString();
    }

    /**
     * Return the response when user wanted to find tasks.
     *
     * @param tasks Tasks matching the query that user inputted.
     */
    public String getFindResponse(ArrayList<Task> tasks) {
        StringBuilder builder = new StringBuilder(
                String.format("Ugh. Here are the tasks you asked for. Don't go asking me again, got it?"));
        for (int i = 0; i < tasks.size(); i++) {
            builder.append(String.format("\n%d.%s", i + 1, tasks.get(i)));
        }
        return builder.toString();
    }

    /**
     * Return the message when user's input does not match any known commands.
     */
    public String getInvalidInputResponse() {
        return "( ˶°ㅁ°) H-hey! That's not a valid command! S-stupid...";
    }

    /**
     * Return the message when user enters the wrong date format.
     */
    public String getInvalidDateFormatResponse() {
        return "B-baka! Just input the date like this: yyyy-mm-dd. It's not that hard!";
    }

    /**
     * Return the message when user enters the wrong task format.
     */
    public String getInvalidTaskFormatResponse() {
        return "Ugh, what is wrong with you? That's not how you're supposed to input a task!";
    }

    /**
     * Return the message when user exit the program.
     */
    public String getExitMessage() {
        return "W-wait! You're leaving already? ...It's not like I'll miss you or anything! B-bye!";
    }
}
