package guibot.command;

import java.io.IOException;

import guibot.Parser;
import guibot.Storage;
import guibot.TaskList;
import guibot.exception.GuibotException;
import guibot.exception.WrongInputFormatException;
import guibot.task.Deadline;
import guibot.task.Event;
import guibot.task.Task;
import guibot.task.TaskType;
import guibot.task.Todo;

/**
 * Command to add a task to the list.
 */
public class AddCommand extends Command {
    private Task task;
    private String output = "Got it, I've added this task:\n%s\nNow you have %d tasks in the list.";

    /**
     * Creates an AddCommand.
     *
     * @param task Task to be added.
     */
    private AddCommand(Task task) {
        assert task != null : "Cannot make an AddCommand with a null task";
        this.task = task;
    }

    /**
     * Static factory method to construct an AddCommand from an input string.
     *
     * @param input The input string to construct the AddCommand from.
     * @param taskType The type of task to be added.
     * @return An AddCommand constructed from the string.
     * @throws WrongInputFormatException If the string is not in the correct format.
     */
    public static AddCommand of(String input, TaskType taskType) throws GuibotException {
        try {
            String[] details = Parser.getDetails(input, taskType.getSplitters());
            Task task = switch (taskType) {
            case TODO -> Todo.of(details[1]);
            case DEADLINE -> Deadline.of(details[1], details[2]);
            case EVENT -> Event.of(details[1], details[2], details[3]);
            };
            return new AddCommand(task);
        } catch (WrongInputFormatException e) {
            throw new WrongInputFormatException(taskType.getExpectedInputFormat());
        }
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws IOException {
        assert tasks != null : "Cannot add to a null tasklist";
        tasks.add(task);
        assert storage != null : "Cannot save to a null storage";
        storage.saveTasks(tasks);
        return String.format(output, task.toString(), tasks.size());
    }
}
