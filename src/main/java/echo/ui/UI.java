package echo.ui;

import java.util.Scanner;

import echo.task.Task;
import echo.tasklist.TaskList;


/**
 * Represents the UI that users will interact with. A <code>UI</code> object
 * stores a <code>Scanner</code> scanner.
 */
public class UI {
    private final Scanner scanner;

    public UI() {
        this.scanner = new Scanner(System.in);
    }

    public String showExit() {
        return "Echo Echo...going back into the Omnitrix! See you soon!\n";
    }

    /**
     * Returns the user task list as a text
     *
     * @param list task list
     * @return tasks in input task list as text
     */
    public String showList(TaskList list) {
        String msg = TaskList.printList(list);
        msg = "Mission briefing! Here are your aliens to capture Ben:\n" + msg + "\n";
        return msg;
    }

    /**
     * Returns tasks in user task list that contains a keyword as text
     *
     * @param list task list
     * @return tasks in input task list as text
     */
    public String showKeywordList(TaskList list) {
        String msg = TaskList.printList(list);
        msg = "Incoming! Matching aliens detected on your list:\n" + msg + "\n";
        return msg;
    }

    /**
     * Returns message to indicate that task has been marked
     *
     * @param task Task that was marked
     * @return message
     */
    public String showMarkedTask(Task task) {
        StringBuilder msg = new StringBuilder();
        msg.append("Boom! Mission Accomplished! Alien contained!:\n");
        msg.append("  ").append(task).append("\n");
        return msg.toString();
    }

    /**
     * Returns message to indicate that task has been unmarked
     *
     * @param task Task that was unmarked
     * @return message to be shown to user
     */
    public String showUnmarkedTask(Task task) {
        StringBuilder msg = new StringBuilder();
        msg.append("Uh-oh! Uh-oh! Mission reset! Alien has escaped!:\n");
        msg.append("  ").append(task).append("\n");
        return msg.toString();
    }

    /**
     * Returns message to indicate input task has been deleted
     *
     * @param task Task that was deleted
     * @return message to be shown to user
     */
    public String showDeletedTask(Task task) {
        StringBuilder msg = new StringBuilder();
        msg.append("Destroying Alien task....AHHHHHH!\n");
        msg.append("  ").append(task).append("\n");
        return msg.toString();
    }

    /**
     * Returns message indicating that input task was added
     *
     * @param task Task that was added
     * @return message to be shown to user
     */
    public String showAddTask(Task task) {
        return "New alien added! New alien added!\n" + "  " + task + "\n";
    }

    /**
     * Returns the size of the input task list as text
     *
     * @param taskList Task list
     * @return size of Task list as text to be shown to user
     */
    public String showListSize(TaskList taskList) {
        if (taskList.getSize() == 1) {
            return "Now you have " + taskList.getSize() + " alien to contain.\n";
        }
        return "Now you have " + taskList.getSize() + " aliens to contain.\n";
    }

}
