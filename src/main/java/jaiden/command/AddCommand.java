package jaiden.command;

import java.time.LocalDate;

import jaiden.exception.JaidenException;
import jaiden.storage.Storage;
import jaiden.task.Deadline;
import jaiden.task.Event;
import jaiden.task.Task;
import jaiden.task.TaskList;
import jaiden.task.Todo;

/**
 * Class for add command.
 */
public class AddCommand extends Command {
    /**
     * Constructor for add command.
     *
     * @param inputs User input.
     */
    public AddCommand(String[] inputs) {
        super(inputs);
        this.commandType = CommandType.ADDCOMMAND;
    }

    /**
     * @inheritDoc
     */
    public void execute(TaskList taskList, Storage storage) throws JaidenException {
        String description = inputs[1];
        assert !description.isBlank();

        Task task;
        switch (inputs[0]) {
        case "todo":
            task = new Todo(description);
            break;
        case "deadline":
            LocalDate by = LocalDate.parse(inputs[3]);
            task = new Deadline(description, by);
            break;
        case "event":
            LocalDate from = LocalDate.parse(inputs[3]);
            LocalDate to = LocalDate.parse(inputs[5]);
            task = new Event(description, from, to);
            break;
        default:
            task = new Task(description);
            break;
        }

        this.string = taskList.add(task);
        assert this.string != null;

        storage.save(taskList);
    }
}
