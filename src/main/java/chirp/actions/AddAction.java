package chirp.actions;

import chirp.exceptions.ChirpException;
import chirp.io.Attribute;
import chirp.io.Parser;
import chirp.io.Ui;
import chirp.tasks.Deadline;
import chirp.tasks.Event;
import chirp.tasks.Task;
import chirp.tasks.TaskList;
import chirp.tasks.Todo;

/**
 * Represents action of adding task to list
 */
public class AddAction extends Action {
    private Task task;

    /**
     * Creates AddAction from user input
     *
     * @param command Command used to determine what type of task to be added
     * @param input   Exact input where attributes of the task can be extracted
     * @throws ChirpException
     */
    public AddAction(Command command, String input) throws ChirpException {
        switch (command) {
        case TODO -> {
            // Todo task
            String description = Parser.extractAttribute(input, Command.TODO.getKeyword());
            task = new Todo(description);
        }
        case DEADLINE -> {
            // Deadline task
            String description = Parser.extractAttribute(input, Command.DEADLINE.getKeyword());
            String endTime = Parser.extractAttribute(input, Attribute.BY.getTag());
            task = new Deadline(description, endTime);
        }
        case EVENT -> {
            // Event task
            String description = Parser.extractAttribute(input, Command.EVENT.getKeyword());
            String startTime = Parser.extractAttribute(input, Attribute.FROM.getTag());
            String endTime = Parser.extractAttribute(input, Attribute.TO.getTag());
            task = new Event(description, startTime, endTime);
        }
        default -> {
            // This should not happen and is a code bug
            assert (false) : "Non task command passed to AddAction";
        }
        }
    }

    /**
     * Adds task in the action to the task list
     *
     * @param taskList List of tasks
     * @param ui       UI interface
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        taskList.addTask(task);
        ui.printMessage("Added task: " + task, ui.taskListCount(taskList));
    }
}
