
package rafayel.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import rafayel.RafayelException;
import rafayel.storage.Storage;
import rafayel.task.Task;
import rafayel.task.TaskList;


/**
 * An abstract class that represents an executable user command.
 */
public abstract class Command {

    protected String output = "Sorry! An error occurred!";

    private final Parser.CommandType commandType;

    public Command(Parser.CommandType commandType) {
        this.commandType = commandType;
    }

    public Parser.CommandType getCommandType() {
        return commandType;
    }

    /**
     * Executing the function of each command.
     *
     * @param tasks ArrayList of tasks that will be executed on.
     * @param storage stores the list after the function is executed.
     * @return a string from the function.
     * @throws RafayelException if any errors are encountered during execution.
     */
    public String execute(TaskList tasks, Storage storage) throws RafayelException {
        return "";
    }

    /**
     * Parses a date string into a LocalDateTime object with three supported formats.
     *
     * @param input input of the date string to parse.
     * @return the parsed LocalDateTime object, null if no format matches.
     */
    public static LocalDateTime handleReadDate(String input) throws RafayelException {
        // check if valid format
        DateTimeFormatter[] differentTimeFormatters = new DateTimeFormatter[] {
                DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"), DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"),
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm") };

        for (DateTimeFormatter formatter : differentTimeFormatters) {
            try {
                return LocalDateTime.parse(input, formatter);
            } catch (Exception ignore) {
                // ignore
            }
        }

        throw new RafayelException("Please use one of: MMM d yyyy HH:mm | yyyy/MM/dd HH:mm | dd-MM-yyyy HH:mm");
    }

    /**
     * Prints the confirmation message when a new task is added to the list
     *
     * @param newTask the task that was added.
     * @param counter the current number of tasks in the ArrayList.
     */
    protected static String getNewTaskString(Task newTask, int counter) {
        return String.format(
                "Got it. I've added this task:\n %s\nNow you have %d tasks in the list.",
                newTask.toString(), counter);
    }
}
