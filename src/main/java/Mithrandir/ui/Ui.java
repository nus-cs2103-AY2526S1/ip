package Mithrandir.ui;

import java.util.ArrayList;

import Mithrandir.MithrandirExceptions.MithrandirException;
import Mithrandir.TaskList;
import Mithrandir.task.Deadline;
import Mithrandir.task.Event;
import Mithrandir.task.Task;
import Mithrandir.task.Todo;

/**
 * Handles all user interface interactions for the Mithrandir application.
 * This class acts as an intermediary between the user interface and the application logic,
 * delegating to IOComponent for actual input/output operations.
 */
public class Ui {
    private final IOComponent IOComponent = new IOComponent();

    /**
     * Delegates to the IOComponent to print a greeting message from Gandalf.
     *
     * @return The greeting message string
     */
    public String greet() {
        return this.IOComponent.greet();
    }

    /**
     * Adds a Todo task to the list and prints a confirmation message.
     *
     * @param todo the Todo task to be added
     * @return The confirmation message string
     */
    public String addTodoToList(Todo todo) {
        return this.IOComponent.printAddToList(todo);
    }

    /**
     * Adds an Event task to the list and prints a confirmation message.
     * Throws an exception if the command format is incorrect.
     *
     * @param event the Event task to be added
     * @return The confirmation message string
     * @throws MithrandirException if the command format is invalid
     */
    public String addEventToList(Event event) throws MithrandirException {
        try {
            return this.IOComponent.printAddToList(event);
        } catch (IndexOutOfBoundsException e) {
            return "Event command need 4 parts: task description, " +
                    "'/from', Start time of" +
                    " task and end time of task. You are missing on something. Check your command!";
        }
    }

    /**
     * Adds a Deadline task to the list and prints a confirmation message.
     * Throws an exception if the command format is incorrect.
     *
     * @param deadline the Deadline task to be added
     * @return The confirmation message string
     * @throws MithrandirException if the command format is invalid
     */
    public String addDeadlineToList(Deadline deadline) throws MithrandirException {
        try {
            return this.IOComponent.printAddToList(deadline);
        } catch (IndexOutOfBoundsException e) {
            return "Deadline command need 3 parts: task description, '/by' and deadline, " +
                    "you are missing on something. Check your command!";
        }
    }

    /**
     * Delegates to the IOComponent to print a farewell message.
     *
     * @return The farewell message string
     */
    public String exit() {
        return this.IOComponent.exit();
    }

    /**
     * Marks a task as done and prints a confirmation message.
     *
     * @param task the task to be marked as done
     * @return The confirmation message string
     */
    public String mark(Task task) {
        return this.IOComponent.printMarkDoneSuccessful(task.toString());
    }

    /**
     * Marks a task as undone and prints a confirmation message.
     *
     * @param task the task to be marked as undone
     * @return The confirmation message string
     */
    public String unmark(Task task) {
        return this.IOComponent.printMarkUndoneSuccessful(task.toString());
    }

    /**
     * Deletes a task from the list and prints a confirmation message.
     *
     * @param task the task to be deleted
     * @return The confirmation message string
     * @throws IndexOutOfBoundsException if the task index is invalid
     */
    public String delete(Task task) throws IndexOutOfBoundsException {
        return this.IOComponent.printRemoved(task.toString());
    }

    public String archive(Task task) {
        return this.IOComponent.printArchived(task.toString());
    }

    /**
     * Prints a given input message using the IOComponent.
     *
     * @param input the message to be printed
     * @return The input message string
     */
    public String print(String input) {
        Mithrandir.ui.IOComponent.print(input);
        return input;
    }

    /**
     * Prints the found tasks to the user interface.
     * This method delegates to the IOComponent to display the search results
     * in a formatted manner. If no tasks are found, it displays a not found message.
     *
     * @param foundTasks the TaskList containing the tasks that match the search criteria
     * @return The formatted search results or a not found message
     */
    public String printFoundTasks(TaskList foundTasks) {
        String str = foundTasks.toString();
        if (str.isEmpty()) {
            return this.IOComponent.printNotFoundTasks();
        } else {
            return this.IOComponent.printFoundTasks(str);
        }
    }

    public String printList(TaskList taskList) {
        return this.IOComponent.printList(taskList.toString());
    }
}
