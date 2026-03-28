package sanbot.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import sanbot.task.DeadlineTask;
import sanbot.task.Task;
import sanbot.ui.Ui;

/**
 * Command to add deadline tasks with due dates to the task list.
 */
public class AddDeadlineTaskCommand implements Command{

    /**
     * Represents a command that takes an input and creates a deadline task.
     * Format: "deadline [description] /by [dd-MM-yyyy HH:mm]"
     *
     * @param input the user input string containing task description and deadline.
     * @param taskList the list of tasks to add the new deadline task to.
     * @return the message to be displayed to user upon execution of command.
     * @throws ArrayIndexOutOfBoundsException if the input format is invalid or missing required parts.
     */
    public String execute(String input, ArrayList<Task> taskList) {
        try {
            String[] details = input.split("/by");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

            LocalDateTime deadline = LocalDateTime.parse(details[1].substring(1), formatter);
            Task deadlineTask = new DeadlineTask(details[0].substring(9), deadline);
            taskList.add(deadlineTask);

            return Ui.deadline(deadlineTask, taskList.size());
        } catch (ArrayIndexOutOfBoundsException e) {
            return Ui.deadlineIndexError();
        } catch (Exception e) {
            return Ui.deadlineError();
        }
    }
}