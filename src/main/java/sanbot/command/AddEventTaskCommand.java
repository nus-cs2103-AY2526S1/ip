package sanbot.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import sanbot.task.EventTask;
import sanbot.task.Task;
import sanbot.ui.Ui;

/**
 * Represents a command to add event tasks with start and end times to the task list.
 */
public class AddEventTaskCommand implements Command{

    /**
     * Parses input and creates an event task.
     * Format: "event [description] /from [dd-MM-yyyy HH:mm] /to [dd-MM-yyyy HH:mm]"
     *
     * @param input the user input string containing task description, start time, and end time.
     * @param taskList the list of tasks to add the new event task to.
     * @return the message to be displayed to user upon execution of command.
     * @throws ArrayIndexOutOfBoundsException if the input format is invalid or missing required parts.
     */
    public String execute(String input, ArrayList<Task> taskList) {
        try {
            String[] details = input.split("/from");
            String[] splitDetails = details[1].split("/to");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            LocalDateTime from = LocalDateTime.parse(splitDetails[0].substring(1).trim(), formatter);
            LocalDateTime to = LocalDateTime.parse(splitDetails[1].substring(1).trim(), formatter);

            Task eventTask = new EventTask(details[0].substring(6), from, to);
            taskList.add(eventTask);

            return Ui.event(eventTask, taskList.size());
        } catch (ArrayIndexOutOfBoundsException e) {
            return Ui.eventIndexError();
        } catch (Exception e) {
            return Ui.eventError();
        }
    }
}