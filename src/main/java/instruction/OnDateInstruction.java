package instruction;

import java.time.LocalDate;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Represents an instruction to display tasks occurring on a specific date.
 * This instruction filters and returns tasks (deadlines and events) that are
 * relevant to the specified date.
 */
public class OnDateInstruction extends Instruction {
    private LocalDate date;

    /**
     * Constructs an OnDateInstruction with the specified date for task filtering.
     *
     * @param date the date to filter tasks by
     */
    public OnDateInstruction(LocalDate date) {
        this.date = date;
    }

    /**
     * Executes the on-date instruction by returning tasks that occur on the specified date.
     * Includes deadlines due on that date and events spanning that date.
     *
     * @param tasks   the task list to filter by date
     * @param ui      the user interface for generating the filtered tasks message
     * @param storage the storage system (unused in this instruction)
     * @return formatted string of tasks on the specified date
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.printTasksOnDate(tasks.getAllTasks(), date);
    }
}
