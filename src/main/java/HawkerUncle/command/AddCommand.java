package HawkerUncle.command;

import HawkerUncle.storage.Storage;
import HawkerUncle.ui.Ui;
import HawkerUncle.task.TaskList;
import HawkerUncle.task.Task;
import HawkerUncle.task.ToDo;
import HawkerUncle.task.Deadline;
import HawkerUncle.task.Event;
import HawkerUncle.parser.Parser;

import java.util.ArrayList;
import java.time.LocalDateTime;

/**
 * Represents a command that adds a task to the task list.
 * This command parses the data and creates the corresponding task type and adds it to the task list.
 */
public class AddCommand implements Command {
    private final ArrayList<String> parsedData;

    /**
     * Initializes an AddCommand with the parsedData
     * @param parsedData The data parsed from user input.
     */
    public AddCommand(ArrayList<String> parsedData) {
        this.parsedData = parsedData;
    }

    /**
     * Executes the command to add a task to the task list based on the parsed data.
     * @param tasks The list of tasks where the new task will be added
     * @param ui The user interface for showing messages
     * @param storage The storage object used to save the updated task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = createTaskFromParsedData();
            tasks.add(task);
            storage.save(tasks);
            return Ui.showTaskAdded(task, tasks.size());
        } catch (Exception e) {
            return Ui.showError("Unable to add task");
        }
    }
    /**
     * Creates a Task object based on the parsed data.
     *
     * @return The Task object to be added.
     */

    private Task createTaskFromParsedData() {
        String taskType = parsedData.get(0).toLowerCase();
        String description;
        switch (taskType) {
        case "todo":
            description = parsedData.get(1);
            return new ToDo(description, false);

        case "deadline":
            description = parsedData.get(1);
            LocalDateTime by = Parser.parseDateTime(parsedData.get(2));
            return new Deadline(description, by, false);

        case "event":
            description = parsedData.get(1);
            LocalDateTime from = Parser.parseDateTime(parsedData.get(2));
            LocalDateTime to = Parser.parseDateTime(parsedData.get(3));
            return new Event(description, from, to, false);

        default:
            assert false : "Unknown task type: " + taskType;
            return null;
        }
    }

    /**
     * Checks if this command is an exit command.
     * @return false, since this command does not exit the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
