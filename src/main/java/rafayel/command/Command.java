
package rafayel.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import rafayel.Rafayel;
import rafayel.RafayelException;
import rafayel.storage.Storage;
import rafayel.task.Task;
import rafayel.task.TaskList;

/**
 * Abstract base class that represents an executable user command.
 * 
 * All concrete command classes extend this class and 
 * implement execute(TaskList, Storage) method,
 * to perform their respective operations on the task list and storage.
 */
public abstract class Command {

    /** Type of the command i.e. DEADLINE / EVENT */
    private final CommandHandle.CommandType commandType;

    /**
     * Constructs a Command with the given command type.
     *
     * @param commandType the type of this command.
     */
    public Command(CommandHandle.CommandType commandType) {
        this.commandType = commandType;
    }

    public CommandHandle.CommandType getCommand() {
        return commandType;
    }

    /**
     * Executes the function of each command.
     * Should be overridden by subclasses to implement the actual functionality.
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
     * @throws RafayelException if no format matches.
     */
    public static LocalDateTime handleReadDate(String input) throws RafayelException {
        // check if valid format
        DateTimeFormatter[] differentTimeFormatters = new DateTimeFormatter[] {
                DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"),
                DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"),
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm") };

        for (DateTimeFormatter formatter : differentTimeFormatters) {
            try {
                return LocalDateTime.parse(input, formatter);
            } catch (Exception ignore) {
                // ignore
            }
        }

        throw new RafayelException(Rafayel.DATE_FORMAT_ERROR);
    }


    /**
     * Gets the confirmation message when a new task is added to the list
     *
     * @param newTask the task that was added.
     * @param counter the current number of tasks in the ArrayList.
     * @return confirmation message.
     */
    protected static String getNewTaskString(Task newTask, int counter) {
        return String.format(
                "Very well. I've graciously added this new stroke to our canvas:\n\n "
                        + "『 %s 』\n\nNow our collection holds %d priceless pieces. "
                        + "Try not to clutter my masterpiece, alright?",
                newTask.toString(), counter);
    }

    /**
    * Formats a list of tasks with a header using varargs for flexible formatting.
    * @param header the header text for the list
    * @param tasks the list of tasks to format
    * @return formatted string with tasks numbered sequentially
    */
    public String formatTaskList(String header, Task... tasks) {
        if (tasks == null || tasks.length == 0) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        if (header != null && !header.trim().isEmpty()) {
            result.append(header).append("\n");
        }

        for (int i = 0; i < tasks.length; i++) {
            result.append(i + 1).append(". ").append(tasks[i].toString()).append("\n");
        }

        return result.toString();
    }

    /**
     * Overloaded version that accepts ArrayList for convenience
     */
    public String formatTaskList(String header, ArrayList<Task> tasks) {
        if (tasks == null || tasks.isEmpty()) {
            return "";
        }
        return formatTaskList(header, tasks.toArray(new Task[0]));
    }
}
