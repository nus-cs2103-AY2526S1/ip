package sanbot.ui;

import java.util.ArrayList;

import sanbot.task.Task;
import sanbot.tasklist.TaskList;

/**
 * Utility class for handling all user interface output messages.
 */
public class Ui {

    /**
     * @return the welcome greeting message.
     */
    public static String greeting() {
        String greeting = "Hello! I'm Sanbot\nStart your list today, keep your worries at bay!\n";
        return greeting;
    }

    /**
     * @param task the task that was marked as done.
     * @return confirmation message for marking task as done.
     */
    public static String markDone(Task task) {
        return "Nice! I have marked this task as done:\n" + task;
    }

    /**
     * @return error message for invalid mark done command format.
     */
    public static String markDoneIndexError() {
        return "Oops! It seems like you are trying to mark your task done\n" +
                "but you missed out the task number\nor maybe forgot to add a space before the number?";
    }

    /**
     * @return error message for invalid mark done task index.
     */
    public static String markDoneInvalidIndex() {
        return "Oops! It seems like you are trying to mark your task done\n" +
                "but you have entered an invalid task index";
    }

    /**
     * @param task the task that was marked as not done.
     * @return confirmation message for marking task as not done.
     */
    public static String markNotDone(Task task) {
        return "OK, I've marked this task as not done yet:\n" + task;
    }

    /**
     * @return error message for invalid mark not done command format.
     */
    public static String markNotDoneIndexError() {
        return "Oops! It seems like you are trying to mark your task as not done\n"
                + "but you missed out the task number\nor maybe forgot to add a space before the number?";
    }

    /**
     * @return error message for invalid mark not done task index.
     */
    public static String markNotDoneInvalidIndex() {
        return "Oops! It seems like you are trying to mark your task as not done\n" +
                "but you have entered an invalid task index";
    }

    /**
     * @param removed the task that was deleted.
     * @param count the number of remaining tasks in the list.
     * @return confirmation message for deleting a task.
     */
    public static String delete(Task removed, int count) {
        return "OK, I have removed this task from the list:\n" + removed +
                String.format("Now you have %d task(s) in the list%n", count);
    }

    /**
     * @return error message for invalid delete command format.
     */
    public static String deleteIndexError() {
        return "Oops! It seems like you are trying to delete a task\n"
                + "but you missed out the task number\nor maybe forgot to add a space before the number?";
    }

    /**
     * @return error message for invalid delete task index.
     */
    public static String deleteInvalidIndex() {
        return "Oops! It seems like you are trying to delete a task\n" +
                "but you have entered an invalid task index";
    }

    /**
     * @param task the todo task that was added.
     * @param count the total number of tasks in the list.
     * @return confirmation message for adding a todo task.
     */
    public static String todo(Task task, int count) {
        return "Got it. I have added this task:\n" + task + "\n" +
                String.format("Now you have %d task(s) in the list%n", count);
    }

    public static String todoError() {
        return "I see you are trying to add a todo task\n" +
                "input in the following format:\n" +
                "todo <task description>";
    }

    /**
     * @param task the deadline task that was added.
     * @param count the total number of tasks in the list.
     * @return confirmation message for adding a deadline task.
     */
    public static String deadline(Task task, int count) {
        return "Got it. I have added this task:\n" + task + "\n" +
                String.format("Now you have %d task(s) in the list%n", count);
    }

    /**
     * @return error message for invalid deadline command format.
     */
    public static String deadlineIndexError() {
        return "I see you are trying to add a task with a deadline.\n"
                + "it seems you did not use the '/by' flag properly\n"
                + "input in the following format:\n"
                + "deadline <task description> /by <dd-MM-yyyy HH:mm>";
    }

    /**
     * @return error message for improper attempt to add deadline task.
     */
    public static String deadlineError() {
        return "I see you are trying to add a task with a deadline.\n"
                + "input in the following format:\n"
                + "deadline <task description> /by <dd-MM-yyyy HH:mm>";
    }

    /**
     * @param task the event task that was added.
     * @param count the total number of tasks in the list.
     * @return confirmation message for adding an event task.
     */
    public static String event(Task task, int count) {
        return "Got it. I have added this task:\n" + task + "\n" +
                String.format("Now you have %d task(s) in the list%n", count);
    }

    /**
     * @return error message for invalid event command format.
     */
    public static String eventIndexError() {
        return "I see you are trying to add an event.\n"
                + "it seems you did not use the '/from' and '/to' flags properly\n"
                + "input in the following format:\n"
                + "event <task description> /from <dd-MM-yyyy HH:mm> /to <dd-MM-yyyy HH:mm>";
    }

    /**
     * @return error message for improper attempt to add event task.
     */
    public static String eventError() {
        return "I see you are trying to add an event.\n"
                + "input in the following format:\n"
                + "event <task description> /from <dd-MM-yyyy HH:mm> /to <dd-MM-yyyy HH:mm>";
    }

    /**
     * @param foundTasks tasks that match input keyword by user.
     * @return list of tasks found using input keyword.
     */
    public static String printFoundTasks(ArrayList<Task> foundTasks) {
        String foundTaskList = "Here are the matching tasks in your task list:\n";
        int index = 1;

        for (Task task : foundTasks) {
            foundTaskList += String.format("%d. %s\n", index, task);
            index++;
        }

        return foundTaskList;
    }

    /**
     * @return message in case no tasks was found using input keyword.
     */
    public static String noTasksFound() {
        return "Oops! No tasks were found using the keyword you input.";
    }

    /**
     * @return error message for missing input for find command.
     */
    public static String missingFindTaskInput() {
        return "Oops! It seems you were tyring to find tasks but did not enter a keyword.\n"
                + "Please enter the command in the format: \n"
                + "find <keyword>";
    }

    /**
     * @return message to indicate tasks have been saved and about to close chatbot window.
     */
    public static String exitApp() {

        return "Your tasks have been saved. See you soon!\n" +
                "Sanbot window closing in\n" +
                "4...3...2...1...";
    }
}