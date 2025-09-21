package pengu;

import pengu.task.Task;
import pengu.task.TaskList;

/**
 * Class to handle crafting responses to user input.
 */
public class Ui {
    private static final String NAME = "Pengu";

    /**
     * Returns a greeting message.
     */
    public String getGreetingMessage() {
        String greetMessage = String.format("Hello! I'm %s\n" + "What can I do for you?", NAME);
        return greetMessage;
    }

    /**
     * Returns an exit message.
     */
    public String getExitMessage() {
        String exitMessage = "Bye. Hope to see you again soon!";
        return exitMessage;
    }

    /**
     * Returns a message showing the task list;
     */
    public String getTaskListMessage(TaskList taskList) {
        return taskList.toString();
    }

    /**
     * Returns a message that says a task has been added, and how many tasks are in the list.
     */
    public String getAddTaskMessage(Task task, TaskList taskList) {
        String message = "Got it, I've added this task:\n  " + task + "\n"
                + "Now you have " + taskList.getSize() + " tasks in the list.";
        return message;
    }

    /**
     * Returns a message that says a task has been deleted, and how many tasks are left in the list.
     */
    public String getDeleteTaskMessage(Task task, TaskList taskList) {
        String message = "Noted. I've removed this task:\n  " + task + "\n"
                + "Now you have " + (taskList.getSize() - 1) + " tasks in the list.";
        return message;
    }

    /**
     * Returns the list of tasks whose description has a string matching the user provided string to find.
     */
    public String getFoundTasksMessage(TaskList taskList) {
        String message = "Here are the matching tasks in your list:\n" + taskList.toString();
        return message;
    }

    /**
     * Returns a message that says a task has been marked as done.
     */
    public String getMarkTaskMessage(Task task) {
        String message = "Nice! I've marked this task as done:\n  " + task;
        return message;
    }

    /**
     * Returns a message that says a task has been marked as undone.
     */
    public String getUnmarkTaskMessage(Task task) {
        String message = "OK, I've marked this task as not done yet:\n  " + task;
        return message;
    }

    /**
     * Returns a message that says a task has been updated.
     */
    public String getUpdateTaskMessage(Task task) {
        String message = "Great! I've updated the details of this task:\n  " + task;
        return message;
    }

    /**
     * Returns an error message, emphasising with "ERROR!!".
     */
    public String getErrorMessage(String message) {
        return "ERROR!!\n" + message;
    }
}
