package jinwoo.util;

import java.util.ArrayList;

import jinwoo.tasks.Task;
import jinwoo.tasks.TaskList;

/**
 * Ui class for handling user interactions
 */
public class Ui {

    private StringBuilder responseBuffer = new StringBuilder();

    /**
     * Append all tasks to buffer
     */
    public void bufferAppendList(TaskList tasks) {
        ArrayList<Task> taskList = tasks.getTasks();
        taskList.forEach(task ->
                responseBuffer.append(String.format("%d.%s"
                        + System.lineSeparator(), taskList.indexOf(task) + 1, task)));

    }

    /**
     * Undo message
     */
    public void printUndo(TaskList tasks) {
        responseBuffer.append("Undo successful" + System.lineSeparator());
        printList(tasks);
    }

    /**
     * Exit message
     *
     * @note doesn't actually print after terminating the program
     */
    public void printExit() {
        responseBuffer.append("Bye Hunter ???. Hope to see you again soon." + System.lineSeparator());
    }

    /**
     * List message
     */
    public void printList(TaskList tasks) {
        responseBuffer.append("Here are the tasks in your list:" + System.lineSeparator());
        bufferAppendList(tasks);
    }

    /**
     * Mark message
     */
    public void printMark(TaskList tasks, int taskNum) {
        responseBuffer.append("Nice! I've marked this task as done:" + System.lineSeparator());
        responseBuffer.append(tasks.getTasks(taskNum));
    }

    /**
     * Unmark message
     */
    public void printUnmark(TaskList tasks, int taskNum) {
        responseBuffer.append("OK, I've marked this task as not done yet:" + System.lineSeparator());
        responseBuffer.append(tasks.getTasks(taskNum));
    }

    /**
     * Delete message
     */
    public void printDelete(Task task, int size) {
        responseBuffer.append("Noted. I've removed this task:" + System.lineSeparator());
        responseBuffer.append(task).append(System.lineSeparator());
        responseBuffer.append(String.format("Now you have %d tasks in the list.", size));
    }

    /**
     * Add message
     */
    public void printAdd(Task task, int size) {
        responseBuffer.append("Got it. I've added this task:" + System.lineSeparator());
        responseBuffer.append(task).append(System.lineSeparator());
        responseBuffer.append(String.format("Now you have %d tasks in the list.", size));
    }

    /**
     * Return Gui message
     */
    public String getResponse() {
        String response = responseBuffer.toString();
        responseBuffer = new StringBuilder(); // Clear buffer after getting response
        return response;
    }


}
