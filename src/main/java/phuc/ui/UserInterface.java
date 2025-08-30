package phuc.ui;

import phuc.model.TaskList;
import phuc.storage.Storage;
import phuc.exception.PhucException;
import phuc.model.DeadlineTask;
import phuc.model.EventTask;
import phuc.model.ToDoTask;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * User interface class that handles all user interactions and display.
 * Provides methods for displaying messages, handling commands, and managing tasks.
 */
public class UserInterface {
    private static final String line = "____________________________________________________________";
    private final TaskList task;
    private Integer count = 0;
    private final Storage storage;

    /**
     * Constructs a new UserInterface instance.
     * Initializes the task list and storage components.
     */
    public UserInterface() {
        this.task = new TaskList();
        this.storage = new Storage();
    }

    /**
     * Loads tasks from storage into the task list.
     *
     * @throws PhucException if loading tasks fails
     */
    public void load() throws PhucException {
        try {
            task.clear();
            task.addAll(storage.load().getAllTasks());
            count = task.size();
        } catch (IOException e) {
            throw new PhucException("Failed to load tasks: " + e.getMessage());
        }
    }

    /**
     * Saves the current task list to storage.
     *
     * @throws PhucException if saving tasks fails
     */
    public void saveToStorage() throws PhucException {
        try {
            storage.save(task);
        } catch (IOException e) {
            throw new PhucException("Failed to save tasks: " + e.getMessage());
        }
    }

    /**
     * Gets the number of tasks in the list.
     *
     * @return the number of tasks plus one (for 1-based indexing)
     */
    public int numTasks() {
        return count+1;
    }

    /**
     * Prints a message with formatted borders.
     *
     * @param input the message to print
     */
    public void print(String input) {
        System.out.println(line);
        System.out.println(input);
        System.out.println(line);
    }

    /**
     * Displays the welcome message.
     */
    public void sayHello() {
        print(" Hello! I'm Phuc \uD83D\uDE03\uD83D\uDD90\uFE0F\n" +
                " What can I do for you?");
    }

    /**
     * Displays the goodbye message.
     */
    public void sayGoodbye() {
        print(" Bye. Hope to see you again soon! \uD83E\uDD27");
    }

    /**
     * Lists all tasks in the task list.
     */
    public void list() {
        String temp = "Here are the tasks in your list:\n";
        for(int i=0; i<count; i++) {
            temp += (i+1) + ". ";
            temp += task.get(i) + "\n";
        }

        System.out.println(line);
        System.out.print(temp);
        System.out.println(line);
    }

    /**
     * Marks a task in the given index as done.
     *
     * @param num the task number to mark (1-based index)
     */
    public void mark(String num) {
        int id = Integer.parseInt(num)-1;
        task.get(id).setDone();
        print("Nice! I've marked this task as done:\n" + task.get(id));
    }

    /**
     * Marks a task in the given index as not done.
     *
     * @param num the task number to unmark (1-based index)
     */
    public void unMark(String num) {
        int id = Integer.parseInt(num)-1;
        task.get(id).setNotDone();
        print("OK, I've marked this task as not done yet:\n" + task.get(id));
    }

    /**
     * Generates a notification message about the number of tasks.
     *
     * @return a string indicating the current task count
     */
    public String notiNumOfTasks() {
        return "Now you have " + (count+1) + " tasks in the list.";
    }

    /**
     * Generates a notification message for adding tasks.
     *
     * @return a string confirming task addition
     */
    public String notiAddTasks() {
        return "Got it. I've added this task:\n";
    }

    /**
     * Adds a new to-do task to the list.
     * Displays to the user that the task have been added.
     *
     * @param newtask the description of the to-do task
     */
    public void toDo(String newtask) {
        task.add(new ToDoTask(newtask));
        String temp = notiAddTasks() + task.get(count) + "\n" + this.notiNumOfTasks();
        print(temp);
        count++;
    }

    /**
     * Adds a new deadline task to the list.
     * Displays to the user that the task have been added.
     *
     * @param newtask the description of the deadline task
     * @param deadline the deadline date and time
     */
    public void deadline(String newtask, LocalDateTime deadline) {
        task.add(new DeadlineTask(newtask, deadline));
        String temp = notiAddTasks() + task.get(count) + "\n" + this.notiNumOfTasks();
        print(temp);
        count++;
    }

    /**
     * Adds a new event task to the list.
     * Displays to the user that the task have been added.
     *
     * @param newtask the description of the event task
     * @param startDate the event start date and time
     * @param endDate the event end date and time
     */
    public void event(String newtask, LocalDateTime startDate, LocalDateTime endDate) {
        task.add(new EventTask(newtask, startDate, endDate));
        String temp = notiAddTasks() + task.get(count) + "\n" + this.notiNumOfTasks();
        print(temp);
        count++;
    }

    /**
     * Deletes a task from the list.
     * Displays to the user that the task have been deleted.
     *
     * @param num the task number to delete (1-based index)
     */
    public void delete(String num) {
        count-=2;
        String temp = "Noted. I've removed this task:\n" +
                    task.get(Integer.parseInt(num)-1) + "\n" +
                    this.notiNumOfTasks();
        task.remove(Integer.parseInt(num)-1);
        print(temp);
        count++;
    }

    /**
     * Displays an error message with formatted borders.
     *
     * @param error the error message to display
     */
    public static void showError(String error) {
        System.out.println(line);
        System.out.println(error);
        System.out.println(line);
    }
}
