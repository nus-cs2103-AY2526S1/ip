package timmy;

import java.util.stream.IntStream;

import exceptions.TimmyTaskListOutOfBoundsException;

/**
 * Collection of methods used to print Timmy's output to the user interface.
 */
public class Ui {
    public Ui() {}

    /**
     * Prints the welcome message to be shown when Timmy is run.
     */
    public String getWelcomeMessage() {
        return "Hello! I'm Timmy.\n" + "What can I do for you?";
    }

    /**
     * Prints the goodbye message to be shown when Timmy exits gracefully
     * from a 'bye' command.
     */
    public String getByeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints the current list of tasks stored in memory.
     *
     * @param list the current list of tasks.
     */
    public String getList(TaskList list) {
        return IntStream.range(0, list.size())
                .mapToObj(i -> {
                    try {
                        return (i + 1) + ". " + list.getTask(i).toCompleteString();
                    } catch (TimmyTaskListOutOfBoundsException e) {
                        return "";
                    }
                })
                .reduce((a, b) -> a + "\n" + b)
                .orElse("You have no tasks at the moment.");
    }

    public String getFilteredList(TaskList list) {
        return IntStream.range(0, list.size())
                .mapToObj(i -> {
                    try {
                        return (i + 1) + ". " + list.getTask(i).toCompleteString();
                    } catch (TimmyTaskListOutOfBoundsException e) {
                        return "";
                    }
                })
                .reduce((a, b) -> a + "\n" + b)
                .orElse("No matching tasks found.");
    }

    /**
     * Prints the message to be shown upon successfully adding a task.
     *
     * @param task the task that has been added.
     * @param size the number of tasks in the list after successful addition.
     */
    public String getAddMessage(Task task, Integer size) {
        return "Got it. I've added this task: \n"
                + task.toCompleteString() + "\n"
                + "Now you have " + size + " tasks in the list.";
    }

    /**
     * Prints the message to be shown upon successfully deleting a task.
     *
     * @param task the task that has been deleted.
     * @param size the number of tasks in the list after successful deletion.
     */
    public String getDeleteMessage(Task task, Integer size) {
        return "Noted. I've removed this task: \n"
                + task.toCompleteString() + "\n"
                + "Now you have " + size + " tasks in the list.";
    }

    /**
     * Prints the message to be shown upon successfully marking a task for completion.
     *
     * @param task the task that has been marked for completion.
     */
    public String getMarkMessage(Task task) {
        return "Nice! I've marked this task as done:\n"
                + task.toCompleteString();
    }

    /**
     * Prints the message to be shown upon successfully marking a task as incomplete.
     *
     * @param task the task that has been marked as incomplete.
     */
    public String getUnmarkMessage(Task task) {
        return "Ok. I've marked this task as not done yet:\n"
                + task.toCompleteString();
    }

    public String getClearMessage() {
        return "Storage Cleared.";
    }

    public String getArchiveMessage(String archivePath) {
        return "Storage Archived to " + archivePath + ".";
    }

    public String getUnknownCommandMessage() {
        return "Sorry, I do not understand that command.";
    }

    public String getInvalidParameterMessage() {
        return "Error: Invalid Parameters were provided.";
    }

    public String getInvalidIndexMessage() {
        return "Error: Invalid Index.";
    }

    public String getNoArgumentsMessage() {
        return "Error: No arguments were provided.";
    }

    public String getInvalidDateFormatMessage() {
        return "Error: Invalid Date Format.";
    }

    public String getFilerErrorMessage() {
        return "Error: An issue was encountered saving the task list.";
    }
}
