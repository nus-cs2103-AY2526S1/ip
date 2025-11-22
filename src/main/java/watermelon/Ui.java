package watermelon;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import watermelon.task.Task;

/**
 * Handles all user interface interactions for the Watermelon chatbot application.
 *
 * <p> This class manages generating welcome/exit messages and chatbot responses to different commands. </p>
 */
public class Ui {
    /** Indentation string used for console output. */
    private static final String INDENT = " ".repeat(4);

    /**
     * Returns the welcome message from Watermelon chatbot.
     */
    public String showWelcomeMessage() {
        return "Hello! I'm Watermelon \uD83C\uDF49\n" + "What can I do for you?";
    }

    /** Returns the chatbot's response to a TodoCommand, DeadlineCommand or EventCommand. **/
    public String showTaskAddedMessage(Task task, int taskListSize) {
        return "Got it. I've added this task:\n"
                + INDENT + task + "\n"
                + String.format("Now you have %d tasks in the list.", taskListSize);
    }

    /** Returns the chatbot's response to a DeleteCommand. **/
    public String showTaskDeletedMessage(Task task, int taskListSize) {
        return "OK, I've removed this task:\n"
                + INDENT + task + "\n"
                + String.format("Now you have %d tasks in the list.", taskListSize);
    }

    /** Returns the chatbot's response to a FindCommand when there are no matching tasks found. **/
    public String showNoTasksFoundMessage() {
        return "Oops! I can't find any matching tasks.";
    }

    /** Returns the chatbot's response to a FindCommand when there are matching tasks found. **/
    public String showTasksFoundMessage(ArrayList<Task> matchingTasks) {
        StringBuilder sb = new StringBuilder("Here are the matching tasks I found:");

        for (int i = 0; i < matchingTasks.size(); i++) {
            int task_index = i + 1;
            sb.append("\n" + task_index + ". " + matchingTasks.get(i));
        }

        return sb.toString();
    }

    /** Returns the chatbot's response to a ListCommand. **/
    public String showTasksListedMessage(TaskList taskList) {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:");

        for (int i = 0; i < taskList.getSize(); i++) {
            int task_index = i + 1;
            sb.append("\n" + INDENT + task_index + ". " + taskList.getTask(i));
        }

        return sb.toString();
    }

    /** Returns the chatbot's response to a MarkCommand. **/
    public String showTaskMarkedMessage(Task task) {
        return "Nice! I've marked this task as done:\n" + INDENT + task;
    }

    /** Returns the chatbot's response to a UnmarkCommand. **/
    public String showTaskUnmarkedMessage(Task task) {
        return "OK, I've marked this task as not done yet:\n" + INDENT + task;
    }

    /** Returns the chatbot's response to a ScheduleCommand when there are no scheduled tasks for a specified date. **/
    public String showNoTasksScheduledMessage(LocalDate date) {
        String dateInString = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return String.format("No tasks scheduled for %s! :)", dateInString);
    }

    /** Returns the chatbot's response to a ScheduleCommand when there are no scheduled tasks for today. **/
    public String showNoTasksScheduledMessage() {
        return "No tasks scheduled for today! :)";
    }

    /** Returns the chatbot's response to a ScheduleCommand when there are scheduled tasks for a specified date. **/
    public String showTasksScheduledMessage(ArrayList<Task> scheduledTasks, LocalDate date) {
        String dateInString = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        StringBuilder sb = new StringBuilder(String.format("Here are your tasks scheduled for %s:", dateInString));

        for (int i = 0; i < scheduledTasks.size(); i++) {
            int task_index = i + 1;
            sb.append("\n" + task_index + ". " + scheduledTasks.get(i));
        }

        return sb.toString();
    }

    /** Returns the chatbot's response to a ScheduleCommand when there are scheduled tasks for today. **/
    public String showTasksScheduledMessage(ArrayList<Task> scheduledTasks) {
        StringBuilder sb = new StringBuilder("Here are your tasks scheduled for today:");

        for (int i = 0; i < scheduledTasks.size(); i++) {
            int task_index = i + 1;
            sb.append("\n" + task_index + ". " + scheduledTasks.get(i));
        }

        return sb.toString();
    }

    /** Returns a goodbye message. **/
    public String endChat() {
        return "Bye! Hope to see you again soon! :D";
    }
}
