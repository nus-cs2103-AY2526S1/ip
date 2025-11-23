package Mithrandir.ui;

import Mithrandir.task.Deadline;
import Mithrandir.task.Event;
import Mithrandir.task.Todo;

/**
 * Handles all console input and output operations for the Mithrandir application.
 * This class is responsible for formatting and displaying messages to the user,
 * including task-related notifications, greetings, and error messages.
 */
public class IOComponent {
    /** The separator line used to format console output. */
    private static final String separator = new String(new char[50]).replace('\0', '-');

    /**
     * Prints a message to the console surrounded by separator lines.
     * This is the main output method used by all other methods in this class.
     *
     * @param message the message to be printed to the console
     * @return the same message that was printed, for method chaining
     */
    public static String print(String message) {
        System.out.println(separator);
        System.out.println(message);
        System.out.println(separator);
        return message;
    }

    /**
     * Returns a themed greeting message from Gandalf.
     *
     * @return the formatted greeting message
     */
    public String greet() {
        return print("A wizard is never late, nor is he early. "
                + "He arrives precisely when he means to. "
                + "Greetings from Gandalf.");
    }

    /**
     * Returns a themed farewell message from Gandalf.
     *
     * @return the formatted farewell message
     */
    public String exit() {
        return print("Farewell. My work is now finished.");
    }

    /**
     * Generates a confirmation message for a newly added Todo task.
     *
     * @param todo the Todo task that was added to the list
     * @return a formatted message confirming the addition of the Todo task
     */
    public String printAddToList(Todo todo) {
        return print("Added Todo: " + todo + "\n" +
                "A simple task, yet no less vital - it has been woven into your journey.");
    }

    /**
     * Generates a confirmation message for a newly added Event task.
     *
     * @param event the Event task that was added to the list
     * @return a formatted message confirming the addition of the Event task
     */
    public String printAddToList(Event event) {
        return print("Added Event: " + event + "\n" +
                "Aye, lad - the event is written, and when its time comes, you shall be ready.");
    }

    /**
     * Generates a confirmation message for a newly added Deadline task.
     *
     * @param deadline the Deadline task that was added to the list
     * @return a formatted message confirming the addition of the Deadline task
     */
    public String printAddToList(Deadline deadline) {
        return print("Added Deadline: " + deadline + "\n" +
                "The hour is set, and the task must be done ere its appointed time.");
    }

    /**
     * Generates a success message for marking a task as done.
     *
     * @param input a string representation of the task that was marked as done
     * @return a formatted success message
     */
    public String printMarkDoneSuccessful(String input) {
        return print("Well done! The following task is deemed complete:\n" + input);
    }

    /**
     * Generates a success message for marking a task as not done.
     *
     * @param input a string representation of the task that was marked as not done
     * @return a formatted success message with a themed response
     */
    public String printMarkUndoneSuccessful(String input) {
        return print("Alas! It's the job that's never started as takes longest to finish. This task is " +
                "marked undone:\n" + input);
    }

    /**
     * Generates a confirmation message for removing a task.
     *
     * @param input a string representation of the task that was removed
     * @return a formatted message confirming the removal
     */
    public String printRemoved(String input) {
        return print("Removed task: " + input + "\n" +
                "The deed is struck from the scrolls of your labors, passing now into shadow and memory");
    }

    /**
     * Formats and returns a message displaying the results of a task search.
     *
     * @param input the string representation of the found tasks
     * @return a themed message containing the search results
     */
    public String printFoundTasks(String input) {
        return print("Ah... so you seek among your tasks, do you?\n" +
                "Very well. I shall unveil what lies hidden in your list...\n" + input);
    }

    /**
     * Returns a themed message indicating that no tasks were found matching the search criteria.
     *
     * @return a formatted message indicating no tasks were found
     */
    public String printNotFoundTasks() {
        return print("""
                Ah... so you seek among your tasks, do you?
                Yet I find no record of such a thing in your keeping...
                Be watchful, for only the tasks you have written may answer your call.""");
    }

    /**
     * Returns a themed success message for archiving a task.
     *
     * @return a formatted message for archiving a task
     */
    public String printArchived(String input) {
        return print("Archived task: " + input +
                "\nthe task is now sealed away in the vaults of memory, never again to trouble your road ahead.");
    }


    /**
     * Formats and returns a themed message displaying the task list.
     *
     * @param input the string representation of the task list
     * @return a formatted message containing the task list with themed text
     */
    public String printList(String input) {
        return print(input + "\n" +
                "Behold! Here are the labors you have set upon, laid plain before your eyes as stones upon the path.");
    }
}
