package mel.commands;

import mel.apps.Parser;
import mel.apps.Storage;
import mel.apps.Ui;

import mel.exceptions.MelException;

import mel.tasks.Deadline;
import mel.tasks.Event;
import mel.tasks.Task;
import mel.tasks.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents the update command
 */
public class UpdateCommand extends Command {
    private String taskId;
    private String updateType;
    private String newValue;

    /**
     * Constructor for update command
     *
     * @param argument
     * @throws MelException
     */
    public UpdateCommand(String argument) throws MelException {
        String[] input = argument.split(" ", 3);
        if (input.length < 3) {
            throw new MelException("Insufficient parameters! Please provide taskId, updateType and new input!");

        }
        String taskId = input[0];
        String updateType = input[1];
        String newValue = input[2];

        if (taskId.isEmpty() || updateType.isEmpty() || newValue.isEmpty()) {
            throw new MelException("Please give update parameters!");

        }

        this.taskId = taskId;
        this.updateType = updateType;
        this.newValue = newValue;

    }

    /**
     * Updates the relevant task with the description or date
     *
     * @param tasks
     * @param ui
     * @param storage
     * @throws MelException
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MelException {
        Task task = tasks.get(Parser.handleIndex(taskId));

        switch (updateType) {
            case "description":
                updateDescription(task);
                break;

            case "date":
                updateDate(task);
                break;

            case "start":
                updateStart(task);
                break;

            case "end":
                updateEnd(task);
                break;

            default:
                throw new MelException("Unknown update type!");


        }

        ui.printOut("Task updated successfully: " + task.toString());

    }

    private void updateDescription(Task task) {
        task.setDescription(newValue);

    }

    private void updateDate(Task task) throws MelException {
        try {
            if (task instanceof Deadline) {
                ((Deadline) task).setDate(LocalDate.parse(newValue));

            } else {
                throw new MelException("This task type does not have a date.");

            }
        } catch (DateTimeParseException e) {
            throw new MelException("Incorrect date format!");

        }
    }

    private void updateStart(Task task) throws MelException {
        if (task instanceof Event) {
            ((Event) task).setStart(newValue);

        } else {
            throw new MelException("This task type does not have a start date!");

        }
    }

    private void updateEnd(Task task) throws MelException {
        if (task instanceof Event) {
            ((Event) task).setEnd(newValue);

        } else {
            throw new MelException("This task type does not have a start date!");

        }
    }


}
