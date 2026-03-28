package sanbot.tasklist;

import java.util.ArrayList;

import sanbot.command.*;
import sanbot.storage.Storage;
import sanbot.task.Task;

/**
 * Static utility class for managing the global task list and delegating commands.
 */
public class TaskList {
    /** The global list of all tasks */
    private static ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Initializes the task list with loaded tasks.
     *
     * @param loadedList the list of tasks to set as the current task list
     */
    public static void initializeTaskList(ArrayList<Task> loadedList) {

        taskList = loadedList;
    }

    /**
     * Returns the complete task list.
     *
     * @return the current list of all tasks
     */
    public static ArrayList<Task> getAllTasks() {

        return new ArrayList<>(taskList);
    }

    /**
     * Prints all tasks with their index numbers.
     *
     * @return the formatted string that lists all the tasks
     */
    public static String printListItems() {
        StringBuilder taskListPrint = new StringBuilder();
        if (taskList.isEmpty()) {
            return "Your task list is empty.\nAdd away!";
        }
        int index = 1;
        for (Task item : taskList) {
            taskListPrint.append(index).append(": ").append(item).append("\n");
            index++;
        }
        return taskListPrint.toString();
    }

    /**
     * Delegates mark done command to MarkDoneCommand.
     *
     * @param input the user input for marking task as done.
     * @return the string that is the result of markDone command.
     */
    public static String markDone(String input) {
        MarkDoneCommand command = new MarkDoneCommand();
        return command.execute(input, taskList);
    }

    /**
     * Delegates mark not done command to MarkNotDoneCommand.
     *
     * @param input the user input for marking task as not done.
     * @return the string that is the result of markNotDone command.
     */
    public static String markNotDone(String input) {
        MarkNotDoneCommand command = new MarkNotDoneCommand();
        return command.execute(input, taskList);
    }

    /**
     * Delegates delete command to DeleteTaskCommand.
     *
     * @param input the user input for deleting a task.
     * @return the string that is the result of deleteTask command.
     */
    public static String deleteTask(String input) {
        DeleteTaskCommand command = new DeleteTaskCommand();
        return command.execute(input, taskList);
    }

    /**
     * Delegates todo creation to AddTodoTaskCommand.
     *
     * @param input the user input for creating a todo task.
     * @return the string that is the result of adding a todo task.
     */
    public static String todoHandler(String input) {
        AddTodoTaskCommand command = new AddTodoTaskCommand();
        return command.execute(input, taskList);
    }

    /**
     * Delegates deadline task creation to AddDeadlineTaskCommand.
     *
     * @param input the user input for creating a deadline task.
     * @return the string that is the result of adding a deadline task.
     */
    public static String deadlineHandler(String input) {
        AddDeadlineTaskCommand command = new AddDeadlineTaskCommand();
        return command.execute(input, taskList);
    }

    /**
     * Delegates event task creation to AddEventTaskCommand.
     *
     * @param input the user input for creating an event task.
     * @return the string that is the result of adding an event task.
     */
    public static String eventHandler(String input) {
        AddEventTaskCommand command = new AddEventTaskCommand();
        return command.execute(input, taskList);
    }

    /**
     * Delegates finding tasks to FindTasksCommand
     *
     * @param input the user input to find related tasks
     * @return the string that is the task(s) to be found, if any.
     */
    public static String findTasks(String input) {
        FindTasksCommand command = new FindTasksCommand();
        return command.execute(input, taskList);
    }

    /**
     * Save tasks and close the chatbot window
     *
     * @param input the user input "bye" that triggers exitApp command
     * @return the string that is the result of exitAppCommand.
     */
    public static String exitApp(String input) {
        ExitAppCommand command = new ExitAppCommand();
        return command.execute(input, taskList);
    }
}
