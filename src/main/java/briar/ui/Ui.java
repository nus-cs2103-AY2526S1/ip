package briar.ui;

import java.util.Scanner;

import briar.command.AddCommand;
import briar.command.DeleteCommand;
import briar.command.ExitCommand;
import briar.command.FindCommand;
import briar.command.HelpCommand;
import briar.command.ListCommand;
import briar.command.MarkCommand;
import briar.command.UnmarkCommand;
import briar.task.Task;
import briar.task.TaskList;

/**
 * Represents the user interface between the chatbot and the user.
 */
public class Ui {
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays welcome message.
     */
    public String showWelcome() {
        String response = "Nice to meet you! I'm hungry... I mean Briar!"
                + System.lineSeparator() + "What can I do for you?";
        System.out.println(response);
        return response;
    }

    /**
     * Reads next command that user inputs.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays error message.
     */
    public String showError(String errorMessage) {
        System.out.println(errorMessage);
        return errorMessage;
    }

    /**
     * Displays number of tasks in the list.
     */
    public String showTaskNumber(TaskList tasks) {
        String response = "You now have " + tasks.getSize() + " tasks in the list!";
        System.out.println(response);
        return response;
    }

    /**
     * Displays task list.
     */
    public String showTaskList(TaskList tasks) {
        String response = "Here's your task list:" + System.lineSeparator() + tasks.toString();
        System.out.println(response);
        return response;
    }

    /**
     * Displays task that was added.
     * @param task Task to be displayed.
     */
    public String showAddedTask(Task task) {
        String response = "Okie! I've added this task:" + System.lineSeparator() + task;
        System.out.println(response);
        return response;
    }

    /**
     * Displays task that was deleted.
     * @param taskString String representation of task that was deleted.
     */
    public String showDeletedTask(String taskString) {
        String response = "Okie! I've removed this task:" + System.lineSeparator() + taskString;
        System.out.println(response);
        return response;
    }

    /**
     * Displays task that was marked as done.
     * @param taskString String representation of task that was marked.
     */
    public String showMarkedTask(String taskString) {
        String response = "Nice! I've marked this task as done:" + System.lineSeparator() + taskString;
        System.out.println(response);
        return response;
    }

    /**
     * Displays task that was marked as undone.
     * @param taskString String representation of task that was unmarked.
     */
    public String showUnmarkedTask(String taskString) {
        String response = "OK, I've marked this task as not done yet:" + System.lineSeparator() + taskString;
        System.out.println(response);
        return response;
    }

    /**
     * Displays tasks found.
     * @param taskString String representation of tasks found.
     */
    public String showFoundTasks(String taskString) {
        String response = "";
        if (taskString.isEmpty()) {
            response = "Oops! No tasks match the keyword given! >.<";
        } else {
            response = "Okie! Here are the tasks that match in your list:"
                    + System.lineSeparator()
                    + taskString;
        }
        System.out.println(response);
        return response;
    }

    /**
     * Displays a list of all commands the user can use.
     */
    public String showHelp() {
        // ChatGPT was used to learn how to improve the code quality of this code.
        // ChatGPT said to use StringBuilder instead which would be more efficient.
        StringBuilder response = new StringBuilder();

        response.append("Here's the list of all commands:").append(System.lineSeparator());
        response.append(AddCommand.getCommandInformation()).append(System.lineSeparator());
        response.append(DeleteCommand.getCommandInformation()).append(System.lineSeparator());
        response.append(ExitCommand.getCommandInformation()).append(System.lineSeparator());
        response.append(FindCommand.getCommandInformation()).append(System.lineSeparator());
        response.append(HelpCommand.getCommandInformation()).append(System.lineSeparator());
        response.append(ListCommand.getCommandInformation()).append(System.lineSeparator());
        response.append(MarkCommand.getCommandInformation()).append(System.lineSeparator());
        response.append(UnmarkCommand.getCommandInformation()).append(System.lineSeparator());

        System.out.println(response);
        return response.toString();
    }

    /**
     * Displays exit message.
     */
    public String showExit() {
        String response = "Bye. Hope to see you again soon!";
        System.out.println(response);
        return response;
    }
}
