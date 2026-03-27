package phuc.ui;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import phuc.exception.PhucException;
import phuc.model.DeadlineTask;
import phuc.model.EventTask;
import phuc.model.Task;
import phuc.model.TaskList;
import phuc.model.ToDoTask;
import phuc.storage.Storage;

/**
 * User interface class that handles all user interactions and display.
 * Provides methods for displaying messages, handling commands, and managing tasks.
 */
public class UserInterface {
    public static final String LINE = "________________________________________";
    private final TaskList taskList;
    private Integer count = 0;
    private final Storage storage;

    /**
     * Constructs a new UserInterface instance.
     * Initializes the task list and storage components.
     */
    public UserInterface() {
        this.taskList = new TaskList();
        this.storage = new Storage();
    }

    /**
     * Displays help information with all available commands and their usage.
     */
    public void help() {
        String helpMessage =
                "PHUC COMMAND HELP\n"
                        + "=================\n\n"
                        + "LIST MANAGEMENT:\n"
                        + "• list - Show all tasks\n"
                        + "• sort ascending - Sort tasks by date (oldest first)\n"
                        + "• sort descending - Sort tasks by date (newest first)\n"
                        + "• find <keyword> - Search for tasks\n\n"
                        + "TASK OPERATIONS:\n"
                        + "• mark <number> - Mark task as done\n"
                        + "• unmark <number> - Mark task as not done\n"
                        + "• delete <number> - Remove a task\n\n"
                        + "ADD TASKS:\n"
                        + "• todo <description>\n"
                        + "      - Add a simple todo task\n"
                        + "      Example: todo Buy milk\n\n"
                        + "• deadline <description> /by <date>\n"
                        + "      - Add task with deadline\n"
                        + "      Example: deadline Submit report /by 25/12/2024 2359\n\n"
                        + "• event <description> /from <date> /to <date>\n"
                        + "      - Add event with time range\n"
                        + "      Example: event Meeting /from 25/12/2024 1000 /to 25/12/2024 1200\n\n"
                        + "DATE FORMATS:\n"
                        + "25/12/2024 2359, 25-12-2024 2359, 2024-12-25 2359, 25/12/2024\n\n"
                        + "APPLICATION:\n"
                        + "• help - Show this help message\n"
                        + "• bye - Exit the application";

        print(helpMessage);
    }

    /**
     * Prints out the task list sorted by the deadline order
     *
     * @param ascending is a boolean parameter, true if need a ascending task, false other wise
     */
    public void printSortedTasks(boolean ascending) {
        String sortedTaskList =
                "Here are the sorted tasks in your list:\n"
                        + IntStream.range(0, count)
                        .mapToObj(i -> (i + 1) + ". " + taskList.getSortedTasks(ascending).get(i))
                        .collect(Collectors.joining("\n", "", "\n"));

        System.out.println(LINE);
        System.out.print(sortedTaskList);
        System.out.println(LINE);
    }

    /**
     * Finds all the tasks contain keyword then print out to the user
     *
     * @param word a string keyword that need to find
     */
    public void find(String word) {
        ArrayList<Task> tasks = taskList.find(word);

        if (tasks.isEmpty()) {
            print("No tasks found for word: " + word);
            return;
        }
        String listOfFoundedTask = "Here are the matching tasks in your list:\n"
                        + IntStream.range(0, tasks.size())
                        .mapToObj(i -> (i + 1) + ". " + tasks.get(i))
                        .collect(Collectors.joining("\n", "", "\n"));

        System.out.println(LINE);
        System.out.print(listOfFoundedTask);
        System.out.println(LINE);
    }


    /**
     * Loads tasks from storage into the task list.
     *
     * @throws PhucException if loading tasks fails
     */
    public void load() throws PhucException {
        try {
            taskList.clear();
            taskList.addAll(storage.load().getAllTasks());
            count = taskList.size();
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
            storage.save(taskList);
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
        return count + 1;
    }

    /**
     * Prints a message with formatted borders.
     *
     * @param input the message to print
     */
    public void print(String input) {
        System.out.println(LINE);
        System.out.println(input);
        System.out.println(LINE);
    }

    /**
     * Displays the welcome message.
     */
    public void sayHello() {
        print(" Hello! I'm Phuc \uD83D\uDE03\uD83D\uDD90\uFE0F\n"
                + " What can I do for you?");
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
        String listOfTheTask =
                "Here are the tasks in your list:\n"
                        + IntStream.range(0, count)
                                .mapToObj(i -> (i + 1) + ". " + taskList.get(i))
                                .collect(Collectors.joining("\n", "", "\n"));

        System.out.println(LINE);
        System.out.print(listOfTheTask);
        System.out.println(LINE);
    }

    /**
     * Marks a task in the given index as done.
     *
     * @param num the task number to mark (1-based index)
     */
    public void mark(String num) {
        int id = Integer.parseInt(num) - 1;
        taskList.get(id).setDone();
        print("Nice! I've marked this task as done:\n" + taskList.get(id));
    }

    /**
     * Marks a task in the given index as not done.
     *
     * @param num the task number to unmark (1-based index)
     */
    public void unMark(String num) {
        int id = Integer.parseInt(num) - 1;
        taskList.get(id).setNotDone();
        print("OK, I've marked this task as not done yet:\n" + taskList.get(id));
    }

    /**
     * Generates a notification message about the number of tasks.
     *
     * @return a string indicating the current task count
     */
    public String notiNumOfTasks() {
        return "Now you have " + (count + 1) + " tasks in the list.";
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
        taskList.add(new ToDoTask(newtask));
        String temp = notiAddTasks() + taskList.get(count) + "\n" + this.notiNumOfTasks();
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
        taskList.add(new DeadlineTask(newtask, deadline));
        String temp = notiAddTasks() + taskList.get(count) + "\n" + this.notiNumOfTasks();
        print(temp);
        count++;
    }

    /**
     * Adds a new event task to the list.
     * Displays to the user that the task have been added.
     *
     * @param newTask the description of the event task
     * @param startDate the event start date and time
     * @param endDate the event end date and time
     */
    public void event(String newTask, LocalDateTime startDate, LocalDateTime endDate) {
        taskList.add(new EventTask(newTask, startDate, endDate));
        String temp = notiAddTasks() + taskList.get(count) + "\n" + this.notiNumOfTasks();
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
        count -= 2;
        String temp = "Noted. I've removed this task:\n"
                + taskList.get(Integer.parseInt(num) - 1)
                + "\n"
                + this.notiNumOfTasks();
        taskList.remove(Integer.parseInt(num) - 1);
        print(temp);
        count++;
    }

    /**
     * Displays an error message with formatted borders.
     *
     * @param error the error message to display
     */
    public static void showError(String error) {
        System.out.println(LINE);
        System.out.println(error);
        System.out.println(LINE);
    }
}
