package pepe.ui;

import java.util.Scanner;

import pepe.task.Task;
import pepe.task.tasklist.TaskList;


/**
 * Handles all user interactions for the Pepe application.
 * <p>
 * This class provides methods to read user input, display messages,
 * and print the state of tasks and task lists in a user-friendly format.
 */
public class Ui {
    private final Scanner scanner = new Scanner(System.in);



    /**
     * Reads the next line of user input from the console.
     *
     * @return the user input as a String
     */
    public String readCommand() {
        return scanner.nextLine();
    }


    /**
     * Displays the greeting message when the application starts.
     * @return the greeting message
     */
    public String showUiGreetUser() {
        String message = "I am Pepe!\n What do you want?";
        return message;
    }

    /**
     * Displays the farewell message when exiting the application.
     * @return the goodbye message
     */
    public String showUiSayBye() {
        String message = "Fine then! Leave! I don't care...\n";
        return message;
    }

    /**
     * Displays all tasks in the provided TaskList.
     *
     * @param taskList the TaskList containing tasks to display
     * @return the lists of task numbered
     */
    public String showUiListTask(TaskList taskList) {
        assert taskList != null : "TaskList should not be null when listing tasks";
        String message = "";
        if (taskList.isEmpty()) {
            message = "WOW! Either you're really on task...or...\n";
        } else {
            message = "Hey lazy bum! Here are the task(s) you still need to do:\n";
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                assert task != null : "Task at index " + i + " should not be null";
                String taskMessage = (i + 1) + ". " + task + "\n";
                message = message + taskMessage;
            }
        }
        return message;
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param tasks an array of tasks to be marked
     * @return the task to string method with the mark changes
     */
    public String showUiMark(Task ... tasks) {
        assert tasks != null : "Tasks to mark should not be null";
        String message = "Bro finally accomplished something!\n";
        for (Task task : tasks) {
            message += task.toString() + "\n";
        }
        return message;
    }
    /**
     * Displays a message indicating that a task has been unmarked (not done yet).
     *
     * @param tasks the Task that was unmarked
     * @return the task to string method with the mark changes
     */
    public String showUiUnmark(Task ... tasks) {
        assert tasks != null : "Task to unmark should not be null";
        String message = "I knew it! You couldn't have finished it that quickly...\n";
        for (Task task : tasks) {
            message += task.toString() + "\n";
        }
        return message;
    }

    /**
     * Displays a message after adding a ToDo task to the list.
     *
     * @param list the TaskList containing the added task
     * @param task the Task that was added
     * @return the string representation of the task and the updated task size
     */
    public String showUiAddToDo(TaskList list, Task task) {
        assert list != null : "TaskList should not be null when adding ToDo";
        assert task != null : "Task to add should not be null";
        String message = "Sure let's add this task that you'll definitely do:\n";
        message = message + (task.toString() + "\n") + "Now you have " + list.size() + " tasks in the list\n";
        return message;
    }

    /**
     * Displays a message after adding a Deadline task to the list.
     *
     * @param list the TaskList containing the added task
     * @param task the Task that was added
     * @return the string representation of the task and the updated task size
     */
    public String showUiAddDeadline(TaskList list, Task task) {
        assert list != null : "TaskList should not be null when adding Deadline";
        assert task != null : "Task to add should not be null";
        String message = "Sure let's add this task that you'll definitely do:\n";
        message = message + (task.toString() + "\n") + "Now you have " + list.size() + " tasks in the list\n";
        return message;
    }

    /**
     * Displays a message after adding an Event task to the list.
     *
     * @param list the TaskList containing the added task
     * @param task the Task that was added
     * @return the string representation of the task and the updated task size
     */
    public String showUiAddEvent(TaskList list, Task task) {
        assert list != null : "TaskList should not be null when adding Event";
        assert task != null : "Task to add should not be null";
        String message = "Sure let's add this task that you'll definitely do:\n";
        message = message + (task.toString() + "\n") + "Now you have " + list.size() + " tasks in the list\n";
        return message;
    }

    /**
     * Displays a message after deleting a task from the list.
     *
     * @param list the TaskList after deletion
     * @param tasks the Tasks that were removed
     * @return the string representation of the task and the updated task size
     */
    public String showUiDelete(TaskList list, Task ... tasks) {
        assert list != null : "TaskList should not be null when deleting";
        String message = "Amazing! Let's just pretend this task didn't exist!\n";
        for (Task task : tasks) {
            message = message + (task.toString() + "\n");
        }
        message += "Now you have " + list.size() + " tasks in the list\n";
        return message;
    }

    /**
     * Displays an ArrayList of type Task from the list.
     *
     * @param tasks the TaskList showing matches
     * @return the string representation of the task found (if any)
     */
    public String showUiFind(TaskList tasks) {
        assert tasks != null : "TaskList should not be null when finding tasks";
        String message = "I tried my best finding these...:\n";
        if (tasks.isEmpty()) {
            message = message + "There are NO tasks that match your search! Maybe try adding them!\n";
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                String taskMessage = (i + 1) + ". " + tasks.get(i) + "\n";
                message = message + taskMessage;
            }
        }
        return message;
    }

    /**
     * Displays an error message to the user.
     *
     * @param message the error message to display
     * @return the error message as a string
     */
    public String showUiError(String message) {
        return message;
    }


}
