package fatty;

import fatty.task.Task;

/**
 * Ui deals user interactions. Takes in user input and displays messages.
 */
public class Ui {

    /**
     * Startup greeting
     * @return string message of greeting
     */
    public String showWelcome() {
        return "Hello skinny legend! I'm fatty.\nWhat can I eat.. I mean do for you?";

    }

    public String showExit() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Show tasks in tasklist
     * @param taskList Tasklist loaded from local
     * @return String message of tasklist.
     */
    public String showTaskList(TaskList taskList) {
        return "Here are the tasks in your list:\n"
                + taskList;
    }

    public String showTaskAdded(Task task, TaskList taskList) {
        return "Got it. I've added this task:\n" + task + "\nNow you have " + taskList.size() + " tasks in the list.";
    }

    public String showMark(Task task) {
        return "Nice! I've marked this task as done:\n" + task;
    }

    public String showUnmark(Task task) {
        return "OK! I've marked this task as not done yet:\n" + task;
    }

    public String showDelete(Task task, TaskList taskList) {
        return "Noted. I've removed this task:\n" + task + "\nNow you have " + taskList.size() + " tasks in the list.";
    }
    public String showError(String message) {
        return "☹ OOPS! Error: " + message;
    }

    /**
     * Show tasks that match keyword given.
     * @param tasks Tasklist that contains tasks that match keyword.
     * @return Message String
     */
    public String showFind(TaskList tasks) {
        if (tasks.size() == 0) {
            return "There are no tasks that match the keyword!";
        } else {
            return "Here are the matching tasks in your list:" + "\n" + tasks;
        }

    }
}
